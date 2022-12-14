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
			// ????????????????????? (??????????????? ?????? ????????????)
		}
		
		Date today = new Date(System.currentTimeMillis());
		// ????????? ????????? ????????? ????????? ???????????? ???????????????
		request.setAttribute("profileImage", acDto.getAc_email());
		// ???????????? ?????????
		List<SelItemDTO> items = selService.getName(acDto.getAc_name());
		// ????????????
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
		// ????????????
		List<PurchaseDTO> sellData = purchaseService.getFromSellerName(acDto.getAc_name());
		// ????????????
		List<CouponDTO> couponData = purchaseService.getCouponFromName(acDto.getAc_name());
		for(CouponDTO coupons : couponData) {
			if(coupons.getCoupon_endDate().before(today)) {
				coupons.setCoupon_used("F");  	// F ??? ???????????? ?????????
			}
		}
		
		request.setAttribute("items", items);
		request.setAttribute("purchaseData", purchaseDatas);
		request.setAttribute("sellData", sellData);
		request.setAttribute("couponData", couponData);
		
		// ??????????????? ????????????
		List<EventCouponDTO> allEventCoupons = purchaseService.allEventCoupons();
		List<EventCouponDTO> downableCoupons = new ArrayList<EventCouponDTO>();
		// ?????? ????????? ?????? ??? ????????? ????????????(???????????????)??? ???????????? ??????		
		int flag = 0;
		for(EventCouponDTO eventCoupon : allEventCoupons) {
			for(CouponDTO ownCoupon : couponData) {
				if(eventCoupon.getEvtcou_name().equals(ownCoupon.getCoupon_name())) {
					flag++;
				}
			}
			// ??????????????? ?????? ????????? ???????????? ???????????????????????? ???????????? ?????? ?????? ???????????? ??????
			if(flag == 0 && eventCoupon.getEvtcou_endDate().after(today)) {
				downableCoupons.add(eventCoupon);				
			}
			flag = 0;
		}
		
		request.setAttribute("downableCoupons", downableCoupons);
		// ???????????? ????????????
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
		// ????????? ?????????, ????????????
		data.setAc_email(email);
		data.setAc_pw(pw);
		
		// ????????? ????????? ?????????, ????????????
		String email2 = acDto.getAc_email();
		String pw2 = acDto.getAc_pw();
		// ????????? ????????? ????????? ??????????????? ??????
		String path = request.getServletContext().getRealPath("/resources/img/profile/");
		File file = new File(path + "\\" + acDto.getAc_email() + ".png");
		// FileUploadDTO ??? ????????? ????????? ?????? ????????? ?????? ??????
		// ?????? FileUploadDTO ????????????
		
		
		
		if(email.equals(email2) && pw.equals(pw2)) { //?????????????????? ???????????? ?????? ?????????
			purchaseService.deleteCoupon(acDto.getAc_name());	// ?????? ??????
			selService.deleteLike(acDto.getAc_name());	  // ????????? ????????? ??????	
			selService.deleteReview(acDto.getAc_name());  // ?????? ??????
			selService.deleteData2(acDto.getAc_name()); // ???????????? ?????????, ?????? ??????
			
			service.delete(data);
			file.delete();	// ????????? ????????? ??????
			System.out.println("?????? ??????");
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
			// originName = ?????????????????? ????????? ???????????? ??????
			String originName = Part.getOriginalFilename();
			System.out.println(originName);
			// location = ????????? ????????? ???????????? + ?????????.png
			String location = request.getServletContext().getRealPath("/resources/img/profile/") + acDto.getAc_email() + ".png"; 
			if(!originName.isEmpty()) {		// ??????????????? ????????? ??????
				Part.transferTo(new File(location));
			}
			service.getLogin(session, data);
			return "redirect:/info";
		} else {
			return "info/modify";
		}
		
	}
	
	// ?????? ????????? ?????????????????????
	@PostMapping(value="modify/nameCheck")
	@ResponseBody
	public String nameCheck(@RequestParam("name") String name) {

		JSONObject json = new JSONObject();
		
		AccountsDTO data = service.nameCheck(name);
		
		System.out.println("????????????????????? : " + data);
		
		if(data == null) {
			json.put("code", "success");
		}else {
			json.put("code", "sameid");
		}
		
		return json.toJSONString();
		
	}
	

}
