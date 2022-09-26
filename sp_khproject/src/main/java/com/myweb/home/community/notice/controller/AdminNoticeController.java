package com.myweb.home.community.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.common.util.PagingCommunity;
import com.myweb.home.community.notice.model.CommunityNoticeDTO;
import com.myweb.home.community.notice.service.AdminNoticeService;
import com.myweb.home.community.notice.service.CommunityNoticeService;
import com.myweb.home.community.notice.vo.CommunityNoticeVO;
import com.myweb.home.community.question.model.CommunityQuestionDTO;
import com.myweb.home.community.question.vo.CommunityQuestionVO;


@Controller
@RequestMapping("/admin/notice")
public class AdminNoticeController {
	
	@Autowired
	private AdminNoticeService noticeService;
	
	@Autowired
	private CommunityNoticeService Service;
	
	//공지사항 정보 전체 조회
	@RequestMapping("/list")
	public String  noticeList(Model model,HttpServletRequest req,HttpSession session) {
//		System.out.println("/admin/notice/list");
		
		//요청 파라미터를 전달하여 paging 객체 생성하기
		PagingCommunity paging = noticeService.noticeListPaging(req);
				
		//공지사항 정보 전체 조회 list
		List<CommunityNoticeDTO> noticeList = noticeService.selectNoticeList(paging);
		
		//모델값 전달
		model.addAttribute("noticeList", noticeList);
		
		//페이징 결과 전달
		model.addAttribute("paging", paging);
		
//		System.out.println(noticeList);
		
		return "admin/notice/list";
	}	
	
	//공지사항 세부정보 보기
	@RequestMapping("/detail")
	public String  noticeDetail(Model model,@RequestParam int noticeNo) {
//		System.out.println("/admin/notice/detail");
//		System.out.println("noticeNo"+noticeNo);
		
		//공지사항 정보 조회 one
		CommunityNoticeDTO noticeone = noticeService.selectNoticeone(noticeNo);
		
		//모델값 전달
		model.addAttribute("noticeone", noticeone);
		
//		System.out.println(noticeone);
		
		return "/admin/notice/detail";
	}	
	
	//공지사항 글쓰기 jsp 
	@RequestMapping("/write")
	public String  noticeWrite(Model model) {
//		System.out.println("/admin/notice/write");
		return "community/notice/add";
	}	
	
	@PostMapping(value="/add")
	public String insertNotice(Model model,
			HttpServletRequest request
			, @SessionAttribute("loginData") AccountsDTO acDto
			,@ModelAttribute CommunityNoticeVO communityNoticevo) {
		CommunityNoticeDTO data = new CommunityNoticeDTO();
		
		data.setNotice_title(communityNoticevo.getNotice_title());
		data.setNotice_content(communityNoticevo.getNotice_content());
		
		int id = noticeService.insertNotice(data);
		
		if(acDto.getAc_index() == 30) {
			if(id != -1) {
				return "redirect:/community/notice/detail?no=" + id;		
			} else {
				request.setAttribute("error", "게시글 저장 실패!");
				return "community/notice/add";
			} 
		}
		model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
		return "error/permission";
		
	}
	
//	//공지사항 글수정하기 jsp
//	@RequestMapping("/modify")
//	public String  updateNotice(Model model,@RequestParam int noticeNo) {
//		//글 가지고오기
//		CommunityNoticeDTO noticeone = noticeService.selectNoticeone(noticeNo);
//		
//		//모델값 전달
//		model.addAttribute("noticeone", noticeone);
//		
//		//수정jsp
//		if(data != null) {
//			if(acDto.getAc_index() == 30)
//				model.addAttribute("data", data);
//				return "community/notice/modify";
//			} else {
//				model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
//				return "error/permission";
//			}
//		} else {
//			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
//			return "error/notExists";
//		}
//	}

//	
//	//공지사항 글수정하기 
//	@RequestMapping("/modify")
//	public String  noticeModify(Model model,@RequestParam int noticeNo
//			,@ModelAttribute CommunityNoticeDTO notice) {
////		System.out.println("/admin/notice/modify");
////		System.out.println("noticeNo"+noticeNo);
////		System.out.println("notice"+notice);
//		
//		notice.setNotice_no(noticeNo);
//		
//		//글 수정하기
//		int res = noticeService.updateNotice(notice);
//		
//		//수정완료
//		return "redirect:/community/notice/detail?no=" + noticeNo;
//	}
	
	@GetMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, @RequestParam int id) {
		CommunityNoticeDTO data = noticeService.selectNoticeone(id);
		
		if(data != null) {
			if(acDto.getAc_index() == 30) {
				model.addAttribute("data", data);
				return "community/notice/modify";
			} else {
				model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
				return "error/permission";
			}
		} else {
			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
			return "error/notExists";
		}
	}
	
	@PostMapping(value="/modify")
	public String updateNotice(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, @ModelAttribute CommunityNoticeVO communityNoticevo) {
		CommunityNoticeDTO data = noticeService.selectNoticeone(communityNoticevo.getNotice_no());
		
		if(data != null) {
			if(acDto.getAc_index() == 30) {
				data.setNotice_title(communityNoticevo.getNotice_title());
				data.setNotice_content(communityNoticevo.getNotice_content());
				boolean result = (noticeService.updateNotice(data) != 0);
				if(result) {
					return "redirect:/community/notice/detail?no=" + data.getNotice_no();
				} else {
					return modify(model, acDto, communityNoticevo.getNotice_no());
				}
			} else {
				model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
				return "error/permission";
			}
		} else {
			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
			return "error/notExists";
		}
	}
	
	
	@PostMapping(value="/delete", produces="application/json; charset=utf-8")
	@ResponseBody
	public String deleteNotice(@SessionAttribute("loginData") AccountsDTO acDto
			, @RequestParam int id) {
		System.out.println("CommunityNoticeDTO data값 : " + id);
		CommunityNoticeDTO data = noticeService.selectNoticeone(id);
		System.out.println("CommunityNoticeDTO data값 : " + data);
		JSONObject json = new JSONObject();
		
		if(data == null) {
			// 삭제할 데이터 없음
			json.put("code", "notExists");
			json.put("message", "이미 삭제 된 데이터 입니다.");
		} else {
			if(acDto.getAc_index() == 30) {
				// 작성자, 수정자 동일인
				boolean result = (noticeService.deleteNotice(id) != 0);
				if(result) {
					json.put("code", "success");
					json.put("message", "삭제가 완료되었습니다.");
				} else {
					// 삭제 실패
					json.put("code", "fail");
					json.put("message", "삭제 작업 중 문제가 발생하였습니다.");
				}
			} else {
				// 작성자, 수정자 동일인 아님 - 권한 없음
				json.put("code", "permissionError");
				json.put("message", "삭제 할 권한이 없습니다.");
			}
		}
		
		return json.toJSONString();
	}
	
}

