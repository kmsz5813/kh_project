package com.myweb.home.community.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.common.util.PagingCommunity;
import com.myweb.home.community.model.CommunityNoticeDAO;
import com.myweb.home.community.model.CommunityNoticeDTO;

//사용자

@Service
public class CommunityNoticeService {

	@Autowired
	CommunityNoticeDAO communityNoticeDao;

	//모든 공지사항 수 조회 - 페이징
	public PagingCommunity noticeListPaging(int curPage) {
		
		//모든 공지사항 수 조회 - 페이징
		int totalCount = communityNoticeDao.selectCntNotice();
		
		//paging객체 생성
		PagingCommunity paging = new PagingCommunity(totalCount, curPage);
		
		//계산된 Paging 객체 반환
		return paging;
	}

	
	//모든 공지사항
	public List<CommunityNoticeDTO> selectNotice(PagingCommunity paging) {
		return communityNoticeDao.selectNotice(paging);
	}

	
	//공지사항 상세
	public CommunityNoticeDTO selectNoticeDetail(int no) {
		return communityNoticeDao.selectNoticeDetail(no);
	}


	//공지사항 검색 - 페이징
	public PagingCommunity noticeListSearchPaging(String keyword, int curPage) {
		
		//검색 후 총 게시글 수를 조회한다
		int totalCount = communityNoticeDao.selectCnNoticeSearch(keyword);
		
		//paging객체 생성
		PagingCommunity paging = new PagingCommunity(totalCount, curPage);
		
		//계산된 Paging 객체 반환
		return paging;
	}


	//공지사항 검색 결과
	public List<CommunityNoticeDTO> selectNoticeSearch(Map<String, Object> searchMap) {
		return communityNoticeDao.selectNoticeSearch(searchMap);
	}
}
