package com.myweb.home.admin.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO {
	@Autowired
	private SqlSession session = null;
	
	private String mapper = "adminMapper.%s";

	public boolean addBlacklist(BlackDTO black) {
		String mapperId = String.format(mapper, "addBlacklist");
		session.insert(mapperId, black);
		return false;
	}
}
