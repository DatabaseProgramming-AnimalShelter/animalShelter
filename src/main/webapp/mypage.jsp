<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<%@ include file="../../css/indexHeader.jsp" %>
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
    <script>
		function login() {
			if (form.userId.value == "") {
				alert("사용자 ID를 입력하십시오.");
				form.userId.focus();
				return false;
			} 
			if (form.password.value == "") {
				alert("비밀번호를 입력하십시오.");
				form.password.focus();
				return false;
			}		
			form.submit();
		}
		
	</script>
<div class="container" >  
	<br>
	<br>
	<table style="width:100%">
    <tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td class="title">&nbsp;&nbsp;<b>사용자 관리 - 사용자 정보 보기</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	  	    
	  	<table style="background-color: YellowGreen">
	  	  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">사용자 ID</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%--<%=user.getUserId()%>-->
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">이름</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%--<%=user.getName()%>-->
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">이메일 주소</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				${user.email} <%-- <%=user.getEmail()%> --%>
			</td>
		  </tr>		  
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">전화번호</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
			<%-- 	${user.phone}<%=user.getPhone()%> --%>
			</td>
		  </tr>	
	 	</table>
	    <br>
	    
 	    
 	    <!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${updateFailed || deleteFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>    
	  </td>
	</tr>
  </table>  
</div>
<%@ include file="../../css/indexFooter.jsp" %>