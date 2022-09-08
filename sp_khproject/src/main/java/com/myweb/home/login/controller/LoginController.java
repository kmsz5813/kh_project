package com.myweb.home.login.controller;

import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.login.service.LoginService;


@Controller
@RequestMapping(value="/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	Random rand = new Random();	
	
	@Autowired
	private LoginService service;
	
	
	@GetMapping(value="")
	public String login(Model model) {
		
		return "login/login";
	}

	@PostMapping(value="")
	public String login(HttpServletRequest request, 
						HttpSession session,
						Model model) {
		
			String email = request.getParameter("email");
			String pw = request.getParameter("pw");
			
			AccountsDTO data = new AccountsDTO();
			data.setAc_email(email);
			data.setAc_pw(pw);
			
			boolean result = service.getLogin(session, data);
			
			System.out.println("logincontroller : "  + result);
			
			if(result) {
				//로그인성공시
				return "redirect:main";
			}else {
				return "login/m_login";
			}
	}
	
	@GetMapping(value="/sign")
	public String sign(Model model) {
		
		return "login/sign";
	}
	
	@GetMapping(value="/cussign")
	public String cussign(Model model) {
		
		return "login/cussign";
	}
	
	@PostMapping(value="/cussign")
	public String cussign(HttpServletRequest request,
						HttpSession session
						) {
		
		JSONObject json = new JSONObject();
		
		String cus_email = request.getParameter("cus_email");
		String cus_name = request.getParameter("cus_name");
		String cus_pw = request.getParameter("cus_pw");
		String cus_job = request.getParameter("cus_job");
		String cus_field = request.getParameter("cus_field");
		String cus_interest = request.getParameter("cus_interest");
		String cus_sendemail = request.getParameter("cus_sendemail");
		
		if("on".equals(cus_sendemail)) {
			cus_sendemail = "Y";
		}else{
			cus_sendemail = "N";
		}
		
		AccountsDTO data = new AccountsDTO();
		data.setAc_email(cus_email);
		data.setAc_name(cus_name);
		data.setAc_pw(cus_pw);
		data.setAc_job(cus_job);
		data.setAc_field(cus_field);
		data.setAc_interest(cus_interest);
		data.setAc_index(10);
		data.setAc_sendemail(cus_sendemail);
		
		if(cus_email == null) {		// 이메일 입력 안했을 경우	
			return "login/cussign";
		}
		
		
		if(cus_sendemail.equals("Y")) {		// 이메일 수신 동의를 했을 경우에만
			
			boolean result = service.add(data);		// DB 에 계정 데이터 추가
			
			if(result) {					// 계정 데이터가 추가되면
				
				data.setAc_email(cus_email);
				data.setAc_pw(cus_pw);
				
				service.getLogin(session, data);
				return "redirect: /home/main";
			}
		}
		
		return "login/cussign";
	}
	
	// 이메일 인증
	@PostMapping("/sendMail")
	@ResponseBody
	public String sendMail(@RequestParam(value="mail") String receiver_mail,
			HttpServletRequest request,
			Model model) {
				
		JSONObject json = new JSONObject();
		
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
		
		String[] to = {receiver_mail};	// 받는 사람 주소
		message.setTo(to);
		String[] cc = {};		// 참조 주소 (추가적으로 받는 사람 주소, 여기선 굳이 필요없음)
		message.setCc(cc);
		String[] bcc = {};		// 숨은 참조 주소 (수신자에게 참조인이 표시되지 않음, 여기선 굳이 필요없음)
		message.setBcc(bcc);
		
		message.setSubject("[FIND 메일 인증]");	// 제목
		
		int randomNumber = rand.nextInt(888888) + 111111;
		System.out.println("이메일 인증 번호 : " + randomNumber);
		json.put("randomNumber", randomNumber);
		message.setText(Integer.toString(randomNumber));	// 내용
		mailSender.send(message);	// 전송
		System.out.println(receiver_mail + "으로 이메일 인증번호 전송 완료");
		
		
		return json.toJSONString();
		
	}
	
	@GetMapping(value="/selsign")
	public String selsign(Model model) {
		
		return "login/selsign";
	}
	
	@PostMapping(value="/selsign")
	public String selsign(Model model, HttpServletRequest request, HttpSession session) {
		String sel_email = request.getParameter("sel_email");
		String sel_name = request.getParameter("sel_name");
		String sel_pw = request.getParameter("sel_pw");
		String sel_job = request.getParameter("sel_job");
		String sel_field = request.getParameter("sel_field");
		String sel_interest = request.getParameter("sel_interest");
		String sel_sendemail = request.getParameter("sel_sendemail");
		
		if("on".equals(sel_sendemail)) {
			sel_sendemail = "Y";
		}else{
			sel_sendemail = "N";
		}
	
		
		AccountsDTO data = new AccountsDTO();
		data.setAc_email(sel_email);
		data.setAc_name(sel_name);
		data.setAc_pw(sel_pw);
		data.setAc_job(sel_job);
		data.setAc_field(sel_field);
		data.setAc_interest(sel_interest);
		data.setAc_index(20);
		data.setAc_sendemail(sel_sendemail);
		
		
		boolean result = service.add(data);
		
		if(result) {

			data.setAc_email(sel_email);
			data.setAc_pw(sel_pw);
			
			boolean result1 = service.getLogin(session, data);

			return "redirect: /home/main";
	

		}
		
		return "login/selsign";
	}

		//이메일중복체크
		@PostMapping("/cussign/idCheck")
		@ResponseBody
		public String idCheck(@RequestParam("id") String id) {
			
		
			JSONObject json = new JSONObject();
			
			AccountsDTO data = service.idCheck(id);
			
	
			
			if(data == null) {
				json.put("code", "success");
			}else {
				json.put("code", "sameid");
			}
			
			return json.toJSONString();
			
		}
		
		//닉네임중복체크
		@PostMapping("/cussign/nameCheck")
		@ResponseBody
		public String nameCheck(@RequestParam("name") String name) {
			
			
			
			JSONObject json = new JSONObject();
			
			AccountsDTO data = service.nameCheck(name);
			
			System.out.println("로그인컨트롤러 : " + data);
			
			if(data == null) {
				json.put("code", "success");
			}else {
				json.put("code", "sameid");
			}
			
			return json.toJSONString();
			
		}
		

		
		
	
		
		
		
		
}
