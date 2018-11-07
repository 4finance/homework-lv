package io.fourfinanceit.homework.validation.filter;

import io.fourfinanceit.homework.model.entity.Risk;
import io.fourfinanceit.homework.repository.RiskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IpFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(IpFilter.class);
	private static final String POST = "POST";

	private IpValidator ipValidator;

	public IpFilter(IpValidator ipValidator) {
		this.ipValidator = ipValidator;
	}

	@Autowired
	private RiskRepository riskRepository;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		if (POST.equalsIgnoreCase(request.getMethod())) {
			validateRequest(request, servletResponse, filterChain);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	private void validateRequest(
			HttpServletRequest request, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		String ipAddress = request.getRemoteAddr();
		if (ipValidator.isIpAddressReachedLimit(ipAddress)) {
			blockRequest(servletResponse, ipAddress);
		} else {
			ipValidator.registerIpAddressAttempt(ipAddress);
			filterChain.doFilter(request, servletResponse);
		}
	}

	private void blockRequest(ServletResponse servletResponse, String ipAddress) {
		logger.info(String.format("Request aborted for IP : %s", ipAddress));
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		registerRisk(ipAddress);
	}

	private void registerRisk(String ip) {
		Risk risk = new Risk(ip);
		riskRepository.save(risk);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
