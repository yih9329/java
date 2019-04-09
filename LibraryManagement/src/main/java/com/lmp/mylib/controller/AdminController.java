package com.lmp.mylib.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lmp.mylib.Admin;
import com.lmp.mylib.MemberDB;
import com.lmp.mylib.MemberWeb;
import com.lmp.mylib.Seat;
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
	
	@RequestMapping("/showSeatInfo")
	public String showSeatInfo(Model model, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "adminLoginRequest";
		
		List<Seat> seat = service.showSeatInfo();
		model.addAttribute("seatList", seat);
		return "seatInfo";
	}
	
	@RequestMapping("/showMemInfo")
	public String showMemInfo(Model model, HttpServletRequest request) {								// 커맨드 객체를 사용하여 view로 넘겨줄땐 왜 되지 않을까?
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "adminLoginRequest";
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int seatNum = Integer.parseInt(request.getParameter("seatNum"));
		String memName = request.getParameter("memName");
		String memPw = request.getParameter("memPw");
		MemberDB member = service.getMemInfo(memName, memPw);
		model.addAttribute("seatNum", seatNum);
		model.addAttribute("memName", member.getMemName());
		model.addAttribute("memSex", member.getMemSex());
		model.addAttribute("memAge", member.getMemAge());
		model.addAttribute("memPhone", member.getMemPhone());
		model.addAttribute("memAddress", member.getMemAddress());
		model.addAttribute("memPassword", member.getMemPassword());
		
		return "memberInfo";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerMember(MemberWeb member, HttpSession session) {
	
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
