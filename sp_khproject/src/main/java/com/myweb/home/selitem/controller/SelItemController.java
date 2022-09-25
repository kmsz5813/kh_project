package com.myweb.home.selitem.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import com.myweb.home.purchase.model.PurchaseDTO;
import com.myweb.home.purchase.service.PurchaseService;
import com.myweb.home.selitem.model.ReviewDTO;
import com.myweb.home.selitem.model.ReviewDetailVO;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.service.SelItemService;
import com.myweb.home.upload.model.FileUploadDTO;
import com.myweb.home.upload.service.FileUploadService;

@Controller
@RequestMapping(value="/sellitem")
public class SelItemController {
			
	@Autowired
	private SelItemService service;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private PurchaseService purchaseService;
	
//	@Autowired
//	private FileUploadService fileUploadService;
	@GetMapping(value="/additem")
	public String additem(Model model
		, @SessionAttribute("loginData") AccountsDTO acData) {
		return "/sellitem/additem";
	}
	
	@PostMapping(value="/additem")
	public String additem(Model model, HttpServletRequest request
			,@SessionAttribute("loginData") AccountsDTO acData
			, MultipartHttpServletRequest mtfRequest
			, @RequestParam("fileUpload") MultipartFile[] files) throws Exception {

		System.out.println(files);
		SelItemDTO data = new SelItemDTO();
		 
		String title = request.getParameter("title");
	    String service1 = request.getParameter("field");
	    String location = request.getParameter("location");

	    int price = Integer.parseInt(request.getParameter("price"));
	    String content = request.getParameter("content");

		data.setSel_title(title);
		data.setSel_field(service1);
		data.setSel_location(location);
		data.setSel_content(content);
		data.setSel_price(price);
		data.setSel_name(acData.getAc_name());
			
		boolean result = service.add(data);
		//해서만들어진 게시물번호값을 불러오기.
		System.out.println(data.getSel_id());
		
		//썸네일 이미지 저장하는것
		for(MultipartFile file: files) {
			String location1 = request.getServletContext().getRealPath("/resources/img/board");
			String url = "/static/img/board"; 
			FileUploadDTO fileData = new FileUploadDTO(data.getSel_id(), location1, url);
			
			
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
		
		return "/sellitem/list";
	}
	

	@GetMapping(value="/itemdetail")
	public String detail(Model model, HttpServletRequest request
			,HttpSession session) {
		// 판매자 닉네임 가져오기
		String name = request.getParameter("search");


		AccountsDTO data = loginService.nameCheck(name);
		//아이템 번호도 가져와야됨
		int itemid = Integer.parseInt(request.getParameter("itemid"));
		SelItemDTO itemdata = service.getData(itemid);

		if(session.getAttribute("loginData") != null) {
			//로그인했을때만... 로그인안했을때 들어가는건 동일ip일땐 안늘게
			AccountsDTO acDto = (AccountsDTO) session.getAttribute("loginData");
			String test1 = itemdata.getSel_name();
			String test2 = acDto.getAc_name();
			if(!test1.equals(test2)) {
				boolean result = service.incViewCnt(itemdata);
				if(!result) {
					request.setAttribute("viewerror", "조회수오류가있습니다.");
				}
			}
			List<PurchaseDTO> buycheck = purchaseService.getFromBuyerName(acDto.getAc_name());
			for(PurchaseDTO check : buycheck) {
				if(check.getBuy_itemNumber() == itemid) {
					request.setAttribute("purchaseCheck", "Y");
				}
			}
		}
		
		List<ReviewDTO> reviews = service.getReviews(itemid);
		int reviewCount = service.getReviewCount(itemid);
		
		request.setAttribute("reviews", reviews);
		request.setAttribute("reviewCount", reviewCount);
		request.setAttribute("data", data);
		request.setAttribute("itemdata", itemdata);

		return "sellitem/itemdetail";
	}
	
	@GetMapping(value="/modify")
	public String itemmodify(Model model
			, HttpServletRequest request
			, @SessionAttribute("loginData") AccountsDTO acDto
			, @RequestParam int id) {
		
		SelItemDTO itemdata = service.getData(id);
		
		request.setAttribute("itemdata", itemdata);
		
		
		return "sellitem/modify";
	}
	
	
	@PostMapping(value="/modify")
	public String itemmodify(Model model, HttpServletRequest request
			,@SessionAttribute("loginData") AccountsDTO acData
			, MultipartHttpServletRequest mtfRequest
			, @RequestParam("fileUpload") MultipartFile[] files
			) {
		
		SelItemDTO data = new SelItemDTO();
	
		int sel_id = Integer.parseInt(request.getParameter("sel_id"));
		String title = request.getParameter("title");
	    String service1 = request.getParameter("field");
	    String location = request.getParameter("location");
	    int price = Integer.parseInt(request.getParameter("price"));
	    String content = request.getParameter("content");


		data.setSel_title(title);
		data.setSel_field(service1);
		data.setSel_location(location);
		data.setSel_content(content);
		data.setSel_price(price);
		data.setSel_name(acData.getAc_name());
		data.setSel_id(sel_id);
			
		boolean result = service.modify(data);
		//해서만들어진 게시물번호값을 불러오기.
		
		
		//썸네일 이미지 저장하는것
		for(MultipartFile file: files) {
			String location1 = request.getServletContext().getRealPath("/resources/img/board");
			String url = "/static/img/board"; 
			FileUploadDTO fileData = new FileUploadDTO(sel_id, location1, url);
			
			
			try {
				int fileResult = fileUploadService.modify(file, fileData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
		if(result) {
			return "redirect: /home/sellitem";
		}else {
			String error = "수정실패";
			request.setAttribute("error", error);
			return "sellitem/itemdetail";
		}
		
	}
	
	@PostMapping(value="/review")
	public String review(Model model, HttpServletRequest request) {
		String itemid = request.getParameter("itemid");	// url 에 쓸것이므로 굳이 int 반환 필요없음
		String seller = request.getParameter("sellerName");
		String writer = request.getParameter("writer");
		
		String sellerName = null;
		try {
			sellerName = URLEncoder.encode(seller, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int starCount = Integer.parseInt(request.getParameter("modal-star"));
		String reviewContent = request.getParameter("modal-desc");
		System.out.println("별점 : " + starCount);
		System.out.println("리뷰내용 : " + reviewContent);
		
		ReviewDTO review = new ReviewDTO();	// buy_number 는 시퀀스.nextval  writeday 는 DB의 SYSDATE로
		review.setReview_itemNumber(Integer.parseInt(itemid));
		review.setReview_starCount(starCount);
		review.setReview_writer(writer);
		review.setReview_content(reviewContent);
		

		int reviewCount = service.getReviewCount(Integer.parseInt(itemid));
		int previousStar = service.getStarScore(Integer.parseInt(itemid));
		int star = (previousStar + starCount) / (reviewCount + 1);			
		ReviewDetailVO detail = new ReviewDetailVO();
		detail.setSel_id(Integer.parseInt(itemid));
		detail.setStar(star);
		
		service.addReview(review);		// 리뷰 테이블에 등록
		service.addReviewCount(Integer.parseInt(itemid));	// 아이템 테이블에 리뷰등록횟수 + 1
		service.addReviewStar(detail);	// 아이템 테이블에 별점 수정
		
		String redirectUrl = "sellitem/itemdetail?search=" + sellerName + "&itemid=" + itemid;
		
		return "redirect:/" + redirectUrl;
	}
}