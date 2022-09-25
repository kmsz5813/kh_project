package com.myweb.home.message.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myweb.home.socket.ChattingCS;

@Repository
public class MessageDAO {

	@Autowired
	private SqlSession sqlSession;
	

	
	private String mapper = "Chat.%s";
	
	// 채팅 대화 DB저장
		public int insertChatting(String message) {
			return sqlSession.insert("Chat.insertChatting", message);
		}
	
	// 채팅 내용 조회
		public List<ChattingCS> selectChatting(ChattingCS ch) {
			return sqlSession.selectList("ChattingCS.selectChatting", ch);
		}
		

}
