package com.kye.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kye.blog.dto.ResponseDto;
import com.kye.blog.model.RoleType;
import com.kye.blog.model.User;
import com.kye.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired // DI를 해서 사용가능하도록 해준다.
	private UserService userService;

//	@Autowired
//	private HttpSession session;

	@PostMapping("/api/user/join")
	public ResponseDto<Integer> save(@RequestBody User user) {
//		System.out.println("UserApiController : save() 호출됨");
//		System.out.println("username : " + user.getUsername());
//		System.out.println("password : " + user.getPassword());
//		System.out.println("email : " + user.getEmail());

		// 실제로 DB에 insert를 하고 아래에서 return 되면 됨
		user.setRole(RoleType.USER); // default값 정의 (다른 항목은 받아오거나 자동생성)
		userService.join(user);
		// 자바오브젝트를 json으로 스프링이 자동으로 변환해서 리턴
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	// HttpSession는 파라미터로 받아도 되고 @Autowired 선언 후 사용해도 됨
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
		System.out.println("UserApiController : save() 호출됨");
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());

		User principal = userService.login(user); 
		if (principal != null) {
			session.setAttribute("principal", principal);  //principal은 접근 주체라는 뜻
		} // 자바오브젝트를 json으로 스프링이 자동으로 변환해서 리턴
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

}
