<!-- 후기 글 목록 화면 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<div class="container">
	<br>
	<h4>내가 쓴 댓글 목록</h4>
	<br>
	<table class="table table-bordered">
      <thead class="thead-inverse">
		<tr>
		  <td>Post id</td>
		  <td>제목</td>
		  <td>글 작성자</td>
		  <td>댓글 작성 날짜</td>
		</tr>
      </thead>
      <tbody>  	 
		<c:forEach var="review" items="${reviewCommnetList}">
			<tr>
			  <td>${review.post_id}</td>
			  <td><a href="<c:url value='/review/view'>
						      <c:param name='post_id' value='${review.post_id}'/>
						   </c:url>">
				  ${review.title}</a>
			  </td>
			  <td>${review.writer}</td>
			  <td>${review.comment_creationDate}</td>
			</tr>
		</c:forEach>
	  </tbody>
	</table>		 
	<br>   
</div>

 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>