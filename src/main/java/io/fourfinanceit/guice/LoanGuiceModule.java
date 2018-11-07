package io.fourfinanceit.guice;

import com.google.inject.AbstractModule;
import io.fourfinanceit.service.LoanService;
import io.fourfinanceit.service.LoanServiceImpl;
import org.hibernate.SessionFactory;


public class LoanGuiceModule extends AbstractModule {

    SessionFactory sessionFactory;

    public LoanGuiceModule(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected void configure(){
        bind(SessionFactory.class).toInstance(sessionFactory);
        bind(LoanService.class).to(LoanServiceImpl.class);
    }
}
