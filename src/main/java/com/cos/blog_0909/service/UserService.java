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

	/*
	 * @Transactional(readOnly = true)//select 시작할때 트랜잭션 시작 서비스종료시 트랜잭션 종료(정합성)
	 * public User login(User user) { return
	 * uesrRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword(
	 * )); }
	 */
	
	
}
