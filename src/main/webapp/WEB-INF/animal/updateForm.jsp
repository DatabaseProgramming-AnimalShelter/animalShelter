<!-- 유기동물 등록 화면 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp"%>

<!DOCTYPE html>
<html>
<style>
.container {
	border: 1px solid;
	background-image: url("<c:url value='/images/grass.png'/>");
	padding-bottom: 20px;
}

.applyForm {
	margin: auto;
	width: 500px;
	background-color: #e0d8cb;
	padding: 30px;
	margin-bottom: 50px;
	border: 5px dashed white;
}

.title {
	text-align: center;
}
</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script>
	$(document).ready(
			function() {
				//테스트용 데이터
				var animal_type = {
					" " : "과 선택",
					"개" : "개",
					"고양이" : "고양이"
				};
				//animal_type이 강아지일경우
				var species_1 = {
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
				//animal_type이 고양이일경우
				var species_2 = {
					201 : "믹스",
					202 : "기타",
					203 : "코리안숏헤어",
					204 : "샴",
					205 : "벵갈",
					206 : "먼치킨",
					207 : "스핑크스"
				};
				//animal_type에 서버에서 받아온 값을 넣기위해..
				// map배열과 select 태그 id를 넘겨주면 option 태그를 붙여줌.
				// map[키이름] = 그 키에 해당하는 value를 반환한다.
				//retOption(데이터맵, select함수 id)
				function retOption(mapArr, select) {
					var html = '';
					var keys = Object.keys(mapArr);
					for ( var i in keys) {
						html += "<option value=" + "'" + keys[i] + "'>"
								+ mapArr[keys[i]] + "</option>";
					}
					$("select[id='" + select + "']").html(html);
				}
				$("select[id='animal_type']").on("change", function() {
					var option = $("#animal_type option:selected").val();
					var subSelName = '';
					if (option == "개") {
						subSelName = "species_1";
					} else if (option == "고양이") {
						subSelName = "species_2";
					} else {
						$("#species").hide();
						return;
					}
					$("#species").show();
					retOption(eval(subSelName), "species");
				})
				retOption(animal_type, "animal_type");
			});
</script>
<title>유기동물 수정 화면 - 관리자</title>

<script>
	function update() {
		if (form.species.value == "") {
			alert("과와 종을 선택하십시오.");
			form.species.focus();
			return false;
		} else if (form.etc.value == "") {
			alert("특이사항을 입력하십시오.");
			form.etc.focus();
			return false;
		}
		form.submit();
	}
</script>
<body>
	<div class="container">
		<br>
		<h2 class="title">유기동물 등록 - 관리자</h2>
		<br>
		<form class="applyForm" name="form" method="POST"
			action="<c:url value='/animal/update'>
                        <c:param name='animal_id' value='${animal.animal_id}'/>
                     </c:url>">
                     <input type="hidden" name="image" value="${animal.image}"/>	  	  
			<!-- 과: type (개, 고양이, 기타) -->
			<div class="form-group row">
				<label for="animal_type" class="col-lg-3 col-form-label">과</label>
				<div class="col-lg-8">
					<select name="animal_type" id="animal_type">
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="species" class="col-lg-3 col-form-label">종</label>
				<div class="col-lg-8">
					<select name="species" id="species" style="">
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="gender" class="col-lg-3 col-form-label">성별</label>
				<div class="col-lg-8">
					<select name="gender">
						<option value="female" selected>암컷</option>
						<option value="male">수컷</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="age" class="col-lg-3 col-form-label">나이</label>
				<div class="col-lg-8">
					<select name="age">
						<option value="0">1살미만</option>
						<option value="1">1살</option>
						<option value="2">2살</option>
						<option value="3">3살</option>
						<option value="4">4살</option>
						<option value="5">5살</option>
						<option value="6">6살</option>
						<option value="7">7살</option>
						<option value="8">8살</option>
						<option value="9">9살</option>
						<option value="10">10살</option>
						<option value="11">11살</option>
						<option value="12">12살</option>
						<option value="13">13살</option>
						<option value="14">14살</option>
						<option value="15">15살</option>
						<option value="16">16살</option>
						<option value="17">17살</option>
						<option value="18">18살</option>
						<option value="19">19살</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="weight" class="col-lg-3 col-form-label"> 몸무게</label>
				<div class="col-lg-8">
					<select name="weight">
						<option value="0">~2kg</option>
						<option value="1">2kg~3kg</option>
						<option value="2">3kg~4kg</option>
						<option value="3">4kg~5kg</option>
						<option value="4">5kg~6kg</option>
						<option value="5">7kg~10kg</option>
						<option value="6">11kg~15kg</option>
						<option value="7">15kg~20kg</option>
						<option value="8">20kg~</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="location" class="col-lg-3 col-form-label">발견장소</label>
				<div class="col-lg-8">
					<select name="location">
						<option value="seoul">서울</option>
						<option value="gyeonggi">경기</option>
						<option value="incheon">인천</option>
						<option value="etc">기타</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="etc" class="col-lg-3 col-form-label">특이사항</label>
				<div class="col-lg-8">
					<textarea name="etc" rows="10" cols="30">${animal.etc}</textarea>
				</div>
			</div>
			<div class="form-group row">
				<div id="imgParent">
					<c:choose>
						<c:when test="${not empty animal.image}">
							<img
								src="${pageContext.request.session.servletContext.contextPath}/upload/${animal.image}"
								height="400px" width="400px" />
						</c:when>
						<c:otherwise>
							<span>사진없음</span>
						</c:otherwise>
					</c:choose>
					<br>
				</div>
			</div>
			<div class="form-group">
				<input type="button" value="수정" onClick="update()"
					style="background-color: white" class=" w-100 btn">
				<a href="<c:url value='/animal/list' />" class="btn btn-link">동물 목록 </a>    		  
			</div>
		</form>
	</div>
</body>
<%@ include file="/WEB-INF/home/mainFooter.jsp"%>