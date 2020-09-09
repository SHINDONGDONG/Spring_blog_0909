package com.cos.blog_0909.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //특정 어노테이션이 붙어있는 클래스파일들을 IoC해서 스프링컨테이너에 관리해준다.
public class BlogControllerTeset {

	
	//http://localhost:8181/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}
