package com.study.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.test.service.MemberService;
import com.study.test.service.MemberServiceImpl;
import com.study.test.vo.MemberVO;

import jakarta.annotation.Resource;

@Controller
public class MemberController {
	@Resource(name = "memberService")
	private MemberService memberService;
	
	//암호화 객체
	@Autowired
	private PasswordEncoder encoder;
	
	//첫 시작 페이지
	@GetMapping("/")
	public String main() {
		//암호화 기본
		String p1 = encoder.encode("java");
		String p2 = encoder.encode("java");
		System.out.println("p1 = " + p1);
		System.out.println("p2 = " + p2);
		
		//암화화 데이터 매치
		boolean b1 = encoder.matches("java" , p1);
		boolean b2 = encoder.matches("java", p2);
		boolean b3 = encoder.matches("java1", p2);
		System.out.println("b1 = " + b1);
		System.out.println("b2 = " + b2);
		System.out.println("b3 = " + b3);
		
		return "main";
	}
	
	//첫 시작 페이지
	@GetMapping("/joinForm")
	public String joinForm() {
		return "join";
	}
	
	//회원 가입
	@PostMapping("/join")
	public String join(MemberVO memberVO) {
		//비밀번호 암호화
		memberVO.setMemPw(encoder.encode(memberVO.getMemPw()));
		memberService.join(memberVO);
		return "redirect:/";
	}
	
	//로그인 페이지 이동
	@GetMapping("/loginForm")
	public String loginForm() {
		return "login";
	}
	
	//개인정보 조회 페이지
	@GetMapping("/memberDetail")
	public String memberDetail(Authentication authentication) {
		//로그인 정보
		User user = (User)authentication.getPrincipal();
		
		//로그인한 회원의 아이디
		System.out.println(user.getUsername());
		//로그인한 회원의 비밀번호
		System.out.println(user.getPassword());
		//로그인한 회원의 권한정보
		//user.getAuthorities() 이것의 자료형이 GrantedAuthority 이기때문에
		//자료형도 똑같이 쓴다
		List<GrantedAuthority> authoList = new ArrayList<>(user.getAuthorities());
		for(GrantedAuthority authority : authoList) {
			System.out.println(authority.getAuthority());
		}
		
		return "member_detail";
	}
	
	//회원목록 페이지(매니저)
	@GetMapping("/memberList")
	public String memberList(Model model) {
		List<MemberVO> memList = memberService.getMemberList();
		model.addAttribute("memberList", memList);
		return "member_list";
	}

	//관리자 페이지
	@GetMapping("/admin")
	public String admin() {
		return "admin_page";
	}
	
	public MemberService aaa() {
		MemberServiceImpl m = new MemberServiceImpl();
		return new MemberServiceImpl();
	}
	
}
