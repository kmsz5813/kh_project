package com.myweb.home.message.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myweb.home.socket.ChattingCS;

@Service
public interface MessageService {

	int insertChatting(ChattingCS ch);
	
	List<ChattingCS> selectChatting(ChattingCS ch);

}
