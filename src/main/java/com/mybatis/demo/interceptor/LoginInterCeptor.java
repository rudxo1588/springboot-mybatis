package com.mybatis.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterCeptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("===============preHandle=======================" + request.getCookies());
		HttpSession session = request.getSession();
		
		boolean result = true;
		System.out.println("====================session.getAttribute(\"id\")====================" + session.getAttribute("id"));
		if((String)session.getAttribute("id") != null && !"".equals((String)(session.getAttribute("id")))) {
			result = true;
		} else {
			result = false;
			response.sendRedirect("/");
		}
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("===============PostHandle=======================" + modelAndView.toString());
		System.out.println("===============PostHandle=======================" + handler.toString());
		HttpSession session = request.getSession();
	}
	

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("===============afterCompletion=======================");
	}
	
	
}
