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
		
		// ????????????
		List<AccountsDTO> datas = service.selectAll();
		request.setAttribute("datas", datas);
		// ???????????? ????????????
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
//		// Selenium ??? ?????????
//		String chromeDriver = "webdriver.chrome.driver";
//		// D ??????????????? ?????? chromedriver.exe ?????? 
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
//		WebDriverWait wait = new WebDriverWait(driver, 30); // Thread.sleep ?????? ???????????? ?????????, ????????? ?????? ??????
//		
//		driver.get("https://www.naver.com/");
//		try {
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#account > a")));
//			driver.findElement(By.cssSelector("#account > a")).click(); // #account > a ????????? ????????? ??????
//			
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id")));
//			driver.findElement(By.id("id")).sendKeys("?????????"); // ?????? ????????? ?????????, ??????
//			
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pw")));
//			driver.findElement(By.id("pw")).sendKeys("????????????");
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
	
	
	// ?????? ???????????????
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
		//System.out.println("div??????" + Length);
		
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
		// resultMap ??? ????????? ???????????????
		//resultMap.put("NameResult", NameResult);
		//resultMap.put("ReviewCount", ReviewCount);
		//System.out.println("resultMap : " + resultMap);
		
		// komoran
		//String res1 = ele.select("h5.card-title").text() + ele.select("p.card-text").text();
		
		String replace_text = Reviews.replace("[^???-???a-zA-Z0-9", " ");	// ???????????? ?????? ??????
		String trim_text = replace_text.trim();  // ???????????? ????????????
		
		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);	// FULL ??? ??????????????? ?????????, LIGHT ??? ???????????????
		// ????????????, ????????????, ????????????, ??????, ???????????? ?????????
		List<String> pList = komoran.analyze(trim_text).getMorphesByTags("NNG", "NNP", "NNB", "VV", "VA");
		Map<String, Integer> rMap = new HashMap<>();
		Set<String> rSet = new HashSet<String>(pList);	// ???????????? ?????? ????????? ??????
		Iterator<String> it = rSet.iterator();
		while(it.hasNext()) {
			String word = it.next();
			int frequency = Collections.frequency(pList, word);	// ?????????
			rMap.put(word, frequency);
		}
		// rMap ??? ???????????? ?????? ?????? ???.
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
		// ????????? ????????? ????????? ??????
		File file = new File(path + "\\" + id + ".png");
		file.delete();
		
		// ?????????????????? ??????
		BlackDTO black = new BlackDTO();
		String ip = service.getIp(id);
		black.setBlack_email(id);
		black.setIp_address(ip);
		black.setBanned("Y");
		// DB ??? ????????? ??? ?????? ??????
		boolean result = service.addBlacklist(name);
		// BLACKLIST ???????????? ?????? ?????? ?????? ??????
		boolean result2 = adminService.addBlacklist(black);
		return "redirect:/admin";
	}
	
	@PostMapping(value="/addEventCoupon")
	public String addEventCoupon(Model model, 
			HttpServletRequest request) {
		String evtcouName = request.getParameter("evtcouName");		// ????????? ?????????
		Date endDate = java.sql.Date.valueOf(request.getParameter("endDate"));		// ?????? ?????????
		int salePercent = Integer.parseInt(request.getParameter("salePercent"));	// ?????? ?????????
		EventCouponDTO eventCoupon = new EventCouponDTO();
		eventCoupon.setEvtcou_name(evtcouName);
		eventCoupon.setEvtcou_endDate(endDate);
		eventCoupon.setEvtcou_salePercent(salePercent);
		
		boolean result = purchaseService.addEventCoupon(eventCoupon);
		
		return "redirect:/admin";
	}
}
