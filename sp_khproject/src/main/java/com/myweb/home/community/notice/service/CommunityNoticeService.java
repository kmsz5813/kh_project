package com.myweb.home.community.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.common.util.PagingCommunity;
import com.myweb.home.community.notice.model.CommunityNoticeDAO;
import com.myweb.home.community.notice.model.CommunityNoticeDTO;

//사용자

@Service
public class CommunityNoticeService {

	@Autowired
	CommunityNoticeDAO communityNoticeDao;


	public List<CommunityNoticeDTO> getData() {
		// 데이터값을 가져오는것
		// CommunityNoticeDTO객체로 담는다.
		List<CommunityNoticeDTO> datas = communityNoticeDao.allDatas();

		// data값이 반환
		return datas;

	}

	public CommunityNoticeDTO getOneData(String notice_no) {

		CommunityNoticeDTO data = communityNoticeDao.getData(notice_no);

		if (data != null) {
			return data;
		}

		return null;
	}
}
