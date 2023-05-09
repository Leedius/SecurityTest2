package com.study.test.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.test.vo.MemberVO;


@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private SqlSessionTemplate sqlSession;

	//회원가입
	@Override
	public void join(MemberVO memberVO) {
		sqlSession.insert("memberMapper.join", memberVO);
	}

	//로그인을 위한 정보 조회 기능
	@Override
	public MemberVO getUserInfoForLogin(String memId) {
		return sqlSession.selectOne("memberMapper.getUserInfoForLogin", memId);
	}

	//회원 목록 조회
	@Override
	public List<MemberVO> getMemberList() {
		return sqlSession.selectList("memberMapper.getMemberList");
	}

	//비밀번호 초기화
	@Override
	public void findPw(MemberVO memberVO) {
		
	}
	
}
