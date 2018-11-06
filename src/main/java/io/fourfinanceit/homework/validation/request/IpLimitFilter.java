package io.fourfinanceit.homework.validation.request;

import io.fourfinanceit.homework.model.RejectionReason;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IpLimitFilter extends RiskFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(IpLimitFilter.class);

	private IpValidator ipValidator;

	public IpLimitFilter(IpValidator ipValidator) {
		this.ipValidator = ipValidator;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		String ipAddress = request.getRemoteAddr();

		if (ipValidator.isIpAddressReachedLimit(ipAddress)) {
			blockRequest(servletResponse, ipAddress);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	private void blockRequest(ServletResponse servletResponse, String ipAddress) {
		logger.info(String.format("Request aborted for IP : %s", ipAddress));
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		registerRisk();
	}

	@Override
	public void destroy() {
	}

	@Override
	protected RejectionReason getRejectionReason() {
		return RejectionReason.MAX_APPLICATIONS_PER_DAY;
	}
}
