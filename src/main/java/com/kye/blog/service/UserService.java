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

	@Autowired //암호화
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

	//회원수정
	@Transactional
	public void userUpdate(User user) {
		
		//수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고 , 영속화된 user오브젝트를 수정
		//select를 해서 User오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서
		// 영속화된 오브젝트를 변경하면 서비스 종료 시 자동으로 DB에 update문을 날려준다.
		User requestUser = userRepository.findById(user.getId()).orElseThrow( () -> {
			return new IllegalArgumentException("회원찾기 실패 : 아이디를 찾을 수 없습니다.");
		}); 
				
		//패스워드 암호화
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
					
		//읽어 온 기존 데이터에 암호화된 패스워드와 클라이언트에서 변경된 email set
		requestUser.setPassword(encPassword);
		requestUser.setEmail(user.getEmail());
				
		// 해당 서비스 종료 시 트랜잭션이 종료되면서 더티체킹이 일어나서 자동 업데이트가 됨
		// 영속화된 객체의 내용이 변경된 것을 자동 감지하고 DB에 flush한다.
		//userRepository.save(requestUser);
		
	}
		
	
//	// security적용하면서 security login을 이용하기 땜에 전통방식의 로그인은 삭제


}
