package com.myweb.home.chat.model;

import java.util.List;

public interface ChatDAO {
	
	public List<ChatVO> list();
	public void insert(ChatVO vo);
	public int last();
}

