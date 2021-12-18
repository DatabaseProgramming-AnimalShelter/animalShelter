<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp"%>
<title>유기동물 목록 조회</title>
<script type="text/javascript">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js">
</script>
<style>
.card {
	float: left;
	margin: 30px;
	margin-left: 20px;
	color: rgb(54, 27, 27);
	padding: auto;
	text-align: center;
}

.card-title {
	font-size: 20px;
}
</style>
<script>
	$(document).ready(
			function() {

				//테스트용 데이터
				var type = {
					"none" : "과 선택",
					"dog" : "개",
					"cat" : "고양이"
				};

				var species_1 = {
					100 : "강아지전체",
					101 : "믹스견",
					102 : "기타",
					103 : "진돗개",
					104 : "풍산개",
					105 : "치와와",
					106 : "요크셔테리어",
					107 : "비숑",
					108 : "푸들",
					109 : "말티즈",
					110 : "골든리트리버",
					111 : "포메라니안",
					112 : "웰시코기"
				};
				var species_2 = {
					200 : "고양이전체",
					201 : "믹스",
					202 : "기타",
					203 : "코리안숏헤어",
					204 : "샴",
					205 : "뱅갈",
					206 : "먼치킨",
					207 : "스핑크스"
				};

				function retOption(mapArr, select) {
					var html = '';
					var keys = Object.keys(mapArr);
					for ( var i in keys) {
						html += "<option value=" + "'" + keys[i] + "'>"
								+ mapArr[keys[i]] + "</option>";
					}

					$("select[id='" + select + "']").html(html);
				}

				$("select[id='type']").on("change", function() {
					var option = $("#type option:selected").val();
					var subSelName = '';
					if (option == "dog") {
						subSelName = "species_1";
					} else if (option == "cat") {
						subSelName = "species_2";
					} else {
						$("#species").hide();
						return;
					}
					$("#species").show();
					retOption(eval(subSelName), "species");
				})
				retOption(type, "type");
			});
</script>
<div class="container">
	<form method="POST" name="form" action="<c:url value='/animal/list' />">
		<br>
		<table class="table" style="background-color: #e0d8cb">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<th scope="row">과</th>
				<td><select name="type" id="type">
				</select></td>
				<th scope="row">종</th>
				<td><select name="species" id="species" style="">
				</select></td>
				<th scope="row">입양유무</th>
				<td><select name="matched">
						<option value=-1 selected>전체</option>
						<option value=0>보호중</option>
						<option value=1>입양완료</option>
				</select></td>
				<th scope="row">발견장소</th>
				<td><select name="location">
						<option value="all">전체</option>
						<option value="seoul">서울</option>
						<option value="gyeonggi">경기</option>
						<option value="incheon">인천</option>
						<option value="etc">기타</option>
				</select></td>
				<td><input type="submit" value="검색"></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<!--   <input type="button" value="검색" onClick="search()">-->


	</form>
	<div class="list">
		<c:choose>
			<c:when test="${ispost==1}">
				<h2>
					"과 :
					<c:choose>
						<c:when test="${type=='none'}">
							<span>전체</span>
						</c:when>
						<c:when test="${type=='dog'}">
							<span>개</span>
						</c:when>
						<c:when test="${type=='cat'}">
							<span>고양이</span>
						</c:when>
					</c:choose>
					/종 :
					<c:choose>
						<c:when test="${category_id==0}">
							<span>전체</span>
						</c:when>
						<c:when test="${category_id==100}">
							<span>강아지 전체</span>
						</c:when>
						<c:when test="${category_id==101}">
							<span>믹스견</span>
						</c:when>
						<c:when test="${category_id==102}">
							<span>기타</span>
						</c:when>
						<c:when test="${category_id==103}">
							<span>진돗개</span>
						</c:when>
						<c:when test="${category_id==104}">
							<span>풍산개</span>
						</c:when>
						<c:when test="${category_id==105}">
							<span>치와와</span>
						</c:when>
						<c:when test="${category_id==106}">
							<span>요크셔테리어</span>
						</c:when>
						<c:when test="${category_id==107}">
							<span>비숑</span>
						</c:when>
						<c:when test="${category_id==108}">
							<span>푸들</span>
						</c:when>
						<c:when test="${category_id==109}">
							<span>말티즈</span>
						</c:when>
						<c:when test="${category_id==110}">
							<span>골든리트리버</span>
						</c:when>
						<c:when test="${category_id==111}">
							<span>포메라니안</span>
						</c:when>
						<c:when test="${category_id==112}">
							<span>웰시코기</span>
						</c:when>
						<c:when test="${category_id==200}">
							<span>고양이 전체</span>
						</c:when>
						<c:when test="${category_id==201}">
							<span>믹스</span>
						</c:when>
						<c:when test="${category_id==202}">
							<span>기타</span>
						</c:when>
						<c:when test="${category_id==203}">
							<span>코리안숏헤어</span>
						</c:when>
						<c:when test="${category_id==204}">
							<span>샴</span>
						</c:when>
						<c:when test="${category_id==205}">
							<span>뱅갈</span>
						</c:when>
						<c:when test="${category_id==206}">
							<span>먼치킨</span>
						</c:when>
						<c:when test="${category_id==207}">
							<span>스핑크스</span>
						</c:when>
					</c:choose>
					/입양유무 :
					<c:choose>

						<c:when test="${matched==-1}">
							<span>전체</span>
						</c:when>
						<c:when test="${matched==0}">
							<span>보호중</span>
						</c:when>
						<c:when test="${matched==1}">
							<span>입양완료</span>
						</c:when>
					</c:choose>
					/지역 :
					<c:choose>
						<c:when test="${location=='all'}">
							<span>전체</span>
						</c:when>
						<c:when test="${location=='seoul'}">
							<span>서울</span>
						</c:when>
						<c:when test="${location=='gyeonggi'}">
							<span>경기</span>
						</c:when>
						<c:when test="${location=='incheon'}">
							<span>인천</span>
						</c:when>
						<c:when test="${location=='etc'}">
							<span>기타</span>
						</c:when>
					</c:choose>
					"에 대한 검색결과
				</h2>
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="${empty animalList}">
							결과없음</c:when>
		</c:choose>

		<c:forEach var="animal" items="${animalList}">
			<span class="card" style="width: 18rem;"> <a
				href="<c:url value='/animal/view'>
	                     <c:param name='animal_id' value='${animal.animal_id}'/>
	                    </c:url>">
					<c:choose>
						<c:when test="${not empty animal.image}">
							<img
								src="${pageContext.request.session.servletContext.contextPath}/upload/${animal.image}"
								class="card-img-top" height="270px" />
						</c:when>
						<c:otherwise>
							<span>사진없음</span>
						</c:otherwise>
					</c:choose> <span class="card-title"> 동물아이디 : ${animal.animal_id} </span> <br>
					동물나이 : <c:choose>
						<c:when test="${animal.age==0}">
							<span>1살미만</span>
						</c:when>
						<c:otherwise>
							<span>${animal.age}</span>
						</c:otherwise>
					</c:choose> <br> 장소 : <c:choose>

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
					</c:choose> <br> 성별 : <c:choose>
						<c:when test="${animal.gender=='female'}">
							<span>암컷</span>
						</c:when>
						<c:otherwise>
							<span>수컷</span>
						</c:otherwise>
					</c:choose>
			</a>
			</span>
		</c:forEach>
	</div>
</div>
<%@ include file="/WEB-INF/home/mainFooter.jsp"%>