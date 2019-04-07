package com.lmp.mylib.controller;

import javax.servlet.http.HttpServletResponse;
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
		session.setAttribute("admin", admin);  				
		boolean res = service.isAdmin(admin.getAdminId(), admin.getAdminPw());				
		if(res) 
			return "adminLoginSuccess";						
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
	public String registerMember(Member member, HttpSession session, HttpServletResponse response) {
		
		return "testjsp";
	/*	Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null) {
			return "adminLoginRequest";
		}
		
		System.out.println("³ªÀÌ : " + member.getMemAge());
		
//		if(member.getMemName() == null || member.getMemSex() == null || member.getMemAge() == null || member.getMemPassword() == null)
	//		return "redirect:/resources/regMember.html";
		
		int res = service.registerMember(member);
		if(res == 1) 									
			return "memberRegisterSuccess";
		else
			return "memberRegisterFail";
*/
	}
	
	
}
