<!-- 후기 글  - detail 화면  - 조회 -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>후기 글 - 조회</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
	function communityRemove() {
		return confirm("정말 삭제하시겠습니까?");
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/home/mainHeader.jsp"%>

	<div class="container">
		<br>
		<h4>QNA detail</h4>
		<br>
		<table class="table table-sm table-striped">
			<tbody>
				<tr>
					<th>제목</th>
					<td>${qna.title}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${qna.user_name}</td>
				</tr>
				<tr>
					<th>질문종류</th>
					<td>${qna.qna_type}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${qna.content}</td>
				</tr>
			</tbody>
		</table>

		<br>
		<!-- 자신이 쓴글이면 삭제, 수정 가능하게 -->
		<c:if test="${user_id=='qna.user_id'}">
			<a class="btn btn-primary"
				href="<c:url value='/qna/update'>
	     		   <c:param name='qna_id' value='${qna.qna_id}'/>
			  </c:url>">수정</a>
			<a class="btn btn-primary"
				href="<c:url value='/qna/delete'>
	     		   	<c:param name='qna_id' value='${qna.qna_id}'/>
			  	  </c:url>">삭제</a>
		</c:if>

		<c:if test="${user_id=='admin'}">
			<a class="btn btn-primary"
				href="<c:url value='/qna/update'>
	     		   <c:param name='qna_id' value='${qna.qna_id}'/>
			  </c:url>">수정</a>
			<a class="btn btn-primary"
				href="<c:url value='/qna/delete'>
	     		   	<c:param name='qna_id' value='${qna.qna_id}'/>
			  	  </c:url>">삭제</a>
		</c:if>

		<!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
		<c:if test="${updateFailed}">
			<h6 class="text-danger">
				<c:out value="${exception.getMessage()}" />
			</h6>
		</c:if>
	</div>
</body>
</html>
<%@ include file="/WEB-INF/home/mainFooter.jsp"%>