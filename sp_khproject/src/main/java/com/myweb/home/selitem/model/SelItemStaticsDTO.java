package com.myweb.home.selitem.model;

import java.sql.Date;

public class SelItemStaticsDTO {
	private int id;
	private int empId;
	private int bId;
	private boolean viewed;
	private Date latestViewDate;
	private boolean liked;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public boolean isViewed() {
		return viewed;
	}

	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}

	public Date getLatestViewDate() {
		return latestViewDate;
	}

	public void setLatestViewDate(Date latestViewDate) {
		this.latestViewDate = latestViewDate;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	@Override
	public String toString() {
		return "SelItemStaticDTO [id=" + id + ", empId=" + empId + ", bId=" + bId + ", viewed=" + viewed
				+ ", latestViewDate=" + latestViewDate + ", liked=" + liked + "]";
	}

	
}
