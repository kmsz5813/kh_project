package com.myweb.home.community.question.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.community.question.model.CommunityQuestionDAO;
import com.myweb.home.community.question.model.CommunityQuestionDTO;
import com.myweb.home.community.question.model.CommunityQuestionStaticsDTO;

@Service
public class CommunityQuestionService {

	@Autowired
	private CommunityQuestionDAO dao;

	public List<CommunityQuestionDTO> getAll() {
		List<CommunityQuestionDTO> datas = dao.selectAll();
		return datas;
	}
	
	public CommunityQuestionDTO getData(int id) {
		CommunityQuestionDTO data = dao.selectData(id);
		return data;
	}
	
	public int add(CommunityQuestionDTO data) {
		int seq = dao.getNextSeq();
		data.setQuestion_Id(seq);
		
		boolean result = dao.insertData(data);
		
		if(result) {
			return data.getQuestion_Id();
		}
		return -1;
	}
	
	public boolean modify(CommunityQuestionDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}
	
	public boolean remove(CommunityQuestionDTO data) {
		CommunityQuestionStaticsDTO staticsData = new CommunityQuestionStaticsDTO();
		staticsData.setQuestion_bId(data.getQuestion_Id());
		
		dao.deleteStaticsData(staticsData);
		boolean result = dao.deleteData(data);
		
		return result;
	}
	
	@Transactional
	public void incViewCnt(HttpSession session, CommunityQuestionDTO data) {
		CommunityQuestionStaticsDTO staticsData = new CommunityQuestionStaticsDTO();
		staticsData.setQuestion_bId(data.getQuestion_Id());
		staticsData.setUser_Name(((AccountsDTO)session.getAttribute("loginData")).getAc_name());
		
		staticsData = dao.selectStatics(staticsData);
		
		boolean result = false;
		if(staticsData == null) {
			result = dao.updateViewCnt(data);
			if(!result) {
				throw new RuntimeException("조회수 통계 업데이트 처리에 문제가 발생 하였습니다.");
			}
			
			staticsData = new CommunityQuestionStaticsDTO();
			staticsData.setQuestion_bId(data.getQuestion_Id());
			staticsData.setUser_Name(((AccountsDTO)session.getAttribute("loginData")).getAc_name());
			result = dao.insertStatics(staticsData);
			if(!result) {
				throw new RuntimeException("조회수 통계 추가 처리에 문제가 발생 하였습니다.");
			}
		} else {
			long timeDiff = new Date().getTime() - staticsData.getQuestion_latestViewDate().getTime();
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
			data.setQuestion_view(data.getQuestion_view() + 1);
		}
	}
	
	public void incLike(HttpSession session, CommunityQuestionDTO data) {
		AccountsDTO acData = (AccountsDTO)session.getAttribute("loginData");
		
		CommunityQuestionStaticsDTO staticsData = new CommunityQuestionStaticsDTO();
		staticsData.setQuestion_bId(data.getQuestion_Id());
		staticsData.setUser_Name(acData.getAc_name());
		
		staticsData = dao.selectStatics(staticsData);
		
		// 이전에 추천을 했는지 안 했는지 확인
		if(staticsData.isQuestion_liked()) {
			// 이전에 추천을 한 기록이 있으면 -> 추천 취소로 전환
			staticsData.setQuestion_liked(false);
			data.setQuestion_like(data.getQuestion_like() - 1);
		} else {
			// 이전에 추천을 한 기록이 없으면 -> 추천으로 전환
			staticsData.setQuestion_liked(true);
			data.setQuestion_like(data.getQuestion_like() + 1);
		}
		
		dao.updateStaticsLike(staticsData);
		boolean result = dao.updateLike(data);
	}


}
