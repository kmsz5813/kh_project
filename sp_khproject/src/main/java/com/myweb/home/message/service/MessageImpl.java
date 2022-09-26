package com.myweb.home.message.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.message.model.MessageDAO;
import com.myweb.home.socket.ChattingCS;

@Service
public class MessageImpl implements MessageService {
	
	@Autowired
	private MessageDAO dao;
	
//	// 채팅 대화 DB저장
//		@Override
//		public int insertChatting(chattingcs message) {
//			return dao.insertChatting(message);
//		}
//
//	// 채팅 내용 조회
//		@Override
//		public List<ChattingCS> selectChatting(ChattingCS ch) {
//			return dao.selectChatting(ch);
//		}

	@Override
	public int insertChatting(ChattingCS ch) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ChattingCS> selectChatting(ChattingCS ch) {
		// TODO Auto-generated method stub
		return null;
	}


}
