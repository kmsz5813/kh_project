package com.myweb.home.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.home.cus.model.CusDTO;
import com.myweb.home.cus.service.CusService;
import com.myweb.home.sel.model.SelDTO;
import com.myweb.home.sel.service.SelService;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
		
	
	@Autowired
	private CusService cusService;
	
	@Autowired
	private SelService selService;
	
	@GetMapping(value="")
	public String login(Model model) {
		
		return "login/login";
	}
	
	@PostMapping(value="")
	public String login(HttpServletRequest request, 
						HttpSession session,
						String url) {
		
		String index = request.getParameter("index");
		
		if(index.equals("10")) {
			String cus_email = request.getParameter("email");
			String cus_pw = request.getParameter("pw");
			
			CusDTO cusData = new CusDTO();
			cusData.setCus_email(cus_email);
			cusData.setCus_pw(cus_pw);
			
			boolean result = cusService.getLogin(session, cusData);
			
			if(result) {
				return "home";
				
			}
			
		}else {
			String sel_email = request.getParameter("email");
			String cus_pw = request.getParameter("pw");
			
			SelDTO selData = new SelDTO();
			selData.setSel_email(sel_email);
			selData.setSel_pw(sel_email);
			
			boolean result = selService.getLogin(session, selData);
			
			if(result) {
				return "home";
			}
		}
		
		return "login/login";
	}
	
	@GetMapping(value="/sign")
	public String sign(Model model) {
		
		return "login/sign";
	}
	
}
