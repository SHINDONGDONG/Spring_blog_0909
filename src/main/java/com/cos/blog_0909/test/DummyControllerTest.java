package com.cos.blog_0909.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog_0909.model.RoleType;
import com.cos.blog_0909.model.User;
import com.cos.blog_0909.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired//依存性注入
	private UserRepository userRepository;
	
	//http://localhost:8181/blog_0909/dummy/join
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("email : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("create : " + user.getCreateDate());
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "成功的に加入";
	}
	
	//{id}주소로 파마레터를 전달받을 수 있음
	//http://localhost:8181/dummy/user/2
	@GetMapping("/dummy/user/{id}")//get 파라메터 id를 받아서 int id에 넣어준다
	public User detail(@PathVariable int id) {
		//아래의 코드는 User객체에 findById(id) 아이디가 있는지 찾아주는 메소드 없으면 throw날려주기 return new 일리걸아규먼트익셉션
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("없다");
		});
		//있으면 user를 리턴
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll(); 	
	}
	
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
		Page<User> page = userRepository.findAll(pageable);
		System.out.println("page~ : " + page);
		List<User> users = page.getContent();
		return users;
	}
	
}
