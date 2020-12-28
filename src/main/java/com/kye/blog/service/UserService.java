package com.kye.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kye.blog.model.RoleType;
import com.kye.blog.model.User;
import com.kye.blog.repository.UserRepository;

@Service // IoC를 bean등록, 트랜잭션 관리 (commit 과 rollback 의 단위)
public class UserService {

	@Autowired // DI를 해서 사용가능하도록 해준다.
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	//회원가입
	@Transactional
	public void join(User user) {
		String rawPassword = user.getPassword(); //해쉬전 원문
		String encPassword = encoder.encode(rawPassword); //해쉬됨
		user.setPassword(encPassword);
		
		//default값 정의 
		//다른 항목은 받아오거나 자동생성되지만 이녀석은 자동생성이 안되니 여기서 수동 생성
		user.setRole(RoleType.USER);

		userRepository.save(user);
	}

//	// security적용하면서 security login을 이용하기 땜에 전통방식의 로그인은 삭제


}
