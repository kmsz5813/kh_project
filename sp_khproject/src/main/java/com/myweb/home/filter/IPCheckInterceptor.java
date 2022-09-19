package com.myweb.home.filter;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.myweb.home.admin.model.BlackDTO;
import com.myweb.home.admin.service.AdminService;

// 블랙리스트 회원은 회원가입할 때의 IP는 차단당한다.
public class IPCheckInterceptor implements HandlerInterceptor{
	private static Logger logger = LoggerFactory.getLogger(IPCheckInterceptor.class);

	@Autowired
	private AdminService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 네트워크 환경에 따른 프록시가 다르므로 전부 적용
		String ip = request.getHeader("X-Forwarded-For");
	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	    }
		logger.info("client ip =" + ip);

		
		List<BlackDTO> list = service.find_ip_ban_list();
		if(list != null) {			
			for(BlackDTO li: list) {
				logger.info("ban ip" + li.getIp_address());
				if(ip.equals(li.getIp_address())) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('차단된 IP 입니다');history.go(-1);</script>");
					out.flush();
					return false;
				}
			}
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
}
