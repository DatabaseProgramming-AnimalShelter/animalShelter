<!-- 유기동물 등록 화면 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>유기동물 등록 화면 - 관리자</title>
    <style>
        body {
            text-align: center;
        }
    </style>

</head>
<body>
    <h2>유기동물 등록 - 관리자</h2>
    <br>
    <form method="post" action=".jsp">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script>
            $( document ).ready(function(){
               
                //테스트용 데이터
                var sel1 = {
                    " ":"종 선택",
                    "D":"강아지",
                    "C":"고양이"
                };
                
                //sel1이 강아지일경우
                var sel2_1 = {
                    " ":"강아지",
                    "D01": "믹스견",
                    "D02": "진돗개",
                    "D03": "풍산개",
                    "D04": "푸들",
                    "D05": "비숑",
                    "D06": "골든리트리버",
                    "D07": "포메라니안",
                    "D08": "말티즈",
                    "D09": "치와와",
                    "D10": "요크셔테리어"
                };
                
                //sel1이 고양이일경우
                var sel2_2 = {
                    " ":"고양이",
                    "C01": "러시안블루",
                    "C02": "먼치킨",
                    "C03": "샴",
                    "C04": "터키시앙고라",
                    "C05": "벵갈",
                    "C06": "스핑크스",
                    "C07": "코리안숏헤어",
                    "C08": "하바나브라운",
                    "C09": "아비시니안",
                    "C10": "스코티시폴드" 
                };
                
               //sel1에 서버에서 받아온 값을 넣기위해..
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
               
               $("select[id='sel1']").on("change", function(){
                    var option = $("#sel1 option:selected").val();
                    var subSelName = '';
                    if(option == "D") {
                        subSelName = "sel2_1";
                    } else if(option == "C"){
                        subSelName = "sel2_2";
                    } else{
                        $("#sel2").hide();
                        return;
                    }
                    $("#sel2").show();
                    retOption(eval(subSelName), "sel2");
                })
               retOption(sel1, "sel1");
            });
            
            </script>

        <!-- 종: type (개, 고양이, 기타) -->
        종:
        <select name="sel1" id="sel1">
        </select>
        <br><br>
        
        <!-- 품종: species (포메라니안, 요크셔테리어, 치와와) -->
      품종:
        <select name="sel2" id="sel2" style="">
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
        <input type="text" name="age" placeholder="나이 입력" />
        <br><br>
        
        <!-- 몸무게: weight -->
        몸무게:
        <input type="text" name="weight" placeholder="몸무게 입력" />
        <br><br>
        
        <!-- 공고번호: notice_number -->
        
        <!-- 발견장소: location -->
        발견장소:
        <input type="text" name="location" placeholder="발견장소 입력" />
        <br><br>
        
        <!-- 특이사항: etc -->
        특이사항:
        <textarea id="etc" name="etc" rows=10" cols="30" placeholder="특이사항을 입력하시오."></textarea>
        <br><br>
        
        <!-- 사진 -->
        <input type="file" id="image" name="image" accept="image/png, image/jpeg">
         <br><br>
        
        <input type="submit" value="등록" />
    </form>
</body>
</html>

 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>