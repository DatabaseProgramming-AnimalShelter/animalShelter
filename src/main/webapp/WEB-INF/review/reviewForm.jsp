<!-- 후기 작성 폼 화면 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>리뷰 작성 폼</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
function commCreate() {
	if (form.name.value == "") {
		alert("제목을 입력하십시오.");
		form.name.focus();
		return false;
	} 
	if (form.desc.value == "") {
		alert("내용을 입력하십시오.");
		form.desc.focus();
		return false;
	}	
	form.submit();
}
</script>
</head>
<body>

<div class="container">  
	<br>
	<h4>후기 작성 화면</h4>
	<br>
	<!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<div class="row col-lg-12">
		<c:if test="${creationFailed}">
			<h6 class="text-danger"><c:out value="${exception.getMessage()}"/></h6>
		</c:if>
	</div>	  
	<!-- registration form  -->
	<form name="form" method="POST" action="<c:url value='/community/create' />">
    	<div class="form-group row">   
	        <label for="userId" class="col-lg-2 col-form-label">제목</label>
	        <div class="col-lg-10">
	            <input type="text" name="title" class="form-control" placeholder="제목"
	            	<c:if test="${creationFailed}">value="${comm.name}"</c:if>>	            	
	        </div>
	    </div>       
	    <div class="form-group row">   
	        <label for="userId" class="col-lg-2 col-form-label">설명</label>
	        <div class="col-lg-10">
        		<textarea id="body" name="body" rows=15" cols="50" placeholder="내용을 입력하시오." class="form-control"></textarea>
	        </div>
	    </div>       
	    <br>
	    <div class="form-group">        
			<input type="button" class="btn btn-primary" value="생성" onClick="commCreate()"> 	     
		</div>   
	</form>
</div>
</body>
</html>
 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>