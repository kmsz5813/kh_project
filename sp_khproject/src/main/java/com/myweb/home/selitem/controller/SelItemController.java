package com.myweb.home.selitem.controller;

import java.io.File;
import java.util.List;

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
import com.myweb.home.common.Paging;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.service.SelItemService;
import com.myweb.home.upload.service.FileUploadService;

@Controller
@RequestMapping(value="/selItem")
public class SelItemController {

	@Autowired
	private SelItemService service;
	
//	 차후 상품등록 추가예정
//	@Autowired
//	private ProductUploadService
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getList(Model model, HttpSession session
			, @RequestParam(defaultValue="1", required=false) int page
			, @RequestParam(defaultValue="0", required=false) int pageCount) {
		List datas = service.getAll();
		
		if(session.getAttribute("pageCount") == null) {
			session.setAttribute("pageCount", 5);
		}
		
		if(pageCount > 0) {
			session.setAttribute("pageCount", pageCount);
		}
		
		pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
		Paging paging = new Paging(datas, page, pageCount);
		
		model.addAttribute("dataas", paging.getPageData());
		model.addAttribute("pageData", paging);
		
		return "selItem/list";
	}
	
//	@GetMapping(value="/detail")
//	public String getDetail(Model model
//			, HttpSession session
//			, @RequestParam int id) {
//		SelItemDTO data = service.getData(id);
//		List<SelItemDTO> filesDatas = FileUploadService.getDatas(id);
//		
//		if(data != null) {
//			service.incViewCnt(session, data);
//			model.addAttribute("data", data);
//			model.addAttribute("filesDatas", filesDatas);
//			return "selItem/detail";
//		} else {
//			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
//			return "error/notExists";
//		}
//		
//	}
//	
//	@GetMapping(value="/add")
//	public String add(HttpServletRequest request
//			, @SessionAttribute("loginData") AccountsDTO accDto
//			, @ModelAttribute )
	
}
