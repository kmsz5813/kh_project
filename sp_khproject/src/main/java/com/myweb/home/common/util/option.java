package com.myweb.home.common.util;

import java.util.ArrayList;
import java.util.List;

public class option {


	public List<String> fieldpage() {
		List<String> test1 = new ArrayList<String>();
		test1.add("IT, 프로그래밍");
		test1.add("디자인");
		test1.add("비즈니스");
		test1.add("마케팅");
		test1.add("번역, 통역");
		test1.add("문서, 글쓰기");
		test1.add("주문 제작");
		test1.add("세무, 법무");
		test1.add("기타");
		
		return test1;
	}
	
	public List<String> Location(){
		List<String> Location = new ArrayList<String>();
		Location.add("서울");
		Location.add("경기");
		Location.add("부산");
		Location.add("대구");
		Location.add("인천");
		Location.add("대전");
		Location.add("울산");
		Location.add("광주");
		Location.add("세종");
		Location.add("경남");
		Location.add("경북");
		Location.add("충북");
		Location.add("충남");
		Location.add("전북");
		Location.add("전남");
		Location.add("제주");
		
		return Location;
	}
	
	
	
}
