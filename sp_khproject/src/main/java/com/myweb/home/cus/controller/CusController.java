package com.myweb.home.cus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.home.cus.model.CusDTO;
import com.myweb.home.cus.service.CusService;

@Controller
@RequestMapping(value="/cus")
public class CusController {
	
	@Autowired
	private CusService service;
	

	@GetMapping(value = "/cussign")
	public String cussign(Model model) {
	
		return "cus/cussign";
		
	}
	
	@PostMapping(value="/cussign")
	public String cussign(HttpServletRequest request
						  ) {
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
	
		
		CusDTO data = new CusDTO();
		data.setCus_email(cus_email);
		data.setCus_name(cus_name);
		data.setCus_pw(cus_pw);
		data.setCus_job(cus_job);
		data.setCus_field(cus_field);
		data.setCus_interest(cus_interest);
		data.setCus_sendemail(cus_sendemail);
		
		boolean result = service.add(data);
		if(result) {
			return "cus/cussign";
		}else {
			return "cus/cussign"; //에러페이지 작성
		}
		
	}
}
