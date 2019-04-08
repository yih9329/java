package com.lmp.mylib.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lmp.mylib.Admin;
import com.lmp.mylib.Member;
import com.lmp.mylib.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService service;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String adminLogin(Admin admin, HttpSession session) {  				
		boolean res = service.isAdmin(admin.getAdminId(), admin.getAdminPw());				
		if(res) {
			session.setAttribute("admin", admin);
			return "adminLoginSuccess";			
		}
		else
			return "adminLoginFail";
	}
	
	@RequestMapping(value="/logout")
	public String adminLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/resources/main.html";
	}
	
	@RequestMapping(value="/goToMngMntPage")
	public String goToMngMntPage(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "adminLoginRequest";
		
		return "adminLoginSuccess";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerMember(Member member, HttpSession session) {
	
		System.out.println(member.getMemAddress());
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null) {
			return "adminLoginRequest";
		}
		
		int res = service.registerMember(member);
		if(res == 1) 									
			return "memberRegisterSuccess";
		else
			return "memberRegisterFail";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteMember(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "adminLoginRequest";
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String memName = request.getParameter("memName");
		String memPw = request.getParameter("memPassword");
		int res = service.deleteMember(memName, memPw);
		if(res == 1)
			return "memberDeleteSuccess";
		else
			return "memberDeleteFail";
	}
}
