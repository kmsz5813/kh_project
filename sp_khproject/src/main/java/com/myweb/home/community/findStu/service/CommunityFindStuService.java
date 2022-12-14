package com.myweb.home.community.findStu.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.community.findStu.model.CommunityFindStuDAO;
import com.myweb.home.community.findStu.model.CommunityFindStuDTO;
import com.myweb.home.community.findStu.model.CommunityFindStuStaticsDTO;



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
		if(session.getAttribute("loginData") != null) {
			staticsData.setUser_Name(((AccountsDTO)session.getAttribute("loginData")).getAc_name());
		}
		staticsData = dao.selectStatics(staticsData);
		
		boolean result = false;
		if(staticsData == null) {
			result = dao.updateViewCnt(data);
			if(!result) {
				throw new RuntimeException("????????? ?????? ???????????? ????????? ????????? ?????? ???????????????.");
			}
			
			staticsData = new CommunityFindStuStaticsDTO();
			staticsData.setFindStu_bId(data.getFindStu_Id());if(session.getAttribute("loginData") != null) {
				staticsData.setUser_Name(((AccountsDTO)session.getAttribute("loginData")).getAc_name());
			}
			result = dao.insertStatics(staticsData);
			if(!result) {
				throw new RuntimeException("????????? ?????? ?????? ????????? ????????? ?????? ???????????????.");
			}
		} else {
			long timeDiff = new Date().getTime() - staticsData.getFindStu_latestViewDate().getTime();
			if(timeDiff / (1000 * 60 * 60 * 24) >= 7) {
				result = dao.updateViewCnt(data);
				if(!result) {
					throw new RuntimeException("????????? ???????????? ????????? ????????? ?????? ???????????????.");
				}
				result = dao.updateStatics(staticsData);
				if(!result) {
					throw new RuntimeException("????????? ?????? ???????????? ????????? ????????? ?????? ???????????????.");
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
		
		// ????????? ????????? ????????? ??? ????????? ??????
		if(staticsData.isFindStu_liked()) {
			// ????????? ????????? ??? ????????? ????????? -> ?????? ????????? ??????
			staticsData.setFindStu_liked(false);
			data.setFindStu_like(data.getFindStu_like() - 1);
		} else {
			// ????????? ????????? ??? ????????? ????????? -> ???????????? ??????
			staticsData.setFindStu_liked(true);
			data.setFindStu_like(data.getFindStu_like() + 1);
		}
		
		dao.updateStaticsLike(staticsData);
		boolean result = dao.updateLike(data);
	}


}
