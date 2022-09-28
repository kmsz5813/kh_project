package com.myweb.home.message.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.socket.ChattingCS;


@Controller
public class MessageController {

	
	@GetMapping(value="/chat")
	public String ChattingUser(Model model, HttpServletRequest request) {
		String itemid = request.getParameter("itemid");
	    request.setAttribute("itemid", itemid);
		return "/message/ChattingUser";
	   }
	   
	 @PostMapping(value="/chat")
	 public String ChattingUser(Model model, HttpServletRequest request
	         , @SessionAttribute("loginData") AccountsDTO accDto 
	         , MultipartHttpServletRequest mtfRequest
	         , @RequestParam("file") MultipartFile[] files
	         ) throws Exception {
	      
	      String chat = request.getParameter("chat");
	      
	      System.out.println(chat);
	      
	      
	      List<MultipartFile> fileList = mtfRequest.getFiles("file");
	      String path = request.getServletContext().getRealPath("/resources/img/item/");
	      for (MultipartFile mf : fileList) {
	            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
	            long fileSize = mf.getSize(); // 파일 사이즈
	            System.out.println("originFileName : " + originFileName);
	            System.out.println("fileSize : " + fileSize);
	            // 파일명 : 현재 시간 + 오리지널 네임
	            String safeFile = path + System.currentTimeMillis() + originFileName;
	            try {
	                mf.transferTo(new File(safeFile));
	            } catch (IllegalStateException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	      
	      
	      return "/message/ChattingUser";
	   }
	
}
