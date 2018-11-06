package io.fourfinanceit.homework;

import io.fourfinanceit.homework.app.IpLimitFilter;
import io.fourfinanceit.homework.app.IpValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}

	@Bean
	@Scope(value="singleton")
	public IpValidator ipValidator() {
		return new IpValidator();
	}

	@Bean
	@Order(1)
	public IpLimitFilter ipLimitFilter(IpValidator ipValidator) {
		return new IpLimitFilter(ipValidator);
	}

	@Bean
	@SuppressWarnings("unchecked")
	public FilterRegistrationBean ipLimitFilterRegistrationBean(IpLimitFilter ipLimitFilter) {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(ipLimitFilter);
//		registrationBean.addUrlPatterns("/api/customers/*");
		registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
		return registrationBean;
	}
}
