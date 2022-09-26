package com.myweb.home.community.life.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.community.life.comment.model.LifeCommentDAO;
import com.myweb.home.community.life.comment.model.LifeCommentDTO;


@Service
public class LifeCommentService {

	@Autowired
	private LifeCommentDAO dao;
	
	public int add(LifeCommentDTO data) {
		boolean result = dao.insertData(data);
		
		if(result) {
			return data.getComment_Id();
		}
		return -1;
	}
	
	public LifeCommentDTO getData(int id) {
		LifeCommentDTO data = dao.selectData(id);
		return data;
	}
	
	public List<LifeCommentDTO> getDatas(int id) {
		List<LifeCommentDTO> datas = dao.selectDatas(id);
		return datas;
	}
	
	public boolean remove(LifeCommentDTO data) {
		boolean result = dao.deleteData(data);
		return result;
	}
	
	public boolean modify(LifeCommentDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}
}
