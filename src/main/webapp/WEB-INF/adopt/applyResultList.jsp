<%@page contentType="text/html; charset=utf-8"%>
<%-- <%@page import="java.util.*, model.*" %> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
	사용자 입장 & 매니저 입장에서 입양 매칭된사람들을 볼 수 있는 페이지
	사용자 입장에서는 자신의 username만 확인가능!
	매니저입장에서는 이름 클릭하면, 입양 신청서 확인 가능 ==> 입양
--%>
<html>
<head>
<title>입양 매칭 결과</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />"
	type="text/css">
</head>
<body>
	<br>

	<table class="table table-hover table-borderless">
		<thead>
			<tr>
				<th scope="col">no</th>
				<th scope="col">동물 번호</th>
				<th scope="col">신청자 이름</th>
				<th scope="col">신청 날짜</th>
				<!-- 신청날짜를 보여주는 이유는 : 선착순의 느낌도 주기 위해서 -->
				<th scope="col">입양완료 여부</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="adopt" items="${adopterList}">
				<th scope="row"><a
						href="<c:url value='/adopt/viewApply'>
					   <c:param name='apply_id' value='${adopt.apply_id}'/>
			 		 </c:url>">
							${adopt.apply_id}</a>
				</th>
				<tr>
					<td>${adopt.userName}</td>
					<td><a
						href="<c:url value='/animal/view'>
					   <c:param name='animal_id' value='${adopt.animal_id}'/>
			 		 </c:url>">
							${adopt.animal_id}</a></td>
					<td>${adopt.apply_date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>