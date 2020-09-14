<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden"  id="id" value="${principal.user.id}"/>
		<div class="form-group">
			<label for="username">username</label> <input type="text" value="${principal.user.username}" class="form-control" placeholder="username" id="username" readonly="readonly">
		</div>

		<c:choose>
			<c:when test="${not empty principal.user.oauth}">
		<div class="form-group">
			<label for="password">password</label> <input type="password" class="form-control" placeholder="Enter password" id="password" readonly>
		</div>
		<div class="form-group">
			<label for="email">Email address</label> <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email" readonly>
		</div>
			</c:when>
		<c:otherwise>
		<div class="form-group">
			<label for="password">password</label> <input type="password" class="form-control" placeholder="Enter password" id="password" >
		</div>
		<div class="form-group">
			<label for="email">Email address</label> <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email" >
		</div>
		</c:otherwise>
		</c:choose>
		
	</form>
	<c:if test="${empty principal.user.oauth}">
	<button id="btn-update" class="btn btn-primary">회원수정완료</button>
	</c:if>
</div>
<script src="/js/user.js"></script>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>

