package com.kye.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder // 빌더패턴 사용
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // user 클래스가 mySql에 테이블이 생성이 된다. //yml파일 의 jpa설정에서 최초에는 ddl-auto: create하고 이후에는 ddl-auto: update로 고쳐야 한다. 안그러면 계속 테이블을 새로 생성하기 때문이다
@DynamicInsert // insert할때 null 인 필드 제외
public class User {
	
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	//즉 , yml파일의 jpa설정에서 use-new-id-generator-mappings: false로 놓으면 jpa 전략을 따라가지 않고 DB의 전략을 따르게 된다.
	private int id; // 오라클에서는 시퀀스 , auto-increment
	
	@Column(nullable = false, length = 30, unique = true) //unique = true은 중복입력 방지 
	private String username;
	
	@Column(nullable = false, length = 100) // 비밀번호를 나중에 해쉬를 이용해서 암호화하기 위해 길이를 넉넉히 준다.
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("'user'") // 권한 중 초기값으로 user를 준다. (실제 insert시에 컬럼을 아예 명시하지 않아야 DB디폴트 값이 작동하여 user가 입력된다. 컬럼이 명시되어 있으면 null값이 들어가는데 이를 방지하기 위해 클래스에 @DynamicInsert 를 붙여  줌)  
//	private String role; //향후 스트링은 userf등 오타가 들어갈 가능성이 있으니 Enum(범위/도메인을 줄수 있음)을 써서 방지
	
	//DB는 RoleType이라는 것이 없기 때문에 아래 @Enumerated를 붙여서 해당 enum이 string이라는 것을 알려준다. 
    @Enumerated(EnumType.STRING) 
    private RoleType role;  // enum타입의 클래스를 만들어 ADMIN, USER로 범위를 강제 설정해놓고 사용하여 실수하지 않도록 함
	
	@CreationTimestamp // 시간이 자동 입력된다.
	private Timestamp createDate;
}
