<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Ծ��û��</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/community.css' />"
	type="text/css">
<script>
function create() {
	<!-- 
	if (form.name.value == "") {
		alert("�̸��� �Է��Ͻʽÿ�.");
		form.name.focus();
		return false;
	} 
	if (form.desc.value == "") {
		alert("������ �Է��Ͻʽÿ�.");
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
			<label for="userName" class="col-sm-2 col-form-label">�̸�</label>
			<div class="col-sm-10">
				<input type="text" readonly class="form-control-plaintext"
					id="userName" value="${apply.userName}">
				<!-- apply��ü ���� userName? -->
			</div>
		</div>
		<div class="form-group row">
			<label for="address" class="col-sm-2 col-form-label">�������</label>
			<div class="col-sm-10">
				<input type="text" readonly class="form-control-plaintext"
					id="address" value="${apply.address}">
			</div>
		</div>
		<div class="mb-3">
			<label for="select_animal" class="form-label">��û �ݷ�����</label> <select
				id="select_animal" class="form-select" required
				aria-label="select example">
				<option value="">��û�ϰ� ���� �ݷ������� �����ϼ���</option>
				<c:forEach var="animal" items="${apply.animalList}">
					<option value="${animal.animal_id}">${animal.animal_name}</option>
				</c:forEach>
				<!-- �ɼǿ� ������ id (matched�ȵ�) �����鳪������ -->
			</select>
			<div class="invalid-feedback">�ݷ����� ������ �ʼ��Դϴ�</div>
		</div>

		<div class="form-group row">
			<label for="have_pets" class="col-sm-2 col-form-label">�ݷ����� ����</label>
			<div class="col-sm-10">
				<input type="text" readonly class="form-control-plaintext"
					id="have_pets" placeholder="��) �����(���þȺ��) - 6�� "> value="${apply.have_pets}">
				<c:if test="${creationFailed}">value="${apply.living_environment}"</c:if>
			</div>
			<div class="invalid-feedback">�ݷ����� ���� ����� �ʼ��Դϴ�</div>
		</div>

		<label for="validationTextarea" class="form-label">���� ȯ��</label>
		<div role="radiogroup" aria-labelledby="living_conditions">

			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="inlineRadioOptions" id="inlineRadio1" value="apartment">
				<label class="form-check-label" for="inlineRadio1">����Ʈ</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="inlineRadioOptions" id="inlineRadio2" value="villa">
				<label class="form-check-label" for="inlineRadio2">����</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="inlineRadioOptions" id="inlineRadio3" value="withYard">
				<label class="form-check-label" for="inlineRadio3">�ܵ�����(����O)</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="inlineRadioOptions" id="inlineRadio4" value="noYard">
				<label class="form-check-label" for="inlineRadio4">�ܵ�����(����X)</label>
			</div>
			<div class="invalid-feedback">����ȯ�� ����� �ʼ��Դϴ�</div>
		</div>

		<div class="mb-3">
			<label for="validationTextarea" class="form-label">���� ����</label>
			<textarea class="form-control is-invalid" id="content"
				placeholder="�о��� �� �� �ִ� �������� ���� �����ּ���!" required>
				<c:if test="${creationFailed}">value="${apply.content}"</c:if>
			</textarea>
			<div class="invalid-feedback">���� ����� �ʼ��Դϴ�</div>
		</div>
		<div class="mb-3">
			<input type="button" value="Submit" onClick="create()"> &nbsp;
			<input type="button" value="������ �ٽú���" onClick="animalList('<c:url value='/animal/list' />')">
		</div>
	</form>
	
</body>
</html>