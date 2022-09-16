package com.myweb.home.community.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.community.model.CommunityMainDAO;


@Service
public class CommunityMainService {

	@Autowired
	CommunityMainDAO communityMainDao;

	
	//공지사항
	public List<Map<String, Object>> selectNotice() {
		return communityMainDao.selectNotice();
	}
	
	//질문게시판
	//public List<Map<String, Object>> selectQuestion() {
	//	return communityMainDao.selectQuestion();
	//}
	
}
