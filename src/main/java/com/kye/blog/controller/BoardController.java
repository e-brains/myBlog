package com.kye.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	//yml에 context-path가 /blog로 설정되어 있음 
	@GetMapping({"","/"}) //아무것도 안적었거나 / 일때 맵핑 http://localhost:8000/blog/ or http://localhost:8000/blog
	public String index() {
		//WEB-INF/views/index.jsp
		return "index";
	}
}
