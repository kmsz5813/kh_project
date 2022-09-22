package com.myweb.home.community.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommunityFindStuDAO {
	
	@Autowired
	private SqlSession session;
	
	private String mapper = "communityFindStuMapper.%s";
	
	public List<CommunityFindStuDTO> selectAll() {
		String mapperId = String.format(mapper, "selectAll");
		List<CommunityFindStuDTO> res = session.selectList(mapperId);
		return res;
	}
	
	public CommunityFindStuDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectData");
		CommunityFindStuDTO res = session.selectOne(mapperId, id);
		return res;
	}
	
	public int getNextSeq() {
		String mapperId = String.format(mapper, "getNextSeq");
		int seq = session.selectOne(mapperId);
		return seq;
	}
	
	public boolean insertData(CommunityFindStuDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateData(CommunityFindStuDTO data) {
		String mapperId = String.format(mapper, "updateData");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean deleteStaticsData(CommunityFindStuStaticsDTO data) {
		String mapperId = String.format(mapper, "deleteStaticsData");
		int res = session.delete(mapperId, data);
		return res >= 0 ? true : false;
	}
	
	public boolean deleteData(CommunityFindStuDTO data) {
		String mapperId = String.format(mapper, "deleteData");
		int res = session.delete(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateViewCnt(CommunityFindStuDTO data) {
		String mapperId = String.format(mapper, "updateViewCnt");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public CommunityFindStuStaticsDTO selectStatics(CommunityFindStuStaticsDTO data) {
		String mapperId = String.format(mapper, "selectStatics");
		CommunityFindStuStaticsDTO res = session.selectOne(mapperId, data);
		return res;
	}
	
	public boolean insertStatics(CommunityFindStuStaticsDTO data) {
		String mapperId = String.format(mapper, "insertStatics");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStatics(CommunityFindStuStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStatics");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}

	public boolean updateLike(CommunityFindStuDTO data) {
		String mapperId = String.format(mapper, "updateLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStaticsLike(CommunityFindStuStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStaticsLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
}
