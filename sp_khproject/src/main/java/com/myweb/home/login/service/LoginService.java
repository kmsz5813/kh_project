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
import com.myweb.home.selitem.model.SelItemStaticsDTO;

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
		System.out.println("???????????? : " + randomNumber);
		
		// ????????? ??????
		JavaMailSender mailSender = null;
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost("smtp.gmail.com");		// gmail smtp ??????
		senderImpl.setPort(587);					// smtp port ??????
		senderImpl.setUsername("findofficial9@gmail.com");	// smtp ?????? ??????
		senderImpl.setPassword("enndjcfcetoipmsj");	// ??? ????????????
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);		// smtp ??????
		prop.put("mail.smtp.starttls.enable", true);	// ?????? ?????????
		prop.put("mail.smtp.ssl.protocols", "TLSv1.3");	// protocol ??????
		senderImpl.setJavaMailProperties(prop);
		mailSender = senderImpl;
		SimpleMailMessage message = new SimpleMailMessage();
		
		String[] to = {email};	// ?????? ?????? ??????
		message.setTo(to);
		String[] cc = {};		// ?????? ?????? (??????????????? ?????? ?????? ??????, ????????? ?????? ????????????)
		message.setCc(cc);
		String[] bcc = {};		// ?????? ?????? ?????? (??????????????? ???????????? ???????????? ??????, ????????? ?????? ????????????)
		message.setBcc(bcc);
		
		message.setSubject("[FIND ?????? ??????]");	// ??????
		
		message.setText(Integer.toString(randomNumber));	// ??????
		mailSender.send(message);	// ??????
				
				
		
		return Integer.toString(randomNumber);
	}

	public boolean addBlacklist(String name) {
		boolean result = dao.addBlacklist(name);
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

	public List<SelItemStaticsDTO> likeDatas(String ac_name) {
		List<SelItemStaticsDTO> list = dao.likeDatas(ac_name);
		return list;
	}





}