package com.mybatis.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.demo.biz.user.vo.User;

public class LoginInterCeptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("===============preHandle=======================" + request.getCookies());
		HttpSession session = request.getSession(false); // true라면 세션이 없을때 새로 만들어준다. false 면 null을 리턴한다(없을때) 
		
		boolean result = true;
		
		if(session != null) {
			User user = (User) session.getAttribute("user");
			if(user == null) {
				result = false;
			}
		} else {
			result = false;
		}
		
		if(!result) response.sendRedirect("/");
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
