package com.myweb.home.community.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.community.model.CommunityFindStuDAO;
import com.myweb.home.community.model.CommunityFindStuDTO;
import com.myweb.home.community.model.CommunityFindStuStaticsDTO;


@Service
public class CommunityFindStuService {

	@Autowired
	private CommunityFindStuDAO dao;

	public List<CommunityFindStuDTO> getAll() {
		List<CommunityFindStuDTO> datas = dao.selectAll();
		return datas;
	}
	
	public CommunityFindStuDTO getData(int id) {
		CommunityFindStuDTO data = dao.selectData(id);
		return data;
	}
	
	public int add(CommunityFindStuDTO data) {
		int seq = dao.getNextSeq();
		data.setFindStu_Id(seq);
		
		boolean result = dao.insertData(data);
		
		if(result) {
			return data.getFindStu_Id();
		}
		return -1;
	}
	
	public boolean modify(CommunityFindStuDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}
	
	public boolean remove(CommunityFindStuDTO data) {
		CommunityFindStuStaticsDTO staticsData = new CommunityFindStuStaticsDTO();
		staticsData.setFindStu_bId(data.getFindStu_Id());
		
		dao.deleteStaticsData(staticsData);
		boolean result = dao.deleteData(data);
		
		return result;
	}
	
	@Transactional
	public void incViewCnt(HttpSession session, CommunityFindStuDTO data) {
		CommunityFindStuStaticsDTO staticsData = new CommunityFindStuStaticsDTO();
		staticsData.setFindStu_bId(data.getFindStu_Id());
		staticsData.setUser_Name(((AccountsDTO)session.getAttribute("loginData")).getAc_name());
		
		staticsData = dao.selectStatics(staticsData);
		
		boolean result = false;
		if(staticsData == null) {
			result = dao.updateViewCnt(data);
			if(!result) {
				throw new RuntimeException("조회수 통계 업데이트 처리에 문제가 발생 하였습니다.");
			}
			
			staticsData = new CommunityFindStuStaticsDTO();
			staticsData.setFindStu_bId(data.getFindStu_Id());
			staticsData.setUser_Name(((AccountsDTO)session.getAttribute("loginData")).getAc_name());
			result = dao.insertStatics(staticsData);
			if(!result) {
				throw new RuntimeException("조회수 통계 추가 처리에 문제가 발생 하였습니다.");
			}
		} else {
			long timeDiff = new Date().getTime() - staticsData.getFindStu_latestViewDate().getTime();
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
			data.setFindStu_view(data.getFindStu_view() + 1);
		}
	}
	
	public void incLike(HttpSession session, CommunityFindStuDTO data) {
		AccountsDTO acData = (AccountsDTO)session.getAttribute("loginData");
		
		CommunityFindStuStaticsDTO staticsData = new CommunityFindStuStaticsDTO();
		staticsData.setFindStu_bId(data.getFindStu_Id());
		staticsData.setUser_Name(acData.getAc_name());
		
		staticsData = dao.selectStatics(staticsData);
		
		// 이전에 추천을 했는지 안 했는지 확인
		if(staticsData.isFindStu_liked()) {
			// 이전에 추천을 한 기록이 있으면 -> 추천 취소로 전환
			staticsData.setFindStu_liked(false);
			data.setFindStu_like(data.getFindStu_like() - 1);
		} else {
			// 이전에 추천을 한 기록이 없으면 -> 추천으로 전환
			staticsData.setFindStu_liked(true);
			data.setFindStu_like(data.getFindStu_like() + 1);
		}
		
		dao.updateStaticsLike(staticsData);
		boolean result = dao.updateLike(data);
	}


}
