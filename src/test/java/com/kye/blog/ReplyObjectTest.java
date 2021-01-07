package com.kye.blog;

import org.junit.jupiter.api.Test;

import com.kye.blog.model.Reply;

public class ReplyObjectTest {

	@Test
	public void 오브젝트출력테스트() {
		Reply reply = Reply.builder()
				.id(1)
				.user(null)
				.board(null)
				.content("안녕")
				.build();
		System.out.println(reply); // 오브젝트를 출력하면 자동으로 toString()호출해서 찍어준다.
	}
}
