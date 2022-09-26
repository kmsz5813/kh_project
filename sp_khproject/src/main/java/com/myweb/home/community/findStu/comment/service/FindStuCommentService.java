package com.myweb.home.community.findStu.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.community.findStu.comment.model.FindStuCommentDAO;
import com.myweb.home.community.findStu.comment.model.FindStuCommentDTO;

@Service
public class FindStuCommentService {

	@Autowired
	private FindStuCommentDAO dao;
	
	public int add(FindStuCommentDTO data) {
		boolean result = dao.insertData(data);
		
		if(result) {
			return data.getComment_Id();
		}
		return -1;
	}
	
	public FindStuCommentDTO getData(int id) {
		FindStuCommentDTO data = dao.selectData(id);
		return data;
	}
	
	public List<FindStuCommentDTO> getDatas(int id) {
		List<FindStuCommentDTO> datas = dao.selectDatas(id);
		return datas;
	}
	
	public boolean remove(FindStuCommentDTO data) {
		boolean result = dao.deleteData(data);
		return result;
	}
	
	public boolean modify(FindStuCommentDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}
}
