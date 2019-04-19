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
import com.lmp.mylib.RTime;
import com.lmp.mylib.Ride;
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
			return "admin/adminLoginSuccess";			
		}
		else
			return "admin/adminLoginFail";
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
			return "admin/adminLoginRequest";
		
		return "admin/adminLoginSuccess";
	}
	
	@RequestMapping("/showSeatInfo")
	public String showSeatInfo(Model model, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "admin/adminLoginRequest";
		
		List<Seat> seat = service.showSeatInfo();
		model.addAttribute("seatList", seat);
		return "admin/seatInfo";
	}
	
	@RequestMapping("/showMemInfo")
	public String showMemInfo(Model model, HttpServletRequest request) {								// 커맨드 객체를 사용하여 view로 넘겨줄땐 왜 되지 않을까?
		HttpSession session = request.getSession();						
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "admin/adminLoginRequest";
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int seatNum = Integer.parseInt(request.getParameter("seatNum"));
		MemberDB member = service.getMemInfo(seatNum);
		model.addAttribute("seatNum", seatNum);
		model.addAttribute("memName", member.getMemName());
		model.addAttribute("memSex", member.getMemSex());
		model.addAttribute("memAge", member.getMemAge());
		model.addAttribute("memPhone", member.getMemPhone());
		model.addAttribute("memAddress", member.getMemAddress());
		model.addAttribute("memPassword", member.getMemPassword());
		return "admin/memberInfo";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerMember(MemberWeb member, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null) {
			return "admin/adminLoginRequest";
		}
		
		int seatNum = Integer.parseInt(request.getParameter("seatNum"));
		int res = service.registerMember(member, seatNum);
		if(res == 1)
			return "admin/memberRegisterSuccess";
		else
			return "admin/memberRegisterFail";
	}
	
	@RequestMapping(value="/delete")
	public String deleteMember(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "admin/adminLoginRequest";
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int seatNum = Integer.parseInt(request.getParameter("seatNum"));
		int res = service.deleteMember(seatNum);
		if(res == 1)
			return "admin/memberDeleteSuccess";
		else
			return "admin/memberDeleteFail";
	}
	
	@RequestMapping("/selectSeat")
	public String selectSeat(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "admin/adminLoginRequest";
		List<Seat> seat = service.showSeatInfo();
		int curSeatNum = Integer.parseInt(request.getParameter("seatNum"));
		model.addAttribute("seatList", seat);
		model.addAttribute("curSeatNum", curSeatNum);
		return "admin/selectNewSeat";
	}
	
	@RequestMapping("/moveSeat")
	public String moveSeat(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "admin/adminLoginRequest";
		int curSeatNum = Integer.parseInt(request.getParameter("curSeatNum"));
		int newSeatNum = Integer.parseInt(request.getParameter("newSeatNum"));
		int res = service.moveSeat(curSeatNum, newSeatNum);
		if(res != 2)
			return "admin/moveSeatFail";
		return "admin/moveSeatSuccess";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyMemInfo(MemberWeb member, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "admin/adminLoginRequest";
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String curMemPw = request.getParameter("curPassword");
		int res = service.modifyMemInfo(member, curMemPw);
		
		if(res == 1)
			return "admin/memberInfoModifySuccess";
		else
			return "admin/memberInfoModifyFail";
	}
	
	@RequestMapping("/showRideInfo")
	public String showRideInfo(Model model, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "admin/adminLoginRequest";
		List<Ride> rideInfo = service.showRideInfo();
		List<RTime> rtimeInfo = service.showRTimeInfo();
		
		model.addAttribute("rideInfo", rideInfo);
		model.addAttribute("rtimeInfo", rtimeInfo);
		return "admin/ride/rideInfo";
	}
	
	@RequestMapping("/setRideTime")
	public String setRideTime(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "admin/adminLoginRequest";
		
		String[] rtime = request.getParameterValues("rtime");
		int maxNum = Integer.parseInt(request.getParameter("maxNum"));
		int res = service.deleteRTime();
		
		if(rtime == null)
			return "admin/ride/rtimeDeleteSuccess";
		res = service.setRTime(rtime, maxNum);
		if(res != rtime.length) {
			service.deleteRTime();
			return "admin/ride/rtimeSetFail";
		}
		return "admin/ride/rtimeSetSuccess";
	}
	
	@RequestMapping("/deleteRideTime")
	public String deleteRideTime(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null)
			return "admin/adminLoginRequest";
		service.deleteRTime();
		return "admin/ride/rtimeDeleteSuccess";
	}
}
