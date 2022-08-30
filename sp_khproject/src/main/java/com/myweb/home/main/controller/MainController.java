package com.myweb.home.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.home.main.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService service;
	
	@RequestMapping(value="/main")
	public String main(Model model) {
		return "main/main";
	}
}
