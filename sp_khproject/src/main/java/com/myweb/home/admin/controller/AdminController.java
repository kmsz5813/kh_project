package com.myweb.home.admin.controller;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.admin.model.BlackDTO;
import com.myweb.home.admin.service.AdminService;
import com.myweb.home.login.controller.LoginController;
import com.myweb.home.login.service.LoginService;
import com.myweb.home.purchase.model.EventCouponDTO;
import com.myweb.home.purchase.model.PurchaseDTO;
import com.myweb.home.purchase.service.PurchaseService;
import com.myweb.home.selitem.service.SelItemService;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.AnalyzeResult;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;



@Controller
@RequestMapping(value="/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService service;
	@Autowired
	private AdminService adminService;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private SelItemService selService;
	
	@GetMapping(value="")
	public String admin(Model model,
			HttpServletRequest request) {
		
		// 회원정보
		List<AccountsDTO> datas = service.selectAll();
		request.setAttribute("datas", datas);
		// 거래내역 전체조회
		List<PurchaseDTO> purchaseDatas = purchaseService.selectAll();
		List<Integer> itemNumbers = new ArrayList<>(); 
		itemNumbers = selService.getItemNumbers();
		for(PurchaseDTO purchaseData : purchaseDatas) {
			int coupon_number = purchaseData.getBuy_usedCoupon();
			String coupon_name = purchaseService.getCouponNameFromNumber(coupon_number);
			purchaseData.setBuy_usedCouponName(coupon_name);
			if(! itemNumbers.contains(Integer.toString(purchaseData.getBuy_itemNumber()))) {
				purchaseData.setItemDelChk("Y");
			}
		}
		request.setAttribute("purchaseDatas", purchaseDatas);

		return "admin/admin";
	}
	
	// Selenium
//	@GetMapping(value="/crawling_kmong")
//	@ResponseBody
//	public List<String> crawlingKmong(String content, HttpServletRequest request) {
//		// Selenium 웹 크롤링
//		String chromeDriver = "webdriver.chrome.driver";
//		// D 드라이브에 있는 chromedriver.exe 파일 
//		String chromePath = "D:\\chromedriver.exe";
//		System.out.println(chromePath);
//		
//		System.setProperty(chromeDriver, chromePath);
//		
//		ChromeOptions options = new ChromeOptions();
//		options.setCapability("ignoreProtectedModeSettings", true);
//		options.addArguments("--disable-dev-shm-usage");
//		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//		chromePrefs.put("profile.default_content_settings.popups", 0);
//		chromePrefs.put("download.default_directory", chromePath);
//		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
//		options.setExperimentalOption("prefs", chromePrefs);
//		options.addArguments("--disable-notifications");
//		options.addArguments("--disable-popup-blocking");
//		options.addArguments("--headless");
//		options.addArguments("--disable-gpu");
//		options.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
//		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//		options.setCapability(ChromeOptions.CAPABILITY, options);
//		options.addArguments("disable-infobars");
//		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//
//		  
//		WebDriver driver = new ChromeDriver();
//		WebDriverWait wait = new WebDriverWait(driver, 30); // Thread.sleep 대신 사용하는 것으로, 대기를 위해 사용
//		
//		driver.get("https://www.naver.com/");
//		try {
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#account > a")));
//			driver.findElement(By.cssSelector("#account > a")).click(); // #account > a 요소를 찾아서 클릭
//			
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id")));
//			driver.findElement(By.id("id")).sendKeys("아이디"); // 해당 요소를 찾아서, 입력
//			
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pw")));
//			driver.findElement(By.id("pw")).sendKeys("비밀번호");
//			
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("log.login")));
//			driver.findElement(By.id("log.login")).click();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		driver.get("localhost/home/sellitem" + content);
//		List<WebElement> review = driver.findElements(By.xpath("/html/body/section/div[3]/div[1]/a/div"));
//		System.out.println(review);
//		for(WebElement element : review) {
//			System.out.println("---------------");
//			System.out.println(element.getText());
//		}
//		
//		List<String> list = null;
//		return list;
//	}
	
	
	// 리뷰 인기키워드
	@PostMapping(value="/crawling")
	@ResponseBody
	public Map<String, Integer> craw_select(String content, 
			HttpServletRequest request, HttpServletResponse response
			, @RequestParam("starCount") int starCount)throws Exception {
		
		//String url = "http://localhost/home/sellitem" + content;
		//System.out.println(url);
		//Document doc = Jsoup.connect(url).get();		
		//Elements ele = doc.select("div.card");		
		//int Length = ele.size();
		//System.out.println("div개수" + Length);
		
		//List<String> NameResult = new ArrayList<>();
		//List<String> ReviewCount = new ArrayList<>();
		//NameResult.add(ele.select("h5.card-title").text());

		//ReviewCount.add(ele.select("p.card-text").text());
		//System.out.println(NameResult);
		//System.out.println(ReviewCount);
		
		String Reviews = null;
		System.out.println("starcount : " + starCount);
		System.out.println(1 + starCount);
		List<String> reviewList = selService.allReviews(starCount);
		for (String review : reviewList) {
			Reviews += review;
		}
		System.out.println(Reviews);
		
		
		//Map<String, Object> resultMap = new HashMap<String, Object>();
		// resultMap 은 단순히 크롤링한거
		//resultMap.put("NameResult", NameResult);
		//resultMap.put("ReviewCount", ReviewCount);
		//System.out.println("resultMap : " + resultMap);
		
		// komoran
		//String res1 = ele.select("h5.card-title").text() + ele.select("p.card-text").text();
		
		String replace_text = Reviews.replace("[^가-힣a-zA-Z0-9", " ");	// 쓸데없는 문자 제거
		String trim_text = replace_text.trim();  // 불필요한 공백제거
		
		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);	// FULL 은 무거운대신 정확도, LIGHT 는 가벼운버전
		// 일반명사, 고유명사, 의존명사, 동사, 형용사만 따오기
		List<String> pList = komoran.analyze(trim_text).getMorphesByTags("NNG", "NNP", "NNB", "VV", "VA");
		Map<String, Integer> rMap = new HashMap<>();
		Set<String> rSet = new HashSet<String>(pList);	// 중복되지 않은 단어만 저장
		Iterator<String> it = rSet.iterator();
		while(it.hasNext()) {
			String word = it.next();
			int frequency = Collections.frequency(pList, word);	// 빈도수
			rMap.put(word, frequency);
		}
		// rMap 은 형태소에 따라 나눈 것.
		System.out.println("rMap : " + rMap);
		List<Map.Entry<String, Integer>> entryList = new LinkedList<>(rMap.entrySet());
		entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
		    @Override
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
			return o2.getValue() - o1.getValue();
		    }
		});

		Map<String, Integer> rMap2 = new HashMap<>();
		for(int i = 0; i < 20; i++) {
			rMap2.put(entryList.get(i).getKey(), entryList.get(i).getValue());
		}

		return rMap2;
	}
	
	@GetMapping(value="/addBlacklist")
	public String addBlacklist(Model model
			, HttpServletRequest request) {
		
		String id = request.getParameter("id");
		AccountsDTO blackData = service.idCheck(id);
		System.out.println(blackData);
		model.addAttribute("blackData", blackData);
		
		return "admin/blacklist";
	}
	
	@PostMapping(value="/addBlacklist")
	public String blacklist(Model model
			, HttpServletRequest request) {
		
		String id = request.getParameter("blackId");
		String name = request.getParameter("blackName");
		String path = request.getServletContext().getRealPath("/resources/img/profile/");
		System.out.println(path + id + ".png");
		// 서버에 저장된 이미지 삭제
		File file = new File(path + "\\" + id + ".png");
		file.delete();
		
		// 블랙리스트에 추가
		BlackDTO black = new BlackDTO();
		String ip = service.getIp(id);
		black.setBlack_email(id);
		black.setIp_address(ip);
		black.setBanned("Y");
		// DB 에 아이디 및 정보 삭제
		boolean result = service.addBlacklist(name);
		// BLACKLIST 테이블에 해당 회원 정보 추가
		boolean result2 = adminService.addBlacklist(black);
		return "redirect:/admin";
	}
	
	@PostMapping(value="/addEventCoupon")
	public String addEventCoupon(Model model, 
			HttpServletRequest request) {
		String evtcouName = request.getParameter("evtcouName");		// 이벤트 쿠폰명
		Date endDate = java.sql.Date.valueOf(request.getParameter("endDate"));		// 쿠폰 마감일
		int salePercent = Integer.parseInt(request.getParameter("salePercent"));	// 쿠폰 할인율
		EventCouponDTO eventCoupon = new EventCouponDTO();
		eventCoupon.setEvtcou_name(evtcouName);
		eventCoupon.setEvtcou_endDate(endDate);
		eventCoupon.setEvtcou_salePercent(salePercent);
		
		boolean result = purchaseService.addEventCoupon(eventCoupon);
		
		return "redirect:/admin";
	}
}
