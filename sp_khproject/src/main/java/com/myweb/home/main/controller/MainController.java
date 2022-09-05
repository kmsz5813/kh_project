package com.myweb.home.main.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
    public String logoutMainGET(HttpServletRequest request) throws Exception{
        
        logger.info("logoutMainGET메서드 진입");
        
        HttpSession session = request.getSession();
        System.out.println("로그아웃");
        session.invalidate();
        
        return "redirect:/main";        
        
    }
	
}
