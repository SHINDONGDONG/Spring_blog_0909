package com.cos.blog_0909.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog_0909.model.User;
import com.cos.blog_0909.repository.UserRepository;

@Service//Bean등록
public class PrincipalService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	//spring이 login 요청을 가로챌때 username과 password 두개를 가로채는데 
	//password부분 처리는 알아서함..
	//해당 username이 db에 있는지만 확인해서 리턴해준다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User principal = userRepository.findByUsername(username).orElseThrow(()->{
				return new UsernameNotFoundException("유저 아이디를 찾지 못하였습니다." +  username);
			});
		return new PrincipalDetails(principal);
	}

	
	
}
