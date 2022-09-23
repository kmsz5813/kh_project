package com.myweb.home.chat.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.chat.model.ChatDAO;
import com.myweb.home.chat.model.ChatVO;

@Controller
public class ChatController {
	
	@Autowired
	ChatDAO dao;
	
	AccountsDTO dto;
	
	@RequestMapping("/chat.json")
	public List<ChatVO> list(){
		return dao.list();
	}
	
	
	@GetMapping(value="/chatting")
	public String Chatting(Model model) {
		return "/chat/chat";
	   }
	   
	 @PostMapping(value="/chatting")
	 public String Chatting(Model model, HttpServletRequest request
	         , @SessionAttribute("loginData") AccountsDTO acDto 
			 ) throws Exception {
	      String chat = request.getParameter("chat");
	      
	      System.out.println(chat);
	      
	      return "/chat/chat";
	   }
	
	 @RequestMapping(value="/chat/insert", method=RequestMethod.POST)
		public int insert(ChatVO vo){
			dao.insert(vo);
			int last=dao.last();
			System.out.println("last......." + last);
			return last;
		}
	 
}
