<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<!-- 스프링 시큐리티가 가로채기 한다 . /auth/loginProc는 SecurityConfig에서 처리됨 -->
	<form  action="/auth/loginProc" method="post" >
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
		</div>

		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>

		<!-- security적용하면서 js이용하지 않고 form로그인 하기 위해 버튼을 form안으로 넣는다 -->
		<button id="btn-login" class="btn btn-primary">로그인</button>
	</form>



</div>

<!-- security적용하면서 js이용하지 않는다 
<script src="/js/user.js"></script>
-->
<%@ include file="../layout/footer.jsp"%>
