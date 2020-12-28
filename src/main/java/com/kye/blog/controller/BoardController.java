package com.kye.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kye.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	//여기는 세션이 생성된 상태에서 수행
	//yml에 context-path가 /blog로 설정되어 있음
	//아무것도 안적었거나 ' /' 일때 맵핑 http://localhost:8000/blog/ or http://localhost:8000/blog
	@GetMapping({"","/"}) 
	public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤에서 세션 찾기
		//WEB-INF/views/index.jsp
		System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
		
		return "index";
	}
}
