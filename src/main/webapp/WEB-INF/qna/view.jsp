<!-- 문의 글  - detail 화면  - 조회 -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>문의 글 - 조회</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
	function commentCreate() {
		if (form.comment_content.value == "") {
			alert("댓글을 입력하십시오.");
			form.comment_content.focus();
			return false;
		}
		form.submit();
	}
	function commentRemove() {
		return confirm("댓글을 정말 삭제하시겠습니까?");
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
					<td>${qna.qna_title}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${qna.qna_writer}</td>
				</tr>
				<tr>
					<th>날짜</th>
					<td>${qna.qna_date}</td>
				</tr>
				<tr>
					<th>질문종류</th>
					<td>${qna.qna_type}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${qna.qna_content}</td>
				</tr>
			</tbody>
		</table>

		<br> <a class="btn btn-primary"
			href="<c:url value='/qna/update'>
	     		   <c:param name='qna_id' value='${qna.qna_id}'/>
			  </c:url>">수정</a>
		<a class="btn btn-primary"
			href="<c:url value='/qna/delete'>
	     		   	<c:param name='qna_id' value='${qna.qna_id}'/>
			  	  </c:url>">삭제</a>

		<!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
		<c:if test="${updateFailed}">
			<h6 class="text-danger">
				<c:out value="${exception.getMessage()}" />
			</h6>
		</c:if>
		<!-- 댓글 작성 부분 -->
		<br>
		<c:if test="${user_id == 'admin'}">
			<form name="form" method="POST"
				action="<c:url value='/qna/create_comment'></c:url>">
				<input name="qna_id" type="hidden" value="${qna.qna_id}" />
				<div class="form-group row">
					<label for="comment_content" class="col-lg-2 col-form-label">내용</label>
					<div class="col-lg-10">
						<textarea id="comment_content" name="comment_content" rows="4"
							cols="130" placeholder="댓글을 입력하세요." class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					<input type="button" class="btn btn-primary" onclick="commentCreate()" value="등록">
				</div>
			</form>
		</c:if>
	</div>
	<!-- 댓글 리스트 부분 -->
	<table class="table table-bordered">
		<thead class="thead-inverse">
			<tr>
				<td>No.</td>
				<td>작성자</td>
				<td>내용</td>
				<td>날짜</td>
				<td>삭제</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="comment" items="${comment_list}">
				<tr>
					<td width="70">${comment.comment_no}</td>
					<td width="70">${comment.comment_writer}</td>
					<td width="600">${comment.comment_content}</td>
					<td width="100">${comment.reg_date}</td>

					<!-- 댓글 작성자와 admin만 삭제 가능하도록 -->
					<td width="80"><a class="btn btn-warning"
						href="<c:url value='/qna/delete_comment'>
					   								<c:param name='comment_no' value='${comment.comment_no}'/>
				 								</c:url>"
						onclick="return commentRemove();"> 삭제</a> <c:otherwise>
							<p>삭제</p>
						</c:otherwise></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
</body>
</html>
<%@ include file="/WEB-INF/home/mainFooter.jsp"%>