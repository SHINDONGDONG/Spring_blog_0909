package com.cos.blog_0909.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.cos.blog_0909.model.KaKaoProfile;
import com.cos.blog_0909.model.OAuthToken;
import com.cos.blog_0909.model.User;
import com.cos.blog_0909.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UserController {
	
	@Value("${cos.key}")
	private String key;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

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
	public  String  kakaoCallback(String code) {
		
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
		
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//POST방식으로 key=value값 데이터를 요청(카카오쪽으로)
		RestTemplate rt2 = new RestTemplate();
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token()); 
		headers2.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"); 
		//헤더에 컨텐트타입을 담아야한다. 내가 담아서 보낼 데이터가 key=value인 값인지 알려주는것이다.
		
		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2);
		 
		//http 요청하기 - post 방식으로 - 그리고 response 변수의 응답받음.
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,//http body들어갈 데이터와 head값
				String.class
				);
		
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KaKaoProfile kaKaoProfile= null;
		try {
			kaKaoProfile = objectMapper2.readValue(response2.getBody(),KaKaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오 아이디(번호) : "+kaKaoProfile.getId());
		System.out.println("카카오 아이디(번호) : "+kaKaoProfile.getKakao_account().getEmail());
		System.out.println("블로그 서버 username : " + kaKaoProfile.getKakao_account().getEmail() +"_"+kaKaoProfile.getId());
		System.out.println("블로그서버 이메일 : "+kaKaoProfile.getKakao_account().getEmail());
		System.out.println("블로그 패스워드 : "+ key);
		
		int k_id = kaKaoProfile.getId();
		String k_email = kaKaoProfile.getKakao_account().getEmail();
		String k_username = k_email + "_" + k_id;
		String k_password = key;  

		User kakaoUser= User.builder()
				.username(k_username)
				.email(k_email)
				.password(k_password)
				.oauth("kakao")
				.build();
		 
		//회원찾아서 가입시키기
		User originUser = userService.findUser(kakaoUser.getUsername());
		if(originUser.getUsername()== null) {
			System.out.println("신규 회원입니다.");
			userService.save(kakaoUser);
		}else {
			System.out.println("이미 가입한 회원입니다.");
		}
		//로그인처리
		System.out.println("자동로그인 진행");
		Authentication authentication = authenticationManager.authenticate
				(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),key));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return  "redirect:/";
	}
}
