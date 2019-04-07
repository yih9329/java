package com.lmp.mylib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lmp.mylib.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService service;
	
	
}
