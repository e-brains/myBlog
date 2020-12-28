package com.kye.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kye.blog.service.BoardService;

@Controller 
public class BoardController {
	
	@Autowired
	private BoardService boardService;
		
	//여기는 세션이 생성된 상태에서 수행
	//yml에 context-path가 /blog로 설정되어 있음
	//아무것도 안적었거나 ' /' 일때 맵핑 http://localhost:8000/blog/ or http://localhost:8000/blog
	@GetMapping({"","/"}) 
	//public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤에서 세션 찾고 싶을때 선언 방법
	public String index(Model model) { 
		model.addAttribute("boards",boardService.boardList()); // 메인페이지로 글목록을 가져가기 위해
		return "index"; // @Controller가 있으면 리턴 시 viewResolver가 작동하는데 model을 들고 이동 
	}
	
	//USER권한이 필요
	@GetMapping({"/board/saveForm"}) 
	public String saveForm() {
		return "board/saveForm";  //글쓰기 창으로 이동
	}
	
}
