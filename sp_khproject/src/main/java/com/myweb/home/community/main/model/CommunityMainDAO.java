package com.myweb.home.community.main.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommunityMainDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	// 공지사항
	public List<Map<String, Object>> selectNotice() {
		return sqlSession.selectList("CommunityMain.selectNotice");
	}

	// 질문게시판
	public List<Map<String, Object>> selectQuestion() {
		return sqlSession.selectList("CommunityMain.selectQuestion");
	}

	// 레슨자 찾아요
	public List<Map<String, Object>> selectFindStu() {
		return sqlSession.selectList("CommunityMain.selectFindStu");
	}

	// 전문가 찾아요
	public List<Map<String, Object>> selectFindPro() {
		return sqlSession.selectList("CommunityMain.selectFindPro");
	}

	// 일상
	public List<Map<String, Object>> selectLife() {
		return sqlSession.selectList("CommunityMain.selectLife");
	}

}
