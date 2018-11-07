package io.fourfinanceit.dao;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import io.fourfinanceit.model.LoanRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class LoanRequestDao extends AbstractDAO<LoanRequest> {

    SessionFactory sessionFactory;

    @Inject
    public LoanRequestDao(SessionFactory factory) {
        super(factory);
        this.sessionFactory = factory;
    }

    public LoanRequest create(LoanRequest loanRequest) {
        Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(requireNonNull(loanRequest));
        transaction.commit();
        session.close();
        return loanRequest;
    }

    public List<LoanRequest> getByIp(String ip){
        Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        List<LoanRequest> loanRequests = list(criteria().add(Restrictions.eq("requestedFromIp", ip))).stream().collect(Collectors.toList());
        session.close();
        return loanRequests;
    }
}
