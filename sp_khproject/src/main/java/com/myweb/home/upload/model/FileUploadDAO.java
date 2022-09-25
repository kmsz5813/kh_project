package com.myweb.home.upload.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileUploadDAO {
	
	@Autowired
	private SqlSession session;
	
	public int getCount(int file_bid) {
		int res = session.selectOne("fileUploadMapper.getCount",file_bid);
		return res;
	}

	public boolean insertData(FileUploadDTO data) {
		int res = session.insert("fileUploadMapper.insertData", data);
		System.out.println(res);
		return res == 1 ? true : false;
	}

	public List<FileUploadDTO> selectDatas(int file_bId) {
		List<FileUploadDTO> res = session.selectList("fileUploadMapper.selectDatas", file_bId);
		return res;
	}

	public boolean updateData(FileUploadDTO data) {
		int res = session.update("fileUploadMapper.updateData", data);
		System.out.println(res);
		return res == 1 ? true : false;
	}

}