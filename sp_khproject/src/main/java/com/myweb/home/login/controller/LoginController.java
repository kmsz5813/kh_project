package com.myweb.home.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.login.service.LoginService;


@Controller
@RequestMapping(value="/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
		
	
	@Autowired
	private LoginService service;
	
	
	@GetMapping(value="")
	public String login(Model model) {
		
		return "login/login";
	}

	@PostMapping(value="")
	public String login(HttpServletRequest request, 
						HttpSession session,
						Model model) {
		
			String email = request.getParameter("email");
			String pw = request.getParameter("pw");
			
			AccountsDTO data = new AccountsDTO();
			data.setAc_email(email);
			data.setAc_pw(pw);
			
			boolean result = service.getLogin(session, data);
			
			System.out.println("logincontroller : "  + result);
			
			if(result) {
				//로그인성공시
				return "redirect:main";
			}else {
				return "login/m_login";
			}
			
			

	}
	
	@GetMapping(value="/sign")
	public String sign(Model model) {
		
		return "login/sign";
	}
	
	@GetMapping(value="/cussign")
	public String cussign(Model model) {
		
		return "login/cussign";
	}
	
	@PostMapping(value="/cussign")
	public String cussign(HttpServletRequest request,
						HttpSession session) {
		String cus_email = request.getParameter("cus_email");
		String cus_name = request.getParameter("cus_name");
		String cus_pw = request.getParameter("cus_pw");
		String cus_job = request.getParameter("cus_job");
		String cus_field = request.getParameter("cus_field");
		String cus_interest = request.getParameter("cus_interest");
		String cus_sendemail = request.getParameter("cus_sendemail");
		
		if("on".equals(cus_sendemail)) {
			cus_sendemail = "Y";
		}else{
			cus_sendemail = "N";
		}
		
		AccountsDTO data = new AccountsDTO();
		data.setAc_email(cus_email);
		data.setAc_name(cus_name);
		data.setAc_pw(cus_pw);
		data.setAc_job(cus_job);
		data.setAc_field(cus_field);
		data.setAc_interest(cus_interest);
		data.setAc_index(10);
		data.setAc_sendemail(cus_sendemail);
		

		if(cus_email == null) {
			return "login/cussign";
		}
			
	
		boolean result = service.add(data);
	
		if(result) {
			
			data.setAc_email(cus_email);
			data.setAc_pw(cus_pw);
			
			boolean result1 = service.getLogin(session, data);

			return "redirect: /home/main";
		}
		
		return "login/cussign";
	}
	
	@GetMapping(value="/selsign")
	public String selsign(Model model) {
		
		return "login/selsign";
	}
	
	@PostMapping(value="/selsign")
	public String selsign(Model model, HttpServletRequest request, HttpSession session) {
		String sel_email = request.getParameter("sel_email");
		String sel_name = request.getParameter("sel_name");
		String sel_pw = request.getParameter("sel_pw");
		String sel_job = request.getParameter("sel_job");
		String sel_field = request.getParameter("sel_field");
		String sel_interest = request.getParameter("sel_interest");
		String sel_sendemail = request.getParameter("sel_sendemail");
		
		if("on".equals(sel_sendemail)) {
			sel_sendemail = "Y";
		}else{
			sel_sendemail = "N";
		}
	
		
		AccountsDTO data = new AccountsDTO();
		data.setAc_email(sel_email);
		data.setAc_name(sel_name);
		data.setAc_pw(sel_pw);
		data.setAc_job(sel_job);
		data.setAc_field(sel_field);
		data.setAc_interest(sel_interest);
		data.setAc_index(20);
		data.setAc_sendemail(sel_sendemail);
		
		
		boolean result = service.add(data);
		
		if(result) {

			data.setAc_email(sel_email);
			data.setAc_pw(sel_pw);
			
			boolean result1 = service.getLogin(session, data);

			return "redirect: /home/main";
	

		}
		
		return "login/selsign";
	}

		//이메일중복체크
		@PostMapping("/cussign/idCheck")
		@ResponseBody
		public String idCheck(@RequestParam("id") String id) {
			
		
			JSONObject json = new JSONObject();
			
			AccountsDTO data = service.idCheck(id);
			
	
			
			if(data == null) {
				json.put("code", "success");
			}else {
				json.put("code", "sameid");
			}
			
			return json.toJSONString();
			
		}
		
		//닉네임중복체크
		@PostMapping("/cussign/nameCheck")
		@ResponseBody
		public String nameCheck(@RequestParam("name") String name) {
			
			
			
			JSONObject json = new JSONObject();
			
			AccountsDTO data = service.nameCheck(name);
			
			System.out.println("로그인컨트롤러 : " + data);
			
			if(data == null) {
				json.put("code", "success");
			}else {
				json.put("code", "sameid");
			}
			
			return json.toJSONString();
			
		}
	
	
}
