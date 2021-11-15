<!-- 후기 글 : detail 화면  - 조회 -->

<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>후기 글 - 조회</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
function communityRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
</script>
</head>
<body>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>

<div class="container">  
	<br>
	<h4>후기 글 조회</h4>
	<br>
	<table class="table table-sm table-striped">
    	<tbody> 
	  	  <tr>
			<th>제목</th>
			<td>${review.title}</td>
		  </tr>
		  <tr>
			<th>작성자</th>
			<td>${review.writer}</td>
		  </tr>
		  <tr>
			<th>날짜</th>
			<td>${review.creationDate}</td>
		  </tr>
		  <tr>
			<th>내용</th>
			<td>${review.content}</td>
		  </tr>
		  <tr>
			<th>사진</th>
			<td>${review.image}</td>
		  </tr>
		</tbody>
	</table>
	
	<br> 		     
	
    <a class="btn btn-primary" 
    	href="<c:url value='/review/update'>
	     		   <c:param name='commId' value='${review.post_id}'/>
			  </c:url>">수정</a>
			  
    <a class="btn btn-warning" 
   		href="<c:url value='/review/delete'>
				   <c:param name='commId' value='${review.post_id}'/>
			 </c:url>" onclick="return communityRemove();">삭제(미구현)</a>
			 
    <a class="btn btn-success" href="<c:url value='/review/list' />">후기 목록</a> 
    <br>	   
	    
	<!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<c:if test="${updateFailed}">
		<h6 class="text-danger"><c:out value="${exception.getMessage()}"/></h6>
    </c:if>  
</div>  
</body>
</html>
 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>