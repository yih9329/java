package com.lmp.mylib.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lmp.mylib.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService service;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String adminLogin(HttpServletRequest request) {
		String adminId = request.getParameter("adminId");
		String adminPw = request.getParameter("adminPw");
		
		boolean res = service.isAdmin(adminId, adminPw);				
		if(res) 
			return "adminLoginSuccess";						//관리자 계정이 맞다면
		else
			return "adminLoginFail";
	}
	
	public String regMember() {
		return null;
	}
}
