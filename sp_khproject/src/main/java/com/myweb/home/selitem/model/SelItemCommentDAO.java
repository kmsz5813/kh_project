package com.myweb.home.selitem.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class SelItemCommentDAO {
	
	@Autowired
	private SqlSession session = null;
	
	public boolean insertData(SelItemCommentDTO data) {
		return false;
	}

}
