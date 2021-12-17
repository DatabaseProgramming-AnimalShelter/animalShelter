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
<style>
.post-comments {
	padding-bottom: 9px;
	margin: 5px 0 5px;
}

.post-comments .comment-meta {
	border-bottom: 1px solid #eee;
	margin-bottom: 5px;
}

.post-comments .media {
	border-left: 1px dotted #000;
	border-bottom: 1px dotted #000;
	margin-bottom: 5px;
	padding-left: 10px;
}

.post-comments .media-heading {
	font-size: 12px;
	color: grey;
}

.post-comments .comment-meta a {
	font-size: 12px;
	color: grey;
	font-weight: bolder;
	margin-right: 5px;
}
</style>
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

		<!-- 댓글 있으면 보여주기 -->
		<c:if test="${comment != null}">
			<section style="background-color: #eee;">
				<div class="container my-5 py-5">
					<div class="row d-flex justify-content-center">
						<div class="col-md-12 col-lg-10 col-xl-8">
							<h4 class="mb-0">Comments</h4>
							<div class="card">
								<div class="card-body">
									<div class="d-flex flex-start align-items-center">
										<div>
											<h6 class="fw-bold text-primary mb-1">관리자</h6>
											<p class="text-muted small mb-0">${comment.reg_date}</p>
										</div>
									</div>
									<p class="mt-3 mb-4 pb-2">${comment.comment_content}</p>
									<c:if test="${user_id == 'admin'}">
										<p class="small mb-0" style="color: #aaa;">
											<a
												href="<c:url value='/qna/delete_comment'>
					   								<c:param name='comment_no' value='${comment.comment_no}'/>
				 									<c:param name='qna_id' value='${comment.qna_id}'/>
				 								</c:url>"
												class="link-grey" onclick="return commentRemove();">Remove</a>
										</p>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</c:if>
		<!-- 댓글 작성 부분 -->
		<br> <br>
		<c:if test="${comment == null && user_id == 'admin' }">
			<form name="form" method="POST"
				action="<c:url value='/qna/create_comment'></c:url>">
				<input name="qna_id" type="hidden" value="${qna.qna_id}" />
				<div class="form-group row">
					<label for="comment_content" class="col-lg-2 col-form-label">내용</label>
					<div class="col-lg-10">
						<textarea name="comment_content" class="form-control" rows="3"
							placeholder="답변을 입력하세요."></textarea>
					</div>
				</div>
				<div class="form-group">
					<input type="button" class="btn btn-primary"
						onclick="commentCreate()" value="등록">
				</div>
			</form>
		</c:if>
	</div>
	<br>
</body>
</html>
<%@ include file="/WEB-INF/home/mainFooter.jsp"%>