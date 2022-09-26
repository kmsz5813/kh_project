package com.myweb.home.community.findPro.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommunityFindProDAO {
	
	@Autowired
	private SqlSession session;
	
	private String mapper = "communityFindProMapper.%s";
	
	public List<CommunityFindProDTO> selectAll() {
		String mapperId = String.format(mapper, "selectAll");
		List<CommunityFindProDTO> res = session.selectList(mapperId);
		return res;
	}
	
	public CommunityFindProDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectData");
		CommunityFindProDTO res = session.selectOne(mapperId, id);
		return res;
	}
	
	public int getNextSeq() {
		String mapperId = String.format(mapper, "getNextSeq");
		int seq = session.selectOne(mapperId);
		return seq;
	}
	
	public boolean insertData(CommunityFindProDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateData(CommunityFindProDTO data) {
		String mapperId = String.format(mapper, "updateData");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean deleteStaticsData(CommunityFindProStaticsDTO data) {
		String mapperId = String.format(mapper, "deleteStaticsData");
		int res = session.delete(mapperId, data);
		return res >= 0 ? true : false;
	}
	
	public boolean deleteData(CommunityFindProDTO data) {
		String mapperId = String.format(mapper, "deleteData");
		int res = session.delete(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateViewCnt(CommunityFindProDTO data) {
		String mapperId = String.format(mapper, "updateViewCnt");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public CommunityFindProStaticsDTO selectStatics(CommunityFindProStaticsDTO data) {
		String mapperId = String.format(mapper, "selectStatics");
		CommunityFindProStaticsDTO res = session.selectOne(mapperId, data);
		return res;
	}
	
	public boolean insertStatics(CommunityFindProStaticsDTO data) {
		String mapperId = String.format(mapper, "insertStatics");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStatics(CommunityFindProStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStatics");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}

	public boolean updateLike(CommunityFindProDTO data) {
		String mapperId = String.format(mapper, "updateLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStaticsLike(CommunityFindProStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStaticsLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
}
