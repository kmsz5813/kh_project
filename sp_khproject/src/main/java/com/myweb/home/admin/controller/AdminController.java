package com.myweb.home.admin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.admin.model.BlackDTO;
import com.myweb.home.admin.service.AdminService;
import com.myweb.home.login.controller.LoginController;
import com.myweb.home.login.service.LoginService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService service;
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping(value="")
	public String admin(Model model,
			HttpServletRequest request) {
		
		// 회원정보
		List<AccountsDTO> datas = service.selectAll();
		request.setAttribute("datas", datas);
		return "admin/admin";
	}
	
	@PostMapping(value="/crawling")
	@ResponseBody
	public Map<String, Object> craw_select(String content, HttpServletRequest request, HttpServletResponse response)throws Exception {
		boolean result = false;
		String url = "https://kmong.com/category/" + content;
		System.out.println(url);
		Document doc = Jsoup.connect(url).get();
		System.out.println(doc);
		
		Elements ele = doc.select(".css-92zmj5.e19f3kve1");
		System.out.println(ele.text());
		System.out.println(ele.html());
		
		
		int Length = ele.size();
		System.out.println("div개수" + Length);
		
		List<String> NameResult = new ArrayList<>();
		List<String> ReviewCount = new ArrayList<>();
		NameResult.add(ele.select("h3.eu87mqk7").text());
		ReviewCount.add(ele.select("div.eu87mqk15").text());
		System.out.println(NameResult);
		System.out.println(ReviewCount);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("NameResult", NameResult);
		resultMap.put("ReviewCount", ReviewCount);
		result = true;
		
		
		return resultMap;
	}
	
	@GetMapping(value="/addBlacklist")
	public String addBlacklist(Model model
			, HttpServletRequest request) {
		
		String id = request.getParameter("id");
		AccountsDTO blackData = service.idCheck(id);
		System.out.println(blackData);
		model.addAttribute("blackData", blackData);
		
		return "admin/blacklist";
	}
	
	@PostMapping(value="/addBlacklist")
	public String blacklist(Model model
			, HttpServletRequest request) {
		
		String id = request.getParameter("blackId");
		String path = request.getServletContext().getRealPath("/resources/img/profile/");
		System.out.println(path + id + ".png");
		// 서버에 저장된 이미지 삭제
		File file = new File(path + "\\" + id + ".png");
		file.delete();
		
		// 블랙리스트에 추가
		BlackDTO black = new BlackDTO();
		String ip = service.getIp(id);
		black.setBlack_email(id);
		black.setIp_address(ip);
		black.setBanned("Y");
		// DB 에 아이디 및 정보 삭제
		boolean result = service.addBlacklist(id);
		// BLACKLIST 테이블에 해당 회원 정보 추가
		boolean result2 = adminService.addBlacklist(black);
		
		

		return "redirect:/admin";
	}
}
