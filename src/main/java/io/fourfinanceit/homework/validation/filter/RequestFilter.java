package io.fourfinanceit.homework.validation.filter;

import io.fourfinanceit.homework.validation.filter.IpFilter;
import io.fourfinanceit.homework.validation.filter.IpValidator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class RequestFilter {

	@Bean
	@Scope(value = "singleton")
	public IpValidator ipValidator() {
		return new IpValidator();
	}

	@Bean
	@Order(1)
	public IpFilter ipFilter(IpValidator ipValidator) {
		return new IpFilter(ipValidator);
	}

	@Bean
	public FilterRegistrationBean<IpFilter> ipLimitFilterRegistrationBean(IpFilter ipFilter) {
		FilterRegistrationBean<IpFilter> filterRegBean = new FilterRegistrationBean<>();
		filterRegBean.setFilter(ipFilter);
		filterRegBean.addUrlPatterns("/api/customers/*");
		filterRegBean.setOrder(Ordered.LOWEST_PRECEDENCE);
		return filterRegBean;
	}
}
