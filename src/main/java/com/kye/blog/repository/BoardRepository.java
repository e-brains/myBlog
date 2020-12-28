package com.kye.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kye.blog.model.Board;

// DAO
// JpaRepository에 있는 DB접근 메소드(findAll, save 등)를 모두 사용할 수 있다.
// 자동으로 bean에 등록되기 때문에 @Repository를 생략 가능하다.
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> { 

		
}
