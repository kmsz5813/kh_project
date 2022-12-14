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

	String email = ""; //???????????? ???????????? ???????????? ????????? ???????????? ?????????
//	String access_token = ""; //????????????????????? ????????? ???????????? ????????? ???????????????
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
				// ????????? ???????????? ????????? ???????????? ??????????????????
				if(notLoginUrl != null) {			
					return "redirect:" + notLoginUrl;
				}
				//??????????????????
				return "redirect:main";
			}else {
				request.setAttribute("error", "error");
				//????????? ?????????error??? ???????????? ????????????...
				return "/login/login";
			}
	}
	
	
	@GetMapping(value="/sign")
	public String sign(Model model, HttpSession session, String accessToken, HttpServletRequest request
			, String access_token , StringBuffer res) {
			
			//????????? ?????????????????????
			if(res != null) {
				UriComponents naverAuthUri = UriComponentsBuilder.newInstance()
						.scheme("https").host("openapi.naver.com").path("/v1/nid/me").build();
				//import org.springframework.http.HttpHeaders; ?????? ??????????????????.
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
					
					//json??? ?????? ???????????? ????????? ???????????? ??????
					JSONObject json = (JSONObject)jsonParser.parse(restResponse.getBody());
					
					JSONObject response = (JSONObject)jsonParser.parse(json.get("response").toString());

					//???????????? ???????????? email?????? ??????
				    email = response.get("email").toString();
					
				    //????????? ????????? ????????? ?????? ??????
					AccountsDTO data = service.idCheck(email);	
					
					if(data != null) {
						// ???????????? ???????????? ????????? ??????????????????
						request.setAttribute("email", email);

						// /login/login?????? ???????????? ???????????? ???????????????.
						//----------------?????? ?????? 
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
				
				//----------------??????????????? ?????? 
				UriComponents naverDeleteAuthUri = UriComponentsBuilder.newInstance()
						.scheme("https").host("nid.naver.com").path("/oauth2.0/token?grant_type=delete&client_id=XH6KjNl4hbD9tFu8FxJd&client_secret=wFkSHDDyt3&access_token=" + access_token + "&service_provider=NAVER").build();
				MultiValueMap<String, String> testparam = new LinkedMultiValueMap<String, String>();
				HttpEntity<MultiValueMap<String, String>> testentity = new HttpEntity<MultiValueMap<String, String>>(testparam, headers);
				ResponseEntity<String> testrestResponse = rest.postForEntity(naverDeleteAuthUri.toUriString(), testentity, String.class);
				
				return "login/sign";
			}
			
			
			//?????????????????? ???????????? ????????? accessToken???
			if(accessToken != null && res == null ) {
				model.addAttribute(accessToken);
				
				UriComponents kakaoAuthUri = UriComponentsBuilder.newInstance()
						.scheme("https").host("kapi.kakao.com").path("/v2/user/me").build();
				//import org.springframework.http.HttpHeaders; ?????? ??????????????????.
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
					
					//json??? ?????? ???????????? ????????? ???????????? ??????
					JSONObject json = (JSONObject)jsonParser.parse(restResponse.getBody());
					
					long Id = Long.valueOf(json.get("id").toString());
				
					JSONObject properties = (JSONObject)jsonParser.parse(json.get("properties").toString());
					JSONObject kakao_account  = (JSONObject)jsonParser.parse(json.get("kakao_account").toString());
					
				
					email = kakao_account.get("email").toString();
				
					
					AccountsDTO data = service.idCheck(email);	
					
					if(data != null) {
						//????????????????????? ???????????? ?????? ???????????? ????????? ??????
						email = "";
						return "login/p_login";
					}

					
				}catch (Exception e) {
					e.printStackTrace();
				}
				//?????????????????? ??????
				UriComponents kakaoLogoutAuthUri = UriComponentsBuilder.newInstance()
						.scheme("https").host("kapi.kakao.com").path("/v1/user/logout").build();
				HttpHeaders testheaders = new HttpHeaders();
				testheaders.add("Authorization", "Bearer " + accessToken);
				
				MultiValueMap<String, String> param1 = new LinkedMultiValueMap<String, String>();
				HttpEntity<MultiValueMap<String, String>> entity1 = new HttpEntity<MultiValueMap<String, String>>(param1, testheaders);
				
				ResponseEntity<String> restResponse1 = rest.postForEntity(kakaoLogoutAuthUri.toUriString(), entity1, String.class);
		
				return "login/sign";
				
			}
			
				//????????? ????????? ???????????? ???????????? ????????????????????? ?????????

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
				
			//???????????? ??????
			AccountsDTO data = new AccountsDTO();
			
			data.setAc_email(email);
			data.setAc_pw(pw);
			
			boolean result = service.modifyPw(data);
			
			if(result) {
				//???????????? ????????????
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
				//???????????? ?????????
				request.setAttribute("success", "success");
				request.setAttribute("email", email);
				return null;
			}else {
				//????????? ????????????
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
		request.setAttribute("email", email); //?????????????????? ??????????????????
		
		//????????? ??? ?????????
		email = "";

		return "login/cussign";
	}
	
	@PostMapping(value="/cussign")
	public String cussign(HttpServletRequest request,
						HttpSession session) {
		// ip ?????? ??????
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
		
		boolean result = service.add(data);		// DB ??? ?????? ????????? ??????
		
		CouponDTO coupon = new CouponDTO();
		Random rand = new Random();
		int couponNumber = rand.nextInt(88888888) + 11111111;
		List<Integer> couponNumberList = purchaseService.getCouponNumberList();
		System.out.println("?????? ???????????? ????????? : " + couponNumberList);
		// ???????????? ???????????? ????????????
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
		coupon.setCoupon_name("???????????? ?????? 10% ??????");
		coupon.setCoupon_startDate(currentDate);
		coupon.setCoupon_endDate(endDate);
		coupon.setCoupon_salePercent(10);
		purchaseService.addCoupon(coupon);
		
		if(result) {					// ?????? ???????????? ????????????
			data.setAc_email(cus_email);
			data.setAc_pw(cus_pw);
			service.getLogin(session, data);
			return "redirect: /home/main";
		}

		
		return "login/cussign";
	}
	
	// ????????? ??????
	@PostMapping("/sendMail")
	@ResponseBody
	public String sendMail(@RequestParam(value="mail") String receiver_mail,
			HttpServletRequest request,
			Model model) {
				
		JSONObject json = new JSONObject();
		
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
		
		String[] to = {receiver_mail};	// ?????? ?????? ??????
		message.setTo(to);
		String[] cc = {};		// ?????? ?????? (??????????????? ?????? ?????? ??????, ????????? ?????? ????????????)
		message.setCc(cc);
		String[] bcc = {};		// ?????? ?????? ?????? (??????????????? ???????????? ???????????? ??????, ????????? ?????? ????????????)
		message.setBcc(bcc);
		
		message.setSubject("[FIND ?????? ??????]");	// ??????
		
		int randomNumber = rand.nextInt(888888) + 111111;
		System.out.println("????????? ?????? ?????? : " + randomNumber);
		json.put("randomNumber", randomNumber);
		message.setText(Integer.toString(randomNumber));	// ??????
		mailSender.send(message);	// ??????
		System.out.println(receiver_mail + "?????? ????????? ???????????? ?????? ??????");
		
		
		return json.toJSONString();
		
	}
	
	@GetMapping(value="/selsign")
	public String selsign(Model model, HttpServletRequest request) {
		request.setAttribute("email", email); //?????????????????? ??????????????????

		email = "";
		return "login/selsign";
	}
	
	@PostMapping(value="/selsign")
	public String selsign(Model model, HttpServletRequest request, HttpSession session) {
		// ip ?????? ??????
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
		
		
		boolean result = service.add(data);		// DB??? ?????? ??????
		if(result) {
			data.setAc_email(sel_email);
			data.setAc_pw(sel_pw);
			service.getLogin(session, data);
			return "redirect: /home/main";
		}
		
		return "login/selsign";
	}

		//?????????????????????
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
		
		//?????????????????????
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
				System.out.println("login?????? ??????");
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
		/*??????????????? ???????????? ???????????? ?????? ?????????????????? ????????? ??????????????? ????????? */
		
		String tokenType = null;
		String accessToken = null;
		long expiresIn = -1;
		String refreshToken = null;
		long refreshTokenExpiresIn = -1;
		String email = null;
		
		if(error == null) {
			//get??? UriComponents??? ??????
			//post??? MultiValueMap??? ?????????????????????
			UriComponents kakaoAuthUri = UriComponentsBuilder.newInstance()
					.scheme("https").host("kauth.kakao.com").path("/oauth/token").build();
			//import org.springframework.http.HttpHeaders; ?????? ??????????????????.
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");
			
			
			MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
			param.add("grant_type", "authorization_code");
			param.add("client_id", "a9bb78cdf999d6e4c34a2f31a8d05510");
			param.add("redirect_uri", "http://localhost/home/login/kakao/auth_code");
			param.add("code", code);
			
			
			 //header??? ????????? ????????? ?????? !!!!!!!!!!! param??? ?????? ???????????? ?????????!
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(param, headers);


					
			RestTemplate rest = new RestTemplate();
			rest.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
			
			ResponseEntity<String> restResponse = rest.postForEntity(kakaoAuthUri.toUriString(), entity, String.class);
			
		
			
			JSONParser jsonParser = new JSONParser();
		
			try {
				
				
				JSONObject json = (JSONObject)jsonParser.parse(restResponse.getBody());
				
				//?????? ?????? : bearer ????????? ??????
				
				tokenType = json.get("token_type").toString();
				
				//????????? ????????? ?????? ???
				accessToken = json.get("access_token").toString();
				
				//????????????
				expiresIn = Long.valueOf(json.get("expires_in").toString());
				
				//???????????? ?????? ???
				refreshToken = json.get("refresh_token").toString();
				
				
				//???????????? ?????? ?????? ???
				refreshTokenExpiresIn = Long.valueOf(json.get("refresh_token_expires_in").toString());
				
				//????????? ???????????? login/sing???????????? ?????????
				re.addAttribute("accessToken", accessToken);

			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return "redirect: /home/login/sign";
		}
		
		
		return "error/kakao_auth_error";
	}
	//????????? ??????
	@RequestMapping(value="/naver", method=RequestMethod.GET)
	public String naverLogin(HttpServletRequest request, HttpSession session) {
		   String clientId = "XH6KjNl4hbD9tFu8FxJd";//?????????????????? ??????????????? ????????????";
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
		   String clientId = "XH6KjNl4hbD9tFu8FxJd";//?????????????????? ??????????????? ????????????";
		    String clientSecret = "wFkSHDDyt3";//?????????????????? ??????????????? ????????????";
		    String code = request.getParameter("code");
		    String state = request.getParameter("state");
		    String redirectURI = null;
			String access_token = ""; //????????????????????? ????????? ???????????? ????????? ???????????????
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
		      //System.out.print("responseCode???="+responseCode);
		      if(responseCode==200) { // ?????? ??????
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // ?????? ??????
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
		    		
		    		//????????????????????? ???????????????
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
