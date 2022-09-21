package com.myweb.home.purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.purchase.model.PurchaseDAO;
import com.myweb.home.purchase.model.PurchaseDTO;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseDAO dao;

	public boolean insertData(PurchaseDTO purchase) {
		boolean result = dao.insertData(purchase);
		if(result == true) {
			return true;
		} else {
			return false;
		}
	}
	
}
