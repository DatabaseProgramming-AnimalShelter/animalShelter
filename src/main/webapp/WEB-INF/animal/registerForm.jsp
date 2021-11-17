<!-- 유기동물 등록 화면 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>

<head>
    <title>유기동물 등록 화면 - 관리자</title>
    <style>
        body {
            text-align: center;
        }
    </style>
    <script>
	function register() {
		if (form.userId.value == "") {
			alert("사용자 ID를 입력하십시오.");
			form.userId.focus();
			return false;
		} 
		if (form.password.value == "") {
			alert("비밀번호를 입력하십시오.");
			form.password.focus();
			return false;
		}		
		form.submit();
	}
	
</script>
</head>
<body>
    <h2>유기동물 등록 - 관리자</h2>
    <br>
    <form class="applyForm"name="form" method="POST" action="<c:url value='/animal/register'/>" enctype="multipart/form-data">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script>
            $( document ).ready(function(){
                //테스트용 데이터
                var animal_type = {
                    " ":"과 선택",
                    "개":"개",
                    "고양이":"고양이"
                };
                //animal_type이 강아지일경우
                var species_1 = {
                    101: "믹스견",
                    102: "기타",
                    103: "진돗개",
                    104: "풍산개",
                    105: "치와와",
                    106: "요크셔테리어",
                    107: "비숑",
                    108: "푸들",
                    109: "말티즈",
                    110: "골든리트리버"
                };
                //animal_type이 고양이일경우
                var species_2 = {
                    201:"믹스",
                    202: "기타",
                    203: "코리안숏헤어",
                    204: "샴",
                    205: "벵갈",
                    206: "먼치킨",
                    207: "스핑크스"
                };
               //animal_type에 서버에서 받아온 값을 넣기위해..
               // map배열과 select 태그 id를 넘겨주면 option 태그를 붙여줌.
               // map[키이름] = 그 키에 해당하는 value를 반환한다.
               //retOption(데이터맵, select함수 id)
               function retOption(mapArr, select){
                    var html = '';
                    var keys = Object.keys(mapArr);
                    for (var i in keys) {
                        html += "<option value=" + "'" + keys[i] + "'>" + mapArr[keys[i]] + "</option>";
                    }
                    $("select[id='" + select +"']").html(html);
               }
               $("select[id='animal_type']").on("change", function(){
                    var option = $("#animal_type option:selected").val();
                    var subSelName = '';
                    if(option == "개") {
                        subSelName = "species_1";
                    } else if(option == "고양이"){
                        subSelName = "species_2";
                    } else{
                        $("#species").hide();
                        return;
                    }
                    $("#species").show();
                    retOption(eval(subSelName), "species");
                })
               retOption(animal_type, "animal_type");
            });
            </script>
        <!-- 과: type (개, 고양이, 기타) -->
        과:
        <select name="animal_type" id="animal_type">
        </select>
        <br><br>
        <!-- 종: species (포메라니안, 요크셔테리어, 치와와) -->
      종:
        <select name="species" id="species" style="">
        </select>
        <br><br>
        <!-- 성별: gender  -->
        성별:
        <select name="gender" id="gender" >
        	<option value="female" selected>암컷</option>
        	<option value="male">수컷</option>
        </select>
        <br><br>
        <!-- 나이: age -->
        나이:
         <select name="age" >
		    <option value="0" > 1살미만 </option>
		    <option value="1"> 1살 </option>
		    <option value="2" > 2살 </option>
		    <option value="3"> 3살 </option>
		    <option value="4" > 4살</option>
		    <option value="5"> 5살 </option>
		    <option value="6" > 6살 </option>
		    <option value="7"> 7살 </option>
		    <option value="8" > 8살 </option>
		    <option value="9"> 9살 </option>
		    <option value="10" > 10살 </option>
		    <option value="11"> 11살 </option>
		    <option value="12" > 12살 </option>
		    <option value="13"> 13살 </option>
		    <option value="14" > 14살 </option>
		    <option value="15"> 15살 </option>
		    <option value="16" > 16살 </option>
		    <option value="17"> 17살 </option>
		    <option value="18" > 18살 </option>
		    <option value="19"> 19살 </option>
	  	</select> <br><br>
        <!-- 몸무게: weight -->
        몸무게:
        <select name="weight" >
		    <option value="0" > ~2kg </option>
		    <option value="1"> 2kg~3kg </option>
		    <option value="2" > 3kg~4kg  </option>
		    <option value="3" > 4kg~5kg</option>
		    <option value="4"> 5kg~6kg </option>
		    <option value="5" > 7kg~10kg </option>
		    <option value="6"> 11kg~15kg </option>
		    <option value="7" > 15kg~20kg  </option>
		    <option value="8"> 20kg~ </option>
	  	</select>
        <br><br>
        <!-- 공고번호: notice_number -->
        <!-- 발견장소: location -->
        발견장소:
         <select name="location" >
		    <option value="seoul" > 서울 </option>
		    <option value="gyeonggi"> 경기 </option>
		    <option value="incheon" > 인천 </option>

		    <option value="etc"> 기타 </option>

	  	</select>
        <br><br>
        <!-- 특이사항: etc -->
        특이사항:
        <textarea id="etc" name="etc" rows=10" cols="30" placeholder="특이사항을 입력하시오."></textarea>
        <br><br>

         <input type="file" id="image" name="image">
         <br><br>
        <input type="submit" value="등록" onClick="register()"/>
    </form>
</body>
</html>
 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>