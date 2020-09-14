package com.cos.blog_0909.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {

	@GetMapping("/auth/joinForm") // auth/** 이하의 주소는 모두허용 해줄예정
	public String joinForm() { // 그냥주소가 "/" 이면 index.jsp허용
		return "user/joinForm"; // static 이하에 있는 css,js,image 허용
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
	//아까전 지정해준 콜백주소를 넣어준다.
	//responseBody 데이터를 리턴해주는 컨트롤러 함수가됨
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String  kakaoCallback(String code) {
		
		//POST방식으로 key=value값 데이터를 요청(카카오쪽으로)
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"); 
		//헤더에 컨텐트타입을 담아야한다. 내가 담아서 보낼 데이터가 key=value인 값인지 알려주는것이다.
		
		//body데이터를 받을 오브젝트를 선언
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("grant_type","authorization_code");
		params.add("client_id","9f92a7f5b04cf67085fa4c503d49090e");
		params.add("redirect_uri","http://localhost:8181/auth/kakao/callback");
		params.add("code",code);
		
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = 
				new HttpEntity<>(params,headers);
		 
		//http 요청하기 - post 방식으로 - 그리고 response 변수의 응답받음.
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,//http body들어갈 데이터와 head값
				String.class
				);
		
		return  "카카오 토큰요청 완료 : " + response;
	}
}
