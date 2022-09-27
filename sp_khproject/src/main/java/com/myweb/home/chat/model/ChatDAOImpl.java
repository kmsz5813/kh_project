package com.myweb.home.chat.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDAOImpl implements ChatDAO {
	
	@Autowired
	private SqlSession session;

	private String namespace = "ChatMapper";

	@Override
	public List<ChatVO> list() {
		return session.selectList(namespace + ".list");
	}

	@Override
	public void insert(ChatVO vo) {
		System.out.println("dao에서의:" + vo);
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public List<ChatVO> select(ChatVO data) {
		return session.selectList(namespace + ".select", data);
	}

	@Override
	public List<ChatVO> sameSelect(ChatVO data) {
		return session.selectList(namespace + ".sameSelect", data);
	}


//	@Override
//	public int last() {
//		return session.selectOne(namespace + ".last");
//	}


}
