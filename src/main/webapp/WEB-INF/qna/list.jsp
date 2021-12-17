<!-- 후기 글 목록 화면 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp"%>

<title>QNA 관리 - 목록</title>
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
				<th>id</th>
				<td>제목</td>
				<td>작성자</td>
				<td>질문종류</td>
				<td>date</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${QnaList}">
				<tr onclick='location.href="<c:url value='/qna/view'>
						      <c:param name='qna_id' value='${list.qna_id}'/>
						   </c:url>"'>
					<th scope="row">${list.qna_id}</th>
					<td>${list.qna_title}</td>
					<td>${list.qna_writer}</td>
					<td>${list.qna_type}</td>
					<td>${list.qna_date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
</div>

<%@ include file="/WEB-INF/home/mainFooter.jsp"%>