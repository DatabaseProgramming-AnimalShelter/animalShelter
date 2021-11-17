<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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

function animalList(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
<style style type="text/css">
.selection {
	margin-top: 50px;
}

#apply {
	width: 70%;
	height: 80%;
	margin: 0 auto;
	padding: 10px;
	display: flex;
	margin-top: 20px;
	border: 1px solid;
	border-radius: 1rem;
}

#imgParent {
	text-align: center;
	border: 1px solid;
	width: 50%;
	height: 400px;
	margin-left: 20px;
	margin-top: 50px;
	vertical-align: middle;
}

#info {
	padding-left: 30px;
	padding-right: 10px;
	padding-bottom: 10px;
	width: 50%;
	float: right;
	margin: auto;
}

.btnAll {
	width: 200px;
	height: 40px;
	margin: 0 auto;
	display: flex;
	margin-top: 30px;
}

#submit, #cancel {
	text-align: center;
	width: 45%;
}

h1 {
	text-align: center;
}
</style>
</head>
<body>
	<!-- registration form  -->
	<img src="${pageContext.request.session.servletContext.contextPath}/upload/${animal.image}" /> <br/> 
${pageContext.request.session.servletContext.contextPath}/upload/${animal.image}
	<h1>입양 신청 하기</h1>
	<form class="was-validated" name="form" method="POST" action="<c:url value='/adopt/register'/>">
		<div id="apply">
			<div id="imgParent">
				<img src="${dir}/${animal.image}" /> <br/> 
			</div>
			<div id="info">
				<div class="form-group row">
					<label for="user_id" class="col-sm-2 col-form-label">1. 이름</label>
					<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext"
							id="user_id" name="user_id" value="${adopter.user_id}">
					</div>
				</div>
				<br>
				<div class="form-group row">
					<label for="address" class="col-sm-2 col-form-label">2.
						사는지역</label>
					<div class="col-sm-10">
						<input type="text" name="address" class="form-control-plaintext" id="address">
					</div>
				</div>
				<br>
				<div class="mb-3">
					<label for="animal_id" class="form-label">3. 신청 반려동물</label>
					<div class="col-sm-10">
						<input type="text" name="animal_id" readonly class="form-control-plaintext"
							id="animal_id"
							value="${animal.animal_id}">
					</div>
				</div>
				<br>
				<div class="form-group row">
					<label for="species" class="col-sm-2 col-form-label">4.
						종</label>
					<div class="col-sm-10">
						<input type="text" name="species" readonly class="form-control-plaintext" id="species"
						value="${animal.species}">
					</div>
				</div>
				<br>

				<div class="form-group row">
					<label for="have_pets" class="col-sm-2 col-form-label">5.반려동물 유무</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="have_pets" name="have_pets"
							placeholder="예) 고양이(러시안블루)-6년 ">
					</div>
					<div class="invalid-feedback">*반려동물 유무 기재는 필수입니다</div>
				</div>
				<br> 
				<label for="living_conditions" class="form-label">6.거주 환경</label>
				<div class="form-check form-check-inline">
					<label class="form-check-label"><input
						class="form-check-input" type="radio" name="living_conditions"
						id="living_conditions" value="apartment">아파트</label>
				</div>
				<div class="form-check form-check-inline">
					<label class="form-check-label"><input
						class="form-check-input" type="radio" name="living_conditions"
						id="living_conditions" value="villa">빌라</label>
				</div>
				<div class="form-check form-check-inline">
					<label class="form-check-label"><input
						class="form-check-input" type="radio" name="living_conditions"
						id="living_conditions" value="withYard">단독주택(마당O)</label>
				</div>
				<div class="form-check form-check-inline">
					<label class="form-check-label"><input
						class="form-check-input" type="radio" name="living_conditions"
						id="living_conditions" value="noYard">단독주택(마당X)</label>
				</div>
				<div class="invalid-feedback">*거주환경 기재는 필수입니다</div>
				<br>

				<div class="mb-3">
					<label for="content" class="form-label">7. 나의 조건</label><br>
					<textarea class="form-control is-invalid" id="content" name="content"
						placeholder="분양을 할 수 있는 조건인지 상세히 적어주세요!" required>
				</textarea>
					<div class="invalid-feedback">*조건 기재는 필수입니다</div>
				</div>
				<br>
			</div>
		</div>
		<div class="btnAll">		
			<input type="submit" value="등록">
			<!-- 등록</button> &nbsp;  -->
			<input id="cancel"
				type="button" value="취소"
				onClick="animalList('<c:url value='/animal/list' />')">
		</div>
	</form>
</body>
</html>