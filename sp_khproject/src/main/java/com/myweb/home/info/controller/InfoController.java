package com.myweb.home.info.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myweb.home.Accounts.model.AccountsDTO;




@Controller
@RequestMapping(value="/info")
public class InfoController {
	private static final Logger logger = LoggerFactory.getLogger(InfoController.class);
	
	
	@GetMapping(value="")
	public String main(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto) {
		logger.info("main({}) cusdata({})", model, acDto);
		
		
		return "info/info";
		
	}

}
