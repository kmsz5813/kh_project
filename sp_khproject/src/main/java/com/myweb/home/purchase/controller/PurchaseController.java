package com.myweb.home.purchase.controller;

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
import com.myweb.home.purchase.model.PurchaseDTO;
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
		
		
		return "purchase/purchase";
	}
	
	// 구매페이지 값 받아서 넘기기
	@PostMapping(value="/purchase")
	public String purchaseOrder(Model model, HttpServletRequest request
			, @SessionAttribute("loginData") AccountsDTO acData) {
		int itemid = Integer.parseInt(request.getParameter("itemid"));
		SelItemDTO itemdata = ItemService.getData(itemid);	// 상품번호로 
		String selName = itemdata.getSel_name();		// 판매자 닉네임 불러오기
		PurchaseDTO purchase = new PurchaseDTO();
		purchase.setBuy_itemNumber(itemid);				// 상품번호 저장
		purchase.setBuy_buyer(acData.getAc_name());		// 구매자 닉네임 저장
		purchase.setBuy_seller(selName);				// 판매자 닉네임 저장
		purchase.setBuy_price(itemdata.getSel_price());	// 가격 저장(쿠폰 사용한 경우 변경해야됨)
		System.out.println(purchase);
		service.insertData(purchase);		// ISBUY 테이블에 삽입
		ItemService.plusCount(itemid);		// 구매횟수 + 1
		
		return "redirect:/sellitem";
	}
	
}
