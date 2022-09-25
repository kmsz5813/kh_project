package com.myweb.home.selitem.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.selitem.model.ReviewDTO;
import com.myweb.home.selitem.model.ReviewDetailVO;
import com.myweb.home.selitem.model.SelItemDAO;
import com.myweb.home.selitem.model.SelItemDTO;
import com.myweb.home.selitem.model.SelItemStaticsDTO;
import com.myweb.home.upload.model.FileUploadDTO;

@Service
public class SelItemService {
	
	@Autowired
	private SelItemDAO dao;
	
	public List<Object> getData(SelItemDTO data) {
		
		List<Object> datas = dao.getData(data);
		
		//데이터 가져오기
		if(datas != null) {
	
			return datas;
		}
		
		return null;
	}
	

	public List getSelect(String selectData) {
		List datas = dao.selectData(selectData);
		
		if(datas != null) {
			return datas;
		}
		
		return null;
	}
	
	public List getLocation(String locationData) {
		List datas = dao.locationData(locationData);
		
		if(datas != null) {
			return datas;
		}
		
		return null;
	}
	
	public List<SelItemDTO> getAll() {
		List<SelItemDTO> datas = dao.selectAll();
	return datas;
	}
	
	public SelItemDTO getData(int id) {
		SelItemDTO data = dao.selectData(id);
		return data;
	}
	
	public boolean add(SelItemDTO data) {
		
		boolean result = dao.insertData(data);
		
		if(result) {
			return result;
		}
		return false;
	}
	
	//sellitem 수정부분
	public boolean modify(SelItemDTO data) {
		boolean result = dao.updateData(data);
		System.out.println("service부분" + data);
		return result;
	}
	
	
	public void incLike(HttpSession session, SelItemDTO data) {
		AccountsDTO acData = (AccountsDTO)session.getAttribute("loginData");
		
		SelItemStaticsDTO staticsData = new SelItemStaticsDTO();
		staticsData.setSel_id(data.getSel_id());
		staticsData.setAc_name(acData.getAc_name());
		
		SelItemStaticsDTO selectData = dao.selectStatics(staticsData);
		
		//조회해서 조회값이 없으면 테이블에 INSERT 생성하기!!!!!!
		if(selectData == null) {
			boolean insert_reuslt = dao.insertStatics(staticsData);	
			selectData = dao.selectStatics(staticsData);
		}
		
		if(selectData.isLiked()) {
			staticsData.setLiked(false);
			data.setSel_like(data.getSel_like() -1);
		}else {
			selectData.setLiked(true);
			data.setSel_like(data.getSel_like() + 1);
		}
		
		dao.updateStaticsLike(selectData);
		
		boolean result = dao.updateLike(data);
		
	}
	//조회로 게시글찾rl
	public List getSearch(String search) {
		List datas = dao.searchData(search);
		
		if(datas != null) {
			return datas;
		}
		
		return null;
	}
	
	// 이름으로 게시글 찾기
	public List<SelItemDTO> getName(String ac_name) {
		List<SelItemDTO> datas = dao.searchName(ac_name);
		if(datas != null) {
			return datas;
		}
		return null;
	}

	// 구매시 구매횟수 + 1
	public boolean plusCount(int itemid) {
		boolean result = dao.plusCount(itemid);
		if(result) {
			return true;
		} else {
			return false;
		}
	}



	public FileUploadDTO getThumbnail(int i) {
		FileUploadDTO thumbnail = dao.getThumbnail(i);
		return thumbnail;
	}


	public int getNextSeq() {
		int nextseq = dao.getNextSeq(); 
		return nextseq;
	}
	public boolean incViewCnt(SelItemDTO itemdata) {
		boolean result = dao.viewCnt(itemdata);
		if(result) {
			return true;
		}else {
			return false;
		}


	}


	public boolean addReview(ReviewDTO review) {
		boolean result = dao.addReview(review);
		return result;
	}


	public List<ReviewDTO> getReviews(int itemid) {
		List<ReviewDTO> result = dao.getReviews(itemid);
		return result;
	}


	public int getReviewCount(int itemid) {
		int result = dao.getReviewCount(itemid);
		return result;
	}


	public int getStarScore(int parseInt) {
		int result = dao.getStarScore(parseInt);
		return result;
	}


	public boolean addReviewCount(int i) {
		boolean result = dao.addReviewCount(i);
		return result;
	}


	public boolean addReviewStar(ReviewDetailVO detail) {
		boolean result = dao.addReviewStar(detail);
		return result;
	}









	
}