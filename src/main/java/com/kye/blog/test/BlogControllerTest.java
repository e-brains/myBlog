package com.kye.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller : 사용자가 요청 -> 응답 (html 파일)
//@RestController : 사용자가 요청 -> 응답 (data)

@RestController
public class BlogControllerTest {
	
	private static final String TAG="BlogControllerTest";
	
	//get뒤에 ?를 붙여서 파라미터를 전달하고 아래와 같이 받는다
	@GetMapping("/http/lombok")
	public String get(Member m) {
		
		System.out.println(TAG + "getter getId() == "+m.getId());
		m.setId(5000);
		System.out.println(TAG + "setter setId() == "+m.getId());
//		Member m1 = new Member(1, "홍길동", "12345", "test@test.com");
		
		return "<h1> lombok테스트 완료 </h1>";
	}
}
