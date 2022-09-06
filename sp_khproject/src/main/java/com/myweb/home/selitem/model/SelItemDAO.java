package com.myweb.home.selitem.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SelItemDAO {

	
	@Autowired
	private SqlSession session;
	
	private String mapper = "selitemMapper.%s";
	
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
	
	public boolean deleteStaticsData(SelItemDTO data) {
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
	
}
