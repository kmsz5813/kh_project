package com.myweb.home.selitem.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.selitem.model.SelItemDAO;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.model.SelItemStaticsDTO;

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
	
	public List<SelItemDTO> getAll() {
		List<SelItemDTO> datas = dao.selectAll();
	return datas;
	}
	
	public SelItemDTO getData(int id) {
		SelItemDTO data = dao.selectData(id);
		return data;
	}
	
	public boolean add(SelItemDTO data) {
		
		boolean result = dao.insertData(data);
		
		if(result) {
			return result;
		}
		return false;
	}
	
	public boolean modify(SelItemDTO data) {
		boolean result = dao.updateData(data);
		System.out.println("service부분" + data);
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
	//조회로 게시글찾rl
	public List getSearch(String search) {
		List datas = dao.searchData(search);
		
		if(datas != null) {
			return datas;
		}
		
		return null;
	}
	
	// 이름으로 게시글 찾기
	public List<SelItemDTO> getName(String ac_name) {
		List<SelItemDTO> datas = dao.searchName(ac_name);
		if(datas != null) {
			return datas;
		}
		return null;
	}

	// 구매시 구매횟수 + 1
	public boolean plusCount(int itemid) {
		boolean result = dao.plusCount(itemid);
		if(result) {
			return true;
		} else {
			return false;
		}
	}





	
}