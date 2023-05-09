package com.study.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration	//객체 생성 + 설정파일임을 지정
@EnableWebSecurity	//
public class SecurityConfig {
	
	//인증과 인가에 대한 설정 세팅
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		
		security.csrf().disable()	//csrf 공격 방어 해제
				.authorizeHttpRequests()	//지금부터 인증 설정 시작
				.requestMatchers("/"
								, "/joinForm"
								, "/join").permitAll()
				.requestMatchers("/css/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/loginForm")
				.loginProcessingUrl("/login")
				.usernameParameter("memId")
				.passwordParameter("memPw")
				.defaultSuccessUrl("/memberList")
				.failureUrl("/loginForm")
				.permitAll()
			.and()
				.logout()	//로그아웃 진행
				.invalidateHttpSession(true)	//로그아웃시 세션 삭제
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				;
		return security.build();
	}
	
	//암화화 객체 생성
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
