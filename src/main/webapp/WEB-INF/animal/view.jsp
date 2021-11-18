<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp"%>
<link href="/css/styles.css" rel="stylesheet" />
<script type="text/javascript">
	function apply(targetUri) {
		request.setAttribute("animal", $(animal));
		form.action = targetUri;
		form.submit();
	}
</script>
<style type="text/css">
.container {
	border: 1px solid;
	height: 700px;
	background-image: url("<c:url value='/images/grass.png'/>");
}
form{
	margin-top: 50px;
	
}
.item {
	width: 80%;
	height: 80%;
	margin: 0 auto;
	padding: 10px;
	display: flex;
	margin-top: 20px;
	border: 1px solid;
	border-radius: 1rem;
	background-color:white;
}
.info {
	padding-left: 30px;
	padding-right: 10px;
	padding-bottom: 10px;
	width: 70%;
	float: right;
	margin: auto;
}
.btn {
	margin: 0 auto;
	padding: 10px;
	display: flex;
	margin-top: 20px;
	width: 100px;
	color: black;
	height: 40px;
	text-align: center;
}
.apply {
	background-color: #FEC8E3;
}
h1 {
	text-align: center;
}
.type{
	margin-left:10%;
}
.btn{
margin-bottom:30px;
background-color: #e0d8cb;
border: 1px black solid}
</style>
<div class="container">
	

	<form name="form" action="<c:url value='/adopt/register' />">
	
		<div class="outer">
		<h2 class="fw-bolder type" >[${animal.animal_type}] >
						${animal.species}</h2>
			<div class="item">
				<div id="imgParent">
					<c:choose>
						<c:when test="${not empty animal.image}">
							<img
								src="${pageContext.request.session.servletContext.contextPath}/upload/${animal.image}" height="400px" width="400px" />
						</c:when>
						<c:otherwise>
							<span>사진없음</span>
						</c:otherwise>
					</c:choose>
					<br>

				</div>
				
				<div class="info">
					
					<table class="table">
  
  
    <tr>
      <th scope="row">동물아이디</th>
      <td> ${animal.animal_id}</td>
    </tr>
    <tr>
      <th scope="row">발견장소</th>
      <td><c:choose>

        <c:when test="${animal.location=='seoul'}">
          <span>서울</span>
        </c:when>
        <c:when test="${animal.location=='gyeonggi'}">
          <span>경기</span>
        </c:when>
        <c:when test="${animal.location=='incheon'}">
          <span>인천</span>
        </c:when>
        <c:when test="${animal.location=='etc'}">
          <span>기타</span>
        </c:when>
      </c:choose></td>
    </tr>
    <tr>
      <th scope="row">나이</th>
      <td><c:choose>
        <c:when test="${animal.age==0}">
          <span>1살미만</span>
        </c:when>
        <c:otherwise>
          <span>${animal.age}</span>
        </c:otherwise>
      </c:choose></td>
    </tr>
    <tr>
      <th scope="row">성별</th>
      <td> <c:choose>
        <c:when test="${animal.gender=='female'}">
          <span>암</span>
        </c:when>
        <c:otherwise>
          <span>수</span>
        </c:otherwise>
      </c:choose></td>
    </tr>
    
    <tr>
      <th scope="row">몸무게</th>
      <td><c:choose>
        <c:when test="${animal.weight==0}">
          <span>2kg미만</span>
        </c:when>
        <c:when test="${animal.weight==1}">
          <span>2kg~3kg</span>
        </c:when>
        <c:when test="${animal.weight==2}">
          <span>3kg~4kg</span>
        </c:when>
        <c:when test="${animal.weight==3}">
          <span>4kg~5kg</span>
        </c:when>
        <c:when test="${animal.weight==4}">
          <span>5kg~6kg</span>
        </c:when>
        <c:when test="${animal.weight==5}">
          <span>7kg~10kg</span>
        </c:when>
        <c:when test="${animal.weight==6}">
          <span>11kg~15kg</span>
        </c:when>
        <c:when test="${animal.weight==7}">
          <span>15kg~20kg </span>
        </c:when>
        <c:when test="${animal.weight==8}">
          <span>20kg~</span>
        </c:when>
      </c:choose></td>
    </tr>
    <tr>
      <th scope="row">특이사항</th>
      <td> ${animal.etc}</td>
    </tr>
</table>
				</div>
			</div>

			<a class="btn btn-primary"
				href="<c:url value='/adopt/register'>
	     		   <c:param name='animal_id' value='${animal.animal_id}'/>
			  </c:url>">입양신청</a>
		</div>
	</form>
</div>

<%@ include file="/WEB-INF/home/mainFooter.jsp"%>