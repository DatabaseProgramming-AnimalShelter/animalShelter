<!-- 후기 글  - detail 화면  - 조회 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>후기 글 - 조회</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
function reviewRemove() {
	return confirm("후기를 정말 삭제하시겠습니까?");		
}
function commentCreate() {
	var userId = '${user_id}';
	
	if (userId == ""|| userId == null) {
		alert("로그인 후 사용가능합니다.");
		return false;
	}
	if (form.content.value == "") {
		alert("댓글을 입력하십시오.");
		form.content.focus();
		return false;
	} 
	form.submit();
}
function commentRemove() {
	return confirm("댓글을 정말 삭제하시겠습니까?");		
}
function commentReply() {
	var userId = '${user_id}';
	
	if (userId == ""|| userId == null) {
		alert("로그인 후 사용가능합니다.");
		return false;
	}
	
	var content = promtp("값을 입력하세요.");
	
	
}
</script>

<body>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>

<div class="container">  
	<br>
	<h5>후기 글 조회</h5>
	<br>
	<table class="table table-sm table-striped">
    	<tbody> 
		  <tr>
			<th width="100">동물번호</th>
			<td>${review.animal_id}</td>
		  </tr>
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
			<td>
				<c:choose>
					<c:when test="${not empty review.image}">
						<img
							src="${pageContext.request.session.servletContext.contextPath}/upload/${review.image}"  class="" height="300px"/>
					</c:when>
					<c:otherwise>
						<span>사진없음</span>
					</c:otherwise>
				</c:choose>
			</td>
		  </tr>
		</tbody>
	</table>
	
	<br> 		     
	<c:if test="${review.writer == user_id || user_id == 'admin'}">
		<a class="btn btn-primary" 
    	href="<c:url value='/review/update'>
	     		   <c:param name='post_id' value='${review.post_id}'/>
			  </c:url>">수정</a>
	    <a class="btn btn-warning" 
	   		href="<c:url value='/review/delete'>
					   <c:param name='post_id' value='${review.post_id}'/>
				 </c:url>" onclick="return reviewRemove();">삭제</a> 
		<!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
		<c:if test="${updateFailed}">
			<h6 class="text-danger"><c:out value="${exception.getMessage()}"/></h6>
	    </c:if>  
	</c:if>
		<a class="btn btn-success" href="<c:url value='/review/list' />">후기 목록</a> 
	    <br>	
	    
	<!-- 댓글 작성 부분 -->
	<br>
	<form name="form" method="POST" action="<c:url value='/review/comment'>
	     		   								<c:param name='post_id' value='${review.post_id}'/>
			  	  							</c:url>"  >
	<table class="table table-bordered">
      <thead class="thead-inverse">
		<tr> 
		  <td colspan="2">당신의 아이디: ${user_id}</td> 
		</tr>
      </thead>
      <tbody>
	  	
		<tr>
		  <td width="1000">
		  		<textarea id="content" name="content" rows="4" cols="130" placeholder="댓글을 입력하세요." class="form-control"></textarea>
		  </td>
		  <td>
		  		<input type="button" class="btn btn-primary" onclick="commentCreate()" value="댓글등록">
		  </td>	
		</tr>	
	  </tbody>
	</table>	 
	</form>
	
	<br>  
	
	
	
	
	<!-- 댓글 리스트 부분 -->
	<br>
	<table class="table table-bordered">
      <thead class="thead-inverse">
		<tr>
		  <td>댓글 id</td>
		  <td>작성자</td>
		  <td>내용</td>
		  <td>날짜</td>
		  <td>삭제</td>
		</tr>
      </thead>
      <tbody>  	 
		<c:forEach var="comment" items="${reviewCommentList}">
			<tr>
			  <td width="70">${comment.comment_id}</td>
			  <td width="70">${comment.user_id}</td>
			  <td width="600">${comment.content}</td>
			  <td width="100">${comment.creationDate}</td>
		  	  
			  <!-- 댓글 작성자와 admin만 삭제 가능하도록 -->
			  <td width="80">
			  <c:choose>
			  	<c:when test="${comment.user_id == user_id || user_id == 'admin'}">
			  	<a class="btn btn-warning" href="<c:url value='/review_comment/delete'>
					   								<c:param name='comment_id' value='${comment.comment_id}'/>
					   								<c:param name='post_id' value='${review.post_id}'/>
				 								</c:url>" onclick="return commentRemove();">
				 삭제</a> 
			   	</c:when>
			   	<c:otherwise>
			   		<p>삭제</p>
			   	</c:otherwise>
			   	</c:choose>
			   </td>
			</tr>
		</c:forEach>
	  </tbody>
	</table>		 
	<br>   
    
</div>  
</body>
</html>
 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>