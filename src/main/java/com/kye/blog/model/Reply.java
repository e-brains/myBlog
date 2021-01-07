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
import org.springframework.util.Assert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 200)
	private String content;

	@ManyToOne // 연관관계 설정, Reply = Many Board = One , 여러개의 답변은 하나의 게시글이 있을 수 있다.
	@JoinColumn(name = "boardId") // 답글이 달릴 메인 게시글과 연관 관계를 맺어줄 컬럼 지정
	private Board board;

	@ManyToOne // 연관관계 설정, Reply = Many User = One , 여러개의 답변은 하나의 유저가 작성할 수 있다.
	@JoinColumn(name = "userId")
	private User user;

	@CreationTimestamp // 시간이 자동 입력된다.
	private Timestamp createDate; // java.sql의 Timestamp사용

	// 안전한 객체 생성 패턴
	@Builder
	public Reply(String content, Board board, User user) {
		// Spring Assert는 인수를 검증하고 조건에 맞지 않는 경우 IllegalArgumentException
		// 또는 IllegalStateException를 발생시킵니다.
		Assert.hasText(content, "content must not be empty");
		Assert.notNull(user, "board must not be empty");
		Assert.notNull(board, "user must not be empty");

		this.content = content;
		this.board = board;
		this.user = user;
	}

}
