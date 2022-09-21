package com.myweb.home.selitem.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.common.Paging;
import com.myweb.home.login.service.LoginService;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.service.SelItemService;
import com.myweb.home.selitem.vo.BoardVO;
import com.myweb.home.upload.model.FileUploadDTO;
import com.myweb.home.upload.service.FileUploadService;

@Controller
@RequestMapping(value="/sellitem")
public class SelItemController {
	
	@Autowired
	private SelItemService service;
	
	@Autowired
	private LoginService service2;
	
//	@Autowired
//	private FileUploadService fileUploadService;
	@GetMapping(value="/additem")
	public String additem(Model model, @SessionAttribute("loginData") AccountsDTO acData) {
		System.out.println(acData);
		System.out.println(acData.getAc_name());
		return "/sellitem/additem";
	}
	
	@PostMapping(value="/additem")
	public String additem(Model model, HttpServletRequest request
			,@SessionAttribute("loginData") AccountsDTO acData
			) {
		SelItemDTO data = new SelItemDTO();
		
		// jsp에서 값을 받아오는
	   String service1 = request.getParameter("service1");
	   String location = request.getParameter("location");
	   String description = request.getParameter("description");
		System.out.println(service1);
		System.out.println(location);
		System.out.println(description);
		data.setSel_interest(service1);
		data.setSel_location(location);
		data.setSel_content(description);
		data.setSel_name(acData.getAc_name());
	
		System.out.println(data.getSel_interest());
		System.out.println(data.getSel_name());
		System.out.println(data);
		
		boolean result = service.add(data);
		
		if(result) {
			return "sellitem/list";
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
		List serachData = null;
		//검색으로 조회
		String search = request.getParameter("search");
		if(search != null) {
			serachData = service.getSearch(search);
		}

		//저장되어 있는 모든 데이터 값 가져오기...	
		SelItemDTO data = new SelItemDTO();
		List result = service.getData(data);
		
		//특정되어있는 값 가져오기
		String selectData = null;
		if(selectData != null) {			
			selectData = request.getParameter("select"); // <<<<<<< 주소값
		}
		
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
	
	@GetMapping(value="/gosudetail")
	public String detail(Model model, HttpServletRequest request) {
		String name = request.getParameter("search");
		AccountsDTO data = service2.nameCheck(name);
		request.setAttribute("data", data);
		
		return "detail/detail";
	}
	
}