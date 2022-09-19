package com.myweb.home.community.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.common.util.PagingCommunity;
import com.myweb.home.community.model.CommunityNoticeDTO;
import com.myweb.home.community.service.CommunityNoticeService;

//사용자
@Controller
@RequestMapping("/community")
public class CommunityNoticeController {

	@Autowired
	CommunityNoticeService communityNoticeService;
	
	@RequestMapping("/notice")
	public String NoticeList(Model model, HttpSession session, @RequestParam(required = false, defaultValue = "1") int curPage) {
		
		AccountsDTO ac = (AccountsDTO) session.getAttribute("loginData");
		
//		if(ac != null) {//로그인이 되어있을때만 실행
			
			
//			System.out.println("공지사항");
			
			//페이징
			PagingCommunity paging = communityNoticeService.noticeListPaging(curPage);
			
			//공지사항
			List<CommunityNoticeDTO> notice = communityNoticeService.selectNotice(paging);
			
			
//			System.out.println(notice);
			
			model.addAttribute("notice", notice);
			model.addAttribute("paging", paging);
			
			
			return "community/notice";

			
//		} else { //로그인이 되어있지 않으면 메인페이지로 리다이렉트
//			
//			return "redirect:/login";
//			
//		}
	}
	
	
	//공지 상세
	@RequestMapping("/notice/detail")
	public String NoticeDetail(Model model, HttpSession session, int no) {
		
		AccountsDTO ac = (AccountsDTO) session.getAttribute("loginData");
		
//		if(ac != null) {//로그인이 되어있을때만 실행
			
			
//			System.out.println("공지사항 상세");
//			System.out.println(no);
			
			//공지사항 상세
			CommunityNoticeDTO noticeDetail = communityNoticeService.selectNoticeDetail(no);
			
//			System.out.println(noticeDetail);
			
			model.addAttribute("notice", noticeDetail);
			
			return "/notice/Detail";

			
//		} else { //로그인이 되어있지 않으면 메인페이지로 리다이렉트
			
//			return "redirect:/home/login";
			
//		}
		
	}
		
	
	@RequestMapping("/notice/search")
	public String NoticeListSearch(Model model, HttpSession session, HttpServletRequest req,
									@RequestParam(required = false, defaultValue = "1") int curPage,
									@RequestParam String keyword) {
		
		AccountsDTO ac = (AccountsDTO) session.getAttribute("loginData");
		
//		if(ac != null) {//로그인이 되어있을때만 실행
			
			System.out.println("공지사항 검색");
			System.out.println(keyword);
			
			//검색어가 없을 때
			if(keyword.equals("")) {
				
				model.addAttribute("alertMsg", "검색어를 입력해주세요");
				model.addAttribute("url", req.getContextPath()+"/community/notice");
				
				return "/notice/error";
			}
			
			
			//페이징
			PagingCommunity paging = communityNoticeService.noticeListSearchPaging(keyword, curPage);
			
			//키워드, 페이징 넣어줄 map
			Map<String, Object> searchMap = new HashMap<String, Object>();
			
			searchMap.put("keyword", keyword);
			searchMap.put("paging", paging);
			
			//공지사항 검색결과
			List<CommunityNoticeDTO> notice = communityNoticeService.selectNoticeSearch(searchMap);
			
			
			System.out.println(notice);
			
			model.addAttribute("notice", notice);
			model.addAttribute("keyword", keyword);
			model.addAttribute("paging", paging);
			
			
			return "/notice/SearchList";

			
//		} else { //로그인이 되어있지 않으면 메인페이지로 리다이렉트
			
//			return "redirect:/home/login";
			
//		}
		
	}
}