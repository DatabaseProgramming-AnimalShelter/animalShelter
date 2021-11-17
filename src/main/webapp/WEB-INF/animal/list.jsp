<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<title>유기동물 목록 조회</title>
<script type="text/javascript">
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<style>
	.card{ 
	float : left;
	margin:30px;
	margin-left:20px;
	color: rgb(54, 27, 27);
	padding:auto;
	text-align:center;
	}
	a{
            font-weight: 900;
            color: rgb(54, 27, 27);
            text-decoration: none;
        }
        a:hover{
            color: rgb(54, 27, 27);
            text-decoration: none;
        }
        a:visited{
            color: rgb(54, 27, 27);
        }
	.card-title{
	font-size:20px;
	}
</style>
<script>
    $( document ).ready(function(){
       
        //테스트용 데이터
        var type = {
            "none":"과 선택",
            "dog":"개",
            "cat":"고양이"
        };
        
        //animal_type이 강아지일경우
        var species_1 = {
            100:"강아지전체",
            101: "믹스견",
            102: "진돗개",
            103: "풍산개",
            104: "치와와",
            105: "요크셔테리어",
            106: "비숑",
            107: "푸들",
            108: "말티즈",
            109: "골든리트리버"
        };
        //animal_type이 고양이일경우
        var species_2 = {
            200:"고양이전체",
            201: "믹스",
            202: "코리안숏헤어",
            203: "샴",
            204: "벵갈",
            205: "먼치킨",
            206: "스핑크스"
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
<div class="container">  
<form method="POST" name="form" action="<c:url value='/animal/list' />">
   <div id="menu">
     과:
        <select name="type" id="type">
        </select>
        
        <!-- 종: species (포메라니안, 요크셔테리어, 치와와) -->
      종:
        <select name="species" id="species" style="">
        </select>
      
    
      <span>입양유무</span>
      <select name="matched">
         <option value=-1 selected>전체</option>
         <option value=0>보호중</option>
         <option value=1>입양완료</option>
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
 <c:forEach var="animal" items="${animalList}">                
  <span class="card" style="width: 18rem;">
  	<a href="<c:url value='/animal/view'>
                     <c:param name='animal_id' value='${animal.animal_id}'/>
                    </c:url>">	<c:choose>
						<c:when test="${not empty animal.image}">
							<img
								src="${pageContext.request.session.servletContext.contextPath}/upload/${animal.image}"  class="card-img-top" height="200px"/>
						</c:when>
						<c:otherwise>
							<span>사진없음</span>
						</c:otherwise>
					</c:choose>
       
          <span class="card-title">   
             동물아이디 :  ${animal.animal_id}    </span>
       <!--   <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> --> 
  
          <br>
          동물나이 : 
<c:choose>
							<c:when test="${animal.age==0}">
								<span>1살미만</span>
							</c:when>
							<c:otherwise>
								<span>${animal.age}</span>
							</c:otherwise>
						</c:choose>     <br>
          장소 :  
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
		
		   성별 :
			<c:choose>
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
<%@ include file="/WEB-INF/home/mainFooter.jsp" %>