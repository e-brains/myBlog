package com.kye.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder // 빌더패턴 사용
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne // 연관관계 설정, Reply = Many  Board = One , 여러개의 답변은 하나의 게시글이 있을 수 있다. 
	@JoinColumn(name = "boardId") // 답글이 달릴 메인 게시글과 연관 관계를 맺어줄 컬럼 지정
	private Board board;
	
	@ManyToOne // 연관관계 설정, Reply = Many  User = One , 여러개의 답변은 하나의 유저가 작성할 수 있다.
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp // 시간이 자동 입력된다.
	private Timestamp createDate;  //java.sql의 Timestamp사용
}
