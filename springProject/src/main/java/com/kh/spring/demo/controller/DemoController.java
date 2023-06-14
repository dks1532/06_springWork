package com.kh.spring.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.demo.model.vo.Dev;

/*
@Controller 클래스의 handler 메소드가 가질수 있는 매개변수 타입
HttpServletRequest
HttpServletResponse
HttpSession

java.util.Locale: 요청에 대한 Locale
InputStream/Reader: 요청에 대한 입력스트림
OutputStream/Writer : 응답에 대한 출력스트림. ServletOutputStream, PrintWriter

사용자입력값처리
Command객체 : http요청 파라미터를 커맨드객체에 저장한 VO객체
CommandMap : HandlerMethodArgumentResolver에 의해 처리된 사용자입력값을 가진 Map 객체 
@Valid : 커맨드 객체 유효성 검사객체
Error, BindingResult: Command객체에 저장결과(Command객체 바로 다음위치시킬것.)
@PathVariable : 요청url 중 일부를 매개변수로 취할 수 있다.
@RequestParam: 사용자입력값을 자바변수에 대입처리(필수여부 설정)
@RequestHeader : 헤더값
@CookieValue: 쿠키값
@RequestBody: http message body에 작성된 json을 vo객체로 변환처리

뷰에 전달할 모델 데이터 설정
ModelAndView
ModelMap
Model

@ModelAttribute : model 속성에 대한 getter
@SessionAttribute : session속성에 대한 getter (required여부 선택가능)
@SessionAttributes : session에서 관리될 속성명을 class-level에 작성
SessionStatus: @SessionAttributes로 등록된 속성에 대하여 사용완료 (complete)처리. 세션을 폐기하지 않고 재사용한다.

기타
MultipartFile: 업로드파일 처리 인터페이스. CommonsMultipartFile
RedirectAttributes : DML처리후 요청주소 변경을 위한 redirect 속성처리 지원
*/

// 사용자의 요청을 처리, 정해진 뷰에 객체를 넘겨주는 역할
@Controller
@RequestMapping("/demo")
public class DemoController {
	
	// 필요한 의존객체의 타입에 맞는 빈을 찾아 주입한다(자동 배선)(빈이나 서비스파일에 많이 사용, 인터페이스에는 annotation잘 안씀)
	@Autowired
	private DemoService demoService;
	
	// @RequestMapping(value="/demo/devForm.do", method=RequestMethod.GET)
	@RequestMapping("/devForm.do")  //   "/demo/devForm.do"
	public String devForm() {
		return "demo/devForm";  // "WEB-INF/views/demo/devForm.jsp"
	}
	
	@RequestMapping("/dev1.do")
	public String dev1(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		int career = Integer.parseInt(request.getParameter("career"));
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String[] lang = request.getParameterValues("lang");
		
		Dev dev = new Dev(0, name, career, email, gender, lang, LocalDateTime.now());
		System.out.println("dev =" + dev);
		
		request.setAttribute("dev", dev);
		return "demo/devResult";
	}
	
	
	/*
	 * handler메소드 : @Controller하위의 사용자 요청 처리 메소드
	 *  * 속성
	 *    - value : path속성에 대한 별칭, value속성명 유일하게 생략가능
	 *    - Method : 생략시 모든 전송방식에 대해 처리
	 *      		 특정 method등록시 해당 전송방식만 처리
	 */
	// @RequestMapping 은 클래스 단위로도 붙일 수 있다
	@RequestMapping("/dev2.do")
	public String dev2(@RequestParam String name,
			@RequestParam int career,
			@RequestParam String email,
			@RequestParam String gender,
			@RequestParam String[] lang,
			Model model) {
		
		// 1. 사용자 입력값 처리
		Dev dev = new Dev(0, name, career, email, gender, lang, LocalDateTime.now());
		System.out.println("dev =" + dev);
		
		// 2. jsp전달	
		model.addAttribute("dev", dev);		// request.setAttribute("dev", dev) 동일(스프링에서는 보통 request 사용안하고 model을 씀)
		return "demo/devResult";
	}

	@RequestMapping("/dev3.do")
	public String dev3(Dev dev) {	// 자기가 알아서 Dev 빈파일에 넣어줌(앞으로 이렇게 할 것)
		dev.setCreateDate(LocalDateTime.now());
		return "demo/devResult";
	}
	
	// @RequestMapping("/insertDev.do")
	//@RequestMapping(path="/insertDev.do", method=RequestMethod.POST)
	@PostMapping("/insertDev.do")
	public String insertDev(Dev dev, RedirectAttributes redirectAttr) {
		int result = demoService.insertDev(dev);
		/*
		 * redirectAttr.addAttribute : requestParameter로 값을 전달 값을 지속적을 사용할 때
		 * redirectAttr.addFlashAttribute : 일회성으로 Redirect후 값이 소멸
		 */
		redirectAttr.addFlashAttribute("msg", "정상적으로 저장했습니다.");
		return "redirect:/demo/devList.do";
	}
	
	@GetMapping("/devList.do")
	public String devList(Model model) {
		// controller -> service(Impl) -> Dao(Impl) -> demo-mapper.xml
		List<Dev> devList = demoService.selectDevList();
		System.out.println("devList = " + devList);
		
		model.addAttribute("devList", devList);
		return "demo/devList";
	}
	
	@GetMapping("/updateDev.do")
	public String updateDev(@RequestParam int no, Model model) {	// 모델에 담아서 넘겨줌
//		Dev dev = demoService.selectOneDev(no);
//		model.addAttribute("dev", dev);
		
		model.addAttribute("dev", demoService.selectOneDev(no));	// 위 두줄을 한줄로 줄임
		return "demo/devUpdateForm";
	}
	
	@PostMapping("/updateDev.do")
	public String updateDev(Dev dev, RedirectAttributes redirectAttr) {	// 위의 메소드와 이름은 같아도 매개변수가 다르게해서 사용할 수 있다.
		int result = demoService.updateDev(dev);
		redirectAttr.addFlashAttribute("msg", "정보 수정성공.");
		return "redirect:/demo/devList.do";
	}
}
