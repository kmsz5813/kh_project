package com.myweb.home.selitem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myweb.home.Accounts.model.AccountsDTO;

@Controller
@RequestMapping(value="/selitemcomment")
public class SelItemCommentController {
	
	@GetMapping(value="/selitemcomment")
	public String ChattingUser(Model model) {
		return "/selitemcomment";
	   }
	   
	 @PostMapping(value="/selitemcomment")
	 public String ChattingUser(Model model, HttpServletRequest request
	         , @SessionAttribute("loginData") AccountsDTO accDto ) {
	      
	      
	      //등록 로직 부분을 짜면 됨.
	      
	      return "/selitemcomment";
	   }
}
