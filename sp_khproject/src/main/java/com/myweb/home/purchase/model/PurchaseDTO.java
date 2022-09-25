package com.myweb.home.purchase.model;

import java.sql.Date;

public class PurchaseDTO {
	private int buy_number;			// 구매번호(PK)
	private int buy_itemNumber;		// 상품번호
	private String buy_buyer;		// 구매자(닉네임)
	private String buy_seller;		// 판매자(닉네임)
	private Date buy_buyday;		// 구매일자
	private int buy_price;			// 상품가격
	private int buy_usedPoint;		// 사용한 포인트
	private int buy_usedCoupon;		// 사용한 쿠폰
	private int buy_realPrice;		// 실제 구매 가격
	
	private String buy_usedCouponName;	// 사용한 쿠폰(쿠폰번호)를 통한 쿠폰명 조회
	private String buy_itemName;
	
	public int getBuy_number() {
		return buy_number;
	}
	public void setBuy_number(int buy_number) {
		this.buy_number = buy_number;
	}
	public int getBuy_itemNumber() {
		return buy_itemNumber;
	}
	public void setBuy_itemNumber(int buy_itemNumber) {
		this.buy_itemNumber = buy_itemNumber;
	}
	public String getBuy_buyer() {
		return buy_buyer;
	}
	public void setBuy_buyer(String buy_buyer) {
		this.buy_buyer = buy_buyer;
	}
	public String getBuy_seller() {
		return buy_seller;
	}
	public void setBuy_seller(String buy_seller) {
		this.buy_seller = buy_seller;
	}
	public Date getBuy_buyday() {
		return buy_buyday;
	}
	public void setBuy_buyday(Date buy_buyday) {
		this.buy_buyday = buy_buyday;
	}
	public int getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}
	public int getBuy_usedPoint() {
		return buy_usedPoint;
	}
	public void setBuy_usedPoint(int buy_usedPoint) {
		this.buy_usedPoint = buy_usedPoint;
	}
	public int getBuy_usedCoupon() {
		return buy_usedCoupon;
	}
	public void setBuy_usedCoupon(int buy_usedCoupon) {
		this.buy_usedCoupon = buy_usedCoupon;
	}
	public int getBuy_realPrice() {
		return buy_realPrice;
	}
	public void setBuy_realPrice(int buy_realPrice) {
		this.buy_realPrice = buy_realPrice;
	}
	public String getBuy_usedCouponName() {
		return buy_usedCouponName;
	}
	public void setBuy_usedCouponName(String buy_usedCouponName) {
		this.buy_usedCouponName = buy_usedCouponName;
	}
	public String getBuy_itemName() {
		return buy_itemName;
	}
	public void setBuy_itemName(String buy_itemName) {
		this.buy_itemName = buy_itemName;
	}
	
	@Override
	public String toString() {
		return "PurchaseDTO [buy_number=" + buy_number + ", buy_itemNumber=" + buy_itemNumber + ", buy_buyer="
				+ buy_buyer + ", buy_seller=" + buy_seller + ", buy_buyday=" + buy_buyday + ", buy_price=" + buy_price
				+ ", buy_usedPoint=" + buy_usedPoint + ", buy_usedCoupon=" + buy_usedCoupon + ", buy_realPrice="
				+ buy_realPrice + "]";
	}
	
}
