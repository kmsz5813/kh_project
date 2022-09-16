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
import org.springframework.web.multipart.MultipartFile;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.common.Paging;
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
	private FileUploadService fileUploadService;

	@GetMapping(value="/additem")
	public String additem(Model model) {
		
		return "/sellitem/additem";
	}
	
	@PostMapping(value="/additem")
	public String additem(Model model, HttpServletRequest request
			, @SessionAttribute("loginData") AccountsDTO acDto
			, @ModelAttribute BoardVO boardVo
			, @RequestParam("fileUpload") MultipartFile[] files) {
		SelItemDTO data = new SelItemDTO();
		data.setSel_title(boardVo.getTitle());
		data.setSel_content(boardVo.getContent());
		
		int id = service.add(data);
		
		if(files != null) {
			
			for(MultipartFile file: files) {
				String location = request.getServletContext().getRealPath("/resources/board/upload");
				String url = "/static/board/upload";
				FileUploadDTO fileData = new FileUploadDTO(id, location, url);
				
				try {
					int fileResult = fileUploadService.upload(file, fileData);
					if(fileResult == -1) {
						request.setAttribute("error", "파일 업로드 수량을 초과하였습니다.");
						return "sellitem/additem";
					}
				} catch(Exception e) {
					request.setAttribute("error", "파일 업로드 작업중 예상치 못한 에러가 발생하였습니다.");
					return "sellitem/additem";
				}
				
			}
		}
		
		if(id != -1) {
			return "redirect:/sellitem/detail?id=" + id;			
		} else {
			request.setAttribute("error", "게시글 저장 실패!");
			return "sellitem/additem";
		}
	}
	
	@GetMapping(value="")
	public String list(Model model, HttpServletRequest request
			, HttpSession session
			, @RequestParam(defaultValue="1", required=false) int page
			, @RequestParam(defaultValue="0", required=false) int pageCount) {
		Paging paging = null;
		
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
			
		}else {
			paging = new Paging(result, page, pageCount);
		}
		
		model.addAttribute("result", paging.getPageData());
		model.addAttribute("pageData", paging);
		
		
		return "/sellitem/list";
	}
	
}
