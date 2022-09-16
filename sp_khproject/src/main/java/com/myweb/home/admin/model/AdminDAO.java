package com.myweb.home.admin.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO {
	@Autowired
	private SqlSession session = null;
	
	private String mapper = "adminMapper.%s";

	public boolean addBlacklist(BlackDTO black) {
		String mapperId = String.format(mapper, "addBlacklist");
		session.insert(mapperId, black);
		return false;
	}

	public List<BlackDTO> find_ip_ban_list() {
		String mapperId = String.format(mapper, "getBannedIP");
		List<BlackDTO> datas = session.selectList(mapperId);
		return datas;
	}
}
