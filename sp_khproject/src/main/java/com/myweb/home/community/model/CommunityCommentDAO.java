package com.myweb.home.community.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CommunityCommentDAO {
	
	@Autowired
	private SqlSession session;
	
	private String mapper = "communityCommentMapper.%s";
	
	public List<CommunityCommentDTO> selectDatas(int cum_com_id) {
		String mapId = String.format(mapper, "selectDatas");
		List<CommunityCommentDTO> res = session.selectList(mapId, cum_com_id);
		return res;
	}
	
	public CommunityCommentDTO selectData(int cum_com_id) {
		String mapId = String.format(mapper, "selectData");
		CommunityCommentDTO res = session.selectOne(mapId, cum_com_id);
		return res;
	}
	
	public int getNextSeq() {
		String mapperId = String.format(mapper, "getNextSeq");
		int seq = session.selectOne(mapperId);
		return seq;
	}
	
	public boolean insertData(CommunityCommentDTO data) {
		String mapId = String.format(mapper, "insertData");
		int res = session.insert(mapId, data);
		return res == 1 ? true : false;
	}

	public boolean updateData(CommunityCommentDTO data) {
		String mapId = String.format(mapper, "updateData");
		int res = session.update(mapId, data);
		return res == 1 ? true : false;
	}
	
	public boolean deleteStaticsData(CommunityCommentStaticsDTO data) {
		String mapperId = String.format(mapper, "deleteStaticsData");
		int res = session.delete(mapperId, data);
		return res >= 0 ? true : false;
	}
	
	public boolean deleteData(CommunityCommentDTO data) {
		String mapId = String.format(mapper, "deleteData");
		int res = session.update(mapId, data);
		return res == 1 ? true : false;
	}
	
	public CommunityCommentStaticsDTO selectStatics(CommunityCommentStaticsDTO data) {
		String mapperId = String.format(mapper, "selectStatics");
		CommunityCommentStaticsDTO res = session.selectOne(mapperId, data);
		return res;
	}
	
	public boolean insertStatics(CommunityCommentStaticsDTO data) {
		String mapperId = String.format(mapper, "insertStatics");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStatics(CommunityCommentStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStatics");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}

	public boolean updateLike(CommunityCommentDTO data) {
		String mapperId = String.format(mapper, "updateLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStaticsLike(CommunityCommentStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStaticsLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
}
