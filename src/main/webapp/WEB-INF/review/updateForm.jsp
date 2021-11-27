<!-- 후기 수정 화면 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>

<title>후기 - 수정</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
function reviewModify() {
	if (form.title.value == "") {
		alert("제목을 입력하십시오.");
		form.title.focus();
		return false;
	} 
	if (form.content.value == "") {
		alert("설명을 입력하십시오.");
		form.content.focus();
		return false;
	}	
	form.submit();
}
</script>

<div class="container">  
	<br>
	<h4>후기 수정</h4>
	<br>
	<!-- Update Form  -->
	<form name="form" method="POST" action="<c:url value='/review/update' />">
		<input type="hidden" name="post_id" value="${review.post_id}"/>	  
	  	<div class="form-group row">   
	        <label class="col-lg-2 col-form-label">POST ID</label>
	        <div class="col-lg-10">
	        	<p class="form-control-static">${review.post_id}</p> 
	        </div>
	    </div>   
	    <div class="form-group row">   
	        <label class="col-lg-2 col-form-label">ANIMAL ID</label>
	        <div class="col-lg-10">
	        	<p class="form-control-static">${review.animal_id}</p> 
	        </div>
	    </div>      
	    <div class="form-group row">   
	        <label for="name" class="col-lg-2 col-form-label">제목</label>
	        <div class="col-lg-10">
	            <input type="text" name="title" class="form-control" value="${review.title}"> 
	        </div>
	    </div>  
	    <div class="form-group row">   
	        <label for="content" class="col-lg-2 col-form-label">내용</label>
	        <div class="col-lg-10">
        		<textarea id="body" name="content" rows=15" cols="50" class="form-control">${review.content}</textarea>
	        </div>
	    </div>    
	    <br>
		<div class="form-group">        
			<input type="button" class="btn btn-primary" value="수정" onClick="reviewModify()">
			
			<a href="<c:url value='/review/list' />" class="btn btn-link">후기 목록</a>     
		</div>	 
	</form>
</div>

 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>