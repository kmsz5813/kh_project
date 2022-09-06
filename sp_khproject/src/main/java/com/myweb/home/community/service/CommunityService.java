package com.myweb.home.community.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.community.model.CommunityDAO;
import com.myweb.home.community.model.CommunityDTO;
import com.myweb.home.community.model.CommunityStaticsDTO;

@Service
public class CommunityService {
	@Autowired
	private CommunityDAO dao;
	
	public List<CommunityDTO> getAll() {
		List<CommunityDTO> datas = dao.selectAll();
		return datas;
	}
	
	public CommunityDTO getData(int cum_id) {
		CommunityDTO data = dao.selectData(cum_id);
		return data;
	}
	
	public int add(CommunityDTO data) {
		int seq = dao.getNextSeq();
		data.setCum_id(seq);
		
		boolean result = dao.insertData(data);
		
		if(result) {
			return data.getCum_id();
		}
		return -1;
	}
	
	public boolean modify(CommunityDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}
	
	public boolean remove(CommunityDTO data) {
		CommunityStaticsDTO staticsData = new CommunityStaticsDTO();
		staticsData.setCum_bId(data.getCum_id());
		
		dao.deleteStaticsData(staticsData);
		boolean result = dao.deleteData(data);
		
		return result;
	}
	
	@Transactional
	public void incViewCnt(HttpSession session, CommunityDTO data) {
		CommunityStaticsDTO staticsData = new CommunityStaticsDTO();
		staticsData.setCum_bId(data.getCum_id());
		staticsData.setCum_name(((AccountsDTO)session.getAttribute("loginData")).getAc_name());
		
		staticsData = dao.selectStatics(staticsData);
		
		boolean result = false;
		if(staticsData == null) {
			result = dao.updateViewCnt(data);
			if(!result) {
				throw new RuntimeException("조회수 통계 업데이트 처리에 문제가 발생 하였습니다.");
			}
			
			staticsData = new CommunityStaticsDTO();
			staticsData.setCum_bId(data.getCum_id());
			staticsData.setCum_name(((AccountsDTO)session.getAttribute("loginData")).getAc_name());
			result = dao.insertStatics(staticsData);
			if(!result) {
				throw new RuntimeException("조회수 통계 추가 처리에 문제가 발생 하였습니다.");
			}
		} else {
			long timeDiff = new Date().getTime() - staticsData.getCum_latestViewDate().getTime();
			if(timeDiff / (1000 * 60 * 60 * 24) >= 7) {
				result = dao.updateViewCnt(data);
				if(!result) {
					throw new RuntimeException("조회수 업데이트 처리에 문제가 발생 하였습니다.");
				}
				result = dao.updateStatics(staticsData);
				if(!result) {
					throw new RuntimeException("조회수 통계 업데이트 처리에 문제가 발생 하였습니다.");
				}
			}
		}
		
		if(result) {
			data.setCum_view(data.getCum_view() + 1);
		}
	}
	
	public void incLike(HttpSession session, CommunityDTO data) {
		AccountsDTO acData = (AccountsDTO)session.getAttribute("loginData");
		
		CommunityStaticsDTO staticsData = new CommunityStaticsDTO();
		staticsData.setCum_bId(data.getCum_id());
		staticsData.setCum_name(acData.getAc_name());
		
		staticsData = dao.selectStatics(staticsData);
		
		// 이전에 추천을 했는지 안 했는지 확인
		if(staticsData.isCum_liked()) {
			// 이전에 추천을 한 기록이 있으면 -> 추천 취소로 전환
			staticsData.setCum_liked(false);
			data.setCum_like(data.getCum_like() - 1);
		} else {
			// 이전에 추천을 한 기록이 없으면 -> 추천으로 전환
			staticsData.setCum_liked(true);
			data.setCum_like(data.getCum_like() + 1);
		}
		
		dao.updateStaticsLike(staticsData);
		boolean result = dao.updateLike(data);
	}

}
