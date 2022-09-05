package com.myweb.home.selitem.model;

import java.sql.Date;

public class SelItemCommentDTO {
	
	private int item_com_id;
	private int item_com_bid;
	private String item_com_cusname;
	private String item_com_selname;
	private String item_com_content;
	private Date item_com_writedday;
	public int getItem_com_id() {
		return item_com_id;
	}
	public void setItem_com_id(int item_com_id) {
		this.item_com_id = item_com_id;
	}
	public int getItem_com_bid() {
		return item_com_bid;
	}
	public void setItem_com_bid(int item_com_bid) {
		this.item_com_bid = item_com_bid;
	}
	public String getItem_com_cusname() {
		return item_com_cusname;
	}
	public void setItem_com_cusname(String item_com_cusname) {
		this.item_com_cusname = item_com_cusname;
	}
	public String getItem_com_selname() {
		return item_com_selname;
	}
	public void setItem_com_selname(String item_com_selname) {
		this.item_com_selname = item_com_selname;
	}
	public String getItem_com_content() {
		return item_com_content;
	}
	public void setItem_com_content(String item_com_content) {
		this.item_com_content = item_com_content;
	}
	public Date getItem_com_writedday() {
		return item_com_writedday;
	}
	public void setItem_com_writedday(Date item_com_writedday) {
		this.item_com_writedday = item_com_writedday;
	}
	
	@Override
	public String toString() {
		return "SelItemCommentDTO [item_com_id=" + item_com_id + ", item_com_bid=" + item_com_bid
				+ ", item_com_cusname=" + item_com_cusname + ", item_com_selname=" + item_com_selname
				+ ", item_com_content=" + item_com_content + ", item_com_writedday=" + item_com_writedday + "]";
	}
	
	

}
