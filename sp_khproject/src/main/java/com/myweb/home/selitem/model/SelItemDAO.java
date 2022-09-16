package com.myweb.home.selitem.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SelItemDAO {

	
	@Autowired
	private SqlSession session;
	
	private String mapper = "selItemMapper.%s";

	public List<Object> getData(SelItemDTO data) {
		
		String mapperId = String.format(mapper, "getData");
		List<Object> result = session.selectList(mapperId, data);
		return result;
	}

	public List selectData(String selectData) {
		String mapperId = String.format(mapper, "selectData");
		List<Object> result = session.selectList(mapperId, selectData);
		return result;
	}

	public List searchData(String search) {
		System.out.println(search);
		String mapperId = String.format(mapper, "searchData");
		List<Object> result = session.selectList(mapperId, search);
		return result;
	}
	
//	private String mapper = ""
}
