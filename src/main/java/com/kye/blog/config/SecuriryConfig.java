package com.kye.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kye.blog.config.auth.PrincipalDetailsService;

//아래 3개의 어노테이션은 시큐리티 적용 시 세트이다.
@Configuration // 빈 등록 (객체 생성) (IoC관리)
@EnableWebSecurity // SecuriryConfig의 설정을 필터 체인에 등록 ( controller보다 앞서 실행됨)  
@EnableGlobalMethodSecurity(prePostEnabled=true) // 특정 주소 접근 시 권한 및 인증을 pre(미리) 체크하겠다.
public class SecuriryConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailsService principalDetailsService;
		
	//고정길이의 스트링 (해쉬값)으로 변환해 준다.
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인할 때 어떤 암호화로 인코딩해서 비번을 비교할지 알려줘야 함.
	//principalDetailsService는 기존 해당 사용자 정보를 DB로 부터 찾아서 시큐리티 세션에 넣어준다. 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailsService).passwordEncoder(encodePWD());
	}
	
	//필터링
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()   //스프링 시큐리티가 csrf토큰을 체크하여 없으면 막아 버리기 때문에 테스트 하는 동안 비활성화 한다
			.authorizeRequests()
			.antMatchers("/", "/auth/**", "/css/**", "/images/**", "/js/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
		    .formLogin()
		    .loginPage("/auth/loginForm") //인증받지 못한 모든 접근은 로그인 창으로 보낸다. (UserController의 login()호출)
		    .loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
		    .defaultSuccessUrl("/"); //성공 시에는 루트로 이동
		   // .failureUrl("/fail"); // 실패 시에는 실패 창으로 이동
		
		//로그인을 가로채서 시큐리티 저장소를 만들어야 하는데 이때 저장 타입이 UserDetails의 타입이어야 한다.
		//이를 위해서 PrincipalDetail을 만든다.
		
	}	

	

}
