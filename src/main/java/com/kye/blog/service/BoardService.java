package com.kye.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kye.blog.dto.ReplySaveRequestDto;
import com.kye.blog.model.Board;
import com.kye.blog.model.Reply;
import com.kye.blog.model.User;
import com.kye.blog.repository.BoardRepository;
import com.kye.blog.repository.ReplyRepository;
import com.kye.blog.repository.UserRepository;

@Service // IoC를 bean등록, 트랜잭션 관리 (commit 과 rollback 의 단위)
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private UserRepository userRepository;

	// 글쓰기
	@Transactional
	public void writeBoard(Board board, User user) { // title, content

		board.setCount(0); // 조회수는 처음 글을 쓰면 0 이다.
		board.setUser(user);
		boardRepository.save(board);
	}
	// DB의 content컬럼의 보면 태그가 들어가 있는데 그건 summernote에서 넣는 것이니 정상임

	// 글목록 (페이징 처리 추가)
	@Transactional(readOnly = true)
	public Page<Board> boardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	// 상세보기
	@Transactional(readOnly = true)
	public Board lookupDetail(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});
	}

	// 수정하기
	@Transactional
	public void updateById(int id, Board requestBoard) {

		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
		}); // 영속화 시키는 작업을 먼저 완료한다.

		// 기존 board의 데이터를 읽어 와서 타이틀과 컨테츨를 변경
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());

		// 해당 함수 종료 시 (서비스가 종료될 때) 트랜잭션이 종료되면서 더티체킹이 일어나서 자동 업데이트가 됨
		// 내용이 변경된 것을 자동 감지하고 DB에 flush한다.
	}

	/*
	 * //댓글 쓰기 (Dto 적용 전)
	 * 
	 * @Transactional public void writeReply(User user, int boardId, Reply
	 * requestReply) {
	 * 
	 * Board board = boardRepository.findById(boardId).orElseThrow( () -> { return
	 * new IllegalArgumentException("댓글 쓰기 실패 : 아이디를 찾을 수 없습니다."); }); // 영속화 시키는
	 * 작업을 먼저 완료한다.
	 * 
	 * requestReply.setUser(user); requestReply.setBoard(board);
	 * replyRepository.save(requestReply); }
	 */

	/*
	 * // 댓글 쓰기 (Dto 적용 후)
	 * 
	 * @Transactional public void writeReply(ReplySaveRequestDto
	 * replySaveRequestDto) {
	 * 
	 * // user 정보 영속화 User user =
	 * userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> {
	 * return new
	 * IllegalArgumentException("댓글 쓰기 user 정보 영속화 실패 : user 아이디를 찾을 수 없습니다."); });
	 * 
	 * // board 정보 영속화 Board board =
	 * boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(() ->
	 * { return new
	 * IllegalArgumentException("댓글 쓰기 board 정보 영속화 실패 : board 아이디를 찾을 수 없습니다.");
	 * });
	 * 
	 * Reply reply =
	 * Reply.builder().user(user).board(board).content(replySaveRequestDto.
	 * getContent()).build();
	 * 
	 * replyRepository.save(reply); }
	 */
	
	// 댓글 쓰기 (네이티브 쿼리 적용)
	// ReplyRepository에 내가 직접 mySave()메소드를 만든다.
	// ReplyRepository의 mySave()메소드를 이용하면 자동으로 영속화 된다.
	@Transactional
	public void writeReply(ReplySaveRequestDto replySaveRequestDto) {
		replyRepository.mySave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
	}
	
	
	// 삭제하기
	@Transactional
	public void deleteById(int id) {

		boardRepository.deleteById(id);
	}

}
