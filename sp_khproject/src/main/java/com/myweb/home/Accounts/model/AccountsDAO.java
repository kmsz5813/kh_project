package com.myweb.home.Accounts.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myweb.home.purchase.model.UsePointVO;
import com.myweb.home.selitem.model.SelItemStaticsDTO;

@Repository
public class AccountsDAO {
	
	@Autowired
	private SqlSession session = null;
	
	private String mapper = "acMapper.%s";
	
	public List<AccountsDTO> selectAll() {
		String mapperId = String.format(mapper, "selectAll");
		
		List<AccountsDTO> datas = session.selectList(mapperId);
		
		
		return datas;
	}
	
	public boolean insertData(AccountsDTO data) {

		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}

	public AccountsDTO selectLogin(AccountsDTO data) {
		String mapperId = String.format(mapper, "selectLogin");
		AccountsDTO result = session.selectOne(mapperId, data);
		return result;
	}

	public AccountsDTO idcheck(String id) {
		String mapperId = String.format(mapper, "getData");
		AccountsDTO result = session.selectOne(mapperId, id);
		return result;
	}
	
	public AccountsDTO namecheck(String name) {
		String mapperId = String.format(mapper, "nameData");
		AccountsDTO result = session.selectOne(mapperId, name);
		return result;
	}
	
	public void deleteData(AccountsDTO data) {
		String mapperId = String.format(mapper, "deleteData");
		session.delete(mapperId, data);
	}

	public boolean modifyData(AccountsDTO data) {
		String mapperId = String.format(mapper, "modifyData");
		int res = session.update(mapperId, data);
		return res >= 1 ? true : false;
	}
	
	public boolean modifyPw(AccountsDTO data) {
		String mapperId = String.format(mapper, "modifyPw");
		int res = session.update(mapperId, data);
		return res >= 1 ? true : false;
	}

	public AccountsDTO getEmail(AccountsDTO data) {
		String mapperId = String.format(mapper, "getEmail");
		AccountsDTO result = session.selectOne(mapperId, data);
		return result;
	}

	public boolean addBlacklist(String id) {
		String mapperId = String.format(mapper, "addBlacklist");
		session.delete(mapperId, id);
		return false;
	}

	public String getIp(String id) {
		String mapperId = String.format(mapper, "getIp");
		String ip = session.selectOne(mapperId, id);
		System.out.println(ip); // 여기서 null 값이 뜸
		return ip;
	}

	public String getNameFromEmail(String sellerEmail) {
		String mapperId = String.format(mapper, "getNameFromEmail");
		String name = session.selectOne(mapperId, sellerEmail); 
		return name;
	}

	public boolean usePoint(UsePointVO usingpoint) {
		String mapperId = String.format(mapper, "usePoint");
		int res = session.update(mapperId, usingpoint);
		return res == 1 ? true : false;
	}

	//좋아요했던거 조회할수 있게끔 
	public List<AccountsDTO> getLikeData(AccountsDTO acData) {
		String mapperId = String.format(mapper, "selectLike");
		
		List<AccountsDTO> datas = session.selectList(mapperId, acData);
		
		return datas;
	}

	public List<SelItemStaticsDTO> likeDatas(String ac_name) {
		String mapperId = String.format(mapper, "likeDatas");
		List<SelItemStaticsDTO> list = session.selectList(mapperId, ac_name);
		return list;
	}






}
