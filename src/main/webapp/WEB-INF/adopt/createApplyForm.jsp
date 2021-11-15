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

	form.submit();s
}

function animalList(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
<style style type="text/css">
.selection{
	margin-top: 50px;
}

#apply{
	width:70%;
	height:80%;
	margin:0 auto;
	padding:10px;
	display:flex;
	margin-top: 20px;
	border: 1px solid;
	border-radius: 1rem;
}

#imgParent{
	text-align:center;
	border: 1px solid;
	width:50%;
	height:400px;
	margin-left:20px;
	margin-top:50px;
	vertical-align:middle;
}

#info{
	padding-left: 30px;
	padding-right: 10px;
	padding-bottom: 10px;
 	width:50%;
	float: right;
	margin:auto;
}

.btnAll{
	width:200px;
	height:40px;
	margin:0 auto;
	display:flex;
	margin-top: 30px;
}

#submit, #cancel{
	text-align:center;
	width:45%;
}

h1{
	text-align:center;
}

</style>
</head>
<body>
	<!-- registration form  -->

	<h1>�Ծ� ��û �ϱ�</h1>
	<form class="was-validated" name="form" method="POST"
		action="<c:url value='/adopt/register' />">
		<div id="apply">
		<div id="imgParent">
			<img class="img" src="'${animal.image}'" />
		</div>
		<div id="info">
			<div class="form-group row">
				<label for="userName" class="col-sm-2 col-form-label">1. �̸�</label>
				<div class="col-sm-10">
					<input type="text" readonly class="form-control-plaintext"
						id="userName" value="${apply.userName}">
					<!-- apply��ü ���� userName? -->
				</div>
			</div><br>
			<div class="form-group row">
				<label for="address" class="col-sm-2 col-form-label">2. �������</label>
				<div class="col-sm-10">
					<input type="text" readonly class="form-control-plaintext"
						id="address">
				</div>
			</div><br>
			<!-- <div class="mb-3">
				<label for="select_animal" class="form-label">��û �ݷ�����</label> <select
					id="select_animal" class="form-select" required
					aria-label="select example">
					<option value="">��û�ϰ� ���� �ݷ������� �����ϼ���</option>
					
					<c:forEach var="animal" items="${apply.animalList}">
						<option value="${animal.animal_id}">${animal.animal_name}</option>
					</c:forEach>
					�ɼǿ� ������ id (matched�ȵ�) �����鳪������
				</select>
				<div class="invalid-feedback">�ݷ����� ������ �ʼ��Դϴ�</div>
			</div> -->
			<div class="mb-3">
				<label for="select_animal" class="form-label">3. ��û �ݷ�����</label> 
					<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext"
							id="select_animal" value="${apply.animal_type} > ${apply.species}">
					</div>
			</div><br>
	
			<div class="form-group row">
				<label for="have_pets" class="col-sm-2 col-form-label">4. �ݷ�����
					����</label>
				<div class="col-sm-10">
					<input type="text" readonly class="form-control-plaintext"
						id="have_pets" placeholder="��) �����(���þȺ��)-6�� "
						value="${apply.have_pets}">
					<c:if test="${creationFailed}">value="${apply.have_pets}"</c:if>
				</div>
				<div class="invalid-feedback">*�ݷ����� ���� ����� �ʼ��Դϴ�</div>
			</div><br>
	
			<label for="living_conditions" class="form-label">5. ���� ȯ��</label>
			<div class="form-check form-check-inline">
	
				<label class="form-check-label"><input
					class="form-check-input" type="radio" name="living_conditions"
					id="living_conditions" value="apartment">����Ʈ</label>
			</div>
			<div class="form-check form-check-inline">
				<label class="form-check-label"><input
					class="form-check-input" type="radio" name="living_conditions"
					id="living_conditions" value="villa">����</label>
			</div>
			<div class="form-check form-check-inline">
				<label class="form-check-label"><input
					class="form-check-input" type="radio" name="living_conditions"
					id="living_conditions" value="withYard">�ܵ�����(����O)</label>
			</div>
			<div class="form-check form-check-inline">
				<label class="form-check-label"><input
					class="form-check-input" type="radio" name="living_conditions"
					id="living_conditions" value="noYard">�ܵ�����(����X)</label>
			</div>
			<div class="invalid-feedback">*����ȯ�� ����� �ʼ��Դϴ�</div><br>
	
			<div class="mb-3">
				<label for="content" class="form-label">6. ���� ����</label><br>
				<textarea class="form-control is-invalid" id="content"
					placeholder="�о��� �� �� �ִ� �������� ���� �����ּ���!" required>
					<c:if test="${creationFailed}">value="${apply.content}"</c:if>
				</textarea>
				<div class="invalid-feedback">*���� ����� �ʼ��Դϴ�</div>
			</div><br>
		</div>
		</div>
		<div class="btnAll">
			<input id="submit" type="button" value="��û" onClick="create()">
			&nbsp; 
			<input id="cancel" type="button" value="���"
				onClick="animalList('<c:url value='/animal/list' />')">
		</div>
	</form>
</body>
</html>