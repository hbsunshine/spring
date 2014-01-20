package com.springmvc.interceptor;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoggingInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory
			.getLogger(LoggingInterceptor.class);

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
		//if (PropertiesUtil.getValue("log_request").equals("true")) {
			final long start = System.currentTimeMillis();
			final String random = UUID.randomUUID().toString();
			request.setAttribute("_request_id", random);

			logger.info(String.format("!--REQUEST START(time: %d, id: %s)--!",
					start, random));

			logger.info("Request URL: " + request.getRequestURL().toString());

			final List<String> requestParameterNames = Collections.list(request
					.getParameterNames());
			logger.info("Parameter number: " + requestParameterNames.size());

			for (final String parameterName : requestParameterNames) {
				logger.info("Parameter name: " + parameterName
						+ " - Parameter value: "
						+ request.getParameter(parameterName));
			}
		//}
		return true;
	}

	@Override
	public void afterCompletion(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final Exception ex) throws Exception {
		//if (PropertiesUtil.getValue("log_request").equals("true")) {
			// TODO Auto-generated method stub
			final long start = System.currentTimeMillis();
			final String random = (String) request.getAttribute("_request_id");
			logger.info(String.format("!--REQUEST ENDS(time: %d, id: %s)--!",
					start, random));
		//}
		super.afterCompletion(request, response, handler, ex);
	}

}
