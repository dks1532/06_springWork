package com.kh.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
//	@GetMapping("memberEnroll.me")	// get방식
//	public String memberEnroll() {
//		return "member/memberEnroll";
//	}
	// 위의 구문을 더 쉽게하는 방법
	@GetMapping("/memberEnroll.me")
	public void memberEnroll() {}	// => /member/memberEnroll
	
	@PostMapping("/memberEnroll.me")
	public String memberEnroll(Member member) {
		System.out.println(member);
		
		memberService.insertMember(member);
		return "redirect:/";	
	}
}
