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

import com.lmp.mylib.RTime;
import com.lmp.mylib.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService service;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
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
			HttpSession session = request.getSession();
			session.setAttribute("member", memName);
			model.addAttribute("memName", memName);
			return "member/memberLoginSuccess";
		}
		else
			return "member/memberLoginFail";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/memberLogout";
	}
	
	@RequestMapping("goToMngMntPage")
	public String goToMngMntPage(HttpSession session) {
		String memName = (String) session.getAttribute("member");
		if(memName == null)
			return "member/memberLoginRequest";
		return "member/memberLoginSuccess";
	}
	
	@RequestMapping("/showRideTime")
	public String showRideTime(Model model, HttpSession session) {
		String memName = (String) session.getAttribute("member");
		if(memName == null)
			return "member/memberLoginRequest";
		
		List<RTime> rtime = service.showRideTime();
		model.addAttribute("rtime", rtime);
		return "member/showRideTime";
	}
	
	public String applyRide(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memName = (String) session.getAttribute("member");
		if(memName == null)
			return "member/memberLoginRequest";
		
		String reqRTime = request.getParameter("reqRTime");
		
	}
}
