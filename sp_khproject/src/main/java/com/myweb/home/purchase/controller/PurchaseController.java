package com.myweb.home.purchase.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.login.service.LoginService;
import com.myweb.home.purchase.model.CouponDTO;
import com.myweb.home.purchase.model.PurchaseDTO;
import com.myweb.home.purchase.model.UsePointVO;
import com.myweb.home.purchase.service.PurchaseService;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.service.SelItemService;

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
		System.out.println(coupon);
		
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
		purchase.setBuy_usedPoint(Integer.parseInt(request.getParameter("use_point")));		// 사용한 포인트 저장
		int couponNumber = Integer.parseInt(request.getParameter("used_coupon"));			// 사용한 쿠폰
		purchase.setBuy_usedCoupon(couponNumber);
		purchase.setBuy_realPrice(Integer.parseInt(request.getParameter("realprice")));		// 실제 구매 가격 저장
		
		UsePointVO usingpoint = new UsePointVO();
		usingpoint.setAc_name(acData.getAc_name());	
		usingpoint.setUse_point(Integer.parseInt(request.getParameter("use_point")));
		    
		
		loginService.usePoint(usingpoint);  // 계정에서 포인트 수정
		service.insertData(purchase);		// ISBUY 테이블에 삽입
		ItemService.plusCount(itemid);		// SEL_ITEM 테이블에서 해당 항목 구매횟수 + 1
		service.usingCoupon(couponNumber);	// COUPON 테이블의 COUPON_USED에 ISBUY 테이블의 구매번호 입력
		
		return "redirect:/sellitem";
	}
	
}
