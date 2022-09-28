package com.myweb.home.selitem.model;

import java.sql.Date;

public class ReviewDTO {

	private int review_number;
	private int review_itemNumber;
	private int review_starCount;
	private Date review_writeDay;
	private String review_writer;
	private String review_content;
	
	private int review_count;
	
	public int getReview_number() {
		return review_number;
	}
	public void setReview_number(int review_number) {
		this.review_number = review_number;
	}
	public int getReview_itemNumber() {
		return review_itemNumber;
	}
	public void setReview_itemNumber(int review_itemNumber) {
		this.review_itemNumber = review_itemNumber;
	}
	public Date getReview_writeDay() {
		return review_writeDay;
	}
	public void setReview_writeDay(Date review_writeDay) {
		this.review_writeDay = review_writeDay;
	}
	public String getReview_writer() {
		return review_writer;
	}
	public void setReview_writer(String review_writer) {
		this.review_writer = review_writer;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	
	public int getReview_starCount() {
		return review_starCount;
	}
	public void setReview_starCount(int review_starCount) {
		this.review_starCount = review_starCount;
	}
	
	public int getReview_count() {
		return review_count;
	}
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}
	
	@Override
	public String toString() {
		return "ReviewDTO [review_number=" + review_number + ", review_itemNumber=" + review_itemNumber
				+ ", review_starCount=" + review_starCount + ", review_writeDay=" + review_writeDay + ", review_writer="
				+ review_writer + ", review_content=" + review_content + "]";
	}
	

	
	


}