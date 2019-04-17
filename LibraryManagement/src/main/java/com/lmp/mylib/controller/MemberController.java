package com.lmp.mylib.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lmp.mylib.RTime;
import com.lmp.mylib.SeatWeb;
import com.lmp.mylib.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService service;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String memberLogin(SeatWeb seat, Model model, HttpServletRequest request) {		
		String memName = seat.getMemName();
		String memPw = seat.getMemPassword();
		boolean res = service.memberLogin(memName, memPw);
		
		if(res) {
			int seatNum = service.getSeatNum(memName, memPw);
			seat.setSeatNum(seatNum);
			HttpSession session = request.getSession();
			session.setAttribute("member", seat);
			model.addAttribute("seat", seat);
			return "member/memberLoginSuccess";
		}
		else
			return "member/memberLoginFail";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/memberLogout";
	}
	
	@RequestMapping("/goToMngMntPage")
	public String goToMngMntPage(Model model, HttpSession session) {
		SeatWeb seat = (SeatWeb) session.getAttribute("member");
		if(seat == null)
			return "member/memberLoginRequest";
		model.addAttribute("seat", seat);
		return "member/memberLoginSuccess";
	}
	
	@RequestMapping("/showRideTime")
	public String showRideTime(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		SeatWeb seat = (SeatWeb) session.getAttribute("member");
		if(seat == null)
			return "member/memberLoginRequest";
		
		int seatNum = seat.getSeatNum();
		List<RTime> rtime = service.showRideTime();
		model.addAttribute("rtime", rtime);
		if(rtime.size() != 0) {
			List<Integer> rideNum = service.showRideNum(rtime);
			model.addAttribute("rideNum", rideNum);
			model.addAttribute("seatNum", seatNum);
		}
		return "member/showRideTime";
	}
	
	@RequestMapping("/applyRide")
	public String applyRide(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SeatWeb seat = (SeatWeb) session.getAttribute("member");
		if(seat == null)
			return "member/memberLoginRequest";
		
		String rtime = request.getParameter("time");
		int seatNum = Integer.parseInt(request.getParameter("seatNum"));
		int res = service.applyRide(seatNum, rtime);
		
		if(res == 0) 
			return "member/rideApplyFail";
		return "member/rideApplySuccess";
	}
	
	@RequestMapping("/showMyRideInfo")
	public String showMyRideInfo(Model model, HttpSession session) {
		SeatWeb seat = (SeatWeb) session.getAttribute("member");
		if(seat == null)
			return "member/memberLoginRequest";
		
		int seatNum = seat.getSeatNum();
		String rtime = service.showMyRideTime(seatNum);
		if(rtime == null)
			return "member/myRideInfoEmpty";
		model.addAttribute("rtime", rtime);
		return "member/myRideInfoExist";
	}
	
	@RequestMapping("/modifyMyRideInfo")
	public String modifyMyRideInfo(HttpSession session) {
		SeatWeb seat = (SeatWeb) session.getAttribute("member");
		if(seat == null)
			return "member/memberLoginRequest";
		
		
	}
	
}
