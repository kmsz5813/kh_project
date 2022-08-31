package com.myweb.home.sel.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.sel.model.SelDAO;
import com.myweb.home.sel.model.SelDTO;

@Service
public class SelService {

	@Autowired
	private SelDAO dao;
	
	public boolean add(SelDTO data) {
		
		boolean result = dao.insertData(data);
		
		if(result) {
			return result;
		}else {
			return false;
		}
	}

	public boolean getLogin(HttpSession session, SelDTO selData) {
		SelDTO data = dao.selectLogin(selData);
		
		if(data != null) {
			session.setAttribute("loginData", data);
			return true;
		}else {
			return false;
		}
		
	}

}
