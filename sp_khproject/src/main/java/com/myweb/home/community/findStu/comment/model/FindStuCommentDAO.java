package com.myweb.home.community.findStu.comment.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FindStuCommentDAO {

	@Autowired
	private SqlSession session;
	
	private String mapper = "findStuCommentMapper.%s";
	
	public boolean insertData(FindStuCommentDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	

	public FindStuCommentDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectData");
		FindStuCommentDTO res = session.selectOne(mapperId, id);
		return res;
	}

	public List<FindStuCommentDTO> selectDatas(int id) {
		String mapperId = String.format(mapper, "selectDatas");
		List<FindStuCommentDTO> res = session.selectList(mapperId, id);
		return res;
	}
	
	public boolean deleteData(FindStuCommentDTO data) {
		String mapperId = String.format(mapper, "deleteData");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateData(FindStuCommentDTO data) {
		String mapperId = String.format(mapper, "updateData");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
}
