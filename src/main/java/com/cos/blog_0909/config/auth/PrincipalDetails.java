package com.cos.blog_0909.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog_0909.model.User;

import lombok.Getter;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
public class PrincipalDetails implements UserDetails{ //userdeatils 를 품고있기때문에 PrincipalDetails라도 괜찮다.
	
	private User user; //콤포지션(객체를 들고있는거) //당연히 User에 저장하기때문에 콤포지션 되어있어야함.
	
	public PrincipalDetails(User user) { //principalDetails에 넘어오는 user 값을 넣어주기위하여 
		this.user = user;
	}
	
	//권한을 리턴해주는 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(()->{ return "ROLE_"+user.getRole();});
		return collection;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
