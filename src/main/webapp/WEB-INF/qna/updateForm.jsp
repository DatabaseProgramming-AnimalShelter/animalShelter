<!-- 후기 수정 화면 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp"%>

<title>수정</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
	function reviewModify() {
		if (form.title.value == "") {
			alert("제목을 입력하십시오.");
			form.title.focus();
			return false;
		}
		if (form.content.value == "") {
			alert("설명을 입력하십시오.");
			form.content.focus();
			return false;
		}
		form.submit();
	}
</script>

<div class="container">
	<br>
	<h4>후기 수정</h4>
	<br>
	<!-- Update Form   -->
	<form name="form" method="POST" action="<c:url value='/qna/update' />">
		<input name="qna_id" type="hidden" value="${qna.qna_id}" />

		<div class="form-group row">
			<label for="qna_title" class="col-lg-2 col-form-label">제목</label>
			<div class="col-lg-10">
				<input type="text" name="qna_title" class="form-control"
					value="${qna.qna_title}">
			</div>
		</div>
		<div class="form-group row">
			<label for="qna_writer" class="col-lg-2 col-form-label">글쓴이</label>
			<div class="col-lg-10">
				<input type="text" name="qna_writer" class="form-control"
					value="${qna.qna_writer}">
			</div>
		</div>
		
		
		<div class="form-group row">
			<label for="name" class="col-lg-2 col-form-label">비밀번호</label>
			<div class="col-lg-10">
				<input type="password" name="qna_password" class="form-control"
					value="${qna.qna_password}">
			</div>
		</div>
		
		<div class="form-group row">
			<label for="content" class="col-lg-2 col-form-label">질문종류</label>
			<div class="col-lg-8">
				<select name="qna_type">
					<option value="suggest">제안</option>
					<option value="inquiry">조사</option>
				</select>
			</div>
		</div>
		<div class="form-group row">
			<label for="qna_content" class="col-lg-2 col-form-label">내용</label>
			<div class="col-lg-10">
				<textarea id="content" name="qna_content" rows="15" cols="50"
					placeholder="내용을 입력하시오." class="form-control">${qna.qna_content}</textarea>
			</div>
		</div>
		<br>
		<div class="form-group">
			<input type="button" class="btn btn-primary" value="수정"
				onClick="reviewModify()"> <a
				href="<c:url value='/qna/list' />" class="btn btn-link">QNA목록</a>
		</div>
	</form>
</div>

<%@ include file="/WEB-INF/home/mainFooter.jsp"%>