<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<div class="container">
	<form>
	<input type="hidden" id="id"  value="${board.id}" />
  <div class="form-group">
    <input value="${board.title}" type="text" name ="title" class="form-control" placeholder="title" id="title">
  </div>
  <div class="form-group">
  <textarea class="form-control summernote"  rows="5"  id="content"  >${board.content}</textarea>
  </div>
</form>
  <button id="btn-update" class="btn btn-primary">수정완료</button>

</div>

 <script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 500
      });
    </script>
<script src="/js/board.js"></script>    
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

