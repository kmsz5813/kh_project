package com.myweb.home.selitem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.selitem.model.SelItemDAO;
import com.myweb.home.selitem.model.SelItemDTO;

@Service
public class SelItemService {
<<<<<<< HEAD
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
	
	
	public List<SelItemDTO> getAll() {
		List<SelItemDTO> datas = dao.selectAll();
	return datas;
	}
	
	public SelItemDTO getData(int id) {
		SelItemDTO data = dao.selectData(id);
		return data;
	}
	
	public int add(SelItemDTO data) {
		int seq = dao.getNextSeq();
		data.setSel_id(seq);
		
		boolean result = dao.insertData(data);
		
		if(result) {
			return data.getSel_id();
		}
		return -1;
	}
	
	public boolean modify(SelItemDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}
	
	public boolean remove(SelItemDTO data) {
		SelItemStaticsDTO staticsData = new SelItemStaticsDTO();
		staticsData.setbId(data.getSel_id());
		
		dao.deleteStaticsData(staticsData);
		boolean result = dao.deleteData(data);
		
		return result;
	}
	
	public void incLike(HttpSession session, SelItemDTO data) {
		AccountsDTO accData = (AccountsDTO)session.getAttribute("loginData");
		
		SelItemStaticsDTO staticsDTO = new SelItemStaticsDTO();
//		staticsData.setbId(data.getSel_id());
		
	}
	
=======
//	@Autowired
//	private SelItemDAO dao;
//	
//	public List<SelItemDTO> getAll() {
//		List<SelItemDTO> datas = dao.selectAll();
//	return datas;
//	}
>>>>>>> branch '예진욱' of https://github.com/kmsz5813/kh_project.git
}
