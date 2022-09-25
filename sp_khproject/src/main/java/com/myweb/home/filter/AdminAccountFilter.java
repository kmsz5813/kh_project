package com.myweb.home.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.home.Accounts.model.AccountsDTO;

@WebFilter(
		urlPatterns = {
				"/admin", "/admin/**", "/detail"  // 추가로 넣어야할것??
		}
)
public class AdminAccountFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		HttpServletRequest req = (HttpServletRequest)request;
		String url = null;
		AccountsDTO acdto = (AccountsDTO) session.getAttribute("loginData");
		if(session.getAttribute("loginData") != null && acdto.getAc_index() == 30) {
			chain.doFilter(request, response);
		} else {
			String context_path = ((HttpServletRequest) request).getContextPath();
			String reqURI = req.getRequestURI();
			if(reqURI.contains(context_path)) {
				url = reqURI.replace(context_path,"");
				System.out.println(url);
			}
			((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/login" + "?url=" + url);
		}
	}
	
}
