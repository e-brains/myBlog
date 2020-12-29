package com.kye.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kye.blog.service.BoardService;

@Controller 
public class BoardController {
	
	@Autowired
	private BoardService boardService;
		
//	//여기는 세션이 생성된 상태에서 수행
//	//yml에 context-path가 /blog로 설정되어 있음
//	//아무것도 안적었거나 ' /' 일때 맵핑 http://localhost:8000/blog/ or http://localhost:8000/blog
//	@GetMapping({"","/"}) 
//	//public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤에서 세션 찾고 싶을때 선언 방법
//	public String index(Model model) { 
//		model.addAttribute("boards",boardService.boardList()); // 메인페이지로 글목록을 가져가기 위해
//		return "index"; // @Controller가 있으면 리턴 시 viewResolver가 작동하는데 model을 들고 이동 
//	}
	
	//메인화면 호출 (목로조회 및 페이징 처리 추가)
	@GetMapping({"","/"}) 
	public String index(Model model, @PageableDefault(size = 3, sort = "id") Pageable pageable) { 
		model.addAttribute("boards",boardService.boardList(pageable)); // 메인페이지로 글목록을 가져가기 위해
		return "index"; // @Controller가 있으면 리턴 시 viewResolver가 작동하는데 model을 들고 이동
		
	}
		
	//상세보기
	@GetMapping("/board/{id}")
	public String detailForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.findById(id));
		return "board/detailForm";
	}
	
	//수정하기 위한 상세 조회 및 수정용 화면 호출
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.findById(id));
		return "board/updateForm";
	}
	
	
	
	//글 쓰기 USER권한이 필요
	@GetMapping({"/board/saveForm"}) 
	public String saveForm() {
		return "board/saveForm";  //글쓰기 창으로 이동
	}
	
}
