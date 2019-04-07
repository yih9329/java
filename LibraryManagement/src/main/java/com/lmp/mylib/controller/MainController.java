package com.lmp.mylib.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String showMainPage(HttpServletResponse response) {
		/*try {
			response.sendRedirect("/mylib/resources/main.html");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return "redirect:/resources/main.html";
	}	
}
