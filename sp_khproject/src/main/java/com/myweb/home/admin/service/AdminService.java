package com.myweb.home.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.admin.model.AdminDAO;
import com.myweb.home.admin.model.BlackDTO;

@Service
public class AdminService {
	
	@Autowired
	private AdminDAO dao;
	
	public boolean addBlacklist(BlackDTO black) {
		boolean result = dao.addBlacklist(black);
		
		return result;
	}

	public List<BlackDTO> find_ip_ban_list() {
		List<BlackDTO> datas = dao.find_ip_ban_list();
		return datas;
	}

}
