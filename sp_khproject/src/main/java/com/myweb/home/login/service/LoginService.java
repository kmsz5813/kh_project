package com.myweb.home.login.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.Accounts.model.AccountsDAO;
import com.myweb.home.Accounts.model.AccountsDTO;

@Service
public class LoginService {
	
	@Autowired
	private AccountsDAO dao;
	
	public boolean add(AccountsDTO data) {
		
		boolean result = dao.insertData(data);
		
		if(result) {
			return result;
		}else {
			return false;
		}
	}

	public boolean getLogin(HttpSession session, AccountsDTO data) {
		
		AccountsDTO acdata = dao.selectLogin(data);
		
		if(data != null) {
			session.setAttribute("loginData", acdata);
			return true;
		}else {
			return false;
		}
	}

}
