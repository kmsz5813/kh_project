package com.myweb.home.community.life.comment.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.community.life.comment.model.LifeCommentDTO;
import com.myweb.home.community.life.comment.service.LifeCommentService;


@Controller
@RequestMapping(value="/community/life")
public class LifeCommentController {
	
	@Autowired
	private LifeCommentService service;
	
	@PostMapping(value="/comment/add")
	public String add(HttpServletRequest request
			, @SessionAttribute("loginData") AccountsDTO acDto
			, @ModelAttribute LifeCommentDTO lifeCommentDTO) {
		LifeCommentDTO data = new LifeCommentDTO();
		String content = request.getParameter("content");
		int bid = Integer.parseInt(request.getParameter("bid"));
		data.setComment_bId(bid);
		data.setComment_Content(content);
		data.setUser_Name(acDto.getAc_name());
		
		int id = service.add(data);
		
		if( id != -1) {
			return "redirect:/community/life/detail?id=" + data.getComment_bId();
		} else {
			request.setAttribute("error", "댓글 저장 실패!");
			return "redirect:/community/life/detail?id=" + id;
		}
	
	}
	
	@PostMapping(value="/comment/modify", produces="application/json; charset=utf-8")
	@ResponseBody
	public String modify(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, @RequestParam int id
			, @RequestParam String content) {
		
		JSONObject json = new JSONObject();
		LifeCommentDTO data = service.getData(id);
		
		if(data != null) {
			if(data.getUser_Name().equals(acDto.getAc_name())) {
				data.setComment_Content(content);
				boolean result = service.modify(data);
				
				if(result) {
					json.put("success", "success");
					json.put("code", "success");
				}
					
				} 
			} 
		
		return json.toJSONString();
	}
				
//				if(result) {
//					return "redirect:/community/life/detail?id=" + data.getComment_Id();
//				} else {
//					return "redirect:/community/life/detail?id=" + data.getComment_Id();
//				}
//			} else {
//				model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
//				return "error/permission";
//			}
//		} else {
//			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
//			return "error/notExists";
//		}
//	}
	
	@PostMapping(value="/comment/delete", produces="application/json; charset=utf-8")
	@ResponseBody
	public String delete(@SessionAttribute("loginData") AccountsDTO acDto
			, @RequestParam int id) {
		LifeCommentDTO data = service.getData(id);
		
		JSONObject json = new JSONObject();
		
		if(data == null) {
			// 삭제할 데이터 없음
			json.put("code", "notExists");
			json.put("message", "이미 삭제 된 데이터 입니다.");
		} else {
			if(data.getUser_Name().equals(acDto.getAc_name())) {
				// 작성자, 수정자 동일인
				boolean result = service.remove(data);
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

