package com.myweb.home;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.login.service.LoginService;
import com.myweb.home.purchase.model.CouponDTO;
import com.myweb.home.purchase.model.PurchaseDTO;
import com.myweb.home.purchase.service.PurchaseService;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.service.SelItemService;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private LoginService service;
	@Autowired
	private SelItemService selService;
	@Autowired
	private PurchaseService purchaseService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model
			) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );

		return "home";

	}
	
	// 회원, 관리자 상관없이 닉네임 클릭 시 들어오는 페이지
	// 접근 권한 정보는 detail.jsp 페이지에서 조정함.
	@GetMapping(value="/detail")
	public String detail(Model model, HttpServletRequest request) {
		String name = request.getParameter("search");
		AccountsDTO data = service.nameCheck(name);
		request.setAttribute("data", data);
		
		// 판매자의 판매글
		List<SelItemDTO> items = selService.getName(name);
		// 구매내역
		List<PurchaseDTO> purchaseDatas = purchaseService.getFromBuyerName(name);
		for(PurchaseDTO purchaseData : purchaseDatas) {
			int coupon_number = purchaseData.getBuy_usedCoupon();
			String coupon_name = purchaseService.getCouponNameFromNumber(coupon_number);
			purchaseData.setBuy_usedCouponName(coupon_name);
			int buy_itemNumber = purchaseData.getBuy_itemNumber();
			String buy_itemName = purchaseService.getBuyItemName(buy_itemNumber);
			purchaseData.setBuy_itemName(buy_itemName);
		}
		// 판매내역
		List<PurchaseDTO> sellData = purchaseService.getFromSellerName(name);
		// 보유쿠폰
		List<CouponDTO> couponData = purchaseService.getCouponFromName(name);
		System.out.println(couponData);
		request.setAttribute("items", items);
		request.setAttribute("purchaseData", purchaseDatas);
		request.setAttribute("sellData", sellData);
		request.setAttribute("couponData", couponData);
		
		
		return "detail/detail";
	}
	
}
