package com.myweb.home.community.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.common.util.PagingCommunity;

@Repository
public class CommunityNoticeDAO {
	
//	@Autowired
//	SqlSessionTemplate sqlSession;

	@Autowired
	private SqlSession session = null;
	
//	//사용자
//	//모든 공지사항 수 조회 - 페이징
//	public int selectCntNotice() {
//		return sqlSession.selectOne("CommunityNotice.selectCntNotice");
//	}
//
//	
//	//모든 공지사항
//	public List<CommunityNoticeDTO> selectNotice(PagingCommunity paging) {
//		return sqlSession.selectList("CommunityNotice.selectNotice", paging);
//	}
//
//
//	//공지사항 상세
//	public CommunityNoticeDTO selectNoticeDetail(int no) {
//		return sqlSession.selectOne("CommunityNotice.selectNoticeDetail", no);
//	}
//
//
//	//공지사항 검색결과 수 
//	public int selectCnNoticeSearch(String keyword) {
//		return sqlSession.selectOne("CommunityNotice.selectCnNoticeSearch", keyword);
//	}
//
//
//	//공지사항 검색결과
//	public List<CommunityNoticeDTO> selectNoticeSearch(Map<String, Object> searchMap) {
//		return sqlSession.selectList("CommunityNotice.selectNoticeSearch", searchMap);
//	}

	//공지사항 저장되어 있는 모든값 가져오기
	
	public List<CommunityNoticeDTO> allDatas() {
		
		List<CommunityNoticeDTO> datas = session.selectList("CommunityNotice.allDatas");
		
	
		
		return datas;
	}

	public CommunityNoticeDTO getData(String notice_no) {
		CommunityNoticeDTO data = session.selectOne("CommunityNotice.getDatas", notice_no);
		return data;
	}
	
	


}
