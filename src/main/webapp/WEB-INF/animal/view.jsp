<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>유기동물 상세정보 보기</title>
        <!-- Core theme CSS (includes Bootstrap)-->
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
<h1>��湲곕��臾� ���몃낫湲�</h1>
�ъ� ���� 寃쎈� : ${dir} <br/>
�ъ� ���� �대� : ${filename} <br/>
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
						<span>諛�寃ъ�μ��</span>
						<c:choose>

							<c:when test="${animal.location=='seoul'}">
								<span>����</span>
							</c:when>
							<c:when test="${animal.location=='gyeonggi'}">
								<span>寃쎄린</span>
							</c:when>
							<c:when test="${animal.location=='incheon'}">
								<span>�몄�</span>
							</c:when>
							<c:when test="${animal.location=='etc'}">
								<span>湲고��</span>
							</c:when>
						</c:choose>
						<br>
						<br> <span>����</span>
						<c:choose>
							<c:when test="${animal.age==0}">
								<span>1�대�몃�</span>
							</c:when>
							<c:otherwise>
								<span>${animal.age}</span>
							</c:otherwise>
						</c:choose>
						<br>
						<br> <span>�깅�</span>
						<c:choose>
							<c:when test="${animal.gender==female}">
								<span>��</span>
							</c:when>
							<c:otherwise>
								<span>��</span>
							</c:otherwise>
						</c:choose>
						<br>
						<br> <span>紐몃Т寃�</span>
						<c:choose>
							<c:when test="${animal.weight==0}">
								<span>2kg誘몃�</span>
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
						<br> <span>�뱀�댁�ы��: ${animal.etc}</span>
					</div>
				</div>

 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>

