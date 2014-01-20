package com.springmvc.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class CheckLoginInterceptor extends HandlerInterceptorAdapter{

	private String returnLoginUrl;
	private String returnMerchantLoginUrl;
	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		if ( request.getMethod().equals(RequestMethod.HEAD)) { return; }
		/*final SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(SESSION_USER);*/
	  //在操作的时候可以判断当前用户是否登录，未登录可以跑错哦，登录可以进行session出来
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler)
					throws Exception {
	
			// 不需要登陆
			return true;
	}

	@Override
	public void afterCompletion(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final Exception ex) throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(
			final HttpServletRequest request,
			final HttpServletResponse response, final Object handler)
					throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	public String getReturnLoginUrl() {
		return returnLoginUrl;
	}

	public void setReturnLoginUrl(final String returnLoginUrl) {
		this.returnLoginUrl = returnLoginUrl;
	}

	public String getReturnMerchantLoginUrl() {
		return returnMerchantLoginUrl;
	}

	public void setReturnMerchantLoginUrl(final String returnMerchantLoginUrl) {
		this.returnMerchantLoginUrl = returnMerchantLoginUrl;
	}

}
