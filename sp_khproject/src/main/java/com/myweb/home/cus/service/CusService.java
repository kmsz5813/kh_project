package com.myweb.home.cus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.cus.model.CusDAO;
import com.myweb.home.cus.model.CusDTO;

@Service
public class CusService {

	@Autowired
	private CusDAO dao;
	
	public int add(CusDTO data) {
		
		boolean result = dao.insertData(data);
		
		if(result) {
			return result;
		}else {
			return -1;
		}
	}
}
