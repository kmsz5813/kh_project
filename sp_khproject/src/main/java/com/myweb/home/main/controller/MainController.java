package com.myweb.home.main.controller;

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
import com.myweb.home.main.service.MainService;

@Controller
@RequestMapping(value="/main")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	
	@Autowired
	private MainService service;
	
	@GetMapping(value="")
	public String main(Model model) {
		logger.info("main({})", model);
		
		
		return "main/main";
		
	}
	
	//로그인했을때 dto정보받아오기...
	@PostMapping(value="")
	public String main(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto) {
		logger.info("main({}, {})", model, acDto);
		
		return "main/main";

	}
	
}