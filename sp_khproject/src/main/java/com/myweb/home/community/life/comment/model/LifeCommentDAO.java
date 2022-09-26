package com.myweb.home.community.life.comment.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class LifeCommentDAO {

	@Autowired
	private SqlSession session;
	
	private String mapper = "lifeCommentMapper.%s";
	
	public boolean insertData(LifeCommentDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	

	public LifeCommentDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectData");
		LifeCommentDTO res = session.selectOne(mapperId, id);
		return res;
	}

	public List<LifeCommentDTO> selectDatas(int id) {
		String mapperId = String.format(mapper, "selectDatas");
		List<LifeCommentDTO> res = session.selectList(mapperId, id);
		return res;
	}
	
	public boolean deleteData(LifeCommentDTO data) {
		String mapperId = String.format(mapper, "deleteData");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateData(LifeCommentDTO data) {
		String mapperId = String.format(mapper, "updateData");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
}
