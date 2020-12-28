package com.kye.blog.test;

import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.Getter;
import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;

//lombok 사용시 아래 어노테이션 사용 가능
//@Getter
//@Setter
@Data // @Getter 와 @Setter 둘다 생성
@AllArgsConstructor // 전체 인자 생성자 생성
@NoArgsConstructor // 빈 인자 생성자 생성
//@RequiredArgsConstructor // 멤버변수를 final로 만들고 final이 붙은 멤버변수로 생성자를 생성한다. 
public class Member {

	private int id;
	private String userName;
	private String password;
	private String email;
	
//	private final int id;
//	private final String userName;
//	private final String password;
//	private final String email;	
		
}
