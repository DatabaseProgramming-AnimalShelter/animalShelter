<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <style>
        .container{
          height: 700px;
          /*시도해봄
          background :url("${pageContext.request.contextPath}loginLogo.png");
          background-image:url("<c:url value='../../images/loginLogo.png'/>");
          background-image:url("images/loginLogo.png");
          background-image:url("<c:url value='loginLogo.png'/>");
          background-image:url("/user/loginLogo.png");*/
          /*
          form따로 뻈을 때 됨
          background-image:url("../../images/loginLogo.png");
          background-image:url("<c:url value='../../images/loginLogo.png'/>");
          */
          
          background-image:url("<c:url value='/images/loginLogo.png'/>");/*form으로 포워딩해야사진뜸*/
          
         
        }
        .loginForm{
          margin: auto;
          width: 300px;
          margin-top:25%;
        }
    </style>
    <script>
		function login() {
			if (form.user_id.value == "") {
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
<div class="container">  
	<br>
	<br>
	<!-- 로그인이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<div class="col-lg-12">
		<c:if test="${loginFailed}">
			<h6 class="text-danger"><c:out value="${exception.getMessage()}"/></h6>
		</c:if>
	</div>	  
	<!-- login form  -->
	<form class="loginForm " name="form" method="POST" action="<c:url value='/user/login' />">
		<div class="form-floating">   
	       
	        <input type="text" name="user_id" class="form-control" id="user_id" placeholder="사용자 ID"> 
	        <label for="user_id">사용자 ID</label>
	    </div>       
	    <div class="form-floating">  
	        <input type="password" name="password" class="form-control" id="password" placeholder="비밀번호"> 
	         <label for="password">비밀번호</label>
	    </div> 
		<div class="form-group">        
			<input type="button" class="w-100 btn btn-lg" value="로그인" onClick="login()" style=background-color:brown> 
			<a  class="w-100 btn btn-lg" href="<c:url value='/user/register' />"><button style=background-color:olive>회원 가입  </button> </a>  		     
		</div>   	
	</form> 
	
</div>
<%@ include file="/WEB-INF/home/mainFooter.jsp" %>