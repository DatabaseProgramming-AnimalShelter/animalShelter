<!-- 후기 작성 폼 화면 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp"%>

<title>리뷰 작성 폼</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
	function Create() {
		if (form.title.value == "") {
			alert("제목을 입력하십시오.");
			form.title.focus();
			return false;
		}
		if (form.content.value == "") {
			alert("내용을 입력하십시오.");
			form.content.focus();
			return false;
		}
		form.submit();
	}
</script>

<div class="container">
	<br>
	<h4>문의 글 작성</h4>
	<br>
	<!-- 리뷰 등록 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<div class="row col-lg-12">
		<c:if test="${creationFailed}">
			<h6 class="text-danger">
				<c:out value="${exception.getMessage()}" />
			</h6>
		</c:if>
	</div>
	<!-- registration form  -->
	<form name="form" method="POST"
		action="<c:url value='/qna/create'></c:url>">

		<div class="form-group row">
			<label for="title" class="col-lg-2 col-form-label">제목</label>
			<div class="col-lg-10">
				<input type="text" name="title" class="form-control"
					placeholder="제목">
			</div>
		</div>
		<div class="form-group row">
			<label for="title" class="col-lg-2 col-form-label">글쓴이</label>
			<div class="col-lg-10">${user_name}</div>
		</div>
		<div class="form-group row">
			<label for="qna_type" class="col-lg-3 col-form-label">질문종류</label>
			<div class="col-lg-8">
				<select name="qna_type">
					<option value="suggest">제안</option>
					<option value="inquiry">조사</option>
				</select>
			</div>
		</div>
		<div class="form-group row">
			<label for="content" class="col-lg-2 col-form-label">내용</label>
			<div class="col-lg-10">
				<textarea id="body" name="content" rows="15" cols="50"
					placeholder="내용을 입력하시오." class="form-control"></textarea>
			</div>
		</div>
		<!-- password?? 
		<div class="form-group row">
			<label for="password" class="col-lg-2 col-form-label">비밀번호</label>
			<div class="col-lg-10">
				<input type="text" name="password" class="form-control"
					placeholder="비밀번호를 입력하세요">
			</div>
		</div>
		-->
		
		<br>
		<div class="form-group">
			<input type="button" class="btn btn-primary" value="질문 보내기"
				onClick="Create()">
		</div>
	</form>
</div>
</body>
</html>
<%@ include file="/WEB-INF/home/mainFooter.jsp"%>