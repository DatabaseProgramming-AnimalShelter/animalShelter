<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
 <style>
        .container{
          background-color:#FFBB00;
          padding-bottom:20px;
        }
        .registerForm{
          margin: auto;
          width: 700px;
          background-color: rgba( 255, 255, 255, 0.7 );
          padding: 30px;
          margin-bottom:50px;
          border:5px dashed white;
        }
        .signupLogo{
        	margin: auto;
        	width: 200px;
        	margin-bottom:20px;
        }
    </style>
	<script>
	function userCreate() {
		if (form.user_id.value == "") {
			alert(" ID를 입력하십시오.");
			form.user_id.focus();
			return false;
		} 
		if (form.password.value == "") {
			alert("비밀번호를 입력하십시오.");
			form.password.focus();
			return false;
		}
		if (form.password.value != form.password2.value) {
			alert("비밀번호가 일치하지 않습니다.");
			form.name.focus();
			return false;
		}
		if (form.name.value == "") {
			alert("이름을 입력하십시오.");
			form.name.focus();
			return false;
		}
		var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		if(emailExp.test(form.email.value)==false) {
			alert("이메일 형식이 올바르지 않습니다.");
			form.email.focus();
			return false;
		}
		var phoneExp = /^\d{2,3}\d{3,4}\d{4}$/;
		if(phoneExp.test(form.phone.value)==false) {
			alert("전화번호 형식이 올바르지 않습니다.");
			form.phone.focus();
			return false;
		}
		form.submit();
	}
	</script>
	<div class="container">  
		<br>
		
		<br>
		<!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
		<div class="row col-lg-12">
			<c:if test="${registerFailed}">
				<h6 class="text-danger"><c:out value="${exception.getMessage()}"/></h6>
			</c:if>
		</div>	  
		<!-- registration form  -->
		<form class="registerForm"name="form" method="POST" action="<c:url value='/user/register'/>">
		
		<img  class ="signupLogo" src="<c:url value='/images/signup.png'/>"  >
			<div class="form-group row">   
		        <label for="user_id" class="col-lg-3 col-form-label">사용자 ID</label>
		        <div class="col-lg-8">
		            <input type="text" name="user_id" class="form-control" placeholder="사용자 ID"> 
		        </div>
		    </div>       
		    <div class="form-group row">   
		        <label for="password" class="col-lg-3 col-form-label">비밀번호</label>
		        <div class="col-lg-8">
		            <input type="password" name="password" class="form-control" placeholder="password"> 
		        </div>
		    </div>       
		    <div class="form-group row">  
		        <label for="password2" class="col-lg-3 col-form-label">비밀번호 확인</label>
		        <div class="col-lg-8">
		        	<input type="password" name="password2" class="form-control" placeholder="비밀번호 확인">
		        </div> 
		    </div> 
			<div class="form-group row">   
		        <label for="user_name" class="col-lg-3 col-form-label">이름</label>
		        <div class="col-lg-8">
		        	<input type="text" name="user_name" class="form-control" placeholder="이름"
		        		<c:if test="${registerFailed}">value="${user.user_name}"</c:if> > 
		        </div>
		    </div>       
		    <div class="form-group row">  
		        <label for="email" class="col-lg-3 col-form-label">이메일</label>
		        <div class="col-lg-8">
		        	<input type="text" name="email" class="form-control" placeholder="you@example.com"
		        		<c:if test="${registerFailed}">value="${user.email}"</c:if> >  
		        </div>
		    </div> 
			<div class="form-group row">  
		        <label for="phone" class="col-lg-3 col-form-label">전화번호</label>
		        <div class="col-lg-8">
		        	<input type="text" name="phone" class="form-control" placeholder="010-XXXX-YYYY"
		        		<c:if test="${registerFailed}">value="${user.phone}"</c:if> >  
		        </div>
		    </div> 
			
	   		<br>
			<div class="form-group">        
				<input type="button" style=background-color:brown class=" w-100 btn" value="가입" onClick="userCreate()">  		     
			</div>   
		</form> 	
	</div>
<%@ include file="/WEB-INF/home/mainFooter.jsp" %>