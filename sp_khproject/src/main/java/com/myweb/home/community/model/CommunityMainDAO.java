package com.myweb.home.community.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommunityMainDAO {

	@Autowired
    SqlSessionTemplate sqlSession;
	
    
    //공지사항
	public List<Map<String, Object>> selectNotice() {
		return sqlSession.selectList("CommunityMain.selectNotice");
	}

	//질문게시판
	//public List<Map<String, Object>> selectQuestion() {
	//	return sqlSession.selectList("CommunityMain.selectQuestion");
	//}

	
}
