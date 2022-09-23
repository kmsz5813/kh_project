package com.myweb.home.purchase.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.login.service.LoginService;
import com.myweb.home.purchase.model.CouponDTO;
import com.myweb.home.purchase.model.PurchaseDTO;
import com.myweb.home.purchase.model.UsePointVO;
import com.myweb.home.purchase.service.PurchaseService;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.service.SelItemService;
import com.myweb.home.upload.model.FileUploadDTO;

@Controller
@RequestMapping(value="/purchase")
public class PurchaseController {
	
	@Autowired
	private PurchaseService service;
	
	@Autowired
	private SelItemService ItemService;
	
	@Autowired
	private LoginService loginService;
	
	// 구매페이지
	@GetMapping(value="")
	public String purchase(Model model, HttpServletRequest request
			, @SessionAttribute("loginData") AccountsDTO acData) {
		int itemid = Integer.parseInt(request.getParameter("itemid"));
		SelItemDTO itemdata = ItemService.getData(itemid);
		request.setAttribute("itemdata", itemdata);
		
		List<CouponDTO> coupon = service.getCouponFromName(acData.getAc_name());
		request.setAttribute("coupon", coupon);
		
		FileUploadDTO thumbnail = ItemService.getThumbnail(itemdata.getSel_id());
		request.setAttribute("thumbnail", thumbnail);
		
		return "purchase/purchase";
	}
	
	
	// 구매페이지 값 받아서 넘기기
	@PostMapping(value="/purchase")
	public String purchaseOrder(Model model, HttpServletRequest request
			, @SessionAttribute("loginData") AccountsDTO acData) {
		int itemid = Integer.parseInt(request.getParameter("itemid"));
		SelItemDTO itemdata = ItemService.getData(itemid);	// 상품번호로 
		String selName = itemdata.getSel_name();			// 판매자 닉네임 불러오기
		
		PurchaseDTO purchase = new PurchaseDTO();			// BUYDAY 는 SQL 에서 SYSDATE 로 처리
		purchase.setBuy_itemNumber(itemid);					// 상품번호 저장
		purchase.setBuy_buyer(acData.getAc_name());			// 구매자 닉네임 저장
		purchase.setBuy_seller(selName);					// 판매자 닉네임 저장
		purchase.setBuy_price(itemdata.getSel_price());		// 상품 가격 저장
		if(request.getParameter("use_point") != "") {			
			purchase.setBuy_usedPoint(Integer.parseInt(request.getParameter("use_point")));		// 사용한 포인트 저장
		}
		if(request.getParameter("used_coupon") != "") {			
			int couponNumber = Integer.parseInt(request.getParameter("used_coupon"));			// 사용한 쿠폰
			purchase.setBuy_usedCoupon(couponNumber);
			service.usingCoupon(couponNumber);	// COUPON 테이블의 coupon_used에 'Y' 추가
		}
		System.out.println("실구매가 : "+ request.getParameter("realprice"));
		purchase.setBuy_realPrice(Integer.parseInt(request.getParameter("realprice")));		// 실제 구매 가격 저장
		
		UsePointVO usingpoint = new UsePointVO();
		usingpoint.setAc_name(acData.getAc_name());	
		usingpoint.setEarn_point((int)(itemdata.getSel_price() / 100));
		

		loginService.usePoint(usingpoint);  // 계정에서 포인트 사용 빼고, 판매가 1% 적립하기
		service.insertData(purchase);		// ISBUY 테이블에 삽입
		ItemService.plusCount(itemid);		// SEL_ITEM 테이블에서 해당 항목 구매횟수 + 1
		
		return "redirect:/info";
	}
	
	@PostMapping("/iamport")
	@ResponseBody
	public int iamport(@RequestBody Map<String, String> buy,
			Model model, HttpServletRequest request){
		System.out.println(buy);
		int res = 1;
		return res;
	}
	
}
