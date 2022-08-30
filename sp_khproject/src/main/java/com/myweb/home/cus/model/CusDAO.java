package com.myweb.home.cus.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CusDAO {
	
	@Autowired
	private SqlSession session = null;

	public boolean insertData(CusDTO data) {
		// TODO Auto-generated method stub
		return false;
	}

}
