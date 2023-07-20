package com.kh.spring.common.interceptor;

// DB에 입력이 잘되었는지 중간에 interceptor로 확인하는 작업 구현

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {
	// HandlerInterceptor를 컨트롤+클릭하면 아래 3개의 메소드가 들어있음(복사해서 사용) 
	// 전처리 Handle 호출전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("===========================================");
		log.debug(request.getMethod() + " " + request.getRequestURI());
		log.debug("-------------------------------------------");
		return true;
	}
	
	// 후처리 Handle 리턴 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		log.debug("-------------------------------------------");
		log.debug("modelAndView = " + modelAndView);
	}
	
	// view단 응답처리 후
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		log.debug("___________________________________________");
		log.debug("end");
		log.debug("===========================================");
		log.debug(" ");
	}
}
