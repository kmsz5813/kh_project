package com.myweb.home.login.controller;



import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.myweb.home.Accounts.model.AccountsDTO;

import com.myweb.home.login.service.LoginService;


@Controller
@RequestMapping(value="/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
		
	
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
	public String sign(Model model, HttpSession session, String accessToken) {
		
			//카카오로그인 부분에서 받아온 accessToken값
			if(accessToken != null) {
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
					System.out.println("json 값 : "  + json);
					
					JSONObject properties = (JSONObject)jsonParser.parse(json.get("properties").toString());
					JSONObject kakao_account  = (JSONObject)jsonParser.parse(json.get("kakao_account").toString());
					
					System.out.println("properties 값 : " + properties);
					System.out.println("kakao_account 값: " + kakao_account);
					
					String email = kakao_account.get("email").toString();
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				
				return "login/sign";
			}else {
				return "login/sign";
			}
			
	
	}
	
	@GetMapping(value="/cussign")
	public String cussign(Model model) {
		
		return "login/cussign";
	}
	
	@PostMapping(value="/cussign")
	public String cussign(HttpServletRequest request,
						HttpSession session
						) {
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
		

		if(cus_email == null) {
			return "login/cussign";
		}
			
	
		boolean result = service.add(data);
	
		if(result) {
			
			data.setAc_email(cus_email);
			data.setAc_pw(cus_pw);
			
			boolean result1 = service.getLogin(session, data);

			return "redirect: /home/main";
		}
		
		return "login/cussign";
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
		System.out.println(code);
		
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
	
}
