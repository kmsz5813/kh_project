package com.myweb.home.cus.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.cus.model.CusDAO;
import com.myweb.home.cus.model.CusDTO;

@Service
public class CusService {

	@Autowired
	private CusDAO dao;
	
	public boolean add(CusDTO data) {
		
		boolean result = dao.insertData(data);
		
		if(result) {
			return result;
		}else {
			return false;
		}
	}

	public boolean getLogin(HttpSession session, CusDTO cusData) {
		
		CusDTO data = dao.selectLogin(cusData);
		
		if(data != null) {
			session.setAttribute("loginData", data);
			return true;
		}else {
			return false;
		}
		
	}
}
