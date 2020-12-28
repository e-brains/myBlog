package com.kye.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.kye.blog.model.User;

// JpaRepository에 있는 DB접근 메소드(findAll, save 등)를 모두 사용할 수 있다.
// 자동으로 bean에 등록되기 때문에 @Repository를 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> { // User테이블을 사용하고 키(ID)의 타입은 integer라는 의미

	//JPA Naming 쿼리
	//SELECT * FROM user WHERE username = ? AND password = ? ; 자동생성
	User findByUsernameAndPassword(String username, String password); 
	
	//아래처럼 사용해도 됨
//	@Query(value = "SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery = true)
//	User login(String username, String password);
}
