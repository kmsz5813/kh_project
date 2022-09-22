package com.myweb.home.community.question.comment.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class QuestionCommentDAO {

	@Autowired
	private SqlSession session;
	
	private String mapper = "questionCommentMapper.%s";
	
	public boolean insertData(QuestionCommentDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	

	public QuestionCommentDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectData");
		QuestionCommentDTO res = session.selectOne(mapperId, id);
		return res;
	}

	public List<QuestionCommentDTO> selectDatas(int id) {
		String mapperId = String.format(mapper, "selectDatas");
		List<QuestionCommentDTO> res = session.selectList(mapperId, id);
		return res;
	}
	
	public boolean deleteData(QuestionCommentDTO data) {
		String mapperId = String.format(mapper, "deleteData");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateData(QuestionCommentDTO data) {
		String mapperId = String.format(mapper, "updateData");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
}
