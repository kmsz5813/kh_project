package com.myweb.home.community.controller;

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
import com.myweb.home.common.util.Paging;
import com.myweb.home.community.model.CommunityFindStuDTO;
import com.myweb.home.community.service.CommunityFindStuService;
import com.myweb.home.community.vo.CommunityFindStuVO;
import com.myweb.home.upload.model.FileUploadDTO;
import com.myweb.home.upload.service.FileUploadService;


@Controller
@RequestMapping(value="/community/findStu")
public class CommunityFindStuController {
	
	@Autowired
	private CommunityFindStuService service;
	
	@Autowired
	private FileUploadService fileUploadService;
	
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
		
		model.addAttribute("datas", paging.getPageData());
		model.addAttribute("pageData", paging);
		
		return "community/findStu";
	}
	
	@GetMapping(value="/detail")
	public String getDetail(Model model
			, HttpSession session
			, @RequestParam int id) {
		CommunityFindStuDTO data = service.getData(id);
		List<FileUploadDTO> fileDatas = fileUploadService.getDatas(id);
		
		if(data != null) {
			service.incViewCnt(session, data);
			model.addAttribute("data", data);
			model.addAttribute("fileDatas", fileDatas);
			return "findStu/detail";
		} else {
			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
			return "error/notExists";
		}
	}
	
	@GetMapping(value="/add")
	public String add() {
		return "findStu/add";
	}
	
	@PostMapping(value="/add")
	public String add(HttpServletRequest request
			, @SessionAttribute("loginData") AccountsDTO acDto
			, @ModelAttribute CommunityFindStuVO communityFindStuVo
			, @RequestParam("fileUpload") MultipartFile[] files) {
		CommunityFindStuDTO data = new CommunityFindStuDTO();
		data.setFindStu_Title(communityFindStuVo.getFindStu_title());
		data.setFindStu_Content(communityFindStuVo.getFindStu_content());
		data.setUser_Name(acDto.getAc_name());
		
		int id = service.add(data);
		
		for(MultipartFile file: files) {
			String location = request.getServletContext().getRealPath("/resources/community/findStu/upload");
			String url = "/static/community/findStu/upload";
			FileUploadDTO fileData = new FileUploadDTO(id, location, url);
			
			try {
				int fileResult = fileUploadService.upload(file, fileData);
				if(fileResult == -1) {
					request.setAttribute("error", "파일 업로드 수량을 초과하였습니다.");
					return "community/findStu/add";
				}
			} catch(Exception e) {
				request.setAttribute("error", "파일 업로드 작업중 예상치 못한 에러가 발생하였습니다.");
				return "community/findStu/add";
			}
			
		}
		
		if(id != -1) {
			return "redirect:/community/findStu/detail?id=" + id;			
		} else {
			request.setAttribute("error", "게시글 저장 실패!");
			return "community/findStu/add";
		}
	}
	
	@GetMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, @RequestParam int id) {
		CommunityFindStuDTO data = service.getData(id);
		List<FileUploadDTO> fileDatas = fileUploadService.getDatas(id);
		
		if(data != null) {
			if(data.getUser_Name() == acDto.getAc_name()) {
				model.addAttribute("data", data);
				model.addAttribute("fileDatas", fileDatas);
				return "community/findStu/modify";
			} else {
				model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
				return "error/permission";
			}
		} else {
			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
			return "error/noExists";
		}
	}
	
	@PostMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, @ModelAttribute CommunityFindStuVO communityFindStuVo) {
		CommunityFindStuDTO data = service.getData(communityFindStuVo.getFindStu_id());
		
		if(data != null) {
			if(data.getUser_Name() == acDto.getAc_name()) {
				data.setFindStu_Title(communityFindStuVo.getFindStu_title());
				data.setFindStu_Content(communityFindStuVo.getFindStu_content());
				boolean result = service.modify(data);
				if(result) {
					return "redirect:/community/findStu/detail?id=" + data.getUser_Name();
				} else {
					return modify(model, acDto, communityFindStuVo.getFindStu_id());
				}
			} else {
				model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
				return "error/permission";
			}
		} else {
			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
			return "error/noExists";
		}
	}
	
	@PostMapping(value="/delete", produces="application/json; charset=utf-8")
	@ResponseBody
	public String delete(@SessionAttribute("loginData") AccountsDTO acDto
			, @RequestParam int id) {
		CommunityFindStuDTO data = service.getData(id);
		
		JSONObject json = new JSONObject();
		
		if(data == null) {
			// 삭제할 데이터 없음
			json.put("code", "notExists");
			json.put("message", "이미 삭제 된 데이터 입니다.");
		} else {
			if(data.getUser_Name() == acDto.getAc_name()) {
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
	
	@PostMapping(value="/like", produces="application/json; charset=utf-8")
	@ResponseBody
	public String like(HttpSession session
			, @RequestParam int id) {
		CommunityFindStuDTO data = service.getData(id);
		JSONObject json = new JSONObject();
		
		if(data == null) {
			// 존재하지 않음.
			json.put("code", "noData");
			json.put("message", "해당 데이터가 존재하지 않습니다.");
		} else {
			service.incLike(session, data);
			json.put("code", "success");
			json.put("like", data.getFindStu_like());
		}
		return json.toJSONString();
	}
	

}
