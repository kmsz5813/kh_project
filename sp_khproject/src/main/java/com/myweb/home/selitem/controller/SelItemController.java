package com.myweb.home.selitem.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
			, MultipartHttpServletRequest mtfRequest) {

		SelItemDTO data = new SelItemDTO();
		 
		// jsp에서 값을 받아오는

		String title = request.getParameter("title");
	    String service1 = request.getParameter("field");
	    String location = request.getParameter("location");
	    String content = request.getParameter("description");
		System.out.println(service1);
		System.out.println(title);

		System.out.println(location);

		System.out.println(content);
		data.setSel_title(title);
		data.setSel_field(service1);
		data.setSel_location(location);
		data.setSel_content(content);
		data.setSel_name(acData.getAc_name());
	
		System.out.println(data.getSel_field());
		System.out.println(data.getSel_name());
		System.out.println(data);
		
		// 이미지 다수 업로드
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		String path = request.getServletContext().getRealPath("/resources/img/item/") + acData.getAc_name() + ".png";
		
		for (MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
            long fileSize = mf.getSize(); // 파일 사이즈
            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);
            String safeFile = path + System.currentTimeMillis() + originFileName;
            try {
                mf.transferTo(new File(safeFile));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
		boolean result = service.add(data);
		
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

		System.out.println("search값 :" + search);
		//저장되어 있는 모든 데이터 값 가져오기...	
		SelItemDTO data = new SelItemDTO();
		List result = service.getData(data);
		
		//특정되어있는 값 가져오기
		
	
		String	selectData = request.getParameter("select"); // <<<<<<< 주소값
			System.out.println(selectData);

		
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
	
}