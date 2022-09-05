package com.myweb.home.info.controller;

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
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.login.service.LoginService;




@Controller
@RequestMapping(value="/info")
public class InfoController {
	private static final Logger logger = LoggerFactory.getLogger(InfoController.class);
	
	@Autowired
	private LoginService service;
	
	
	@GetMapping(value="")
	public String main(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto) {
		logger.info("main({}) cusdata({})", model, acDto);
		
		return "info/info";
		
	}
	
	@GetMapping(value="/modifycheck")
	public String modifycheck(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto ) {
		
		
		return "info/modifycheck";
	}
	
	@PostMapping(value="/modifycheck")
	public String modifycheck(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		AccountsDTO data = new AccountsDTO();
		data.setAc_email(email);
		data.setAc_pw(pw);
		
		boolean result = service.getCheck(data);

		if(result) {
			//이메일주소랑 비밀번호 체크 완료시
			return "info/modify";
		}else {
			return "login/m_login";
		}
		
	}
	
	@GetMapping(value="/deletecheck")
	public String deletecheck(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto ) {
		
		
		return "info/deletecheck";
	}
	
	@PostMapping(value="/deletecheck")
	public String deletecheck(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, HttpServletRequest request, HttpSession session) {
		
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		AccountsDTO data = new AccountsDTO();
		data.setAc_email(email);
		data.setAc_pw(pw);
		
		boolean result = service.getCheck(data);
		
		if(result) { //이메일주소랑 비밀번호 체크 완료시	
			service.delete(data);
			System.out.println("삭제 완료");
			session.invalidate();
			
			return "/login/m_login";
		}else {
			request.setAttribute("msg", false);
			return null;
		}
		
	}

}
