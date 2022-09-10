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
import com.myweb.home.upload.model.FileUploadDTO;
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
			session.setAttribute("pageCount", 8);
		}
		
		if(pageCount > 0) {
			session.setAttribute("pageCount", pageCount);
		}
		else {
			pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
		}
		
		Paging paging = new Paging(datas, page, pageCount);
		
		model.addAttribute("datas", paging.getPageData());
		model.addAttribute("pageData", paging);
		
		return "selItem/list";
	}
	
//	@PostMapping(value="/add")
//	public String add(HttpServletRequest request
//			, @SessionAttribute("loginData") AccountsDTO empDto
//			, @ModelAttribute BoardVO boardVo
//			, @RequestParam("fileUpload") MultipartFile[] files) {
//		SelItemDTO data = new SelItemDTO();
//		data.setTitle(boardVo.getTitle());
//		data.setContent(boardVo.getContent());
//		data.setEmpId(empDto.getEmpId());
//		
//		int id = service.add(data);
//		
//		for(MultipartFile file: files) {
//			String location = request.getServletContext().getRealPath("/resources/board/upload");
//			String url = "/static/board/upload";
//			FileUploadDTO fileData = new FileUploadDTO(id, location, url);
//			
//			try {
//				int fileResult = FileUploadService.upload(file, fileData);
//				if(fileResult == -1) {
//					request.setAttribute("error", "파일 업로드 수량을 초과하였습니다.");
//					return "board/add";
//				}
//			} catch(Exception e) {
//				request.setAttribute("error", "파일 업로드 작업중 예상치 못한 에러가 발생하였습니다.");
//				return "board/add";
//			}
//			
//		}
//
//	@GetMapping
//	public String items(Model model) {
//		List<> items = 
//	}
//	
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
