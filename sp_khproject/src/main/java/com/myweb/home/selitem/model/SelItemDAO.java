package com.myweb.home.selitem.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myweb.home.upload.model.FileUploadDTO;

@Repository
public class SelItemDAO {
	
	
	@Autowired
	private SqlSession session;
	
	private String mapper = "selItemMapper.%s";
	//조회기능
	public List<Object> getData(SelItemDTO data) {
		
		String mapperId = String.format(mapper, "getData");
		List<Object> result = session.selectList(mapperId, data);
		return result;
	}
	//조회기능
	public List selectData(String selectData) {
		String mapperId = String.format(mapper, "selectData");
		List<Object> result = session.selectList(mapperId, selectData);
		return result;
	}
	//조회기능
	public List locationData(String locationData) {
		String mapperId = String.format(mapper, "locationData");
		List<Object> result = session.selectList(mapperId, locationData);
		return result;
	}
	//조회기능
	public List searchData(String search) {
		
		String mapperId = String.format(mapper, "searchData");
		List<Object> result = session.selectList(mapperId, search);
		return result;
	}
	
	
	public List<SelItemDTO> selectAll() {
		String mapperId = String.format(mapper, "selectAll");
		List<SelItemDTO> res = session.selectList(mapperId);
		return res;
	}
	
	public SelItemDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectIdData");
		SelItemDTO res = session.selectOne(mapperId, id);
		System.out.println("판매횟수 : " + res.getSel_number());
		return res;
	}
	

	
	public int getNextSeq() {
		String mapperId = String.format(mapper, "getNextSeq");
		int seq = session.selectOne(mapperId);
		return seq;
	}
	
	public boolean insertData(SelItemDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1? true : false;
	}
	
	
	
	public boolean updateData(SelItemDTO data) {
		String mpapperId = String.format(mapper, "updateData");
		int res = session.update(mpapperId, data);
		return res == 1? true : false;
	}
	
	
	//데이터삭제
	public boolean deleteData(int id) {
		String mapperId = String.format(mapper, "deleteData");
		int res = session.delete(mapperId, id);
		return res == 1 ? true : false;
	}
	
	public boolean deleteData2(String name) {
		String mapperId = String.format(mapper, "deleteData2");
		int res = session.delete(mapperId, name);
		return res == 1 ? true : false;
	}
	
	//statics 조회-----------조회수가 추가되어 있는지 안되어 있는지
	public SelItemStaticsDTO selectStatics(SelItemStaticsDTO data) {
		String mapperId = String.format(mapper, "selectStatics");
		SelItemStaticsDTO res = session.selectOne(mapperId, data);
		return res;
	}
	
	//테이블에 값넣어주기 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public boolean insertStatics(SelItemStaticsDTO staticsData) {
		String mapperId = String.format(mapper, "insertStatics");
		int res = session.insert(mapperId, staticsData);
		return res == 1 ? true : false;
	}
	
	public boolean updateStatics(SelItemStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStatics");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}

	public boolean updateLike(SelItemDTO data) {
		String mapperId = String.format(mapper, "updateLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStaticsLike(SelItemStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStaticsLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}

	//이름으로찾기
	public List<SelItemDTO> searchName(String ac_name) {
		String mapperId = String.format(mapper, "searchName");
		List<SelItemDTO> datas = session.selectList(mapperId, ac_name);
		return datas;
	}
	
	//좋아요순을 ㅗ찾기
	public List searchLike() {
		String mapperId = String.format(mapper, "searchLike");
		List datas = session.selectList(mapperId);
		return datas;
	}
	
	//조회순으로 찾기
	public List searchView() {
		String mapperId = String.format(mapper, "searchView");
		List datas = session.selectList(mapperId);
		return datas;
	}

	public boolean plusCount(int itemid) {
		String mapperId = String.format(mapper, "plusCount");
		int res = session.update(mapperId, itemid);
		return res == 1 ? true : false;
	}

	public FileUploadDTO getThumbnail(int i) {
		String mapperId = String.format(mapper, "getThumbnail");
		FileUploadDTO thumbnail = session.selectOne(mapperId, i);
		return thumbnail;
	}	
	public boolean viewCnt(SelItemDTO itemdata) {
		String mapperId = String.format(mapper, "viewCnt");
		int res = session.update(mapperId, itemdata);
		return res == 1 ? true : false;
	}

	public boolean addReview(ReviewDTO review) {
		String mapperId = String.format(mapper, "addReview");
		int res = session.insert(mapperId, review);
		return res == 1 ? true : false;
	}

	public List<ReviewDTO> getReviews(int itemid) {
		String mapperId = String.format(mapper, "getReviews");
		List<ReviewDTO> result = session.selectList(mapperId, itemid);
		return result;
	}

	public int getReviewCount(int itemid) {
		String mapperId = String.format(mapper, "getReviewCount");
		int res = session.selectOne(mapperId, itemid);
		return res;
	}

	public int getStarScore(int parseInt) {
		String mapperId = String.format(mapper, "getStarScore");
		if(session.selectOne(mapperId, parseInt) != null) {
			int res = session.selectOne(mapperId, parseInt);
			return res;
		}
		return 0;
	}

	public boolean addReviewCount(int i) {
		String mapperId = String.format(mapper, "addReviewCount");
		int res = session.update(mapperId, i);
		return res == 1 ? true : false;
	}

	public boolean addReviewStar(ReviewDetailVO detail) {
		String mapperId = String.format(mapper, "addReviewStar");
		int res = session.update(mapperId, detail);
		return res == 1 ? true : false; 
	}
	
	public String getTitle(int sel_id) {
		String mapperId = String.format(mapper, "getTitle");
		String result = session.selectOne(mapperId, sel_id);
		return result;
	}
	public String getSeller(int sel_id) {
		String mapperId = String.format(mapper, "getSeller");
		String result = session.selectOne(mapperId, sel_id);
		return result;
	}
	public boolean deleteReview(int id) {
		String mapperId = String.format(mapper, "deleteReview");
		int res = session.delete(mapperId, id);
		return res == 1 ? true : false;
	}
	public boolean deleteStatics(int id) {
		String mapperId = String.format(mapper, "deleteStatics");
		int res = session.delete(mapperId, id);
		return res == 1 ? true : false;
	}
	public boolean deleteFile(int id) {
		String mapperId = String.format(mapper, "deleteFile");
		int res = session.delete(mapperId, id);
		return res == 1 ? true : false;
	}
	//번호로조회
	public boolean deleteReviewNumber(int id) {
		String mapperId = String.format(mapper, "deleteReviewNumber");
		int res = session.delete(mapperId, id);
		return res == 1 ? true : false;
	}
	public boolean deleteReviewCount(int sel_id) {
		String mapperId = String.format(mapper, "deleteReviewCount");
		int res = session.update(mapperId, sel_id);
		return res == 1 ? true : false;
	}
	public boolean deleteLike(String ac_name) {
		String mapperId = String.format(mapper, "deleteLike");
		int res = session.delete(mapperId, ac_name);
		return res == 1 ? true : false;
	}
	public boolean deleteReview(String ac_name) {
		String mapperId = String.format(mapper, "deleteReview2");
		int res = session.delete(mapperId, ac_name);
		return res == 1 ? true : false;
	}
<<<<<<< HEAD
	public List<FileUploadDTO> selectAllFiles(String name) {
		String mapperId = String.format(mapper, "selectAllFiles");
		List<FileUploadDTO> list = session.selectList(mapperId, name);
		return list;
	}
	public boolean deleteFiles(String name) {
		String mapperId = String.format(mapper, "deleteFiles");
		int res = session.delete(mapperId, name);
		return res == 1 ? true : false;
	}
	/*
	 * public int nameReviewCount(String name) { String mapperId =
	 * String.format(mapper, "nameReviewCount"); int count =
	 * session.selectOne(mapperId, name); return count; } public boolean
	 * minusReviewCount(ReviewDetailVO datas) { String mapperId =
	 * String.format(mapper, "minusReviewCount"); int res = session.update(mapperId,
	 * datas); return res == 1 ? true : false; }
	 */
	
=======
	public boolean modifyReview(ReviewDTO data) {
		String mpapperId = String.format(mapper, "modifyReview");
		int res = session.update(mpapperId, data);
		return res == 1? true : false;
	}
>>>>>>> refs/remotes/origin/이윤지

	


}
