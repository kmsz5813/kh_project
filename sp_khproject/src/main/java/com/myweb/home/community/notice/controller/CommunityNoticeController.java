package com.myweb.home.community.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.common.util.Paging;
import com.myweb.home.common.util.PagingCommunity;
import com.myweb.home.community.notice.model.CommunityNoticeDTO;
import com.myweb.home.community.notice.service.CommunityNoticeService;

//사용자
@Controller
@RequestMapping(value="/community")
public class CommunityNoticeController {

	@Autowired
	CommunityNoticeService communityNoticeService;
	
	//제가만든 부분
	//아래 이제 네게이션 부분
	@GetMapping(value="/notice/list")
	public String getlist(Model model, HttpSession session
			, @RequestParam(defaultValue="1", required=false) int page
			, @RequestParam(defaultValue="0", required=false) int pageCount) {
		
		//데이터를 가져와서 notice화면에 뿌려야한다 뿌리는 과정을 나타내는 로직
		List data = communityNoticeService.getData();

		//데이터값을 notice페이지에 보낸다
//		model.addAttribute("data", data);
		
		session.setAttribute("pageCount", 10); //10개씩 보여주기 위하게 만든것.
		
		if(pageCount > 0) {
			session.setAttribute("pageCount", pageCount);
		}
		
		pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
		
		Paging paging = new Paging(data, page, pageCount);
		
		model.addAttribute("result", paging.getPageData());
		model.addAttribute("pageData", paging);
	
		return "community/notice/list";
	}
	
	@GetMapping(value="/notice/detail")
	public String detail(Model model, HttpServletRequest request) {
		String notice_no = request.getParameter("no");
		
		//notice_no을 기준으로 데이터 조회
		
		CommunityNoticeDTO data = communityNoticeService.getOneData(notice_no);
		model.addAttribute("data", data);
		
		return "community/notice/detail";
	}
	
}