<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container">

	<form > <!-- action="/api/user/join" method="post" 옛날 방식은 사용하지 않기로 함 -->
		<input type="hidden" id="id" value="${principal.user.id}"/>
		<div class="form-group">
			<label for="username">User Name:</label> 
			<input type="text" value="${principal.user.username}" class="form-control" id="username" readonly="readonly">
		</div>

		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" id="password">
		</div>

		<div class="form-group">
			<label for="email">Email address:</label> 
			<input type="email" value="${principal.user.email}" class="form-control" id="email">
		</div>

	</form>
	<button id="btn-update" class="btn btn-primary">회원수정</button> <!-- form 태그 밖으로 빼야 submit를 막을 수 있다. -->

</div>

<script src="/js/user.js"></script> 

<%@ include file="../layout/footer.jsp"%>
