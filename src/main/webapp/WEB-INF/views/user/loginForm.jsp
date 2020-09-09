<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<div class="container">
<form action="/action_page.php">
  <div class="form-group">
    <label for="username">username:</label>
    <input type="text" class="form-control" placeholder="username" id="username">
  </div>
  <div class="form-group">
    <label for="password">password:</label>
    <input type="password" class="form-control" placeholder="Enter password" id="password">
  </div>
</form>
  <button id="" class="btn btn-primary">로그인</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

