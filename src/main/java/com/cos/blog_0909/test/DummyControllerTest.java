package com.cos.blog_0909.test;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog_0909.model.RoleType;
import com.cos.blog_0909.model.User;
import com.cos.blog_0909.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired//依存性注入
	private UserRepository userRepository;
	
	
	
	@Transactional
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제에 실패하였습니다" + e.getMessage();
		}
		return "삭제에 성공하였다" + id;
	}
	
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
		System.out.println("id :" + id);
		System.out.println("password :" + requestUser.getPassword());
		System.out.println("email :" + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("못찾았다잉");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user); 세이브 하지않아도 transactional을 붙여주면 더티체킹이 들어가 업데이트가됨
		return null;
	}
	
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
			return new IllegalArgumentException("찾으시는 사용자가 없습니다. id : "+ id);
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
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return users;
	}
	
}
