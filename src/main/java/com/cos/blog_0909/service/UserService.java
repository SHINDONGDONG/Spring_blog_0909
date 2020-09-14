package com.cos.blog_0909.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog_0909.model.RoleType;
import com.cos.blog_0909.model.User;
import com.cos.blog_0909.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository uesrRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Transactional
	public void save(User user) {
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		uesrRepository.save(user);
	} 

	
	@Transactional
	public void updateUser(User requestUser) {
		User user = uesrRepository.findById(requestUser.getId()).orElseThrow(()->{
			return new IllegalArgumentException("수정할 사용자를 찾지 못하였습니다.");
		});

		//oauth 가 null이거나 "" 일때만 수정이 가능함.
		if(user.getOauth() ==null || user.getOauth().equals("")) {
			String rawPassword = requestUser.getPassword();
			String encPassword = bCryptPasswordEncoder.encode(rawPassword);
			user.setPassword(encPassword);
			user.setEmail(requestUser.getEmail());
		}
		//회원수정 함수종료시 서비스 종료(트랜잭션 종료) = commit 자동
	}
	/*
	 * @Transactional(readOnly = true)//select 시작할때 트랜잭션 시작 서비스종료시 트랜잭션 종료(정합성)
	 * public User login(User user) { return
	 * uesrRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword(
	 * )); }
	 */
	
	@Transactional(readOnly = true)
	public User findUser(String username) {
		User user = uesrRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
	
}
