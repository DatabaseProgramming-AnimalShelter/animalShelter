<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>유기동물 목록 조회</title>
<script type="text/javascript">

function search(targetUri){
	form.action = targetUri;
	form.submit();
}

function setValue(val){ // 선택에 따른 파라미터 값 setting
	if(val.equals('all'))
		request.setAttribute("all", val);
	else if(val.equals('dog'))
		request.setAttribute("dog", val);
	else
		request.setAttribute("cat", val);
	
	search('<c:url value='/animal/search' />')
}
</script>
<style type="text/css">
 * {
		margin: 0;
		padding: 0;
	} 
/* ul li태그에 리스트 스타일을 없앰 */
	ul li{
		list-style: none;
	}
/* a태그에 텍스트 밑줄을 없애고 색상을 #333 */
	a {
		text-decoration: none;
		color: black;
	}
/* 글자크기를 16px 맑은 고딕 굵게하고 width넓이 700, 높이 50만큼 배경색은 #ccc, 글자색은 검정색, 라인높이50px
menu박스 가운데정렬, 글자가운데 정렬 */
	#menu {
		font:bold 16px "malgun gothic";
		width:300px;
		height:50px;
		background: #FEC8E3;
		color:black;
		line-height: 50px; 
		margin:0 auto;
		text-align: center;
	}

/* menu태그 자식의 ul의 자식 li를 왼쪽정렬과 넓이 140설정 */
	#menu > ul > li {
		
		position:relative;
	}
#menu > ul > li > ul {
		width:130px;
		display:none;
		position: absolute;
		font-size:14px;
		background: #FEE5E3;
	}
#menu > ul > li:hover > ul {
		display:block;
	}
#menu > ul > li > ul > li:hover {
		background: #ccc;
		transition: ease 1s;
		}	

.selection{
	margin-top: 50px;
}

.item{
	width:60%;
	height:150px;
	margin:0 auto;
	padding:10px;
	display:flex;
	margin-top: 20px;
	border: 1px solid;
	border-radius: 1rem;
}

.item > .img{
	text-align:center;
}

.imgParent{
	text-align:center;
	border: 1px solid;
	width:30%;
	line-height:100%;
	margin-left:10px;
	vertical-align:middle;
}

.info{
	padding: 20px;
  width:70%;
 
  float: right;
  margin:auto;
}
</style>
</head>
<body>
<div id="menu">
	<ul>
		<li><a href="#">품종 (검색하실 품종을 선택해주세요)</a>
			<ul>
				<li onClick="setValue('all')">전체</li>
				<li onClick="setValue('dog')">개</li>
				<li onClick="setValue('cat')">고양이</li>
			</ul>
		
		</li>
	</ul>
</div>

<!-- Section-->
	<section class="selection">
		<!-- search 해서 받아온 animal 리스트 만큼 반복 -->
       <c:forEach var="animal" items="${animalList}">
			<div class="item" onClick="<c:url value='/animal/view'><c:param name='animal_id' value='${animal.animal_id}'/> </c:url>">        
				<div class="imgParent">
					<img class="img" src="'${animal.image}'" />
				</div>
				<div class="info">
					<h3 class="fw-bolder">1. 품종 : ${animal.animal_type} > ${animal.species}</h5><br>
					2. 성별 : ${animal.gender}<br><br>
					3. 발견장소 : ${animal.location}<br><br> 
				</div>
			</div>
		</c:forEach>
	</section> 
</body>
</html>