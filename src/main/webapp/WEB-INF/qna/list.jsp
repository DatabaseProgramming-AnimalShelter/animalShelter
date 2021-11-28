<!-- 후기 글 목록 화면 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp"%>

<title>커뮤니티 관리 - 목록</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<div class="container">
	<br>
	<h4>QNA 목록</h4>
	<br>
	<table class="table table-bordered">
		<thead class="thead-inverse">
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>질문종류</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="qna" items="${qnaList}">
				<tr
					onclick='location.href="<c:url value='/qna/view'>
						      <c:param name='qna_id' value='${qna.qna_id}'/>
						   </c:url>"'>
					<th scope="row">${qna.qna_id}</th>
					<td>${qna.title}</td>
					<td>${qna.user_name}</td>
					<td>${qna.qna_type}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
</div>

<%@ include file="/WEB-INF/home/mainFooter.jsp"%>