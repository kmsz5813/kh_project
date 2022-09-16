package com.myweb.home.community.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.community.service.CommunityMainService;

@Controller
@RequestMapping("/community")
public class CommunityMainController {

	@Autowired
	CommunityMainService communityMainService;

	@RequestMapping("/main")
	public String communityMain(Model model, HttpSession session) {
		
		//System.out.println("게시판 메인");
		
//		AccountsDTO ac = (AccountsDTO)session.getAttribute("loginData");
//	
//		if(ac != null) {//로그인이 되어있을때만 실행
			
			//공지사항
			List<Map<String, Object>> notice = communityMainService.selectNotice();
			
			//질문게시판
			//List<Map<String, Object>> question = communityMainService.selectQuestion();			
			
			model.addAttribute("notice", notice);
			//model.addAttribute("question", question);
			
			return "community/main";
		
//		} else { //로그인이 되어있지 않으면 로그인인페이지로 리다이렉트
//			
//			return "redirect:/login";
			
		}
		
	}
