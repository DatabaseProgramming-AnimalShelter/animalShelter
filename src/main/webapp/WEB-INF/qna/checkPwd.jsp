<!-- 문의 글  - pwd checking 화면  - 조회 -->
<%@ include file="/WEB-INF/home/mainHeader.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>문의 글 - 비밀번호 확인</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
	function Create() {
		if (form.check_qna_password.value == "") {
			alert("비밀번호를 입력하십시오.");
			form.check_qna_password.focus();
			return false;
		}
		form.submit();
	}
</script>

<div class="container">
	<br>
	<h4>비밀번호 확인</h4>
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
		action="<c:url value='/qna/view'><c:param name='qna_id' value='${qna.qna_id}'/></c:url>">

		<div class="form-group row">
			<label for="qna_title" class="col-lg-2 col-form-label">제목</label>
			<div class="col-lg-10">
				<input type="text" name="qna_title" class="form-control" readOnly placeholder="${qna.qna_title}">
			</div>
		</div>
		<div class="form-group row">
			<label for="qna_password" class="col-lg-2 col-form-label">비밀번호</label>
			<div class="col-lg-10">
				<input type="password"  name="check_qna_password" class="form-control" id="check_qna_password"
					placeholder="비밀번호를 입력하세요">
			</div>
		</div>
		<br>
		<div class="form-group">
			<input type="button" class="btn btn-primary" value="확인" onClick="Create()">
		</div>
	</form>
</div>
</body>
</html>
<%@ include file="/WEB-INF/home/mainFooter.jsp"%>