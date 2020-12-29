<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<button class="btn btn-warning" id="btn-update">수정</button>
		<!--  문서의 사용자 id와 로그인 세션의 사용자 id가 같을때  삭제 버튼 허용 -->
		<c:if test="${board.user.id == principal.user.id}">
			<button class="btn btn-danger" id="btn-delete">삭제</button>
		</c:if>
		<br /><br />
		<div>
			글번호 : <span id="id"><i>${board.id} </i></span>&nbsp;&nbsp;&nbsp;&nbsp;
			작성자: <span><i>${board.user.username} </i></span>
		</div>
		<br />
		<div >
			<h3>${board.title}</h3>
		</div>
		<hr />
		<div>
			<div>${board.content}</div>
		</div>
		<hr />


</div>

<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>
