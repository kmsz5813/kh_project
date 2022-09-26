package com.myweb.home.community.findPro.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.community.findPro.model.CommunityFindProDAO;
import com.myweb.home.community.findPro.model.CommunityFindProDTO;
import com.myweb.home.community.findPro.model.CommunityFindProStaticsDTO;


@Service
public class CommunityFindProService {

	@Autowired
	private CommunityFindProDAO dao;

	public List<CommunityFindProDTO> getAll() {
		List<CommunityFindProDTO> datas = dao.selectAll();
		return datas;
	}
	
	public CommunityFindProDTO getData(int id) {
		CommunityFindProDTO data = dao.selectData(id);
		return data;
	}
	
	public int add(CommunityFindProDTO data) {
		int seq = dao.getNextSeq();
		data.setFindPro_Id(seq);
		
		boolean result = dao.insertData(data);
		
		if(result) {
			return data.getFindPro_Id();
		}
		return -1;
	}
	
	public boolean modify(CommunityFindProDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}
	
	public boolean remove(CommunityFindProDTO data) {
		CommunityFindProStaticsDTO staticsData = new CommunityFindProStaticsDTO();
		staticsData.setFindPro_bId(data.getFindPro_Id());
		
		dao.deleteStaticsData(staticsData);
		boolean result = dao.deleteData(data);
		
		return result;
	}
	
	@Transactional
	public void incViewCnt(HttpSession session, CommunityFindProDTO data) {
		CommunityFindProStaticsDTO staticsData = new CommunityFindProStaticsDTO();
		staticsData.setFindPro_bId(data.getFindPro_Id());
		staticsData.setUser_Name(((AccountsDTO)session.getAttribute("loginData")).getAc_name());
		
		staticsData = dao.selectStatics(staticsData);
		
		boolean result = false;
		if(staticsData == null) {
			result = dao.updateViewCnt(data);
			if(!result) {
				throw new RuntimeException("조회수 통계 업데이트 처리에 문제가 발생 하였습니다.");
			}
			
			staticsData = new CommunityFindProStaticsDTO();
			staticsData.setFindPro_bId(data.getFindPro_Id());
			staticsData.setUser_Name(((AccountsDTO)session.getAttribute("loginData")).getAc_name());
			result = dao.insertStatics(staticsData);
			if(!result) {
				throw new RuntimeException("조회수 통계 추가 처리에 문제가 발생 하였습니다.");
			}
		} else {
			long timeDiff = new Date().getTime() - staticsData.getFindPro_latestViewDate().getTime();
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
			data.setFindPro_view(data.getFindPro_view() + 1);
		}
	}
	
	public void incLike(HttpSession session, CommunityFindProDTO data) {
		AccountsDTO acData = (AccountsDTO)session.getAttribute("loginData");
		
		CommunityFindProStaticsDTO staticsData = new CommunityFindProStaticsDTO();
		staticsData.setFindPro_bId(data.getFindPro_Id());
		staticsData.setUser_Name(acData.getAc_name());
		
		staticsData = dao.selectStatics(staticsData);
		
		// 이전에 추천을 했는지 안 했는지 확인
		if(staticsData.isFindPro_liked()) {
			// 이전에 추천을 한 기록이 있으면 -> 추천 취소로 전환
			staticsData.setFindPro_liked(false);
			data.setFindPro_like(data.getFindPro_like() - 1);
		} else {
			// 이전에 추천을 한 기록이 없으면 -> 추천으로 전환
			staticsData.setFindPro_liked(true);
			data.setFindPro_like(data.getFindPro_like() + 1);
		}
		
		dao.updateStaticsLike(staticsData);
		boolean result = dao.updateLike(data);
	}


}