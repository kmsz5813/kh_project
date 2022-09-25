package com.myweb.home.login.service;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.myweb.home.Accounts.model.AccountsDAO;
import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.purchase.model.UsePointVO;

@Service
public class LoginService {
	Random rand = new Random();	
	
	@Autowired
	private AccountsDAO dao;
	
	public List<AccountsDTO> selectAll() {
		List<AccountsDTO> datas = dao.selectAll();

		return datas;
	}
	
	public boolean add(AccountsDTO data) {

		boolean result = dao.insertData(data);

		if(result) {
			return result;
		}else {
			return false;
		}
	}

	public boolean getLogin(HttpSession session, AccountsDTO data) {
		
		AccountsDTO acdata = dao.selectLogin(data);
		
	
		if(acdata != null) {
			session.setAttribute("loginData", acdata);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean getEmail(AccountsDTO data) {
		
		AccountsDTO acdata = dao.getEmail(data);
		if(acdata != null) {
			return true;
		}else {
			return false;
		}
	
	}
	
	public boolean getCheck(AccountsDTO data) {
		AccountsDTO acdata = dao.selectLogin(data);
		
		if(acdata != null) {
			return true;
		}else {
			return false;
		}
	}

	public AccountsDTO idCheck(String id) {
		
		AccountsDTO data = dao.idcheck(id);
		
		if(data != null) {
			return data;
		}
		return null;
	}
	
	public AccountsDTO nameCheck(String name) {
		
		AccountsDTO data = dao.namecheck(name);
		
		if(data != null) {
			return data;
		}
		return null;
	}
	
	public void delete(AccountsDTO data) {
		dao.deleteData(data);	
	}

	public boolean modify(AccountsDTO data) {
		boolean result = dao.modifyData(data);
		
		if(result) {
			return result;
		}else {
			return false;
		}
	}
	
	public boolean modifyPw(AccountsDTO data) {
	boolean result = dao.modifyPw(data);
		
		if(result) {
			return result;
		}else {
			return false;
		}
	}
	

	public String joinEmail(String email) {
		
		int randomNumber = rand.nextInt(888888) + 111111;
		System.out.println("인증번호 : " + randomNumber);
		
		// 이메일 설정
		JavaMailSender mailSender = null;
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost("smtp.gmail.com");		// gmail smtp 주소
		senderImpl.setPort(587);					// smtp port 번호
		senderImpl.setUsername("findofficial9@gmail.com");	// smtp 메일 계정
		senderImpl.setPassword("enndjcfcetoipmsj");	// 앱 비밀번호
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);		// smtp 인증
		prop.put("mail.smtp.starttls.enable", true);	// 보안 활성화
		prop.put("mail.smtp.ssl.protocols", "TLSv1.3");	// protocol 종류
		senderImpl.setJavaMailProperties(prop);
		mailSender = senderImpl;
		SimpleMailMessage message = new SimpleMailMessage();
		
		String[] to = {email};	// 받는 사람 주소
		message.setTo(to);
		String[] cc = {};		// 참조 주소 (추가적으로 받는 사람 주소, 여기선 굳이 필요없음)
		message.setCc(cc);
		String[] bcc = {};		// 숨은 참조 주소 (수신자에게 참조인이 표시되지 않음, 여기선 굳이 필요없음)
		message.setBcc(bcc);
		
		message.setSubject("[FIND 메일 인증]");	// 제목
		
		message.setText(Integer.toString(randomNumber));	// 내용
		mailSender.send(message);	// 전송
				
				
		
		return Integer.toString(randomNumber);
	}

	public boolean addBlacklist(String id) {
		boolean result = dao.addBlacklist(id);
		if(result) {
			return true;
		} else {
			return false;
		}
	}

	public String getIp(String id) {
		String ip_address = dao.getIp(id);
		return ip_address;
	}

	public String getNameFromEmail(String sellerEmail) {
		String name = dao.getNameFromEmail(sellerEmail);
		return name;
	}

	public boolean usePoint(UsePointVO usingpoint) {
		boolean result = dao.usePoint(usingpoint);
		if(result) {
			return true;
		} else {
			return false;			
		}
	}

	public List<AccountsDTO> getlikeData(AccountsDTO acData) {
		List<AccountsDTO> result = dao.getLikeData(acData);
		
	
		if(result != null) {
			return result;
		}else {
			return null;
		}
		
	
	}




}