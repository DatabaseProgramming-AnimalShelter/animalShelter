<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>입양신청 결과 목록</title>
<div class="container">
	<h1>입양신청 결과 목록</h1>
	<table class="table table-hover ">
		<thead style="background-color: #fd8a69">
			<tr>
				<th scope="col">no</th>
				<th scope="col">동물 번호</th>
				<th scope="col">과 [종]</th>
				<th scope="col">승인 날짜</th>
				<th scope="col">입양 승인 여부</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="adopt" items="${userAdoptList}">
				<tr>
					<td><a
						href="<c:url value='/adopt/view'>
					   <c:param name='apply_id' value='${adopt.apply_id}'/>
			 		 </c:url>">
							${adopt.apply_id}</a></td>
					<td>
					<a href="<c:url value='/animal/view'>
					   <c:param name='animal_id' value='${adopt.animal_id}'/>
			 		 </c:url>">${adopt.animal_id}</a>
					</td>
					<td>${adopt.animal_type}[${adopt.species}]</td>
					<td>${adopt.approval_date}</td>
					<td>
						<c:if test="${adopt.apply_matched == 0}">
							관리자가 아직 승인 전 입니다.
						</c:if> 
						<c:if test="${adopt.apply_matched == 1}">
							<c:if test="${adopt.animal_matched == 1}">
								<a class="btn btn-outline-danger" role="button"
									href="<c:url value='/adopt/result'><c:param name='animal_id' value='${adopt.animal_id}'/>
                    			</c:url>">후기 작성</a>
							</c:if> 
                    		<c:if test="${adopt.animal_matched == 0}">
								입양 신청이 반려 되었습니다.
							</c:if>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="/WEB-INF/home/mainFooter.jsp"%>