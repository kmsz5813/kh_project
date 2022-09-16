package com.myweb.home.selitem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myweb.home.common.util.Paging;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.service.SelItemService;

@Controller
@RequestMapping(value="/sellitem")
public class SelItemController {
	
	@Autowired
	private SelItemService service;
	
	//getmapping을 해야지 페이지가 나온다고 생각하면돼 
	@GetMapping(value="/additem")
	public String additem(Model model) {
		
		return "/sellitem/additem";
	}
	
	@PostMapping(value="/additem")
	public String additem(Model model, HttpServletRequest request) {
		
		String test = request.getParameter("test");
		
		System.out.println(test);
		
		
		//등록 로직 부분을 짜면 됨.
		
		return "redirect: /home/sellitem/additem";
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
		
		//특정되어있는 값 가져오기
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
		
		
		return "/sellitem/list";
	}

	

	
	
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
