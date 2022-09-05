package com.myweb.home.upload.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileUploadDAO {
	
	@Autowired
	private SqlSession session;
	
	public int getCount(int bid) {
		int res = session.selectOne("fileUploadMapper.getCount", bid);
		return res;
	}

	public boolean insertData(FileUploadDTO data) {
		int res = session.insert("fileUploadMapper.insertData", data);
		System.out.println(res);
		return res == 1 ? true : false;
	}

	public List<FileUploadDTO> selectDatas(int bId) {
		List<FileUploadDTO> res = session.selectList("fileUploadMapper.selectDatas", bId);
		return res;
	}

}