package com.study.test.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.test.vo.MemberVO;

import jakarta.annotation.Resource;

//시큐리티가 로그인 할 때 자동으로 실행하는 클래스
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO userInfo = memberService.getUserInfoForLogin(username);
		
		UserDetails user = User.withUsername(userInfo.getMemId())
								.password(userInfo.getMemPw())
								//아래 userInfo.getRole()은 "ADMIN,USER,MANAGER" 이므로
								//split으로 ,빼서 "ADMIN", "USER", "MANAGER"로 분리해준다.
								.roles(userInfo.getRole().split(","))
								.build();
		return user;
	}

}
