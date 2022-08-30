package com.myweb.home.selitem.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class SelItemDAO {

	
	@Autowired
	private SqlSession session = null;
	
	public boolean insertData(SelItemDTO data) {
		return false;
	}
}
