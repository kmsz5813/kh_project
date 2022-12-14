package com.myweb.home.chat.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.chat.model.ChatDAOImpl;
import com.myweb.home.chat.model.ChatVO;

public class ChatHandler extends TextWebSocketHandler {
	
	@Autowired
	private ChatDAOImpl dao;
	
	
	// 여러개의 웹소켓 세션을 담도록 리스트를 생성한다.
		List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();


		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			System.out.println(session.getUri().getQuery());
			Map<String, Object> map = session.getAttributes();
			
			for(Entry<String, Object> e: map.entrySet()) {
				System.out.println(e.getKey() + ": " + e.getValue());
			}
			
			// 연결되었을떄
			System.out.println("연결됨 : " + session.getId());
			sessionList.add(session);
			System.out.println("연결된갯수 : " + sessionList.size());
			super.afterConnectionEstablished(session);
		}

		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			// 연결끊겼을때
			System.out.println("연결끊김 : " + session.getId());
			sessionList.remove(session);
			super.afterConnectionClosed(session, status);
		}

		// 클라이언트(브라우저)에서 서버로 메시지를 보냈을때
		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			Map<String, Object> map = session.getAttributes();
			String name = "익명";
			
			if(map.get("loginData") != null) {
				name = ((AccountsDTO)map.get("loginData")).getAc_name();
			}
			
			String m = message.getPayload();
			System.out.println("m의 값 : " + m);
			String[] strArray = m.split("\\|");
			
	
			ChatVO v = new ChatVO();
			System.out.println("strArra[0] : " +  strArray[0]);
			System.out.println("strArra[1] : " +  strArray[1]);
			System.out.println("strArra[2] : " +  strArray[2]);
			System.out.println("strArra[3] :" +  strArray[3]);

			//ㄴㄴ 값은 반환받아서 arry로 나눠서 다 나분핧해썽 
			//이렇게해도저장되나?
			
			v.setSender(strArray[0]);
			v.setMessage(strArray[1]);
			v.setItem_id(strArray[2]);
			v.setReceiver(strArray[3]);
			
			dao.insert(v);
			
			
			
			// 메시지가 들어오는 부분
			String strMessage = message.getPayload();
			System.out.println("메시지 : " + strMessage);
			
//			// 연결된세션들에게 메시지를 보낼때
//			// 현재 시간도 보내보자.
//			SimpleDateFormat stf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
//			String strDate=stf.format(new Date());
//			strMessage+="|"+strDate;
			for (WebSocketSession webSocketSession:sessionList) {
				webSocketSession.sendMessage(new TextMessage(strMessage));
			}
			super.handleTextMessage(session, message);
		}

}
