<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<title>입양신청 결과 목록</title>

	<div class="container">
	<h1>입양신청 결과 목록</h1>
		<table class="table table-hover ">
			<thead style="background-color: #fd8a69">
				<tr>
					<th scope="col">no</th>
					<th scope="col">신청자 이름</th>
					<th scope="col">동물 번호</th>
					<th scope="col">승인 날짜</th>
					<!-- 신청날짜를 보여주는 이유는 : 선착순의 느낌도 주기 위해서 -->
					<th scope="col">입양 승인 거부 여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="adopt" items="${AdoptApplyList}">
					<tr><td><a
						href="<c:url value='/adopt/viewApply'>
					   <c:param name='apply_id' value='${adopt.apply_id}'/>
			 		 </c:url>">
							${adopt.apply_id}</a></td>
						<td>${adopt.user_name}</td>
						<td>${adopt.animal_id}</td>
						<td>${adopt.approval_date}</td>
						<td>
						<c:choose>
							<c:when  test="${adopt.animal_matched==1}">
							승인
							</c:when>
							<c:when  test="${adopt.animal_matched==0}">
							반려
							</c:when>
						</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ include file="/WEB-INF/home/mainFooter.jsp" %>