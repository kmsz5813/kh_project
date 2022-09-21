package com.myweb.home.purchase.model;

import java.sql.Date;

public class PurchaseDTO {
	private int buy_number;			// 구매번호(PK)
	private int buy_itemNumber;		// 상품번호
	private String buy_buyer;		// 구매자(이메일)
	private String buy_seller;		// 판매자(이메일)
	private Date buy_buyday;		// 구매일자
	private int buy_price;			// 구매가격
	
	
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
	@Override
	public String toString() {
		return "PurchaseDTO [buy_number=" + buy_number + ", buy_itemNumver=" + buy_itemNumber + ", buy_buyer="
				+ buy_buyer + ", buy_seller=" + buy_seller + ", buy_buyday=" + buy_buyday + ", buy_price=" + buy_price
				+ "]";
	}
	
	
}
