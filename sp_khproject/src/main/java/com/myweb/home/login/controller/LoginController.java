package com.myweb.home.login.controller;


import java.net.http.HttpRequest;
import java.util.Properties;
import java.util.Random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.admin.model.AdminDAO;
import com.myweb.home.admin.model.BlackDTO;
import com.myweb.home.admin.service.AdminService;
import com.myweb.home.login.service.LoginService;
import com.myweb.home.purchase.model.CouponDTO;
import com.myweb.home.purchase.service.PurchaseService;


@Controller
@RequestMapping(value="/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	Random rand = new Random();	

	String email = ""; //카카오나 네이버로 로그인시 토큰값 저장하기 위한것
//	String access_token = ""; //네이버로그인시 토큰값 저장하기 위해서 만들어둔것
//	StringBuffer res = null;
	
	@Autowired
	private LoginService service;
	@Autowired 
	private AdminService adminService;
	@Autowired
	private PurchaseService purchaseService;
	
	
	private String notLoginUrl = null;
	@GetMapping(value="")
	public String login(Model model, HttpServletRequest request) {
		notLoginUrl = null;
		if(request.getParameter("url") != null) {
			notLoginUrl = request.getParameter("url");
		}
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

			if(result) {
				// 잘못된 접근으로 로그인 페이지로 들어왔을경우
				if(notLoginUrl != null) {			
					return "redirect:" + notLoginUrl;
				}
				//로그인성공시
				return "redirect:main";
			}else {
				request.setAttribute("error", "error");
				//로그인 실패시error값 넣어줘서 체크하기...
				return "/login/login";
			}
	}
	
	
	@GetMapping(value="/sign")
	public String sign(Model model, HttpSession session, String accessToken, HttpServletRequest request
			, String access_token , StringBuffer res) {
			
			//네이버 토큰로그인방식
			if(res != null) {
				UriComponents naverAuthUri = UriComponentsBuilder.newInstance()
						.scheme("https").host("openapi.naver.com").path("/v1/nid/me").build();
				//import org.springframework.http.HttpHeaders; 값이 임포트값된다.
				HttpHeaders headers = new HttpHeaders();
				
				headers.add("User-Agent", "curl/7.12.1 (i686-redhat-linux-gnu) libcurl/7.12.1 OpenSSL/0.9.7a zlib/1.2.1.2 libidn/0.5.6");
				headers.add("Host", "openapi.naver.com");
				headers.add("Pragma", "no-cache");
				headers.add("Accept", "*/*");
				headers.add("Authorization", "Bearer " + access_token);
				
				RestTemplate rest = new RestTemplate();
				rest.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
				MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
				
				
				HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(param, headers);

				
				ResponseEntity<String> restResponse = rest.postForEntity(naverAuthUri.toUriString(), entity, String.class);
				
				JSONParser jsonParser = new JSONParser();
				
				try {
					
					//json을 다시 파싱해서 카카오 어카운트 계정
					JSONObject json = (JSONObject)jsonParser.parse(restResponse.getBody());
					
					JSONObject response = (JSONObject)jsonParser.parse(json.get("response").toString());

					//빈객체를 만들어서 email값을 저장
				    email = response.get("email").toString();
					
				    //가입된 이메일 정보가 있나 확인
					AccountsDTO data = service.idCheck(email);	
					
					if(data != null) {
						// 네이버가 아이디가 동일한 아이디일경우
						request.setAttribute("email", email);

						// /login/login으로 리턴할시 이미지가 매핑이안됨.
						//----------------토큰 삭제 
						UriComponents naverDeleteAuthUri = UriComponentsBuilder.newInstance()
								.scheme("https").host("nid.naver.com").path("/oauth2.0/token?grant_type=delete&client_id=XH6KjNl4hbD9tFu8FxJd&client_secret=wFkSHDDyt3&access_token=" + access_token + "&service_provider=NAVER").build();
						MultiValueMap<String, String> testparam = new LinkedMultiValueMap<String, String>();
						HttpEntity<MultiValueMap<String, String>> testentity = new HttpEntity<MultiValueMap<String, String>>(testparam, headers);
						ResponseEntity<String> testrestResponse = rest.postForEntity(naverDeleteAuthUri.toUriString(), testentity, String.class);
						
						email = "";
						return "login/p_login";
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				//----------------네이버토큰 삭제 
				UriComponents naverDeleteAuthUri = UriComponentsBuilder.newInstance()
						.scheme("https").host("nid.naver.com").path("/oauth2.0/token?grant_type=delete&client_id=XH6KjNl4hbD9tFu8FxJd&client_secret=wFkSHDDyt3&access_token=" + access_token + "&service_provider=NAVER").build();
				MultiValueMap<String, String> testparam = new LinkedMultiValueMap<String, String>();
				HttpEntity<MultiValueMap<String, String>> testentity = new HttpEntity<MultiValueMap<String, String>>(testparam, headers);
				ResponseEntity<String> testrestResponse = rest.postForEntity(naverDeleteAuthUri.toUriString(), testentity, String.class);
				
				return "login/sign";
			}
			
			
			//카카오로그인 부분에서 받아온 accessToken값
			if(accessToken != null && res == null ) {
				model.addAttribute(accessToken);
				
				UriComponents kakaoAuthUri = UriComponentsBuilder.newInstance()
						.scheme("https").host("kapi.kakao.com").path("/v2/user/me").build();
				//import org.springframework.http.HttpHeaders; 값이 임포트값된다.
				HttpHeaders headers = new HttpHeaders();
				
				headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");
				headers.add("Authorization", "Bearer " + accessToken);
				
				String[] test = null;
				
				MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
				
				MultiValueMap<String, Object> paramtest = new LinkedMultiValueMap<>();
				paramtest.add("property_keys", test);
				
	
				HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(paramtest, headers);
				

						
				RestTemplate rest = new RestTemplate();
				rest.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
				
				ResponseEntity<String> restResponse = rest.postForEntity(kakaoAuthUri.toUriString(), entity, String.class);
		
			
				
				JSONParser jsonParser = new JSONParser();
				
				try {
					
					//json을 다시 파싱해서 카카오 어카운트 계정
					JSONObject json = (JSONObject)jsonParser.parse(restResponse.getBody());
					
					long Id = Long.valueOf(json.get("id").toString());
				
					JSONObject properties = (JSONObject)jsonParser.parse(json.get("properties").toString());
					JSONObject kakao_account  = (JSONObject)jsonParser.parse(json.get("kakao_account").toString());
					
				
					email = kakao_account.get("email").toString();
				
					
					AccountsDTO data = service.idCheck(email);	
					
					if(data != null) {
						//카카오아이디가 디비버에 있는 아이디오 동일할 경우
						email = "";
						return "login/p_login";
					}

					
				}catch (Exception e) {
					e.printStackTrace();
				}
				//카카오토큰값 삭제
				UriComponents kakaoLogoutAuthUri = UriComponentsBuilder.newInstance()
						.scheme("https").host("kapi.kakao.com").path("/v1/user/logout").build();
				HttpHeaders testheaders = new HttpHeaders();
				testheaders.add("Authorization", "Bearer " + accessToken);
				
				MultiValueMap<String, String> param1 = new LinkedMultiValueMap<String, String>();
				HttpEntity<MultiValueMap<String, String>> entity1 = new HttpEntity<MultiValueMap<String, String>>(param1, testheaders);
				
				ResponseEntity<String> restResponse1 = rest.postForEntity(kakaoLogoutAuthUri.toUriString(), entity1, String.class);
		
				return "login/sign";
				
			}
			
				//카카오 네이버 이메일값 받아와서 로그인페이지로 넘기기

				return "login/sign";
	
	}
	
	@GetMapping(value="/seekpw")
	public String seekpw(Model model) {
		
		
		
		return "login/seekpw";
	}
	
	@PostMapping(value="/seekpw")
	public String seekpw(Model model, HttpServletRequest request) {
		
		String test = request.getParameter("test");
		String pw = request.getParameter("cus_pw");
		
		if(pw != null) {
			String email = request.getParameter("email");
			email = email.trim();
				
			//비밀번호 수정
			AccountsDTO data = new AccountsDTO();
			
			data.setAc_email(email);
			data.setAc_pw(pw);
			
			boolean result = service.modifyPw(data);
			
			if(result) {
				//비밀번호 수정완료
				return "redirect: /home/login";
			}else {
				
				return null;
			}
			
		}
		
		
		if(test == null) {
			
			String email = request.getParameter("email");
			AccountsDTO data = new AccountsDTO();
			data.setAc_email(email);
			
			
			boolean result = service.getEmail(data);
			
			if(result) {
				//이메일로 보내기
				request.setAttribute("success", "success");
				request.setAttribute("email", email);
				return null;
			}else {
				//이메일 없을시에
				request.setAttribute("error", "error");
				return null;
			}
		}else {
			String email = request.getParameter("email");
			String emailtest = request.getParameter("test1");
			request.setAttribute("emailtest", "emailtest");
			request.setAttribute("email", email);
			
			return null;
		}
	}
	
	
	@GetMapping(value="/cussign")
	public String cussign(Model model, HttpServletRequest request
						  ) {
		request.setAttribute("email", email); //가입페이지에 저장시켜놓기
		
		//이메일 값 초기화
		email = "";

		return "login/cussign";
	}
	
	@PostMapping(value="/cussign")
	public String cussign(HttpServletRequest request,
						HttpSession session) {
		// ip 주소 저장
		String ip = request.getHeader("X-Forwarded-For");
	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	    }
	    logger.info("Result : IP Address : "+ ip);
	    
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
		data.setAc_ip(ip);
		
		boolean result = service.add(data);		// DB 에 계정 데이터 추가
		
		CouponDTO coupon = new CouponDTO();
		Random rand = new Random();
		int couponNumber = rand.nextInt(88888888) + 11111111;
		List<Integer> couponNumberList = purchaseService.getCouponNumberList();
		System.out.println("기존 쿠폰번호 리스트 : " + couponNumberList);
		// 쿠폰번호 중복되면 안되므로
		for(int i = 0; i < couponNumberList.size(); i++) {
			if(couponNumberList.get(i) == couponNumber) {
				couponNumber = rand.nextInt(88888888) + 11111111;
			}
		}
		Date currentDate = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate); cal.add(Calendar.DATE, 30);
		Date endDate = new Date(cal.getTimeInMillis());
		coupon.setCoupon_number(couponNumber);
		coupon.setCoupon_userName(cus_name);
		coupon.setCoupon_name("회원가입 축하 10% 쿠폰");
		coupon.setCoupon_startDate(currentDate);
		coupon.setCoupon_endDate(endDate);
		coupon.setCoupon_salePercent(10);
		purchaseService.addCoupon(coupon);
		
		if(result) {					// 계정 데이터가 추가되면
			data.setAc_email(cus_email);
			data.setAc_pw(cus_pw);
			service.getLogin(session, data);
			return "redirect: /home/main";
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
	public String selsign(Model model, HttpServletRequest request) {
		request.setAttribute("email", email); //가입페이지에 저장시켜놓기

		email = "";
		return "login/selsign";
	}
	
	@PostMapping(value="/selsign")
	public String selsign(Model model, HttpServletRequest request, HttpSession session) {
		// ip 주소 저장
		String ip = request.getHeader("X-Forwarded-For");
	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	    }
	    logger.info("Result : IP Address : "+ ip);
			    
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
		data.setAc_ip(ip);
		
		
		boolean result = service.add(data);		// DB에 계정 추가
		if(result) {
			data.setAc_email(sel_email);
			data.setAc_pw(sel_pw);
			service.getLogin(session, data);
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
			
			if(name.getBytes().length <= 40) {
				AccountsDTO data = service.nameCheck(name);
				
				if(data == null) {
					json.put("code", "success");
				}else {
					json.put("code", "sameid");
				}
			}else {
				System.out.println("login에서 작동");
				json.put("code", "nameLength");
			}
			
			
			
			return json.toJSONString();
			
		}
		
		@RequestMapping(value="/kakao", method=RequestMethod.GET)
		public String kakaoLogin() {
			UriComponents kakaoAuthUri = UriComponentsBuilder.newInstance()
					.scheme("https").host("kauth.kakao.com").path("/oauth/authorize")
					.queryParam("client_id", "a9bb78cdf999d6e4c34a2f31a8d05510")
					.queryParam("redirect_uri", "http://localhost/home/login/kakao/auth_code")
					.queryParam("response_type", "code").build();
		
			RestTemplate rest = new RestTemplate();
			
			CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
			
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			
			factory.setHttpClient(httpClient);
			rest.setRequestFactory(factory);
			
			ResponseEntity<String> restResponse = rest.getForEntity(kakaoAuthUri.toUriString(), String.class);
		

			return "redirect:" + restResponse.getHeaders().getLocation();
		}
	
	@RequestMapping(value="/kakao/auth_code", method=RequestMethod.GET)
	public String kakaoAuthCode(HttpSession session,
								String code, String state,
								String error,
								RedirectAttributes re,
								@RequestParam(name="error_description", required=false) String errorDescription) {
		/*인증마치고 넘길장소 인가코드 받고 토큰요청하면 사용자 토큰정보를 보내줌 */
		
		String tokenType = null;
		String accessToken = null;
		long expiresIn = -1;
		String refreshToken = null;
		long refreshTokenExpiresIn = -1;
		String email = null;
		
		if(error == null) {
			//get은 UriComponents를 이용
			//post는 MultiValueMap를 이용해줘야한다
			UriComponents kakaoAuthUri = UriComponentsBuilder.newInstance()
					.scheme("https").host("kauth.kakao.com").path("/oauth/token").build();
			//import org.springframework.http.HttpHeaders; 값이 임포트값된다.
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");
			
			
			MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
			param.add("grant_type", "authorization_code");
			param.add("client_id", "a9bb78cdf999d6e4c34a2f31a8d05510");
			param.add("redirect_uri", "http://localhost/home/login/kakao/auth_code");
			param.add("code", code);
			
			
			 //header를 만든게 있으면 사용 !!!!!!!!!!! param값 까지 넣어주기 위해서!
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(param, headers);


					
			RestTemplate rest = new RestTemplate();
			rest.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
			
			ResponseEntity<String> restResponse = rest.postForEntity(kakaoAuthUri.toUriString(), entity, String.class);
			
		
			
			JSONParser jsonParser = new JSONParser();
		
			try {
				
				
				JSONObject json = (JSONObject)jsonParser.parse(restResponse.getBody());
				
				//토큰 타입 : bearer 값으로 고정
				
				tokenType = json.get("token_type").toString();
				
				//사용자 액세스 토큰 값
				accessToken = json.get("access_token").toString();
				
				//토큰만료
				expiresIn = Long.valueOf(json.get("expires_in").toString());
				
				//리프레쉬 토큰 값
				refreshToken = json.get("refresh_token").toString();
				
				
				//리프레쉬 토큰 만료 값
				refreshTokenExpiresIn = Long.valueOf(json.get("refresh_token_expires_in").toString());
				
				//토큰을 저장해서 login/sing페이지로 넘기기
				re.addAttribute("accessToken", accessToken);

			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return "redirect: /home/login/sign";
		}
		
		
		return "error/kakao_auth_error";
	}
	//네이버 발급
	@RequestMapping(value="/naver", method=RequestMethod.GET)
	public String naverLogin(HttpServletRequest request, HttpSession session) {
		   String clientId = "XH6KjNl4hbD9tFu8FxJd";//애플리케이션 클라이언트 아이디값";
		    String redirectURI = null;
			try {
				redirectURI = URLEncoder.encode("http://localhost/home/login/naver/auth_code", "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    SecureRandom random = new SecureRandom();
		    String state = new BigInteger(130, random).toString();
		    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		    apiURL += "&client_id=" + clientId;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&state=" + state;
		    session.setAttribute("state", state);
		return "redirect:" + apiURL;
	}
	
	
	
	@RequestMapping(value="/naver/auth_code", method=RequestMethod.GET)
	public String naverToken(HttpServletRequest request, HttpSession session, RedirectAttributes re) {
		   String clientId = "XH6KjNl4hbD9tFu8FxJd";//애플리케이션 클라이언트 아이디값";
		    String clientSecret = "wFkSHDDyt3";//애플리케이션 클라이언트 시크릿값";
		    String code = request.getParameter("code");
		    String state = request.getParameter("state");
		    String redirectURI = null;
			String access_token = ""; //네이버로그인시 토큰값 저장하기 위해서 만들어둔것
			StringBuffer res = null;
			try {
				redirectURI = URLEncoder.encode("http://localhost/home/login/naver/auth_code", "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    String apiURL;
		    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
//		    String access_token = "";
		    String refresh_token = "";
		    
		    try {
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      //System.out.print("responseCode값="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      res = new StringBuffer();
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      if(responseCode==200) {
		    	
		 
		    	JSONParser jsonParser = new JSONParser();
		    	
		    	try {
		    		JSONObject obj = (JSONObject)jsonParser.parse(res.toString());
		    		
		    		//빈객체만들어서 토큰값저장
		    		access_token = obj.get("access_token").toString();
		    		
		    		re.addAttribute("res", res);
		    		re.addAttribute("access_token", access_token);
		    	}catch (Exception e){
		    		e.printStackTrace();
		    	}

		        
		      }
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		return "redirect: /home/login/sign";
	}
	
	
	
}
