package com.myweb.home.cus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myweb.home.cus.model.CusDTO;

@Controller
@RequestMapping(value="/cus")
public class CusController {
	
	

	@GetMapping(value = "/cussign")
	public String cussign(Model model) {
	
		return "cus/cussign";
		
	}
	
	@PostMapping(value="/cussign")
	public String cussign(HttpServletRequest request
						  ) {
		String cus_email = request.getParameter("cus_email");
		String cus_pw = request.getParameter("cus_pw");
		String cus_job = request.getParameter("cus_job");
		String cus_field = request.getParameter("cus_field");
		String cus_interest = request.getParameter("cus_interest");
		String cus_sendemail = request.getParameter("cus_sendemail");
		
		CusDTO data = new CusDTO();
		data.setCus_email(cus_email);
		data.setCus_pw(cus_pw);
		data.setCus_job(cus_job);
		data.setCus_field(cus_field);
		data.setCus_interest(cus_interest);
		data.setCus_sendemail(cus_sendemail);
		
		System.out.println(cus_sendemail);
		
		return "cus/cussign";
	}
}
