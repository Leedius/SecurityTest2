package com.study.test.config;


//@Configuration
public class BeanController {
	//이렇게 하면 serviceImpl에 @service(name="memberService)
	//쓸 필요가 없어지지만 추천하는 방법은 아니다.
	//@Bean(name="memberService")
	//public MemberService memberService(){
	//	return new MemberServiceImpl();
	//}
}
