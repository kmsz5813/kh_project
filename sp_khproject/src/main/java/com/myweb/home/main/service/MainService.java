package com.myweb.home.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.home.main.model.MainDAO;

@Service
public class MainService {

	@Autowired
	private MainDAO dao;
}
