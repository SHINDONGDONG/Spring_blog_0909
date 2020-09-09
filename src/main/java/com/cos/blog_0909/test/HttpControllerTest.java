package com.cos.blog_0909.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController//USERがリクエスト―＞レスポンス（データ―）
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder()
				.username("ssar").password("1234").email("sarr@naver.com").build();
		return "lombok test完了";
	}
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		System.out.println("getリクエスト："+m.getId()+","+m.getUsername()+","+m.getPassword());
		System.out.println(TAG+"Getter :"+m.getId());
		return "GETリクエスト";
	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		System.out.println("postリクエスト："+m.getId()+","+m.getUsername()+","+m.getPassword());
		return "POSTリクエスト : ";
	}
	@PutMapping("/http/put")
	public String pushTest() {
		return "PUTリクエスト";
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "DELETEリクエスト";
	}
	
}
