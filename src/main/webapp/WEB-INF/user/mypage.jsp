<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	User user = (User)request.getAttribute("user");
--%>
 <style>
        .container{
          height: 700px;
          background-image:url("<c:url value='images/loginLogo.png'/>");
        }
        .loginForm{
          margin: auto;
          width: 300px;
          margin-top:25%;
        }
    </style>
<div class="container" >  
	<br>
	<br>
	<table style="width:100%">
    <tr>
	  <td width="20"></td>
	  <td>
	   <table style="width:100%">
    <tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td class="title">&nbsp;&nbsp;<b>마이페이지임미다</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	  	    
	  	<table class="">
	  	  <tr>
			<td class="">사용자 ID</td>
			<td class="">
				${user.id}
			</td>
		  </tr>
		  <tr>
			<td class="userHead">이름</td>
			<td class="userCell">
				${user.name}
			</td>
		  </tr>
		 
		  <tr>
			<td class="userHead">이메일</td>
			<td class="userCell">
				${user.email}
			</td>
		  </tr>
		  <tr>
			<td class="userHead">전화번호</td>
			<td class="userCell">
					${user.phone} 
			</td>
		  </tr>	
		
	 	</table>
	    <br>
	    <a href="<c:url value='/user/update'>
	     		   <c:param name='userId' value='${user.id}'/>
			 	 </c:url>">회원정보 수정</a> &nbsp;
 	    <a href="<c:url value='/user/delete'>
				   <c:param name='userId' value='${user.id}'/>
			 	 </c:url>" onclick="return userRemove();">회원탈퇴</a> &nbsp;
 	   
 	    <br><br>	   
 	   
 	    <!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${updateFailed || deleteFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>    
	  </td>
	</tr>
  </table>  
</div>
<%@ include file="/WEB-INF/home/mainFooter.jsp" %>