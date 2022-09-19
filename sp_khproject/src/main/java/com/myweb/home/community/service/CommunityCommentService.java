package com.myweb.home.community.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.community.model.CommunityCommentDAO;
import com.myweb.home.community.model.CommunityCommentDTO;
import com.myweb.home.community.model.CommunityCommentStaticsDTO;

@Service
public class CommunityCommentService {
	
	@Autowired
	private CommunityCommentDAO dao;
	
	public List<CommunityCommentDTO> getDatas(int cum_com_id) {
		List<CommunityCommentDTO> datas = dao.selectDatas(cum_com_id);
		return datas;
	}
	
	public CommunityCommentDTO getData(int cum_com_id) {
		CommunityCommentDTO data = dao.selectData(cum_com_id);
		return data;
	}
	
	public int add(CommunityCommentDTO data) {
		int seq = dao.getNextSeq();
		data.setCum_com_id(seq);
		
		boolean result = dao.insertData(data);
		
		if(result) {
			return data.getCum_com_id();
		}
		return -1;
	}

	public boolean modify(CommunityCommentDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}
	
	public boolean remove(CommunityCommentDTO data) {
		CommunityCommentStaticsDTO staticsData = new CommunityCommentStaticsDTO();
		staticsData.setCum_com_bId(data.getCum_com_id());
		
		dao.deleteStaticsData(staticsData);
		boolean result = dao.deleteData(data);
		
		return result;
	}

	public void incLike(HttpSession session, CommunityCommentDTO data) {
		AccountsDTO acData = (AccountsDTO)session.getAttribute("loginData");
		
		CommunityCommentStaticsDTO staticsData = new CommunityCommentStaticsDTO();
		staticsData.setCum_com_bId(data.getCum_com_id());
		staticsData.setCum_com_name(acData.getAc_name());
		
		staticsData = dao.selectStatics(staticsData);
		
		// 이전에 추천을 했는지 안 했는지 확인
		if(staticsData.isCum_com_liked()) {
			// 이전에 추천을 한 기록이 있으면 -> 추천 취소로 전환
			staticsData.setCum_com_liked(false);
			data.setCum_com_like(data.getCum_com_like() - 1);
		} else {
			// 이전에 추천을 한 기록이 없으면 -> 추천으로 전환
			staticsData.setCum_com_liked(true);
			data.setCum_com_like(data.getCum_com_like() + 1);
		}
		
		dao.updateStaticsLike(staticsData);
		boolean result = dao.updateLike(data);
	}
	

}
