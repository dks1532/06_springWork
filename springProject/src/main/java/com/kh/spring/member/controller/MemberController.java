package com.kh.spring.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})
public class MemberController {
	
	// 직접 Logger를 얻어와서 출력
	// private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
//	@GetMapping("/memberEnroll.me")	// get방식
//	public String memberEnroll() {
//		return "member/memberEnroll";
//	}
	// 위의 구문을 더 쉽게하는 방법
	@GetMapping("/memberEnroll.me")
	public void memberEnroll() {}  // => /member/memberEnroll
	
	@PostMapping("/memberEnroll.me")
	public String memberEnroll(Member member) {
		log.trace("memberEnroll 시작 !");
		try {
			log.debug("요청확인");
			
			// 비밀번호 암호화
			String rawPassword = member.getUserPwd();
			String encodedPassword = passwordEncoder.encode(rawPassword);
			member.setUserPwd(encodedPassword);
			log.debug("changePass = {}", member);
			
			int result = memberService.insertMember(member);
		} catch (Exception e) {
			log.error("회원가입오류!", e);
			throw e;
		}
		log.trace("memberEnroll 끝!");
		return "redirect:/";
	}
	
	@GetMapping("/memberLogin.me")
	public String memberLogin() {
		return "member/memberLogin";
	}
	
	@PostMapping("/memberLogin.me")
	public String memberLogin(String userId, String userPwd, Model model, RedirectAttributes redirectAtt) {
		log.debug("userId = {}", userId);
		log.debug("userPwd = {}", userPwd);
		
		Member member = memberService.selectOneMember(userId);
		System.out.println("member = " + member);
		
		// 인증
		if(member != null && passwordEncoder.matches(userPwd, member.getUserPwd())) {
			model.addAttribute("loginMember", member);	// requestScope => sessionScope 바꾸기
		} else {
			redirectAtt.addFlashAttribute("msg", "아이디 또는 비밀번호가 맞지 않습니다.");
		}
		return "redirect:/";
	}
	
	// @SessionAttributes + medel 통해 로그인정보를 관리하는 경우
	/*
	 * SessionStatus객체를 통해 사용완료 처리해야 한다
	 * 	- session객체를 폐기하지 않고 재사용
	 */	
	@GetMapping("/memberLogout.me")
	public String memberLogout(SessionStatus status) {
		if(!status.isComplete())
			status.setComplete();
		log.debug("세션반환");
		return "redirect:/";
	}
	
	@GetMapping("/memberDetail.me")
	public void memberDetail() {
	}
	
	@PostMapping("/memberUpdate.me")
	public String memberUpdate(Member member, Model model, RedirectAttributes redirectAtt) {
		// pw암호화해서 member.userPwd에 넣기
		String rawPassword = member.getUserPwd();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		member.setUserPwd(encodedPassword);
		
		int result = memberService.updateMember(member);
		
		if(result > 0) {
			redirectAtt.addFlashAttribute("msg", "회원정보 수정 성공");
		} else {
			redirectAtt.addFlashAttribute("msg", "회원정보 수정 실패");
		}
		
		return "redirect:/member/memberInfo.me?userId="+member.getUserId();
	}

	@GetMapping("/memberInfo.me")
	public String memberInfo(String userId, Model model) {
		model.addAttribute("loginMember", memberService.selectOneMember(userId));
		return "redirect:/";
	}
	
	@GetMapping("/checkId.me")
	public String checkId(@RequestParam String userId, Model model) {
		// @RequestParam으로 memberEnroll의 ajax에서 userId 넘겨준거 받아옴
		Member member = memberService.selectOneMember(userId);
		boolean available = member == null;
		
		model.addAttribute("userId", userId);
		model.addAttribute("available", available);
		
		return "jsonView";
	}

}









