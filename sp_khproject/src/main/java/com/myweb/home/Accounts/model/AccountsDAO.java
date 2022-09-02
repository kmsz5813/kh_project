package com.myweb.home.Accounts.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountsDAO {
	
	@Autowired
	private SqlSession session = null;
	
	private String mapper = "acMapper.%s";
	
	public boolean insertData(AccountsDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}

	public AccountsDTO selectLogin(AccountsDTO data) {
		String mapperId = String.format(mapper, "selectLogin");
		AccountsDTO result = session.selectOne(mapperId, data);
		return result;
	}

	public AccountsDTO idcheck(String id) {
		String mapperId = String.format(mapper, "getData");
		AccountsDTO result = session.selectOne(mapperId, id);
		return result;
	}
	
	public AccountsDTO namecheck(String name) {
		String mapperId = String.format(mapper, "nameData");
		AccountsDTO result = session.selectOne(mapperId, name);
		return result;
	}




}