<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<!--  문서의 사용자 id와 로그인 세션의 사용자 id가 같을때  수정/삭제 버튼 허용 -->
	<c:if test="${board.user.id == principal.user.id}">
		<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
		<button class="btn btn-danger" id="btn-delete">삭제</button>
	</c:if>
	<br />
	<br />
	<div>
		글번호 : <span id="id"><i>${board.id} </i></span>&nbsp;&nbsp;&nbsp;&nbsp; 작성자: <span><i>${board.user.username} </i></span>
	</div>
	<br />
	<div>
		<h3>${board.title}</h3>
	</div>
	<hr />
	<div>
		<div>${board.content}</div>
	</div>
	<hr />
	<br />
	
<!-- 댓글 입력 bootstrap  BS4 Cards  -->
	<div class="card">
		<div class="card-body">
			<h6 class="card-title">댓글달기</h6>
			<textarea class="form-control" rows="1" cols=""></textarea>
		</div>
		<div class="card-footer">
			<button class="btn btn-primary">등록</button>
		</div>
	</div>

	<!-- 댓글 리스트  BS4 List Groups -->
	<br />
	<div class="card">
		<div class="card-body">댓글 리스트	</div>
		<ul id="comment--box" class="list-group">
		  <li id="comment--1" class="list-group-item d-flex justify-content-between">
		  		<div>댓글내용</div>
		  		<div class="d-flex">
			  		<div class="font-italic">작성자 : 홍길동&nbsp;</div>
			  		<button class="badge">삭제</button>
		  		</div>
		  </li>
		</ul>
	</div>


</div>

<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>
