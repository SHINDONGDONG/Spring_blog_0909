package com.cos.blog_0909.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog_0909.model.User;


//DAO
//BEAN
//@Repository 省略可能
public interface UserRepository extends JpaRepository<User, Integer>{

	//네이밍쿼리임
	//SELECT * FROM user WHERE ueranme =?1;
	Optional<User> findByUsername(String username);




}


//jpa네이밍 전략
//Select * from user WHERE useranme =?1 AND password=?2
//User findByUsernameAndPassword(String username,String password);