<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>입양신청서</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/community.css' />"
	type="text/css">
<script>
function create() {
	<!-- 
	if (form.name.value == "") {
		alert("이름을 입력하십시오.");
		form.name.focus();
		return false;
	} 
	if (form.desc.value == "") {
		alert("설명을 입력하십시오.");
		form.desc.focus();
		return false;
	}		
	-->

	form.submit();
}

function animalList((targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body>
	<!-- registration form  -->
	
	<form class="was-validated" name="form" method="POST"
		action="<c:url value='/adopt/createForm' />">

		<div class="form-group row">
			<label for="userName" class="col-sm-2 col-form-label">이름</label>
			<div class="col-sm-10">
				<input type="text" readonly class="form-control-plaintext"
					id="userName" value="${apply.userName}">
				<!-- apply객체 안의 userName? -->
			</div>
		</div>
		<div class="form-group row">
			<label for="address" class="col-sm-2 col-form-label">사는지역</label>
			<div class="col-sm-10">
				<input type="text" readonly class="form-control-plaintext"
					id="address" value="${apply.address}">
			</div>
		</div>
		<div class="mb-3">
			<label for="select_animal" class="form-label">신청 반려동물</label> <select
				id="select_animal" class="form-select" required
				aria-label="select example">
				<option value="">신청하고 싶은 반려동물을 선택하세요</option>
				<c:forEach var="animal" items="${apply.animalList}">
					<option value="${animal.animal_id}">${animal.animal_name}</option>
				</c:forEach>
				<!-- 옵션에 동물들 id (matched안된) 동물들나오도록 -->
			</select>
			<div class="invalid-feedback">반려동물 선택은 필수입니다</div>
		</div>

		<div class="form-group row">
			<label for="have_pets" class="col-sm-2 col-form-label">반려동물 유무</label>
			<div class="col-sm-10">
				<input type="text" readonly class="form-control-plaintext"
					id="have_pets" placeholder="예) 고양이(러시안블루) - 6년 "> value="${apply.have_pets}">
				<c:if test="${creationFailed}">value="${apply.living_environment}"</c:if>
			</div>
			<div class="invalid-feedback">반려동물 유무 기재는 필수입니다</div>
		</div>

		<label for="validationTextarea" class="form-label">거주 환경</label>
		<div role="radiogroup" aria-labelledby="living_conditions">

			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="inlineRadioOptions" id="inlineRadio1" value="apartment">
				<label class="form-check-label" for="inlineRadio1">아파트</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="inlineRadioOptions" id="inlineRadio2" value="villa">
				<label class="form-check-label" for="inlineRadio2">빌라</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="inlineRadioOptions" id="inlineRadio3" value="withYard">
				<label class="form-check-label" for="inlineRadio3">단독주택(마당O)</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="inlineRadioOptions" id="inlineRadio4" value="noYard">
				<label class="form-check-label" for="inlineRadio4">단독주택(마당X)</label>
			</div>
			<div class="invalid-feedback">거주환경 기재는 필수입니다</div>
		</div>

		<div class="mb-3">
			<label for="validationTextarea" class="form-label">나의 조건</label>
			<textarea class="form-control is-invalid" id="content"
				placeholder="분양을 할 수 있는 조건인지 상세히 적어주세요!" required>
				<c:if test="${creationFailed}">value="${apply.content}"</c:if>
			</textarea>
			<div class="invalid-feedback">조건 기재는 필수입니다</div>
		</div>
		<div class="mb-3">
			<input type="button" value="Submit" onClick="create()"> &nbsp;
			<input type="button" value="동물들 다시보기" onClick="animalList('<c:url value='/animal/list' />')">
		</div>
	</form>
	
</body>
</html>