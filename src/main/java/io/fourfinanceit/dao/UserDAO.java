package io.fourfinanceit.dao;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import io.fourfinanceit.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class UserDAO extends AbstractDAO<User> {

    SessionFactory sessionFactory;

    @Inject
    public UserDAO(SessionFactory factory) {
        super(factory);
        this.sessionFactory = factory;
    }

    public User create(User user) {
        Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(requireNonNull(user));
        transaction.commit();
        session.close();
        return user;
    }

    public User getByName(String name){
        Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        User user = list(criteria().add(Restrictions.eq("name", name))).stream().findAny().orElseThrow(()-> new RuntimeException("User not found"));
        session.close();
        return user;
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        List<User> users = list(namedQuery("io.fourfinanceit.model.User.findAll"));
        session.close();
        return users;
    }
}
