package com.kye.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kye.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행 
//principal (접근 주체) = 세션처럼 사용 = Spring Security Context 에 보관됨
public class PrincipalDetail implements UserDetails{

	private static final long serialVersionUID = 7645618956884452156L;

	//PrincipalDetail가 세션저장소에 저장될때 User객체도 같이 저장되도록 여기서 선언한다.
	// 컴포지션 (객체를 포함할때 extends를 사용하면 상속이라 하고 클래스 내 품고 있는 것은 컴포지션이라 한다.)
	private User user;  
	
	public PrincipalDetail(User user) {
		this.user = user;
	}
		
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴한다. (true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있지 않은지 리턴한다. (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호가 만료되지 않았는지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정이 활성화(사용가능)인지  리턴한다. (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	//계정이 갖고 있는 권한목록을 리턴한다 
	// 원래는 권한이 여러개 있을 수 있어서  루프를 돌아야 하는데 우리는 한개이기 때문에 한개만 리턴하도록 만듬
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collectors = new ArrayList<>(); 

//		//자바에서는 오브젝트는 넣어도 메서드를 파라메터로 넣을 수 없다 그래서 아래와 같은 복잡한 코딩이 됨
//		collectors.add(new GrantedAuthority() {
//			@Override
//			public String getAuthority() {
//				return "ROLE_" + user.getRole(); //ROLE_를 반드시 붙여줘야 함 (ROLE_USER)
//			}
//		});
		
		// 위 식에서 GrantedAuthority는 내부에 메서드가 하나 밖에 없기 때문에 
		// 무조건 해당 메서드 하나가 호출되기 때문에 그냥 람다식으로 함수만 표현하고 내부 로직만 구현해 줘도 된다.
		// preFix 로 ROLE_를 반드시 붙여야 한다.
		collectors.add( ()->{return "ROLE_" + user.getRole();} );
		return collectors;
	}
	
}
