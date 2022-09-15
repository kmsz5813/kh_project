package com.myweb.home.selitem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.selitem.model.SelItemDAO;
import com.myweb.home.selitem.model.SelItemDTO;

@Service
public class SelItemService {

	@Autowired
	private SelItemDAO dao;
	
	public List<Object> getData(SelItemDTO data) {
		
		List<Object> datas = dao.getData(data);
		
		//데이터 가져오기
		if(datas != null) {
	
			return datas;
		}
		
		return null;
	}


	public List getSelect(String selectData) {
		List datas = dao.selectData(selectData);
		
		if(datas != null) {
			return datas;
		}
		
		return null;
	}
}
