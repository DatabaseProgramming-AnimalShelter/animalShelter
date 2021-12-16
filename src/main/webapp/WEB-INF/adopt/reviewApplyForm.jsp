<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 매니저 일때만 가능하게 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style type="text/css">
.container {
	border: 1px solid;
	height: 700px;
	background-image: url("<c:url value='/images/yg1.png'/>");
}

form {
	margin-top: 50px;
}

.item {
	width: 80%;
	height: 80%;
	margin: 0 auto;
	padding: 10px;
	display: flex;
	margin-top: 20px;
	border: 1px solid;
	border-radius: 1rem;
	background-color: white;
}

.info {
	padding-left: 30px;
	padding-right: 10px;
	padding-bottom: 10px;
	width: 70%;
	float: right;
	margin: auto;
}

.btn {
	margin: 0 auto;
	padding: 10px;
	display: flex;
	margin-top: 20px;
	width: 100px;
	color: black;
	height: 40px;
	text-align: center;
}

.apply {
	background-color: #FEC8E3;
}

h1 {
	text-align: center;
}

.type {
	margin-left: 10%;
}

.btn {
	margin-bottom: 30px;
	background-color: #e0d8cb;
	border: 1px black solid
}
</style>
<div class="container">
	<form method="POST" name="form"
		action="<c:url value='/adopt/result'>
						<c:param name='apply_id' value='${apply.apply_id}'/>
				</c:url>">
		<h2 class="fw-bolder type">no.${apply.apply_id}</h2>
		<div class="outer">
			<div class="item">
				<div id="imgParent">
					<c:choose>
						<c:when test="${not empty apply.image}">
							<img
								src="${pageContext.request.session.servletContext.contextPath}/upload/${apply.image}"
								height="400px" width="400px" />
						</c:when>
						<c:otherwise>
							<span>사진없음 </span>
						</c:otherwise>
					</c:choose>
					<br>

				</div>

				<div class="info">

					<table class="table">
						<tr>
							<th scope="row">신청 날짜</th>
							<td>${apply.apply_date}</td>
						</tr>
						<tr>
							<th scope="row">동물아이디</th>
							<td><a
								href="<c:url value='/animal/view'>
	                     <c:param name='animal_id' value='${apply.animal_id}'/>
	                    </c:url>">${apply.animal_id}</a></td>
						</tr>
						<tr>
							<th scope="row">품종</th>
							<td>[${apply.animal_type}] > ${apply.species}</td>
						</tr>
						<tr>
							<th scope="row">신청자 이름</th>
							<td>${apply.user_name}</td>
						</tr>
						<tr>
							<th scope="col">반려동물 유무</th>
							<td>${apply.have_pets}</td>
						</tr>
						<tr>
							<th scope="row">거주환경</th>
							<td>${apply.living_environment}</td>
						</tr>
						<tr>
							<th scope="row">나의 조건</th>
							<td>${apply.content}</td>
						</tr>
						<tr>
							<th scope="row">입양완료 여부</th>
							<td>${apply.apply_matched}</td>
						</tr>
						<c:if test="${user_id == 'admin' && apply.apply_matched == 0}">
							<tr>
								<th><input class="btn btn-outline-danger" type="submit" value="승인"></th>
								<th><a class="btn btn-outline-secondary" role="button"
									href="<c:url value='/adopt/result'><c:param name='apply_id' value='${apply.apply_id}'/>
                   					 </c:url>">거부</a></th>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</form>
</div>
<br>
<!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
<c:if test="${creationFailed}">
	<h6 class="text-danger">
		<c:out value="${exception}" />
	</h6>
</c:if>
<%@ include file="/WEB-INF/home/mainFooter.jsp"%>