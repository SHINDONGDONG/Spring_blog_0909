package com.cos.blog_0909.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog_0909.config.auth.PrincipalService;

@Configuration
@EnableWebSecurity //시큐리티 필터추가 (필터가 등록이된다) 그설정을 여기서함.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정주소 접근 권한 및 인증 미리체크
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder encPWD() {
		return new BCryptPasswordEncoder();
	} 
	
	@Autowired
	private PrincipalService principalService;

	
	//시큐리티가 대신 로그인해주는데 password를 가로채는데
	//해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	//같은 해쉬로 암호화되어서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalService).passwordEncoder(encPWD());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //csrf토큰을 가지고있지 않고 요청을하면 다 막아버림 (그래서 disable해놓음) (user.js 에서 우리가 ajax로 요청한것)
			.authorizeRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
				.permitAll()
				.anyRequest() //위의 주소아닌 주소는
				.authenticated()//인증이 되어야해 
			.and() //인증이 필요한 요청이오면
				.formLogin() // 로그인 페이지가 요청이오면
				.loginPage("/auth/loginForm")//로그인 페이지로 이동시켜라 우리의경우 auth/loginForm
				.loginProcessingUrl("/auth/loginProc")//해당주소로 오는 요청을 스프링 시큐리티가 가로챈다.  
				.defaultSuccessUrl("/"); //정상적일때 로그인이 끝나면 어디로가면될까? 라는 
	}
	
}
