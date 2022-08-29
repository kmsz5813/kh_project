package com.myweb.home.sel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.home.sel.model.SelItemDTO;

@Controller
@RequestMapping(value="/sel")
public class SelItemController {

	@GetMapping(value="/selitem")
}
