package com.kye.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kye.blog.model.Board;
import com.kye.blog.model.User;
import com.kye.blog.repository.BoardRepository;

@Service // IoC를 bean등록, 트랜잭션 관리 (commit 과 rollback 의 단위)
public class BoardService {

	@Autowired 
	private BoardRepository boardRepository;

	//글쓰기
	@Transactional
	public void writeBoard(Board board , User user) { //title, content
		System.out.println("BoardService>> writeBoard >> user 정보 : " + user.getId());
		board.setCount(0); // 조회수는 처음 글을 쓰면 0 이다.
		board.setUser(user);
		boardRepository.save(board);
	} 
	
	public List<Board> boardList(){
		return boardRepository.findAll();
	}
	
	
	//DB의 content컬럼의 보면 태그가 들어가 있는데 그건 summernote에서 넣는 것이니 정상임
	
}
