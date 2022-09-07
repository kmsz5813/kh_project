package com.myweb.home.info.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		// 폼에서 입력한 값
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		AccountsDTO data = new AccountsDTO();
		data.setAc_email(email);
		data.setAc_pw(pw);
		
		String email2 = acDto.getAc_email();
		String pw2 = acDto.getAc_pw();
		
		
		if(email.equals(email2) && pw.equals(pw2)) {
	        return "info/modify";

	    } else {
        	request.setAttribute("errorMsg", false);
            return null;
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
		// 입력한 이메일, 비밀번호
		data.setAc_email(email);
		data.setAc_pw(pw);
		
		// 세션에 저장된 이메일, 비밀번호
		String email2 = acDto.getAc_email();
		String pw2 = acDto.getAc_pw();
		
		if(email.equals(email2) && pw.equals(pw2)) { //이메일주소랑 비밀번호 체크 완료시	
			service.delete(data);
			System.out.println("삭제 완료");
			session.invalidate();
			request.setAttribute("errorMsg", true);
			return "redirect:/main";
		} else {
			request.setAttribute("errorMsg", false);
			return null;
		}
		
	}
	
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
    public String logoutMainGET(HttpServletRequest request) throws Exception{
        
        logger.info("logoutMainGET메서드 진입");
        HttpSession session = request.getSession();
        System.out.println("로그아웃");
        session.invalidate();
        
        return "redirect:/main";        
        
    }
	
	@GetMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto ) {
		
		return "info/modify";
	}
	
	@PostMapping(value="/modify")
	public String modify(Model model
				, @SessionAttribute("loginData") AccountsDTO acDto
				, HttpServletRequest request
				, HttpSession session) {
		
		String mod_email = request.getParameter("mod_email");
		String mod_name = request.getParameter("mod_name");
		String mod_pw = request.getParameter("mod_pw");
		String mod_job = request.getParameter("mod_job");
		String mod_field = request.getParameter("mod_field");
		String mod_interest = request.getParameter("mod_interest");

		AccountsDTO data = new AccountsDTO();
		
		data.setAc_email(mod_email);
		data.setAc_name(mod_name);
		data.setAc_pw(mod_pw);
		data.setAc_job(mod_job);
		data.setAc_field(mod_field);
		data.setAc_interest(mod_interest);
		
		boolean result = service.modify(data);

		if(result) {
			service.getLogin(session, data);
			return "redirect:/info";
		} else {
			return "info/modify";
		}
		
		// 세션에 비밀번호 바뀐거 제대로 적용됐는지도 체크해야 함.
		
	}
	
	// 수정 페이지 닉네임중복체크
	@PostMapping("modify/nameCheck")
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
