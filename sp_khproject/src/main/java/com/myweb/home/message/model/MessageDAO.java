package com.myweb.home.message.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageDAO {

	@Autowired
	private SqlSession session = null;

	public boolean insertData(MessageDTO data) {
		return false;
	}

}
