package io.fourfinanceit.service;

import com.google.inject.Inject;
import io.fourfinanceit.dao.LoanRequestDao;
import io.fourfinanceit.dao.UserDAO;
import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.LoanRequest;
import io.fourfinanceit.model.User;
import io.fourfinanceit.requestparam.ExtendLoanRequestParams;
import io.fourfinanceit.requestparam.LoanRequestParam;
import io.fourfinanceit.requestparam.PayLoanRequestParams;
import io.fourfinanceit.requestparam.UserInfoRequestParams;
import org.joda.time.DateTime;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LoanServiceImpl implements LoanService {

    private UserDAO userDao;
    private LoanRequestDao loanRequestDao;
    private DateTime midnight;
    private DateTime morning;

    @Inject
    public LoanServiceImpl(UserDAO userDAO, LoanRequestDao loanRequestDao) {
        this.userDao = userDAO;
        this.loanRequestDao = loanRequestDao;
        this.midnight = new DateTime().withTimeAtStartOfDay();
        this.morning = new DateTime().withTimeAtStartOfDay().plusHours(4);
    }

    public CompletableFuture<Response> initialise(String name) {
        User user1 = new User(name, 20l);
        return CompletableFuture
                .supplyAsync(() -> userDao.create(user1))
                .thenApply(user -> Response.ok().entity(userDao.getByName(name)).build());
    }

    public CompletableFuture<Response> getByName(UserInfoRequestParams requestParams) {
        return CompletableFuture
                .supplyAsync(() -> userDao.getByName(requestParams.getUserName()))
                .thenApply(user -> Response.ok().entity(user).build());
    }

    public CompletableFuture<Response> applyForLoan(final LoanRequestParam requestParams) {
        LoanRequest loanRequest = new LoanRequest(requestParams.getLoanAmount(), requestParams.getRemoteAddr());
        return CompletableFuture.supplyAsync(() -> loanRequestDao.create(loanRequest))
                .thenApply(loan -> userDao.getByName(requestParams.getUserName()))
                .thenApply(user -> checkRisks(requestParams, user))
                .thenApply(user -> approvedLoan(requestParams, user))
                .thenApply(user -> userDao.create(user))
                .thenApply(user -> Response.ok().entity(user).build());
    }

    public CompletableFuture<Response> extendLoan(ExtendLoanRequestParams requestParams) {
        return CompletableFuture
                .supplyAsync(() -> userDao.getByName(requestParams.getUserName()))
                .thenApply(user -> extendLoan(user, requestParams))
                .thenApply(userDao::create)
                .thenApply(user -> Response.ok().entity(user).build());
    }

    public CompletableFuture<Response> payLoan(PayLoanRequestParams requestParams) {

        return CompletableFuture
                .supplyAsync(() -> userDao.getByName(requestParams.getUserName()))
                .thenApply(user -> payLoan(user, requestParams))
                .thenApply(userDao::create)
                .thenApply(user -> Response.ok().entity(user).build());
    }

    private User extendLoan(User user, ExtendLoanRequestParams requestParams) {
        Loan loan = getLoan(user, requestParams.getLoanId());
        if (loan.getPaidAt() != null) throw new RuntimeException("Loan already paid");
        loan.setPaymentExpectationDateExtended(loan.getPaymentExpectationDate().plusWeeks(1));
        loan.setAmount(loan.getAmount() + loan.getAmount() * 0.15);
        return user;
    }

    private Loan getLoan(User user, long loanId) {
        return user.getLoans().stream().filter(l -> l.getLoanId() == loanId).findFirst().orElseThrow(() -> new RuntimeException("Loan Not Found"));
    }

    private User payLoan(User user, PayLoanRequestParams requestParams) {
        Loan loan = getLoan(user, requestParams.getLoanId());
        loan.setPaidAt(DateTime.now());
        return user;
    }

    private User checkRisks(LoanRequestParam requestParams, User user) {
        DateTime now = DateTime.now();
        if ((now.isAfter(midnight) && now.isBefore(morning)) && requestParams.getLoanAmount() == user.getMaxAmount()) {
            deepRiskCheck();
        }
        List<LoanRequest> loanRequests = loanRequestDao.getByIp(requestParams.getRemoteAddr());
        if (!loanRequests.isEmpty() && loanRequests.size() >= 2) {
            deepRiskCheck();
        }
        return user;
    }

    private User approvedLoan(LoanRequestParam requestParams, User user) {
        Loan loan = new Loan();
        loan.setIssuedAt(DateTime.now());
        loan.setAmount(requestParams.getLoanAmount());
        loan.setPaymentExpectationDate(DateTime.now().plusMonths(1));
        user.getLoans().add(loan);
        return user;
    }

    private boolean deepRiskCheck() {
        return false;
    }
}
