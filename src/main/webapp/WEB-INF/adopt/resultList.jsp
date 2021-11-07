<%@page contentType="text/html; charset=utf-8" %>
<%-- <%@page import="java.util.*, model.*" %> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	사용자 입장 & 매니저 입장에서 입양 매칭된사람들을 볼 수 있는 페이지
	사용자 입장에서는 자신의 username만 확인가능!
	매니저입장에서는 이름 클릭하면, 입양 신청서 확인 가능 ==> 입양
--%>
<html>
<head>
<title>입양 매칭 결과</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
</head>
<body>
<br>

<table class="table table-hover table-borderless">
  <thead>
    <tr>
      <th scope="col">no</th>
      <th scope="col">동물 번호</th>
      <th scope="col">신청자 이름</th>
      <th scope="col">신청 날짜</th><!-- 신청날짜를 보여주는 이유는 : 선착순의 느낌도 주기 위해서 -->
      <th scope="col">입양완료 여부</th>
    </tr>
  </thead>
  <tbody>
  	  <c:forEach var="adopt" items="${adopterList}">  
  	  	<th scope="row">${adopt.apply_id}</th>			  	
  		<tr>
  		
		  <td>
		  	${adopt.userName}      
		  </td>
		  <td>
			<a href="<c:url value='/animal/view'>
					   <c:param name='animal_id' value='${adopt.animal_id}'/>
			 		 </c:url>">
			  ${adopt.animal_id}</a>
		  </td>
		  <td>
		    ${adopt.apply_date}        
		  </td>
		  <td>
			<a href="<c:url value='/community/view'>
					   <c:param name='commId' value='${user.commId}'/>
			 		 </c:url>">		
			${user.commName}</a>
		  </td>
		</tr>
	  </c:forEach> 
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td colspan="2">Larry the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
<table style="width:100%">
  <tr>
  	<td width="20"></td>
    <td><a href="<c:url value='/user/logout' />">로그아웃(&nbsp;${curUserId}&nbsp;)</a></td>
  </tr>
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <tr>
	<td width="20"></td>
	<td>
	  <table>
		<tr>
		  <td class="title">&nbsp;&nbsp;<b>사용자 관리 - 리스트</b>&nbsp;&nbsp;</td>
		</tr>
	  </table>  
	  <br>		  
	  <table style="background-color: YellowGreen">
		<tr>
		  <td width="190" align="center" bgcolor="E6ECDE" height="22">사용자 ID</td>
		  <td width="200" align="center" bgcolor="E6ECDE">이름</td>
		  <td width="200" align="center" bgcolor="E6ECDE">이메일</td>
		  <td width="200" align="center" bgcolor="E6ECDE">커뮤니티</td>
		</tr>
<%-- 
	if (userList != null) {	
	  Iterator<User> userIter = userList.iterator();
	
	  //사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
	  while ( userIter.hasNext() ) {
		User user = (User)userIter.next();
--%>	  	
	  <c:forEach var="user" items="${userList}">  			  	
  		<tr>
		  <td width="190" align="center" bgcolor="ffffff" height="20">
		  	${user.userId}       <%-- <%=user.getUserId()%> --%>
		  </td>
		  <td width="200" bgcolor="ffffff" style="padding-left: 10">
			<a href="<c:url value='/user/view'>
					   <c:param name='userId' value='${user.userId}'/>
			 		 </c:url>">
			  ${user.name}</a>	 <%-- <%=user.getName()%></a> --%>
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
		    ${user.email}        <%-- <%=user.getEmail()%> --%>
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
			<a href="<c:url value='/community/view'>
					   <c:param name='commId' value='${user.commId}'/>
			 		 </c:url>">		
			${user.commName}</a>
		  </td>
		</tr>
	  </c:forEach> 
<%--
	  }
	}
--%>	 
	  </table>	  	 
	  <br>   
	  <a href="<c:url value='/user/register/form' />">사용자 추가</a>
	  <br>   
	  <a href="<c:url value='/community/list' />">커뮤니티 목록</a>
	</td>
  </tr>
</table>  
</body>
</html>