package com.myweb.home.purchase.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseDAO {
	
	@Autowired
	private SqlSession session;
	
	private String mapper = "purchaseMapper.%s";

	public boolean insertData(PurchaseDTO purchase) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, purchase);
		return res == 1? true: false;
	}
	
	
}
