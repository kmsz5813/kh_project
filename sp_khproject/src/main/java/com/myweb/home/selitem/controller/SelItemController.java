package com.myweb.home.selitem.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.io.Files;
import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.common.Paging;
import com.myweb.home.login.service.LoginService;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.service.SelItemService;
import com.myweb.home.upload.model.FileUploadDTO;
import com.myweb.home.upload.service.FileUploadService;

@Controller
@RequestMapping(value="/sellitem")
public class SelItemController {
	private static String CURR_IMAGE_REPO_PATH = null;
			
	@Autowired
	private SelItemService service;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private LoginService loginService;
	
//	@Autowired
//	private FileUploadService fileUploadService;
	@GetMapping(value="/additem")
	public String additem(Model model
		, @SessionAttribute("loginData") AccountsDTO acData) {
		System.out.println(acData);
		System.out.println(acData.getAc_name());
		String test = "김민성";
		model.addAttribute("test", test);
		return "/sellitem/additem";
	}
	
	@PostMapping(value="/additem")
	public String additem(Model model, HttpServletRequest request
			,@SessionAttribute("loginData") AccountsDTO acData
			, MultipartHttpServletRequest mtfRequest
			, @RequestParam("fileUpload") MultipartFile[] files) throws Exception {

		SelItemDTO data = new SelItemDTO();
		 
		// jsp에서 값을 받아오는


		String title = request.getParameter("title");
	    String service1 = request.getParameter("field");
	    String location = request.getParameter("location");
	    String content = request.getParameter("content");
	  

		data.setSel_title(title);
		data.setSel_field(service1);
		data.setSel_location(location);
		data.setSel_content(content);
		data.setSel_name(acData.getAc_name());
		
		
			
			
		boolean result = service.add(data);
		//해서만들어진 게시물번호값을 불러오기.
		System.out.println(data.getSel_id());
		
		//썸네일 이미지 저장하는것
		for(MultipartFile file: files) {
			String location1 = request.getServletContext().getRealPath("/resources/img/board");
			String url = "/static/img/board"; 
			FileUploadDTO fileData = new FileUploadDTO(data.getSel_id(), location1, url);
			System.out.println(fileData);
			
			int fileResult = fileUploadService.upload(file, fileData);

		}
		
		
		
		if(result) {
			return "redirect:/sellitem";
		}else {
			return "sellitem/additem";
		}
	}
	


	@GetMapping(value="")
	public String list(Model model, HttpServletRequest request
			, HttpSession session
			, @RequestParam(defaultValue="1", required=false) int page
			, @RequestParam(defaultValue="0", required=false) int pageCount) {
		Paging paging = null;
		
		
		//검색으로 조회
		String search = request.getParameter("search");
		List serachData = service.getSearch(search);
		
		//저장되어 있는 모든 데이터 값 가져오기...	
		SelItemDTO data = new SelItemDTO();
		List result = service.getData(data);
	
		//SEL_FIELD로 추적
		String selectData = request.getParameter("select"); // <<<<<<< 주소값
		List seletResult = service.getSelect(selectData);
		
		if(session.getAttribute("pageCount") == null) {
			session.setAttribute("pageCount", 8);
		}
		
		if(pageCount > 0) {
			session.setAttribute("pageCount", pageCount);
		}
	
		pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
		
		if(selectData != null) {
			//SEL_FIELD로추적
			paging = new Paging(seletResult, page, pageCount);
			model.addAttribute("selectData", "select=" + selectData);
			
		}else if(search != null){
			//검색으로 조회
			paging = new Paging(serachData, page, pageCount);
			model.addAttribute("selectData", "search=" + search);
		}
		else {
			paging = new Paging(result, page, pageCount);
		}
		
		model.addAttribute("result", paging.getPageData());
		model.addAttribute("pageData", paging);

		
		// 로그인세션 존재유무 (전문가만 등록버튼 구현하기위해서)
		// ??????
		
		return "/sellitem/list";
	}
	

	@GetMapping(value="/itemdetail")
	public String detail(Model model, HttpServletRequest request
			, @SessionAttribute("loginData") AccountsDTO acData) {
		// 판매자 닉네임 가져오기

		String name = request.getParameter("search");


		AccountsDTO data = loginService.nameCheck(name);
		//아이템 번호도 가져와야됨
		int itemid = Integer.parseInt(request.getParameter("itemid"));
		SelItemDTO itemdata = service.getData(itemid);


		

		request.setAttribute("data", data);
		request.setAttribute("itemdata", itemdata);
		request.setAttribute("loginData", acData);
		return "sellitem/itemdetail";
	}
	
//	@GetMapping(value="/itemmodify")
//	public String itemmodify(Model model
//			, HttpServletRequest request
//			, @SessionAttribute("loginData") AccountsDTO acDto) {
//		request.setAttribute("", acDto)
//	}
	
}