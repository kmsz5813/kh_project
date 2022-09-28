package com.myweb.home.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.myweb.home.Accounts.model.AccountsDTO;

	
public class ChattingCS extends TextWebSocketHandler {

   
   private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
   private Map<String, WebSocketSession> sessionMap = new HashMap<String, WebSocketSession>();
   
   @Override
   public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      System.out.println(session.getUri().getQuery());
      Map<String, Object> map = session.getAttributes();
      String name = (String) map.get("HTTP.SESSION.ID");
      
      if(map.get("loginData") != null) {
         name = ((AccountsDTO)map.get("loginData")).getAc_name();
      }
      
      for(Entry<String, WebSocketSession> entry: sessionMap.entrySet()) {
         entry.getValue().sendMessage(new TextMessage("<p>" + name + " 님이 접속하였습니다.</p>"));
      }
      sessionMap.put(name, session);
      
      super.afterConnectionEstablished(session);
   }
   
   protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
      
      Map<String, Object> map = session.getAttributes();
      String name = (String) map.get("HTTP.SESSION.ID");
      
      if(map.get("loginData") != null) {
         name = ((AccountsDTO)map.get("loginData")).getAc_name();
      }
      
      for(Entry<String, WebSocketSession> entry: sessionMap.entrySet()) {
         WebSocketSession ws = entry.getValue();
         ws.sendMessage(new TextMessage("<p>" + name + " 님이 보낸 메시지<br>" + message.getPayload() + "</p>"));
      }
      super.handleTextMessage(session, message);
   }
   
   @Override
   public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
      Map<String, Object> map = session.getAttributes();
      String name = (String) map.get("HTTP.SESSION.ID");
      
      if(map.get("loginData") != null) {
         name = ((AccountsDTO)map.get("loginData")).getAc_name();
      }
      
      for(Entry<String, WebSocketSession> entry: sessionMap.entrySet()) {
         WebSocketSession ws = entry.getValue();
         if(ws.isOpen()) {
            ws.sendMessage(new TextMessage("<p>" + name + " 님이 접속을 종료합니다."));
         }
      }
      
      sessionMap.remove(name);
      
      super.afterConnectionClosed(session, status);
   }



}