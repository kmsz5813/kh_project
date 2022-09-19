package com.myweb.home.admin.model;

public class BlackDTO {
	private String black_email;
	private String ip_address;
	private String banned;
	
	public String getBlack_email() {
		return black_email;
	}
	public void setBlack_email(String black_email) {
		this.black_email = black_email;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getBanned() {
		return banned;
	}
	public void setBanned(String banned) {
		this.banned = banned;
	}
	
	@Override
	public String toString() {
		return "AdminDTO [black_email=" + black_email + ", ip_address=" + ip_address + ", banned=" + banned + "]";
	}
	
}
