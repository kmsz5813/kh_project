package com.myweb.home.community.findPro.comment.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FindProCommentDAO {

	@Autowired
	private SqlSession session;
	
	private String mapper = "findProCommentMapper.%s";
	
	public boolean insertData(FindProCommentDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	

	public FindProCommentDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectData");
		FindProCommentDTO res = session.selectOne(mapperId, id);
		return res;
	}

	public List<FindProCommentDTO> selectDatas(int id) {
		String mapperId = String.format(mapper, "selectDatas");
		List<FindProCommentDTO> res = session.selectList(mapperId, id);
		return res;
	}
	
	public boolean deleteData(FindProCommentDTO data) {
		String mapperId = String.format(mapper, "deleteData");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateData(FindProCommentDTO data) {
		String mapperId = String.format(mapper, "updateData");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
}
