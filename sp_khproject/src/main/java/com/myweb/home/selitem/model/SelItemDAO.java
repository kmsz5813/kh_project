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
	//조회기능
	public List<Object> getData(SelItemDTO data) {
		
		String mapperId = String.format(mapper, "getData");
		List<Object> result = session.selectList(mapperId, data);
		return result;
	}
	//조회기능
	public List selectData(String selectData) {
		String mapperId = String.format(mapper, "selectData");
		List<Object> result = session.selectList(mapperId, selectData);
		return result;
	}
	//조회기능
	public List locationData(String locationData) {
		String mapperId = String.format(mapper, "locationData");
		List<Object> result = session.selectList(mapperId, locationData);
		return result;
	}
	//조회기능
	public List searchData(String search) {
		
		String mapperId = String.format(mapper, "searchData");
		List<Object> result = session.selectList(mapperId, search);
		return result;
	}
	
	
	public List<SelItemDTO> selectAll() {
		String mapperId = String.format(mapper, "selectAll");
		List<SelItemDTO> res = session.selectList(mapperId);
		return res;
	}
	
	public SelItemDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectIdData");
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
	
	
	//데이터삭제
	public boolean deleteData(int id) {
		String mapperId = String.format(mapper, "deleteData");
		int res = session.delete(mapperId, id);
		return res == 1 ? true : false;
	}
	
	//statics 조회-----------조회수가 추가되어 있는지 안되어 있는지
	public SelItemStaticsDTO selectStatics(SelItemStaticsDTO data) {
		String mapperId = String.format(mapper, "selectStatics");
		SelItemStaticsDTO res = session.selectOne(mapperId, data);
		return res;
	}
	
	//테이블에 값넣어주기 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public boolean insertStatics(SelItemStaticsDTO staticsData) {
		String mapperId = String.format(mapper, "insertStatics");
		int res = session.insert(mapperId, staticsData);
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

	public List<SelItemDTO> searchName(String ac_name) {
		String mapperId = String.format(mapper, "searchName");
		List<SelItemDTO> datas = session.selectList(mapperId, ac_name);
		return datas;
	}

	public boolean plusCount(int itemid) {
		String mapperId = String.format(mapper, "plusCount");
		int res = session.update(mapperId, itemid);
		return res == 1 ? true : false;
	}

	public boolean viewCnt(SelItemDTO itemdata) {
		String mapperId = String.format(mapper, "viewCnt");
		int res = session.update(mapperId, itemdata);
		return res == 1 ? true : false;
	}


}
