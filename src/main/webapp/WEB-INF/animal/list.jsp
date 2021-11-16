<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<title>유기동물 목록 조회</title>
<script type="text/javascript">
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script>
    $( document ).ready(function(){
       
        //테스트용 데이터
        var type = {
            "none":"종 선택",
            "dog":"개",
            "cat":"고양이"
        };
        
        //type이 강아지일경우
        var species_1 = {
            " ":"개",
            1: "믹스견",
            2: "진돗개",
            3: "풍산개",
            4: "치와와",
            5: "요크셔테리어",
            6: "비숑",
            7: "푸들",
            8: "말티즈",
            9: "골든리트리버"
        };
        
        //type이 고양이일경우
        var species_2 = {
            " ":"고양이",
            10: "러시안블루",
            11: "먼치킨",
            12: "샴",
            13: "벵갈",
            14: "코리안숏헤어",
            15: "스핑크스"
        };
       //type에 서버에서 받아온 값을 넣기위해..
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
       
       $("select[id='type']").on("change", function(){
            var option = $("#type option:selected").val();
            var subSelName = '';
            if(option == "dog") {
                subSelName = "species_1";
            } else if(option == "cat"){
                subSelName = "species_2";
            } else{
                $("#species").hide();
                return;
            }
            $("#species").show();
            retOption(eval(subSelName), "species");
        })
       retOption(type, "type");
    });
    
    </script>
    <script>
   
}
   
</script>

<form method="POST" name="form" action="<c:url value='/animal/list' />">
   <div id="menu">
     과:
        <select name="type" id="type">
        </select>
        <br><br>
        
        <!-- 종: species (포메라니안, 요크셔테리어, 치와와) -->
      종:
        <select name="species" id="species" style="">
        </select>
        <br><br>
      
    
      <span>입양유무</span>
      <select name="matched">
         <option value=0 selected>전체</option>
         <option value=1>보호중</option>
         <option value=2>입양완료</option>
      </select>
     <!--   <span>발견장소</span>
         <select name="location" >
		    <option value="seoul" > 서울 </option>
		    <option value="gyeonggi"> 경기 </option>
		    <option value="incheon" > 인천 </option>
		    <option value="etc"> 기타 </option>
	  	</select>-->
  <!--   <input type="button" value="검색" onClick="search()">-->
  <input type="submit" value="검색"  >
   </div>
</form>


<!-- Section
   <section class="selection">
      <!-- search 해서 받아온 animal 리스트 만큼 반복 -->
       <c:forEach var="animal" items="${searchAnimalList}">
         <a class="item" href="<c:url value='/animal/view'><c:param name='animal_id' value=''/></c:url>" class="nav-link link-dark px-2">${animal.animal_id}</a>       
            <div class="imgParent">
               <img class="img" src="'${animal.image}'" />
            </div>
            <div class="info">
               <h3 class="fw-bolder">  ${animal.species}</h5><br>
               1. 성별 : ${animal.gender}<br><br>
               2. 발견장소 : ${animal.location}<br><br> 
            </div>
         </div>
      </c:forEach>
   </section> 
-->
   <table class="table table-bordered">
      <thead class="thead-inverse">
         <tr>
        <td>동물 나이</td>
        <td>동물 아이디</td>
        <td>장소</td>
      </tr>
      </thead>
      <tbody> 
      <c:forEach var="animal" items="${animalList}">                
            <tr>
           <td>
              ${animal.age}     
           </td>
           <td>
           
            <a href="<c:url value='/animal/view'>
                     <c:param name='animal_id' value='${animal.animal_id}'/>
                    </c:url>">
              ${animal.animal_id}</a>    
           </td>
           <td>
             ${animal.location} 
           </td>
         </tr>
       </c:forEach> 
     </tbody>
   </table>   

<%@ include file="/WEB-INF/home/mainFooter.jsp" %>