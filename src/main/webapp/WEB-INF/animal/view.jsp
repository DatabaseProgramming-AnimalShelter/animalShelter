<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>���⵿�� ������ ����</title>
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/css/styles.css" rel="stylesheet" />
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
</head>
<body>
<h1>���⵿�� �󼼺���</h1>
<div class="outer">
	<div class="item" onClick="<c:url value='/animal/view'><c:param name='animal_id' value='${animal.animal_id}'/> </c:url>">        
		<div id="imgParent">
			<img class="img" src="'${animal.image}'" />
		</div>
		<div class="info">
			<h2 class="fw-bolder">[${animal.animal_type}] > ${animal.species}</h2>
			<div class="fs-5 mb-5">
				<span>�߰����: ${animal.location}</span><br><br>
	            <span>��������: ${animal.age}</span><br><br>
	            <span>����: ${animal.gender}</span><br><br>
	            <span>������: ${animal.weight}</span><br><br>
             	<span>Ư�̻���: ${animal.etc}</span>
            </div>
			</div>
	</div>

	<div class="btn"> 
		<button id="apply" type="button"
					onClick="<c:url value='/adopt/apply'><c:param name='animal_id' value='${animal.animal_id}'/> </c:url>">
				�Ծ��û
		</button>
	</div>
</div>
</body>
</html>