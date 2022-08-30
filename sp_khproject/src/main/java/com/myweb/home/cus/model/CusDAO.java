package com.myweb.home.cus.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CusDAO {
	
	@Autowired
	private SqlSession session = null;
	
	private String mapper = "cusMapper.%s";

	public boolean insertData(CusDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}

	public CusDTO selectLogin(CusDTO cusData) {
		String mapperId = String.format(mapper, "selectLogin");
		CusDTO result = session.selectOne(mapperId, cusData);
		return result;
	}

}
