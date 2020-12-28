package com.kye.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	//http://localhost:8000/blog/temp/home	
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome 들어옴");
		//스프링부트의 파일리턴 기본경로는 src/main/resources/static이다
		//따라서 return "home.html"; 이라고 하면 src/main/resources/statichome.html이라서 못찾는다.
		//그래서 아래와 같이 /를 주면 경로명이 src/main/resources/static/home.html이라서 찾는다.
		//그리고 static에는 브라우저가 인식할 수 있는 html, img 등과 같은 정적인 자원만 인식한다.
		return "/home.html";
	}
	
	//스프링부트는 기본적으로 jsp를 지원하지 않는다. 그래서 pom.xml에 jsp 템플릿 엔진을 설정했다
	//그리고 yaml에 prefix: /WEB-INF/views/  suffix: .jsp를 설정해서 jsp파일을 별도로 찾게한다.
	// 풀패스 : /WEB-INF/views/test.jsp를 가지고 정상적으로 찾아서 동작함
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		System.out.println("tempJsp 들어옴");
		return "test";
	}
}
