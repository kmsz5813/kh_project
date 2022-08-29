package com.myweb.home.sel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.home.sel.model.SelDTO;
import com.myweb.home.sel.service.SelService;



@Controller
@RequestMapping(value="/sel")
public class SelController {

	@Autowired
	private SelService service;
	
	@GetMapping(value="/selsign")
	public String selsign(Model model) {
		return "sel/selsign";
	}
	
	@PostMapping(value="/selsign")
	public String cussign(HttpServletRequest request) {
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
	
		
		SelDTO data = new SelDTO();
		data.setSel_email(sel_email);
		data.setSel_name(sel_name);
		data.setSel_pw(sel_pw);
		data.setSel_job(sel_job);
		data.setSel_field(sel_field);
		data.setSel_interest(sel_interest);
		data.setSel_sendemail(sel_sendemail);
		
		boolean result = service.add(data);
		
		
		return "sel/selsign";
		
	}
}
