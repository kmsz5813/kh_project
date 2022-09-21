package com.myweb.home.community.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommunityQuestionDAO {
	
	@Autowired
	private SqlSession session;
	
	private String mapper = "communityQuestionMapper.%s";
	
	public List<CommunityQuestionDTO> selectAll() {
		String mapperId = String.format(mapper, "selectAll");
		List<CommunityQuestionDTO> res = session.selectList(mapperId);
		return res;
	}
	
	public CommunityQuestionDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectData");
		CommunityQuestionDTO res = session.selectOne(mapperId, id);
		return res;
	}
	
	public int getNextSeq() {
		String mapperId = String.format(mapper, "getNextSeq");
		int seq = session.selectOne(mapperId);
		return seq;
	}
	
	public boolean insertData(CommunityQuestionDTO data) {
		String mapperId = String.format(mapper, "insertData");
		System.out.println("insertData: " + data);
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateData(CommunityQuestionDTO data) {
		String mapperId = String.format(mapper, "updateData");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean deleteStaticsData(CommunityQuestionStaticsDTO data) {
		String mapperId = String.format(mapper, "deleteStaticsData");
		int res = session.delete(mapperId, data);
		return res >= 0 ? true : false;
	}
	
	public boolean deleteData(CommunityQuestionDTO data) {
		String mapperId = String.format(mapper, "deleteData");
		int res = session.delete(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateViewCnt(CommunityQuestionDTO data) {
		String mapperId = String.format(mapper, "updateViewCnt");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public CommunityQuestionStaticsDTO selectStatics(CommunityQuestionStaticsDTO data) {
		String mapperId = String.format(mapper, "selectStatics");
		CommunityQuestionStaticsDTO res = session.selectOne(mapperId, data);
		return res;
	}
	
	public boolean insertStatics(CommunityQuestionStaticsDTO data) {
		String mapperId = String.format(mapper, "insertStatics");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStatics(CommunityQuestionStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStatics");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}

	public boolean updateLike(CommunityQuestionDTO data) {
		String mapperId = String.format(mapper, "updateLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStaticsLike(CommunityQuestionStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStaticsLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
}
