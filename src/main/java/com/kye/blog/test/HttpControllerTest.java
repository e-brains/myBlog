package com.kye.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Controller : 사용자가 요청 -> 응답 (html 파일)
//@RestController : 사용자가 요청 -> 응답 (data)

@RestController
//@Controller
public class HttpControllerTest {
	
	//get뒤에 ?를 붙여서 파라미터를 전달하고 아래와 같이 받는다
	@GetMapping("/http/get")
	public String get(@RequestParam int id, @RequestParam String userName) {
		return "<h1> get 요청 </h1>" + id + userName;
	}
	
	//get뒤에 ?를 붙여서 파라미터를 전달받지 않고 한꺼번에 받는 방법 : 모델 Member를 만들어서 활용 
	@GetMapping("/http/getm")
	public String getm(Member m) {
		return "<h1> getm 요청 </h1>" + m.getId() + m.getUserName() + m.getPassword() + m.getEmail();
	}
	
	//post방식 테스트를 위해 form을 부르기 위한 메소드
	@GetMapping("/http/getp")
	public String getp() {
		return "createMemberForm";
	}
	
	
	//인터넷 브라우저 요청은 get만 허용됨 따라서 url에 post, put, delete를 사용할 수 없다.
	@PostMapping("/http/post")
	public String post() {
		return "<h1> post 요청 </h1>";
	}

	//text/plain 은 일반 텍스트로 @RequestBody로 받는다 
	@PostMapping("/http/posta")
	public String posa(@RequestBody String text ) {
		return "<h1> post 요청 </h1>" + text;
	}
	
	//application/json 은  @RequestBody로 받는다 
	@PostMapping("/http/postz")
	public String posz(@RequestBody Member m ) {
		return "<h1> post 요청 </h1>" + m.getId() + m.getUserName() + m.getPassword() + m.getEmail();
	}
		
	//post방식은 쿼리스트링을 사용할 수 없으니 body에 담아서 전달해야 한다.
	@PostMapping("/http/postm")
	public String postm(Member m) {
		return "<h1> post 요청 </h1>"+ m.getId() + m.getUserName() + m.getPassword() + m.getEmail();
	}

	
	//인터넷 브라우저 요청은 get만 허용됨 따라서 url에 post, put, delete를 사용할 수 없다.
	@PutMapping("/http/put")
	public String put() {
		return "<h1> put 요청 </h1>";
	}
	//그래서 post 방식과 동일하게 body에 담아 전달한다.
	@PutMapping("/http/puta")
	public String puta(@RequestBody Member m) {
		return "<h1> puta 요청 </h1>"+ m.getId() + m.getUserName() + m.getPassword() + m.getEmail();
	}
		
	//인터넷 브라우저 요청은 get만 허용됨 따라서 url에 post, put, delete를 사용할 수 없다.
	@DeleteMapping("/http/delete")
	public String delete() {
		return "<h1> delete 요청 </h1>";
	}
	//그래서 post 방식과 동일하게 body에 담아 전달한다.
	@PutMapping("/http/deletek")
	public String deletek(@RequestBody Member m) {
		return "<h1> deletek 요청 </h1>"+ m.getId() + m.getUserName() + m.getPassword() + m.getEmail();
	}
	
}
