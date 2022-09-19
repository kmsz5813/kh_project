package com.myweb.home.community.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.myweb.home.common.util.PagingCommunity;
import com.myweb.home.community.model.CommunityNoticeDTO;


public interface CommunityNoticeAdminService {
	
	//공지사항 전체 조회
		public List<CommunityNoticeDTO> selectNoticeList(PagingCommunity paging);

		//공지사항 상세 페이지
		public CommunityNoticeDTO selectNoticeone(int noticeNo);

		//공지사항 글쓰기
		public int insertNotice(CommunityNoticeDTO notice);

		//공지사항 수정하기
		public int updateNotice(CommunityNoticeDTO notice);

		//공지사항 삭제하기(상세보기 페이지에서 삭제)
		public int deleteNotice(int noticeNo);

		//공지사항 제목 내용 검색 결과
		public List<CommunityNoticeDTO> selectSearchNotice(Map<String, Object> searchMap);

		//공지사항 전체 리스트 페이징
		public PagingCommunity noticeListPaging(HttpServletRequest req);

		//공지사항 검색 리스트 페이징
		public PagingCommunity noticeSearchPaging(HttpServletRequest req, String keyword);

}
