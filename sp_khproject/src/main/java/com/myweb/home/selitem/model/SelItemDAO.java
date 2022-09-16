package com.myweb.home.selitem.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SelItemDAO {

	
	@Autowired
	private SqlSession session;
	
<<<<<<< HEAD
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
	
	
	public List<SelItemDTO> selectAll() {
		String mapperId = String.format(mapper, "selectAll");
		List<SelItemDTO> res = session.selectList(mapperId);
		return res;
	}
	
	public SelItemDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectData");
		SelItemDTO res = session.selectOne(mapperId, id);
		return res;
	}
	
	public int getNextSeq() {
		String mapperId = String.format(mapper, "getNextSeq");
		int seq = session.selectOne(mapperId);
		return seq;
	}
	
	public boolean insertData(SelItemDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1? true : false;
	}
	
	public boolean updateData(SelItemDTO data) {
		String mpapperId = String.format(mapper, "updateData");
		int res = session.update(mpapperId, data);
		return res == 1? true : false;
	}
	
	public boolean deleteStaticsData(SelItemStaticsDTO data) {
		String mapperId = String.format(mapper, "deleteStaticsData");
		int res = session.delete(mapperId, data);
		return res >= 0 ? true : false;
	}
	
	public boolean deleteData(SelItemDTO data) {
		String mapperId = String.format(mapper, "deleteData");
		int res = session.delete(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateViewCnt(SelItemDTO data) {
		String mapperId = String.format(mapper, "updateViewCnt");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public SelItemDTO selectStatics(SelItemStaticsDTO data) {
		String mapperId = String.format(mapper, "selectStatics");
		SelItemDTO res = session.selectOne(mapperId, data);
		return res;
	}
	
	public boolean insertStatics(SelItemStaticsDTO data) {
		String mapperId = String.format(mapper, "insertStatics");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStatics(SelItemStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStatics");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}

	public boolean updateLike(SelItemDTO data) {
		String mapperId = String.format(mapper, "updateLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStaticsLike(SelItemStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStaticsLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
=======
//	private String mapper = ""
>>>>>>> branch '예진욱' of https://github.com/kmsz5813/kh_project.git
}
