package com.kye.blog.controller.api;

//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kye.blog.dto.ResponseDto;

import com.kye.blog.model.User;
import com.kye.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired // DI를 해서 사용가능하도록 해준다.
	private UserService userService;

	//회원가입
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {

		userService.join(user);

		// 자바오브젝트를 json으로 스프링이 자동으로 변환해서 리턴
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	//  회원수정
	//json 데이터를 받으려면 @RequestBody를 사용함
	@PutMapping("/api/user/update")
	public ResponseDto<Integer> userUpdate(@RequestBody User user) {

		userService.userUpdate(user);

		// 여기서는 트랜잭션이 종료되면서 DB의 값이 변경됨
		// 그러나 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 변경해야 됨
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
// security적용하면서 security login을 이용하기 땜에 전통방식의 로그인은 삭제


}
