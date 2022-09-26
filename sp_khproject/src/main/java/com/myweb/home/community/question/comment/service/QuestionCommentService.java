package com.myweb.home.community.question.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.community.question.comment.model.QuestionCommentDAO;
import com.myweb.home.community.question.comment.model.QuestionCommentDTO;

@Service
public class QuestionCommentService {

	@Autowired
	private QuestionCommentDAO dao;
	
	public int add(QuestionCommentDTO data) {
		boolean result = dao.insertData(data);
		
		if(result) {
			return data.getComment_Id();
		}
		return -1;
	}
	
	public QuestionCommentDTO getData(int id) {
		QuestionCommentDTO data = dao.selectData(id);
		return data;
	}
	
	public List<QuestionCommentDTO> getDatas(int id) {
		List<QuestionCommentDTO> datas = dao.selectDatas(id);
		return datas;
	}
	
	public boolean remove(QuestionCommentDTO data) {
		boolean result = dao.deleteData(data);
		return result;
	}
	
	public boolean modify(QuestionCommentDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}
}
