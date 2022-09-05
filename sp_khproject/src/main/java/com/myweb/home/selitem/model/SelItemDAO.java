package com.myweb.home.selitem.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SelItemDAO {

	
	@Autowired
	private SqlSession session;
	
//	private String mapper = ""
}
