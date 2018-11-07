package io.fourfinanceit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Environment;
import io.dropwizard.setup.Bootstrap;
import io.fourfinanceit.guice.LoanGuiceModule;
import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.LoanRequest;
import io.fourfinanceit.model.User;
import io.fourfinanceit.resource.LoanResource;

import java.text.SimpleDateFormat;

public class HomeworkApplication extends Application<HomeworkConfiguration> {

    public static void main(String[] args) throws Exception {
        new HomeworkApplication().run(args);
    }

    @Override
    public String getName(){
        return "HomeWork";
    }

    private final HibernateBundle<HomeworkConfiguration> hibernate = new HibernateBundle<HomeworkConfiguration>(User.class, Loan.class, LoanRequest.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(HomeworkConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<HomeworkConfiguration> bootStrap){
        bootStrap.addBundle(hibernate);
    }

    @Override
    public void run(HomeworkConfiguration configuration, Environment environment){
        Injector guice = Guice.createInjector(new LoanGuiceModule(hibernate.getSessionFactory()));
        environment.jersey().register(guice.getInstance(LoanResource.class));
        environment.getObjectMapper().setDateFormat(new SimpleDateFormat("YYYY-MM-dd"));
    }
}
