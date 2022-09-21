package com.myweb.home.info.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.login.service.LoginService;
import com.myweb.home.purchase.model.PurchaseDTO;
import com.myweb.home.purchase.service.PurchaseService;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.service.SelItemService;


@Controller
@MultipartConfig
@RequestMapping(value="/info")
public class InfoController {
	private static final Logger logger = LoggerFactory.getLogger(InfoController.class);
	
	@Autowired
	private LoginService service;
	@Autowired
	private SelItemService selService;
	@Autowired
	private PurchaseService purchaseService;
	
	@GetMapping(value="")
	public String main(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, HttpServletRequest request) {
		
		logger.info("main({}) cusdata({})", model, acDto);
		
		// 프로필 이미지 이름은 서버에 이메일로 저장되므로
		request.setAttribute("profileImage", acDto.getAc_email());
		// 판매자의 판매글
		List<SelItemDTO> items = selService.getName(acDto.getAc_name());
		// 구매내역
		List<PurchaseDTO> purchaseData = purchaseService.getFromBuyerName(acDto.getAc_name());
		// 판매내역
		List<PurchaseDTO> sellData = purchaseService.getFromSellerName(acDto.getAc_name());
		System.out.println(sellData);
		request.setAttribute("items", items);
		request.setAttribute("purchaseData", purchaseData);
		request.setAttribute("sellData", sellData);
		
		
		
		return "info/info";
		
	}
	
	@GetMapping(value="/modifycheck")
	public String modifycheck(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto ) {
		
		return "info/modifycheck";
	}
	
	@PostMapping(value="/modifycheck")
	public String modifycheck(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, HttpServletRequest request) {
		

		acDto.getAc_email();
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		AccountsDTO data = new AccountsDTO();
		data.setAc_email(email);
		data.setAc_pw(pw);
		
		String email2 = acDto.getAc_email();
		String pw2 = acDto.getAc_pw();

		if(email.equals(email2) && pw.equals(pw2)) {
	        return "redirect:/info/modify";

	    } else {
        	request.setAttribute("errorMsg", false);
            return null;
        } 

	}
	

	@GetMapping(value="/deletecheck")
	public String deletecheck(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto ) {
		
		return "info/deletecheck";
	}
	
	@PostMapping(value="/deletecheck")
	public String deletecheck(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, HttpServletRequest request, HttpSession session) {
		
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		AccountsDTO data = new AccountsDTO();
		// 입력한 이메일, 비밀번호
		data.setAc_email(email);
		data.setAc_pw(pw);
		
		// 세션에 저장된 이메일, 비밀번호
		String email2 = acDto.getAc_email();
		String pw2 = acDto.getAc_pw();
		// 서버에 저장된 프로필 이미지파일 경로
		String path = request.getServletContext().getRealPath("/resources/img/profile/");
		File file = new File(path + "\\" + acDto.getAc_email() + ".png");
		
		if(email.equals(email2) && pw.equals(pw2)) { //이메일주소랑 비밀번호 체크 완료시	
			service.delete(data);
			file.delete();
			System.out.println("삭제 완료");
			session.invalidate();
			request.setAttribute("errorMsg", true);
			return "redirect:/main";
		} else {
			request.setAttribute("errorMsg", false);
			return null;
		}
		
	}
	
	
	@GetMapping(value="/modify")
	public String modify(Model model
			, HttpServletRequest request
			, @SessionAttribute("loginData") AccountsDTO acDto ) {
		request.setAttribute("profileImage", acDto.getAc_email());
		return "info/modify";
	}
	
	@PostMapping(value="/modify")
	public String modify(Model model
				, @SessionAttribute("loginData") AccountsDTO acDto
				, HttpServletRequest request
				, HttpSession session
				, @RequestParam("uploadImg") MultipartFile Part) throws IOException, ServletException {
		
		String mod_email = request.getParameter("mod_email");
		String mod_name = request.getParameter("mod_name");
		String mod_pw = request.getParameter("mod_pw");
		String mod_job = request.getParameter("mod_job");
		String mod_field = request.getParameter("mod_field");
		String mod_interest = request.getParameter("mod_interest");

		AccountsDTO data = new AccountsDTO();
		
		data.setAc_email(mod_email);
		data.setAc_name(mod_name);
		data.setAc_pw(mod_pw);
		data.setAc_job(mod_job);
		data.setAc_field(mod_field);
		data.setAc_interest(mod_interest);
		
		boolean result = service.modify(data);

		if(result) {
			// originName = 클라이언트가 전송한 사진파일 이름
			String originName = Part.getOriginalFilename();
			System.out.println(originName);
			// location = 사진이 저장될 서버경로 + 이메일.png
			String location = request.getServletContext().getRealPath("/resources/img/profile/") + acDto.getAc_email() + ".png"; 
			if(!originName.isEmpty()) {		// 사진파일이 있으면 저장
				Part.transferTo(new File(location));
			}
			service.getLogin(session, data);
			return "redirect:/info";
		} else {
			return "info/modify";
		}
		
	}
	
	// 수정 페이지 닉네임중복체크
	@PostMapping(value="modify/nameCheck")
	@ResponseBody
	public String nameCheck(@RequestParam("name") String name) {

		JSONObject json = new JSONObject();
		
		AccountsDTO data = service.nameCheck(name);
		
		System.out.println("로그인컨트롤러 : " + data);
		
		if(data == null) {
			json.put("code", "success");
		}else {
			json.put("code", "sameid");
		}
		
		return json.toJSONString();
		
	}
	

}
