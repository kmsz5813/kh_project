package com.myweb.home.info.service;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
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

import com.myweb.home.Accounts.model.AccountsDTO;
import com.myweb.home.info.controller.InfoController;
import com.myweb.home.login.service.LoginService;

/*
 * @Controller
 * 
 * @RequestMapping(value="/info/modify") public class InfoModifyController {
 * private static final Logger logger =
 * LoggerFactory.getLogger(InfoController.class);
 * 
 * 
 * @Autowired private LoginService service;
 * 
 * @GetMapping(value="") public String main(Model model
 * , @SessionAttribute("loginData") AccountsDTO acDto , HttpServletRequest
 * request) { String name = request.getParameter("mod_name");
 * System.out.println(name); return "./info/modify"; }
 * 
 * 
 * @PostMapping(value="") public String modify(Model model
 * , @SessionAttribute("loginData") AccountsDTO acDto , HttpServletRequest
 * request) { String name = request.getParameter("mod_name");
 * System.out.println(name); return "info/info"; }
 * 
 * 
 * 
 * 
 * //닉네임중복체크
 * 
 * @PostMapping("/nameCheck")
 * 
 * @ResponseBody public String nameCheck(@RequestParam("name") String name) {
 * 
 * JSONObject json = new JSONObject();
 * 
 * AccountsDTO data = service.nameCheck(name);
 * 
 * System.out.println("로그인컨트롤러 : " + data);
 * 
 * if(data == null) { json.put("code", "success"); }else { json.put("code",
 * "sameid"); }
 * 
 * return json.toJSONString();
 * 
 * } }
 */
