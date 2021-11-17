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
        
        var species_1 = {
            100:"강아지전체",
            101:"믹스견",
            102:"기타",
            103:"진돗개",
            104:"풍산개",
            105:"치와와",
            106:"요크셔테리어",
            107:"비숑",
            108:"푸들",
            109:"말티즈",
            110:"골든리트리버",
            111:"포메라니안",
            112:"웰시코기"
        };
        var species_2 = {
            200:"고양이전체",
            201:"믹스",
            202:"기타",
            203:"코리안숏헤어",
            204:"샴",
            205:"뱅갈",
            206:"먼치킨",
            207:"스핑크스"
        };
        
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
<br>
<table class="table" style="background-color: #e0d8cb">
  <tr><td> </td><td> </td><td> </td>
      <th scope="row">과</th>
      <td>  <select name="type" id="type">
        </select></td>
         <th scope="row">종</th>
      <td>   <select name="species" id="species" style="">
        </select>
      </td>
       <th scope="row">입양유무</th>
      <td>   <select name="matched">
         <option value=-1 selected>전체</option>
         <option value=0>보호중</option>
         <option value=1>입양완료</option>
      </select>
      </td>
       <th scope="row">발견장소</th>
      <td>    <select name="location" >
		    <option value="seoul" > 서울 </option>
		    <option value="gyeonggi"> 경기 </option>
		    <option value="incheon" > 인천 </option>
		    <option value="etc"> 기타 </option>
	  	</select>
      </td>
      <td><input type="submit" value="검색"  ></td>
   <td> </td><td> </td> </tr>
    </table>
  <!--   <input type="button" value="검색" onClick="search()">-->
  
 
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