package com.myweb.home.purchase.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseDAO {
	
	@Autowired
	private SqlSession session;
	
	private String mapper = "purchaseMapper.%s";

	public boolean insertData(PurchaseDTO purchase) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, purchase);
		return res == 1? true: false;
	}

	public List<PurchaseDTO> getFromBuyerName(String ac_name) {
		String mapperId = String.format(mapper, "getFromBuyerName");
		List<PurchaseDTO> res = session.selectList(mapperId, ac_name);
		return res;
	}

	public List<PurchaseDTO> getFromSellerName(String ac_name) {
		String mapperId = String.format(mapper, "getFromSellerName");
		List<PurchaseDTO> res = session.selectList(mapperId, ac_name);
		return res;
	}

	public List<PurchaseDTO> selectAll() {
		String mapperId = String.format(mapper, "selectAll");
		List<PurchaseDTO> datas = session.selectList(mapperId);
		return datas;
	}

	public List<Integer> getCouponNumberList() {
		String mapperId = String.format(mapper, "getCouponNumberList");
		List<Integer> numbers = session.selectList(mapperId);
		return numbers;
	}

	public boolean addSignCoupon(CouponDTO coupon) {
		String mapperId = String.format(mapper, "addSignCoupon");
		int res = session.insert(mapperId, coupon);
		return res == 1? true: false;
	}
	
	
}
