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
	public String Chatting(Model model, HttpServletRequest request) {
		
		
		String id = request.getParameter("itemid");
		String sel = request.getParameter("sel");
		String cus = request.getParameter("cus");
		
		
		
		System.out.println(id);
		System.out.println(sel);
		System.out.println(cus);
		
		ChatVO data = new ChatVO();
		
		//자동커밋 
		//다시 들어왔을때 뿌려줘야하낟...

		
		data.setItem_id(id);
		data.setSender(cus);
		data.setReceiver(sel);
		//조회 하면 되겠네
		if(sel.equals(cus)) {
			List<ChatVO> samedata = dao.sameSelect(data); //SEL_ITEM의 번호와 RECEIVER 를 조회했는데 RECEIVER는 전부다 나올수밖에 구조로 되어있어
			System.out.print("samdata값 :" + samedata);
			model.addAttribute("SameData", samedata);
		}
		
		
		List<ChatVO> Resultdata = dao.select(data);
//		
		model.addAttribute("data", data);
		if(Resultdata != null) {
			model.addAttribute("Resultdata", Resultdata);
		}
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
	
//	 @RequestMapping(value="/chat/insert", method=RequestMethod.POST)
//		public int insert(ChatVO vo){
//			dao.insert(vo);
//			int last=dao.last();
//			System.out.println("last......." + last);
//			return last;
//		}
//	 
}
