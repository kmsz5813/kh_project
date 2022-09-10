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
		
	
		if(acdata != null) {
			session.setAttribute("loginData", acdata);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean getCheck(AccountsDTO data) {
		AccountsDTO acdata = dao.selectLogin(data);
		
		if(acdata != null) {
			return true;
		}else {
			return false;
		}
	}

	public AccountsDTO idCheck(String id) {
		
		AccountsDTO data = dao.idcheck(id);
		
		if(data != null) {
			return data;
		}
		return null;
	}
	
	public AccountsDTO nameCheck(String name) {
		
		AccountsDTO data = dao.namecheck(name);
		
		if(data != null) {
			return data;
		}
		return null;
	}
	
	public void delete(AccountsDTO data) {
		dao.deleteData(data);	
	}


	
	

}
