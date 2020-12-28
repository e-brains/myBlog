package com.kye.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kye.blog.model.User;

// DAO
// JpaRepository에 있는 DB접근 메소드(findAll, save 등)를 모두 사용할 수 있다.
// 자동으로 bean에 등록되기 때문에 @Repository를 생략 가능하다.
@Repository
public interface UserRepository extends JpaRepository<User, Integer> { // User테이블을 사용하고 키(ID)의 타입은 integer라는 의미

//	JPA Naming 쿼리
//	SELECT * FROM user WHERE username = ?1 AND password = ?2;
//	User findByUsernameAndPassword(String username, String password);
//	
//	@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);
		
	
	//SELECT * FROM user WHERE username = ?1; 가 실행됨
	Optional<User> findByUsername(String username);
	
//	// security적용하면서 security login을 이용하기 땜에 전통방식의 로그인은 삭제

}
