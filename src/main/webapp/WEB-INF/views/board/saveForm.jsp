<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<div class="container">
	<form>
  <div class="form-group">
    <label for="title">Title</label>
    <input type="text" name ="title" class="form-control" placeholder="title" id="title">
  </div>
  <div class="form-group">
    <label for="content">Content</label>
  <textarea class="form-control summernote"  rows="5" id="content" ></textarea>
  </div>
</form>
  <button id="btn-save" class="btn btn-primary">작성완료</button>

</div>

 <script>
      $('.summernote').summernote({
        placeholder: '내용을 작성해주세요.',
        tabsize: 2,
        height: 500
      });
    </script>
<script src="/js/board.js"></script>    
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

