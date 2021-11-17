<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<title>입양신청 목록 조회</title>
<div class="container"> 
	<table class="table table-hover " >
		<thead style="background-color: #e0d8cb">
			<tr>
				<th scope="col">no</th>
				<th scope="col">동물 번호</th>
				<th scope="col">신청자 이름</th>
				<th scope="col">신청 날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="adopt" items="${adoptApplyList}">
			
			 		 <tr onclick='location.href="<c:url value='/adopt/view'>
					   <c:param name='apply_id' value='${adopt.apply_id}'/>
			 		 </c:url>"'>	<th scope="row">
							
			 	${adopt.apply_id} </th>
					<td>${adopt.user_name}</td>
					<td>${adopt.animal_id}</td>
					<td>${adopt.apply_date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="/WEB-INF/home/mainFooter.jsp" %>