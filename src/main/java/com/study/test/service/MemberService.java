package com.study.test.service;

import java.util.List;

import com.study.test.vo.MemberVO;

public interface MemberService {
	
	//회원가입
	void join(MemberVO memberVO);
	
	//로그인을 위한 정보 조회 기능
	MemberVO getUserInfoForLogin(String memId);
	
	//회원 목록 조회
	List<MemberVO> getMemberList();
	
	//비밀번호 초기화
	void findPw(MemberVO memberVO);
}
