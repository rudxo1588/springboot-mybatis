package com.mybatis.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.demo.biz.user.vo.User;

public class LoginInterCeptor implements HandlerInterceptor{

	@Override
	/*
	 * preHandle은 Controller의 메소드에 매핑된
	 * 특정 URI를 호출했을 때 Contoller에 접귾기 전에 실행된다.
	 * 세션이 있다면 true 없으면 false 를 리턴한다.
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
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
	/*
	 * postHandle은 controller를 경유한 후
	 * DispatcherServlet 에 결과를 리턴하기 전에 실핼되는 메소드이다.
	 * 요청의 끝을 알리는 로그를 콘솔에 출력하여 처리한다.
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("===============PostHandle=======================" + modelAndView.toString());
		System.out.println("===============PostHandle=======================" + handler.toString());
		HttpSession session = request.getSession();
	}


	@Override
	/*
	 *
	 * view가 랜더링 된 후에 실행된다.
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("===============afterCompletion=======================");
	}


}
