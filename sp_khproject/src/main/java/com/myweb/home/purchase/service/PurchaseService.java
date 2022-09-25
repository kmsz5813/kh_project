package com.myweb.home.purchase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.purchase.model.CouponDTO;
import com.myweb.home.purchase.model.EventCouponDTO;
import com.myweb.home.purchase.model.PurchaseDAO;
import com.myweb.home.purchase.model.PurchaseDTO;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseDAO dao;

	public boolean insertData(PurchaseDTO purchase) {
		boolean result = dao.insertData(purchase);
		if(result == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<PurchaseDTO> selectAll() {
		List<PurchaseDTO> datas = dao.selectAll();
		return datas;
	}
	
	public List<PurchaseDTO> getFromBuyerName(String ac_name) {
		List<PurchaseDTO> datas = dao.getFromBuyerName(ac_name);
		
		return datas;
	}

	public List<PurchaseDTO> getFromSellerName(String ac_name) {
		List<PurchaseDTO> datas = dao.getFromSellerName(ac_name);
		return datas;
	}

	public List<Integer> getCouponNumberList() {
		List<Integer> numbers = dao.getCouponNumberList();
		return numbers;
	}

	public boolean addCoupon(CouponDTO coupon) {
		boolean result = dao.addCoupon(coupon);
		return result;
	}

	public List<CouponDTO> getCouponFromName(String ac_Name) {
		List<CouponDTO> list = dao.getCouponFromName(ac_Name);
		if(list != null) {
			return list;
		}
		return null;
	}

	public boolean usingCoupon(int couponNumber) {
		boolean res = dao.usingCoupon(couponNumber);
		if(res) {
			return true;
		} else {
			return false;
		}
	}

	public String getCouponNameFromNumber(int coupon_number) {
		String res = dao.getCouponNameFromNumber(coupon_number);
		return res;
	}

	public String getBuyItemName(int buy_itemNumber) {
		String itemName = dao.getBuyItemName(buy_itemNumber);
		return itemName;
	}

	public boolean addEventCoupon(EventCouponDTO eventCoupon) {
		boolean result = dao.addEventCoupon(eventCoupon);
		return result;
	}

	public boolean checkOverlap(CouponDTO coupon) {
		boolean result = dao.checkOverlap(coupon);
		return result;
	}



	public List<EventCouponDTO> allEventCoupons() {
		List<EventCouponDTO> list = dao.allEventCoupons();
		return list;
	}












	
}
