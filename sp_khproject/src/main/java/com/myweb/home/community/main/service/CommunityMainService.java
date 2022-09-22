package com.myweb.home.community.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.community.main.model.CommunityMainDAO;

@Service
public class CommunityMainService {

	@Autowired
	CommunityMainDAO communityMainDao;

	// 공지사항
	public List<Map<String, Object>> selectNotice() {
		return communityMainDao.selectNotice();
	}

	// 질문게시판
	public List<Map<String, Object>> selectQuestion() {
		return communityMainDao.selectQuestion();
	}

	// 레슨자 찾아요
	public List<Map<String, Object>> selectFindStu() {
		return communityMainDao.selectFindStu();
	}

	// 전문가 찾아요
	public List<Map<String, Object>> selectFindPro() {
		return communityMainDao.selectFindPro();
	}

	// 일상
	public List<Map<String, Object>> selectLife() {
		return communityMainDao.selectLife();
	}

}
