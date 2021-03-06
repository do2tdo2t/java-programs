package com.checkin.webapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.checkin.webapp.Constants;
import com.checkin.webapp.accomodation.model.AccomodationDAOInterface;
import com.checkin.webapp.accomodation.model.AccomodationVO;

public class LoginMasterInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("LoginMasterInterceptor");
		if(wasLogin(request)) {
			return true;
		}else {
			response.sendRedirect("/webapp/main/login");
			return false;
		}
		
	}
	
	public boolean wasLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("mid");
		if(mid==null || "".equals(mid)) {
			
			return false;
		}else {
			
			return true;
		}
	}
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		

	}

}
