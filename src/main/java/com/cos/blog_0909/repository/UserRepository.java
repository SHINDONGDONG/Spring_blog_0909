package com.cos.blog_0909.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog_0909.model.User;


//DAO
//BEAN
//@Repository 省略可能
public interface UserRepository extends JpaRepository<User, Integer>{

	
	
}
