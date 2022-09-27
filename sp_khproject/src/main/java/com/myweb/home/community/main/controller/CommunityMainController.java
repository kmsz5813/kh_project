package com.myweb.home.community.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.community.main.service.CommunityMainService;

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
			System.out.println(notice);
			//질문게시판
			List<Map<String, Object>> question = communityMainService.selectQuestion();		
			
			//레슨자 찾아요
			List<Map<String, Object>> findStu = communityMainService.selectFindStu();	
			
			//전문가 찾아요
			List<Map<String, Object>> findPro = communityMainService.selectFindPro();	
			
			//일상
			List<Map<String, Object>> life = communityMainService.selectLife();	
			

			List<Map<String, Object>>  joined = new ArrayList<>();
			joined.addAll(notice);
			joined.addAll(question);
			joined.addAll(findStu);
			System.out.println(joined);
			//방법하나 ----------> NO를 바꿔서 넣고 EX) NOTICE_NO만 이름값을 QUESTION_NO
			//조인을 순서랜덤값으로 바꾸잖아???????? 바꿔도 의미가 없는게 출력할ㄸㄴ느 joined.findstu_title <<< 이렇게해야 조회값이 나오잖아 순서는 상관이
			//없어 랜덤돌려가지고 출력하면 
			
			model.addAttribute("joined", joined);
			model.addAttribute("notice", notice);
			model.addAttribute("question", question);
			model.addAttribute("findStu", findStu);
			model.addAttribute("findPro", findPro);
			model.addAttribute("life", life);
			
			return "community/main";
		
//		} else { //로그인이 되어있지 않으면 로그인인페이지로 리다이렉트
//			
//			return "redirect:/login";
			
		}
		
	}
