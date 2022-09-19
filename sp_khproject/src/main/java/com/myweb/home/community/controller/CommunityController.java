//package com.myweb.home.community.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.json.simple.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttribute;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.myweb.home.Accounts.model.AccountsDTO;
//import com.myweb.home.common.util.Paging;
//import com.myweb.home.community.model.CommunityDTO;
//import com.myweb.home.community.service.CommunityService;
//import com.myweb.home.community.vo.CommunityVO;
//import com.myweb.home.upload.model.FileUploadDTO;
//import com.myweb.home.upload.service.FileUploadService;
//
//@Controller
//@RequestMapping(value="/community")
//public class CommunityController {
//	
//	@Autowired
//	private CommunityService service;
//	
//	@Autowired
//	private FileUploadService fileUploadService;
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
//		model.addAttribute("datas", paging.getPageData());
//		model.addAttribute("pageData", paging);
//		
//		return "community/list";
//	}
//	
//	@GetMapping(value="/detail")
//	public String getDetail(Model model
//			, HttpSession session
//			, @RequestParam int cum_id) {
//		CommunityDTO data = service.getData(cum_id);
//		List<FileUploadDTO> fileDatas = fileUploadService.getDatas(cum_id);
//		
//		if(data != null) {
//			service.incViewCnt(session, data);
//			model.addAttribute("data", data);
//			model.addAttribute("fileDatas", fileDatas);
//			return "community/detail";
//		} else {
//			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
//			return "error/notExists";
//		}
//	}
//	
//	@GetMapping(value="/add")
//	public String add() {
//		return "community/add";
//	}
//	
//	@PostMapping(value="/add")
//	public String add(HttpServletRequest request
//			, @SessionAttribute("loginData") AccountsDTO accountsDto
//			, @ModelAttribute CommunityVO communityVo
//			, @RequestParam("fileUpload") MultipartFile[] files) {
//		CommunityDTO data = new CommunityDTO();
//		data.setCum_title(communityVo.getCum_title());
//		data.setCum_content(communityVo.getCum_content());
//		data.setCum_name(accountsDto.getAc_name());
//
//		
//		int id = service.add(data);
//		
//		for(MultipartFile file: files) {
//			String location = request.getServletContext().getRealPath("/resources/community/upload");
//			String url = "/static/community/upload";
//			FileUploadDTO fileData = new FileUploadDTO(id, location, url);
//			
//			try {
//				int fileResult = fileUploadService.upload(file, fileData);
//				if(fileResult == -1) {
//					request.setAttribute("error", "파일 업로드 수량을 초과하였습니다.");
//					return "community/add";
//				}
//			} catch(Exception e) {
//				request.setAttribute("error", "파일 업로드 작업중 예상치 못한 에러가 발생하였습니다.");
//				return "community/add";
//			}
//			
//		}
//		
//		if(id != -1) {
//			return "redirect:/community/detail?cum_id=" + id;			
//		} else {
//			request.setAttribute("error", "게시글 저장 실패!");
//			return "community/add";
//		}
//	}
//	
//	@GetMapping(value="/modify")
//	public String modify(Model model
//			, @SessionAttribute("loginData") AccountsDTO accountsDto
//			, @RequestParam int cum_id) {
//		CommunityDTO data = service.getData(cum_id);
//		List<FileUploadDTO> fileDatas = fileUploadService.getDatas(cum_id);
//		
//		if(data != null) {
//			if(data.getCum_name() == accountsDto.getAc_name()) {
//				model.addAttribute("data", data);
//				model.addAttribute("fileDatas", fileDatas);
//				return "community/modify";
//			} else {
//				model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
//				return "error/permission";
//			}
//		} else {
//			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
//			return "error/noExists";
//		}
//	}
//	
//	@PostMapping(value="/modify")
//	public String modify(Model model
//			, @SessionAttribute("loginData") AccountsDTO accountsDto
//			, @ModelAttribute CommunityVO communityVo) {
//		CommunityDTO data = service.getData(communityVo.getCum_id());
//		
//		if(data != null) {
//			if(data.getCum_name() == accountsDto.getAc_name()) {
//				data.setCum_title(communityVo.getCum_title());
//				data.setCum_content(communityVo.getCum_content());
//				boolean result = service.modify(data);
//				if(result) {
//					return "redirect:/community/detail?id=" + data.getCum_name();
//				} else {
//					return modify(model, accountsDto, communityVo.getCum_id());
//				}
//			} else {
//				model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
//				return "error/permission";
//			}
//		} else {
//			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
//			return "error/noExists";
//		}
//	}
//	
//	@PostMapping(value="/delete", produces="application/json; charset=utf-8")
//	@ResponseBody
//	public String delete(@SessionAttribute("loginData") AccountsDTO accountsDto
//			, @RequestParam int cum_id) {
//		CommunityDTO data = service.getData(cum_id);
//		
//		JSONObject json = new JSONObject();
//		
//		if(data == null) {
//			// 삭제할 데이터 없음
//			json.put("code", "notExists");
//			json.put("message", "이미 삭제 된 데이터 입니다.");
//		} else {
//			if(data.getCum_name() == accountsDto.getAc_name()) {
//				// 작성자, 수정자 동일인
//				boolean result = service.remove(data);
//				if(result) {
//					json.put("code", "success");
//					json.put("message", "삭제가 완료되었습니다.");
//				} else {
//					// 삭제 실패
//					json.put("code", "fail");
//					json.put("message", "삭제 작업 중 문제가 발생하였습니다.");
//				}
//			} else {
//				// 작성자, 수정자 동일인 아님 - 권한 없음
//				json.put("code", "permissionError");
//				json.put("message", "삭제 할 권한이 없습니다.");
//			}
//		}
//		
//		return json.toJSONString();
//	}
//	
//	@PostMapping(value="/like", produces="application/json; charset=utf-8")
//	@ResponseBody
//	public String like(HttpSession session
//			, @RequestParam int cum_id) {
//		CommunityDTO data = service.getData(cum_id);
//		JSONObject json = new JSONObject();
//		
//		if(data == null) {
//			// 존재하지 않음.
//			json.put("code", "noData");
//			json.put("message", "해당 데이터가 존재하지 않습니다.");
//		} else {
//			service.incLike(session, data);
//			json.put("code", "success");
//			json.put("like", data.getCum_like());
//		}
//		return json.toJSONString();
//	}
//	
//}
