package com.myweb.home.chat.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDAOImpl implements ChatDAO {
	
	@Autowired
	SqlSession session;

	String namespace = "ChatMapper";

	@Override
	public List<ChatVO> list() {
		return session.selectList(namespace + ".list");
	}

	@Override
	public void insert(ChatVO vo) {
		session.insert(namespace + ".insert", vo);
	}


	@Override
	public int last() {
		return session.selectOne(namespace + ".last");
	}


}
