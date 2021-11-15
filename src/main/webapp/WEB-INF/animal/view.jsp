<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
        <link href="/css/styles.css" rel="stylesheet" />
<script type="text/javascript">

function apply(targetUri){
	request.setAttribute("animal", $(animal));
	
	form.action = targetUri;
	form.submit();
}
</script>
<style type="text/css">
.selection{
	margin-top: 50px;
}

.item{
	width:80%;
	height:80%;
	margin:0 auto;
	padding:10px;
	display:flex;
	margin-top: 20px;
	border: 1px solid;
	border-radius: 1rem;
}

#imgParent{
	text-align:center;
	border: 1px solid;
	width:30%;
	line-height:100%;
	margin-left:10px;
	vertical-align:middle;
}

.info{
	padding-left: 30px;
	padding-right: 10px;
	padding-bottom: 10px;
 	width:70%;
	float: right;
	margin:auto;
}

.btn{
	margin:0 auto;
	padding:10px;
	display:flex;
	margin-top: 20px;
	width: 100px;
	color:black;
	height: 40px; 
	text-align: center;
}

.apply{
	background-color: #FEC8E3;

}

h1{
	text-align:center;
}

</style>
<div class="container">
<h1>유기동물 상세보기</h1>
사진 저장 경로 : ${dir} <br/>
사진 파일 이름 : ${filename} <br/>
<img src="${dir}/${filename}" /> <br/>
	<form name="form" method="POST">
		<div class="outer">
			<div class="item">
				<div id="imgParent">
					<img class="img" src="${dir}/${animal.image}" />
				</div>
				<div class="info">
					<h2 class="fw-bolder">[${animal.animal_type}] >
						${animal.species}</h2>
					<div class="fs-5 mb-5">
						<span>발견장소</span>
						<c:choose>

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
						</c:choose>
						<br>
						<br> <span>나이</span>
						<c:choose>
							<c:when test="${animal.age==0}">
								<span>1살미만</span>
							</c:when>
							<c:otherwise>
								<span>${animal.age}</span>
							</c:otherwise>
						</c:choose>
						<br>
						<br> <span>성별</span>
						<c:choose>
							<c:when test="${animal.gender==female}">
								<span>암</span>
							</c:when>
							<c:otherwise>
								<span>수</span>
							</c:otherwise>
						</c:choose>
						<br>
						<br> <span>몸무게</span>
						<c:choose>
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
						</c:choose>
						<br>
						<br> <span>특이사항: ${animal.etc}</span>
					</div>
				</div>
			</div>

			<div class="btn">
				<button id="apply" type="button"
					onClick="apply('<c:url value='/adopt/apply' />")'>입양신청</button>
			</div>
		</div>
	</form>
</div>
 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>