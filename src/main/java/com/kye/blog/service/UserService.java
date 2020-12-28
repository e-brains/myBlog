package com.kye.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kye.blog.model.User;
import com.kye.blog.repository.UserRepository;

@Service // IoC를 bean등록, 트랜잭션 관리 (commit 과 rollback 의 단위)
public class UserService {

	@Autowired // DI를 해서 사용가능하도록 해준다.
	private UserRepository userRepository;

	@Transactional
	public void join(User user) {
		userRepository.save(user);
	}

	@Transactional(readOnly = true) // select 할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 보장)
	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

}
