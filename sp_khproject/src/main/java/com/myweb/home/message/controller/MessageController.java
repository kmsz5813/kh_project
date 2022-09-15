package com.myweb.home.message.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.socket.ChattingCS;


@Controller
public class MessageController {

	
	@GetMapping(value="/chat")
	public String ChattingUser(Model model) {
		return "/message/ChattingUser";
	   }
	   
	 @PostMapping(value="/chat")
	 public String ChattingUser(Model model, HttpServletRequest request
	         , @SessionAttribute("loginData") AccountsDTO accDto ) {
	      
	      String chat = request.getParameter("chat");
	      
	      System.out.println(chat);
	      
	      
	      //등록 로직 부분을 짜면 됨.
	      
	      return "/message/ChattingUser";
	   }
	
}
