package com.lmp.mylib.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lmp.mylib.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService service;
	
	@RequestMapping("/login")
	public String memberLogin(Model model, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String memName = request.getParameter("memName");
		String memPw = request.getParameter("memPassword");
		boolean res = service.memberLogin(memName, memPw);
		
		if(res) {
			model.addAttribute("memName", memName);
			return "member/memberLoginSuccess";
		}
		else
			return "member/memberLoginFail";
		
	}
}
