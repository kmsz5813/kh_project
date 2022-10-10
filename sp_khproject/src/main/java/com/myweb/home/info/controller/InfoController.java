package com.myweb.home.info.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
import com.myweb.home.purchase.model.CouponDTO;
import com.myweb.home.purchase.model.EventCouponDTO;
import com.myweb.home.purchase.model.PurchaseDTO;
import com.myweb.home.purchase.service.PurchaseService;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.model.SelItemStaticsDTO;
import com.myweb.home.selitem.service.SelItemService;
import com.myweb.home.upload.model.FileUploadDTO;


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
	
	@SuppressWarnings("null")
	@GetMapping(value="")
	public String main(Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, HttpServletRequest request) {
		
		logger.info("main({}) cusdata({})", model, acDto);
		
		if(request.getParameter("purchaseError") != null) {
			request.setAttribute("purchaseError", 01);
			// 로직작성해야됨 (관리자에게 정보 넘어가게)
		}
		
		Date today = new Date(System.currentTimeMillis());
		// 프로필 이미지 이름은 서버에 이메일로 저장되므로
		request.setAttribute("profileImage", acDto.getAc_email());
		// 판매자의 판매글
		List<SelItemDTO> items = selService.getName(acDto.getAc_name());
		// 구매내역
		List<PurchaseDTO> purchaseDatas = purchaseService.getFromBuyerName(acDto.getAc_name());
		List<Integer> itemNumbers = new ArrayList<>(); 
		itemNumbers = selService.getItemNumbers();
		for(PurchaseDTO purchaseData : purchaseDatas) {
			int coupon_number = purchaseData.getBuy_usedCoupon();
			String coupon_name = purchaseService.getCouponNameFromNumber(coupon_number);
			purchaseData.setBuy_usedCouponName(coupon_name);
			int buy_itemNumber = purchaseData.getBuy_itemNumber();
			String buy_itemName = purchaseService.getBuyItemName(buy_itemNumber);
			purchaseData.setBuy_itemName(buy_itemName);
			if(! itemNumbers.contains(Integer.toString(buy_itemNumber))) {
				purchaseData.setItemDelChk("Y");
			}
		}
		// 판매내역
		List<PurchaseDTO> sellData = purchaseService.getFromSellerName(acDto.getAc_name());
		// 보유쿠폰
		List<CouponDTO> couponData = purchaseService.getCouponFromName(acDto.getAc_name());
		for(CouponDTO coupons : couponData) {
			if(coupons.getCoupon_endDate().before(today)) {
				coupons.setCoupon_used("F");  	// F 는 유효기간 지난거
			}
		}
		
		request.setAttribute("items", items);
		request.setAttribute("purchaseData", purchaseDatas);
		request.setAttribute("sellData", sellData);
		request.setAttribute("couponData", couponData);
		
		// 이벤트쿠폰 전체조회
		List<EventCouponDTO> allEventCoupons = purchaseService.allEventCoupons();
		List<EventCouponDTO> downableCoupons = new ArrayList<EventCouponDTO>();
		// 전체 이벤트 쿠폰 중 가지고 있는쿠폰(사용된것도)과 겹치는지 확인		
		int flag = 0;
		for(EventCouponDTO eventCoupon : allEventCoupons) {
			for(CouponDTO ownCoupon : couponData) {
				if(eventCoupon.getEvtcou_name().equals(ownCoupon.getCoupon_name())) {
					flag++;
				}
			}
			// 보유쿠폰과 중복 안되고 유효기간 안지났을경우에만 다운로드 가능 쿠폰 리스트에 추가
			if(flag == 0 && eventCoupon.getEvtcou_endDate().after(today)) {
				downableCoupons.add(eventCoupon);				
			}
			flag = 0;
		}
		
		request.setAttribute("downableCoupons", downableCoupons);
		// 관심상품 전체조회
		List<SelItemStaticsDTO> likedData = service.likeDatas(acDto.getAc_name()); 
		for(SelItemStaticsDTO data : likedData) {
			
			data.setSel_title(selService.getTitle(data));
			data.setSel_name(selService.getSeller(data.getSel_id()));
		}
		System.out.println(likedData);
		request.setAttribute("likedData", likedData);
		
		return "info/info";
	}
	
	@PostMapping(value="/downEventCoupon")
	public String downEventCoupon (Model model
			, @SessionAttribute("loginData") AccountsDTO acDto
			, HttpServletRequest request) {
		
		String evtcou_name = request.getParameter("evtcouName");
		Date evtcou_endDate = java.sql.Date.valueOf(request.getParameter("evtcouEndDate"));  
		int evtcou_salePercent = Integer.parseInt(request.getParameter("evtcouSalePercent"));
		
		CouponDTO coupon = new CouponDTO();
		Random rand = new Random();
		int couponNumber = rand.nextInt(88888888) + 11111111;
		List<Integer> couponNumberList = purchaseService.getCouponNumberList();
		for(int i = 0; i < couponNumberList.size(); i++) {
			if(couponNumberList.get(i) == couponNumber) {
				couponNumber = rand.nextInt(88888888) + 11111111;
			}
		}
		Date currentDate = new Date(System.currentTimeMillis());
		coupon.setCoupon_number(couponNumber);
		coupon.setCoupon_name(evtcou_name);
		coupon.setCoupon_startDate(currentDate);
		coupon.setCoupon_endDate(evtcou_endDate);
		coupon.setCoupon_userName(acDto.getAc_name());
		coupon.setCoupon_salePercent(evtcou_salePercent);
		purchaseService.addCoupon(coupon);
		
		
		
		return "redirect:/info";
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
		// FileUploadDTO 의 게시글 번호를 통해 판매자 이름 추가
		// 일단 FileUploadDTO 전체조회
		
		
		
		if(email.equals(email2) && pw.equals(pw2)) { //이메일주소랑 비밀번호 체크 완료시
			purchaseService.deleteCoupon(acDto.getAc_name());	// 쿠폰 삭제
			selService.deleteLike(acDto.getAc_name());	  // 좋아요 테이블 삭제	
			selService.deleteReview(acDto.getAc_name());  // 리뷰 삭제
			selService.deleteData2(acDto.getAc_name()); // 업로드한 게시물, 파일 삭제
			
			service.delete(data);
			file.delete();	// 프로필 이미지 삭제
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
