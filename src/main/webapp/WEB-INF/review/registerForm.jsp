<!-- 후기 작성 폼 화면 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>

<title>리뷰 작성 폼</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
function reviewCreate() {
	if (form.title.value == "") {
		alert("제목을 입력하십시오.");
		form.title.focus();
		return false;
	} 
	if (form.content.value == "") {
		alert("내용을 입력하십시오.");
		form.content.focus();
		return false;
	}	
	form.submit();
}
</script>

<!--     
//       CREATE TABLE Review
//       (
//          post_id              INTEGER NOT NULL ,
//          title                VARCHAR2(40) NOT NULL ,    //제목ㅇㅇㅇㅇ
//          content              VARCHAR2(40) NULL ,		//내용ㅇㅇㅇㅇ
//          creationDate         DATE NULL ,				//날짜
//          image                VARCHAR2(40) NULL ,		//사진
//          writer               VARCHAR2(20) NOT NULL ,	//작성자
//          animal_id            INTEGER NOT NULL 			//동물 아이디
//       );
 -->   

<div class="container">  
	<br>
	<h4>후기 작성 화면</h4>
	<br>
	
	<!-- 리뷰 등록 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<div class="row col-lg-12">
		<c:if test="${creationFailed}">
			<h6 class="text-danger"><c:out value="${exception.getMessage()}"/></h6>
		</c:if>
	</div>	  
	
	<!-- registration form  -->
	<form name="form" method="POST" action="<c:url value='/review/register'>
	     		   								<c:param name='animal_id' value='${animal_id}'/>
			  	  							</c:url>" 
			  	  							enctype="multipart/form-data" >
    	<div class="form-group row">   
	        <label for="title" class="col-lg-2 col-form-label">동물번호</label>
	        <div class="col-lg-10">	    
	        	   ${animal_id} 
	        </div>
	    </div> 
    	<div class="form-group row">   
	        <label for="title" class="col-lg-2 col-form-label">제목</label>
	        <div class="col-lg-10">
	        	<c:choose>
	        		<c:when test="${review.title != null}">
	        			<input type="text" name="title" class="form-control" value="${review.title}">
	        		</c:when>
	        		<c:otherwise>
	        			<input type="text" name="title" class="form-control" placeholder="제목">
	        		</c:otherwise>
	        	</c:choose>           	
	        </div>
	    </div>       
	    <div class="form-group row">   
	        <label for="content" class="col-lg-2 col-form-label">내용</label>
	        <div class="col-lg-10">
	        	<c:choose>
	        		<c:when test="${review.content != null}">
        				<textarea id="body" name="content" rows="15" cols="50" class="form-control">${review.content}</textarea>
	        		</c:when>
	        		<c:otherwise>
        				<textarea id="body" name="content" rows="15" cols="50" placeholder="내용을 입력하시오." class="form-control"></textarea>
	        		</c:otherwise>
	        	</c:choose>
	        </div>
	    </div> 
	    <div class="form-group row">   
	        <label for="content" class="col-lg-2 col-form-label">사진</label>
	        <div class="col-lg-10">
	     	   <c:choose>
	        		<c:when test="${review.image != null}">
	        			<input type="file" name="image" size="11"/>
	        		</c:when>
	        		<c:otherwise>
						<input type="file" name="image" size="11"/>	        		
					</c:otherwise>
	        	</c:choose> 
	        </div>
	    </div>         
	    <br>
	    <div class="form-group">        
			<input type="button" class="btn btn-primary" value="확인" onClick="reviewCreate()"> 	     
		</div>   
	</form>
</div>
</body>
</html>
 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>