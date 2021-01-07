package com.kye.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kye.blog.config.auth.PrincipalDetail;
import com.kye.blog.dto.ReplySaveRequestDto;
import com.kye.blog.dto.ResponseDto;
import com.kye.blog.model.Board;
import com.kye.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board") //글쓰기
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal ) {
		
		boardService.writeBoard(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/api/board/{id}") //수정
	public ResponseDto<Integer> updateById(@PathVariable int id, @RequestBody Board board) {

		boardService.updateById(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	/*
	 * //댓글쓰기 //@AuthenticationPrincipal PrincipalDetail에서 세션값을 읽을 수 있다.
	 * 
	 * @PostMapping("/api/board/{boardId}/reply") public ResponseDto<Integer>
	 * replySave(@PathVariable int boardId, @RequestBody Reply
	 * reply, @AuthenticationPrincipal PrincipalDetail principal ) {
	 * 
	 * boardService.writeReply(principal.getUser(), boardId, reply); return new
	 * ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 */

	//큰 프로젝트일 수록 데이터 받을때 컨트롤러에서 dto를 만들어서 받는게 좋다. 
	//dto를 이용하는 방법으로 수정 20210107 
	//1. ReplySaveRequestDto 클래스를 만든다.
	//2. detailForm.jsp에 user id를 히든으로 만든다.
	//3. board.js에서 data에 user id와 board id를 모두 넣어준다.
	//4. BoardApiController에 댓글달기 추가
	//5. BoardService에 댓글달기 추가
	@PostMapping("/api/board/{boardId}/reply") 
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto ) {
		
		boardService.writeReply(replySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	} 	
	
	
	
	
	@DeleteMapping("/api/board/{id}") //삭제
	public ResponseDto<Integer> deleteById(@PathVariable int id) {

		boardService.deleteById(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
		
}
