package com.myweb.home.community.question.controller;

import java.util.List;

import javax.servlet.annotation.MultipartConfig;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.common.util.Paging;
import com.myweb.home.community.question.comment.model.QuestionCommentDTO;
import com.myweb.home.community.question.comment.service.QuestionCommentService;
import com.myweb.home.community.question.model.CommunityQuestionDTO;
import com.myweb.home.community.question.service.CommunityQuestionService;
import com.myweb.home.community.question.vo.CommunityQuestionVO;
import com.myweb.home.upload.model.FileUploadDTO;
import com.myweb.home.upload.service.FileUploadService;


@Controller
@RequestMapping(value="/community/question")
@MultipartConfig
public class CommunityQuestionController {
	
	@Autowired
	private CommunityQuestionService service;
	
	@Autowired
	private QuestionCommentService commentService;
	
//	@Autowired
//	private FileUploadService fileUploadService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getList(Model model, HttpSession session
			, @RequestParam(defaultValue="1", required=false) int page
			, @RequestParam(defaultValue="0", required=false) int pageCount) {
		List datas = service.getAll();
		
//		if(session.getAttribute("pageCount") == null) {
//			session.setAttribute("pageCount", 5);
//		}
		
		session.setAttribute("pageCount", 10); //10?????? ???????????? ????????? ?????????.
		
		if(pageCount > 0) {
			session.setAttribute("pageCount", pageCount);
		}
		
		pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
		Paging paging = new Paging(datas, page, pageCount);
		
		model.addAttribute("datas", paging.getPageData());
		model.addAttribute("pageData", paging);
		
		return "community/question/list";
	}
	
	@GetMapping(value="/detail")
	public String getDetail(Model model
			, HttpSession session
			, @RequestParam int id) {
		System.out.println("question_id " + ":" + id );
		CommunityQuestionDTO data = service.getData(id);
//		List<FileUploadDTO> fileDatas = fileUploadService.getDatas(id);
		
		//id?????? ?????? ????????? ?????? 
		List<QuestionCommentDTO> datas = commentService.getDatas(id);
		
		System.out.println(datas);
		if(data != null) {
			service.incViewCnt(session, data);
			model.addAttribute("data", data);
			model.addAttribute("datas", datas);
//			model.addAttribute("fileDatas", fileDatas);
			return "community/question/detail";
		} else {
			model.addAttribute("error", "?????? ???????????? ???????????? ????????????.");
			return "error/notExists";
		}
	}
	
	@GetMapping(value="/add")
	public String add() {
		return "community/question/add";
	}
	
	
	
	@PostMapping(value="/add")
	public String add(HttpServletRequest request
			, @SessionAttribute("loginData") AccountsDTO acDto
			, @ModelAttribute CommunityQuestionVO communityQuestionVo) {
//			, @RequestParam("fileUpload") MultipartFile[] files) {
		CommunityQuestionDTO data = new CommunityQuestionDTO();
		data.setQuestion_Title(communityQuestionVo.getQuestion_title());
		data.setQuestion_Content(communityQuestionVo.getQuestion_content());
		data.setUser_Name(acDto.getAc_name());
		
		int id = service.add(data);
			
		if(id != -1) {
			return "redirect:/community/question/detail?id=" + id;			
		} else {
			request.setAttribute("error", "????????? ?????? ??????!");
			return "community/question/add";
		} 
	}
	

	@GetMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, @RequestParam int id) {
		CommunityQuestionDTO data = service.getData(id);
//		List<FileUploadDTO> fileDatas = fileUploadService.getDatas(id);
		
		if(data != null) {
			if(data.getUser_Name().equals(acDto.getAc_name()) ) {
				model.addAttribute("data", data);
//				model.addAttribute("fileDatas", fileDatas);
				return "community/question/modify";
			} else {
				model.addAttribute("error", "?????? ????????? ????????? ????????? ????????????.");
				return "error/permission";
			}
		} else {
			model.addAttribute("error", "?????? ???????????? ???????????? ????????????.");
			return "error/notExists";
		}
	}
	
	@PostMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, @ModelAttribute CommunityQuestionVO communityQuestionVo) {
		//System.out.println(" vo : " + communityQuestionVo);
		CommunityQuestionDTO data = service.getData(communityQuestionVo.getQuestion_id());
		
		if(data != null) {
			if(data.getUser_Name().equals(acDto.getAc_name())) {
				data.setQuestion_Title(communityQuestionVo.getQuestion_title());
				data.setQuestion_Content(communityQuestionVo.getQuestion_content());
				boolean result = service.modify(data);
				if(result) {
					return "redirect:/community/question/detail?id=" + data.getQuestion_Id();
				} else {
					return modify(model, acDto, communityQuestionVo.getQuestion_id());
				}
			} else {
				model.addAttribute("error", "?????? ????????? ????????? ????????? ????????????.");
				return "error/permission";
			}
		} else {
			model.addAttribute("error", "?????? ???????????? ???????????? ????????????.");
			return "error/notExists";
		}
	}
	
	@PostMapping(value="/delete", produces="application/json; charset=utf-8")
	@ResponseBody
	public String delete(@SessionAttribute("loginData") AccountsDTO acDto
			, @RequestParam int id) {
		//System.out.println("question_id " + ":" + id );
		CommunityQuestionDTO data = service.getData(id);
		
		JSONObject json = new JSONObject();
		
		if(data == null) {
			// ????????? ????????? ??????
			json.put("code", "notExists");
			json.put("message", "?????? ?????? ??? ????????? ?????????.");
		} else {
			if(data.getUser_Name().equals(acDto.getAc_name())) {
				// ?????????, ????????? ?????????
				boolean result = service.remove(data);
				if(result) {
					json.put("code", "success");
					json.put("message", "????????? ?????????????????????.");
				} else {
					// ?????? ??????
					json.put("code", "fail");
					json.put("message", "?????? ?????? ??? ????????? ?????????????????????.");
				}
			} else {
				// ?????????, ????????? ????????? ?????? - ?????? ??????
				json.put("code", "permissionError");
				json.put("message", "?????? ??? ????????? ????????????.");
			}
		}
		
		return json.toJSONString();
	}
	
	@PostMapping(value="/like", produces="application/json; charset=utf-8")
	@ResponseBody
	public String like(HttpSession session
			, @RequestParam int id) {
		CommunityQuestionDTO data = service.getData(id);
		JSONObject json = new JSONObject();
		
		if(data == null) {
			// ???????????? ??????.
			json.put("code", "noData");
			json.put("message", "?????? ???????????? ???????????? ????????????.");
		} else {
			service.incLike(session, data);
			json.put("code", "success");
			json.put("like", data.getQuestion_like());
		}
		return json.toJSONString();
	}
	

}
