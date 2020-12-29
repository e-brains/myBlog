package com.kye.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

		board.setCount(0); // 조회수는 처음 글을 쓰면 0 이다.
		board.setUser(user);
		boardRepository.save(board);
	} 
	//DB의 content컬럼의 보면 태그가 들어가 있는데 그건 summernote에서 넣는 것이니 정상임

	//글목록 (페이징 처리 추가)
	@Transactional(readOnly = true)
	public Page<Board> boardList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	//상세보기
	@Transactional(readOnly = true)
	public Board findById(int id) {
		return boardRepository.findById(id).orElseThrow( () -> {
			return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});
	}
	
	//삭제하기
	@Transactional
	public void deleteById(int id) {
		System.out.println("BoardService >> deleteById => id : " + id);
		boardRepository.deleteById(id);
	}
	
	
}
