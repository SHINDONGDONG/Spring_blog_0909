package com.cos.blog_0909.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cos.blog_0909.config.auth.PrincipalDetails;

@Controller
public class BoardController {

	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal PrincipalDetails principalDetails,Model mv) { //컨트롤러에서는 세션을 어떻게 찾는지??
		mv.addAttribute("principal",principalDetails);
		System.out.println("사용자 아이디 : " +principalDetails.getUsername());
		return "index";
	}
	
}
