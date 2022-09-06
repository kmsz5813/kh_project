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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.home.common.Paging;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.service.SelItemService;

@Controller
@RequestMapping(value="/selItem")
public class SelItemController {

//	@Autowired
//	private SelItemService service;
//	
//	 차후 상품등록 추가예정
//	@Autowired
//	private ProductUploadService 
//	
//	@RequestMapping(value="", method=RequestMethod.GET)
//	public String getList(Model model, HttpSession session
//			, @RequestParam(defaultValue="1", required=false) int page
//			, @RequestParam(defaultValue="0", required=false) int pageCount) {
//		List datas = service.getAll();
//		
//		if(session.getAttribute("pageCount") == null) {
//			session.setAttribute("pageCount", 5);
//		}
//		
//		if(pageCount > 0) {
//			session.setAttribute("pageCount", pageCount);
//		}
//		
//		pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
//		Paging paging = new Paging(datas, page, pageCount);
//		
//		model.addAttribute("dataas", paging.getPageData());
//		model.addAttribute("pageData", paging);
//		
//		return "selItem/list";
//	}
//	
//	@GetMapping(value="/detail")
//	public String getDetail(Model model
//			, HttpSession session
//			, @RequestParam int id) {
//		SelItemDTO data = service.getData(id);
//		
//		return "selItem/detail";
//	}
	
}
