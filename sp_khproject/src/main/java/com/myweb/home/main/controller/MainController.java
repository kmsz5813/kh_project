package com.myweb.home.main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.login.service.LoginService;
import com.myweb.home.main.service.MainService;
import com.myweb.home.purchase.model.CouponDTO;
import com.myweb.home.purchase.service.PurchaseService;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.service.SelItemService;
import com.myweb.home.upload.model.FileUploadDTO;

@Controller
@RequestMapping(value="/main")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	
	@Autowired
	private MainService service;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private SelItemService selItemService;

	
	@GetMapping(value="")
	public String main(Model model, HttpSession session, HttpServletRequest request) {
		logger.info("main({})", model);
		
		/*	 배너에서 쿠폰 다운받을 수 있게
		 * session = request.getSession(); if(session.getAttribute("loginData") != null)
		 * { // 로그인세션 확인 AccountsDTO acdto = (AccountsDTO)
		 * session.getAttribute("loginData"); String ac_name = acdto.getAc_name();
		 * CouponDTO coupon = new CouponDTO(); String couponName = "오픈이벤트 15% 할인 쿠폰";
		 * coupon.setCoupon_name(couponName); coupon.setCoupon_userName(ac_name);
		 * boolean result = purchaseService.checkOverlap(coupon); // 쿠폰 보유 확인(중복이면
		 * false) System.out.println(result); if(! result) { // 중복되지 않았을경우
		 * request.setAttribute("couponcheck", "1"); } else {
		 * request.setAttribute("couponcheck", "0"); } }
		 * request.setAttribute("couponcheck", "0");
		 */
	    
		List<SelItemDTO> list = selItemService.searchLike2();
		SelItemDTO list1 = null;
		SelItemDTO list2 = null;
		SelItemDTO list3 = null;
		SelItemDTO list4 = null;
		SelItemDTO list5 = null;
		SelItemDTO list6 = null;
		SelItemDTO list7 = null;
		SelItemDTO list8 = null;
		SelItemDTO list9 = null;
		if(list.size() > 8) {
			list1 = list.get(0);
			list2 = list.get(1);
			list3 = list.get(2);
			list4 = list.get(3);
			list5 = list.get(4);
			list6 = list.get(5);
			list7 = list.get(6);
			list8 = list.get(7);
			list9 = list.get(8);
		}
		/*
		 * if(list.get(0) != null) { list1 = list.get(0); } if(list.get(1) != null) {
		 * list2 = list.get(1); } if(list.size() >= 2) { list3 = list.get(2); }
		 * if(list.get(3) != null) { list4 = list.get(3); } if(list.get(4) != null) {
		 * list5 = list.get(4); } if(list.get(5) != null) { list6 = list.get(5); }
		 * if(list.get(6) != null) { list7 = list.get(6); } if(list.get(7) != null) {
		 * list8 = list.get(7); } if(list.get(8) != null) { list9 = list.get(8); }
		 */

		request.setAttribute("list1", list1);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		request.setAttribute("list4", list4);
		request.setAttribute("list5", list5);
		request.setAttribute("list6", list6);
		request.setAttribute("list7", list7);
		request.setAttribute("list8", list8);
		request.setAttribute("list9", list9);
		return "main/main";	
	}
	
//  배너에서 쿠폰 다운받기
	
//	@PostMapping(value="")
//	public String main(Model model
//			, @SessionAttribute("loginData") AccountsDTO acDto
//			, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		
//		CouponDTO coupon = new CouponDTO();
//		String couponName = "오픈이벤트 15% 할인 쿠폰";
//		coupon.setCoupon_name(couponName);
//		coupon.setCoupon_userName(acDto.getAc_name());
//		boolean result = purchaseService.checkOverlap(coupon);	// 쿠폰 보유 확인(중복이면 false)
//			
//		Random rand = new Random();
//		Date currentDate = new Date(System.currentTimeMillis());
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(currentDate); cal.add(Calendar.DATE, 30);
//		Date endDate = new Date(cal.getTimeInMillis());		// 마감기한 = 다운로드일 + 30일
//		int couponNumber = rand.nextInt(88888888) + 11111111;
//		List<Integer> couponNumberList = purchaseService.getCouponNumberList();
//		System.out.println("기존 쿠폰번호 리스트 : " + couponNumberList);
//		for(int i = 0; i < couponNumberList.size(); i++) {
//			if(couponNumberList.get(i) == couponNumber) {
//				couponNumber = rand.nextInt(88888888) + 11111111;
//			}
//		}
//		coupon.setCoupon_number(couponNumber);
//		coupon.setCoupon_startDate(currentDate);
//		coupon.setCoupon_endDate(endDate);
//		coupon.setCoupon_salePercent(15);
//
//		return "redirect:/info";
//
//	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
    public String logoutMainGET(HttpServletRequest request) throws Exception{
        
        logger.info("logoutMainGET메서드 진입");
        
        HttpSession session = request.getSession();
        System.out.println("로그아웃");
        session.invalidate();
        
        return "redirect:/main";        
        
    }
	
}
