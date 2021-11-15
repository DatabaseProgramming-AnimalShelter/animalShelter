<!-- 후기 글 목록 화면 -->
 
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<html lang="ko-kr">
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>커뮤니티 관리 - 목록</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<h2>후기 글 목록 화면 </h2>
	<br>

	<a href="<c:url value="/review/form" class=""></c:url>" class= ""> 후기 작성하기</a>
	<br>

<div class="container">
	<br>
	<h4>후기 목록</h4>
	<br>
	<table class="table table-bordered">
      <thead class="thead-inverse">
		<tr>
		  <td>제목</td>
		  <td>작성자</td>
		  <td>날짜</td>
		</tr>
      </thead>
      <tbody>  	 
		<c:forEach var="review" items="${findReviewList}">
			<tr>
			  <td><a href="<c:url value='/review/view'>
						      <c:param name='post_id' value='${review.post_id}'/>
						   </c:url>">
				  ${review.title}</a>
			  </td>
			  <td>${review.writer}</td>
			  <td>${review.creationDate}</td>
			</tr>
		</c:forEach>
	  </tbody>
	</table>		 
	<br>   
</div>


</body>
</html>
 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>