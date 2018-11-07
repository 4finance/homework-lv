package io.fourfinanceit.service;

import io.fourfinanceit.requestparam.ExtendLoanRequestParams;
import io.fourfinanceit.requestparam.LoanRequestParam;
import io.fourfinanceit.requestparam.PayLoanRequestParams;
import io.fourfinanceit.requestparam.UserInfoRequestParams;

import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;

public interface LoanService {

     CompletableFuture<Response> initialise(String name);
     CompletableFuture<Response> getByName(UserInfoRequestParams requestParamse);
     CompletableFuture<Response> applyForLoan(LoanRequestParam requestParams);
     CompletableFuture<Response> extendLoan(ExtendLoanRequestParams requestParams);
     CompletableFuture<Response> payLoan(PayLoanRequestParams requestParams);


}
