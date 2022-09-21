package com.myweb.home.community.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.common.util.PagingCommunity;
import com.myweb.home.community.model.AdminNoticeDAO;
import com.myweb.home.community.model.CommunityNoticeDTO;

@Service
public class AdminNoticeServiceImpl implements AdminNoticeService{
	
	@Autowired
	private AdminNoticeDAO noticeDao;
	
	@Override
	public List<CommunityNoticeDTO> selectNoticeList(PagingCommunity paging) {
		List<CommunityNoticeDTO> noticeList = noticeDao.selectNoticeList(paging);
		return noticeList;
	}
	
	@Override
	public CommunityNoticeDTO selectNoticeone(int noticeNo) {
		CommunityNoticeDTO noticeone = noticeDao.selectNoticeone(noticeNo);
		return noticeone;
	}
	
	@Override
	public int insertNotice(CommunityNoticeDTO notice) {
		int res = noticeDao.insertNotice(notice);
		return res;
	}
	
	@Override
	public int updateNotice(CommunityNoticeDTO notice) {
		int res = noticeDao.updateNotice(notice);
		return res;
	}
	
	@Override
	public int deleteNotice(int noticeNo) {
		int res = noticeDao.deleteNotice(noticeNo);
		return res;
	}
	
	@Override
	public List<CommunityNoticeDTO> selectSearchNotice(Map<String, Object> searchMap) {
		List<CommunityNoticeDTO> noticeList = noticeDao.selectSearchNotice(searchMap);
		return noticeList;
	}
	
	@Override
	public PagingCommunity noticeListPaging(HttpServletRequest req) {
			
		//전달 파라미터  curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0 ;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		
		//classbooking 테이블의 총 게시글 수를 조회한다
		int totalCount = noticeDao.selectCntNoticeAll();
		
		//paging객체 생성
		PagingCommunity paging = new PagingCommunity(totalCount, curPage);
		
		//계산된 Paging 객체 반환
		return paging;
	}
	
	@Override
	public PagingCommunity noticeSearchPaging(HttpServletRequest req, String keyword) {
		//전달 파라미터  curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0 ;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		
		//classbooking 테이블의 총 게시글 수를 조회한다
		int totalCount = noticeDao.selectCntNoticeSearchAll(keyword);
		
		//paging객체 생성
		PagingCommunity paging = new PagingCommunity(totalCount, curPage);
		
		//계산된 Paging 객체 반환
		return paging;
	}

}
