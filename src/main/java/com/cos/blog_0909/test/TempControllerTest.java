package com.cos.blog_0909.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	
	//
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("temp()");
		return "home.html";
	}

	@GetMapping("/temp/jsp")
	public String tempJsp() {
		System.out.println("temp()");
		return "test";
	}
	
}
