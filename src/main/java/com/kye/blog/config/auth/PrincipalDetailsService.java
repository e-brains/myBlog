package com.kye.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kye.blog.model.User;
import com.kye.blog.repository.UserRepository;

@Service //bean등록
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	// LoginForm에서 action="/loginProc" 되면
	// 스프링 필터 체인이 낚아채서 loadUserByUsername함수를 호출한다.
	// password 부분 자동처리 되고 우리는  username이 DB에 있는지만 확인해주면 됨
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
				});
		
		return new PrincipalDetail(principal) ;  // 이때 시큐리티 세션에 유저 정보가 저장됨
	}

}
