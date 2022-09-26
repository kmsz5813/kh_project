package com.myweb.home.community.findPro.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.community.findPro.comment.model.FindProCommentDAO;
import com.myweb.home.community.findPro.comment.model.FindProCommentDTO;

@Service
public class FindProCommentService {

	@Autowired
	private FindProCommentDAO dao;
	
	public int add(FindProCommentDTO data) {
		boolean result = dao.insertData(data);
		
		if(result) {
			return data.getComment_Id();
		}
		return -1;
	}
	
	public FindProCommentDTO getData(int id) {
		FindProCommentDTO data = dao.selectData(id);
		return data;
	}
	
	public List<FindProCommentDTO> getDatas(int id) {
		List<FindProCommentDTO> datas = dao.selectDatas(id);
		return datas;
	}
	
	public boolean remove(FindProCommentDTO data) {
		boolean result = dao.deleteData(data);
		return result;
	}
	
	public boolean modify(FindProCommentDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}
}
