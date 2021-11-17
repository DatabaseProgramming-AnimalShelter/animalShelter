<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 매니저 일때만 가능하게 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<br>
	<table class="table table-hover table-borderless">
		<thead>
			<tr>
				<!-- 신청날짜를 보여주는 이유는 : 선착순의 느낌도 주기 위해서 -->
				<th scope="col">신청 날짜</th>
				<th scope="col">no</th>
				<th scope="col">품종</th>
				<th scope="col">신청자 이름</th>
				<th scope="col">반려동물 유무</th>
				<th scope="col">거주환경</th>
				<th scope="col">나의 조건</th>
				<th scope="col">입양완료 여부</th>
			</tr>
		</thead>
		<tbody> 
		  <tr>
			<td>${apply.apply_date}</td>
		  </tr>
		  <tr>
			<!-- <th>신청 반려동물</th> -->
			<td>${apply.animal_id}</td>
		  </tr>
		  <tr>
			<!-- <th>품종</th> -->
			<td>[${apply.animal_type}] > ${apply.species}</td>
		  </tr>
		  <tr>
			<!-- <th>이름</th> -->
			<td>${apply.user_name}</td>
		  </tr>
		  <tr>
			<!-- <th>반려동물 유무</th> -->
			<td>${apply.have_pets}</td>
		  </tr>
		  <tr>
			<!-- <th>거주 환경</th> -->
			<td>${apply.living_environment}</td>
		  </tr>
		  <tr>
			<!-- <th>나의 조건</th> -->
			<td>${apply.content}</td>
		  </tr>
		  <tr>
			<!-- <th>입양완료여부</th> -->
			<td>${apply.apply_matched}</td>
		  </tr>
		  <tr>
		  	<th>
		  	<a class="btn btn-outline-danger" role="button" href="<c:url value='adopt/approval'><c:param name='apply_id' value='${apply.apply_id}'/>
                    </c:url>">
              	수락</a> 
             </th>
		  	<th>
		  	<a class="btn btn-outline-secondary" href="<c:url value='/adopt/decline'/>" role="button">거부</a></th>
		  </tr>
		</tbody>
	</table>	
	<br> 		     
	<!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<c:if test="${creationFailed}">
		<h6 class="text-danger"><c:out value="${exception}"/></h6>
    </c:if>    
<%@ include file="/WEB-INF/home/mainFooter.jsp"%>