package com.cos.blog_0909.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog_0909.dto.ResponseDto;
import com.cos.blog_0909.model.RoleType;
import com.cos.blog_0909.model.User;
import com.cos.blog_0909.service.UserService;

@RestController 
public class UserApiController {

	@Autowired 
	private UserService userService;
	

	
//	@Autowired
//	private HttpSession session;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("user api controller :save 호출됨");
		userService.save(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	/*
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user) {
	 * System.out.println("user api conroller : login 호출됨"); User principal =
	 * userService.login(user); if(principal != null) {
	 * session.setAttribute("principal", principal); } return new
	 * ResponseDto<Integer>(HttpStatus.OK.value(),1); }
	 */
	
}
