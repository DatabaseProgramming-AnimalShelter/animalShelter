<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>유기동물 목록 조회</title>
<script type="text/javascript">

<!-- 검색 버튼 누르면 파라미터 settings -->
function search(){
	String type = $("select[name='type']").val();
	Integer matched = Integer.parseInt($("select[name='matched']").val());
	Integer species = 0;
	
	if(type.equals("dog")){
		species = Integer.parseInt($("select[name='dogSpecies']").val());
	}
	else if(type.equals("dog")){
		species = Integer.parseInt($("select[name='catSpecies']").val());
	}
	
	request.setAttribute("type", type);
	request.setAttribute("species", species);
	request.setAttribute("matched", matched);
}	

function type(){
	String species = document.getElementById("type").options[langSelect.selectedIndex].value;
	if (species == "dog") {
		$('#dogSpecies').show();
	} else {	
		$('#catSpecies').show();
	}
		 
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
		width:500px;
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

/*#dogSpecies, #catSpecies{
	display:none;
}
*/
</style>
</head>
<body>
<form name="form" action="<c:url value='/animal/search' />">
	<div id="menu">
		<span>과</span>
		<select id="type" name="type" onchange="type()">
			<option value="typeAll" selected>전체</option>
			<option value="dog">개</option>
			<option value="cat">고양이</option>		
		</select>
		<div id="dogSpecies">
			<span>품종</span>
			<select name="dogSpecies">
				<option value="dogSpeciesAll" selected>전체</option>
				
				<!-- db에 넣으면 반복문으로 쓰기 -->
				<%-- <c:forEach var="animal" items="${searchAnimalList}">
					<option value="${animal.category_id}">${animal.category_id}</option>
				</c:forEach> --%>
			
				<option value="02">진돗개</option>
				<option value="03">풍산개</option>
				<option value="04">치와와</option>
				<option value="05">요크셔테리어</option>
				<option value="06">비숑</option>
				<option value="07">푸들</option>
				<option value="08">말티즈</option>
				<option value="09">골든리트리버</option>
			</select>
		</div>
		
		<div id="catSpecies">
			<span>품종</span>
			<select name="catSpecies">
				<option value="catSpeciesAll" selected>전체</option>
				
				<!-- db에 넣으면 반복문으로 쓰기 -->
				<%-- <c:forEach var="animal" items="${searchAnimalList}">
					<option value="${animal.category_id}">${animal.category_id}</option>
				</c:forEach> --%>
				
				<option value="10">샴</option>
				<option value="11">뱅갈</option>
				<option value="12">코리안숏헤어</option>
				<option value="13">스핑크스</option>
			</select>
		</div>
		
		<span>입양유무</span>
		<select name="matched">
			<option value="matchedAll" selected>전체</option>
			<option value="notMatched">보호중</option>
			<option value="matched">입양완료</option>
		</select>
	<input type="button" value="검색" onClick="search()">
	</div>
</form>


<!-- Section-->
	<section class="selection">
		<!-- search 해서 받아온 animal 리스트 만큼 반복 -->
       <c:forEach var="animal" items="${searchAnimalList}">
			<div class="item" onClick="<c:url value='/animal/view'><c:param name='animal_id' value='${animal.animal_id}'/> </c:url>">        
				<div class="imgParent">
					<img class="img" src="'${animal.image}'" />
				</div>
				<div class="info">
					<h3 class="fw-bolder">${animal.animal_type} > ${animal.species}</h5><br>
					1. 성별 : ${animal.gender}<br><br>
					2. 발견장소 : ${animal.location}<br><br> 
				</div>
			</div>
		</c:forEach>
	</section> 
</body>
</html>