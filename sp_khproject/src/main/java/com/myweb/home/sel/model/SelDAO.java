package com.myweb.home.sel.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SelDAO {

	@Autowired
	private SqlSession session = null;
	
	private String mapper = "selMapper.%s";
	
	public boolean insertData(SelDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}

}
