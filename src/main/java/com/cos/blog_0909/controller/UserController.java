package com.cos.blog_0909.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/auth/joinForm") //auth/** 이하의 주소는 모두허용 해줄예정
	public String joinForm() {    // 그냥주소가 "/" 이면 index.jsp허용
		return "user/joinForm";   //static 이하에 있는 css,js,image 허용
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
}
