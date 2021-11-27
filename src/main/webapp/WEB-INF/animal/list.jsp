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
$(document).ready(function() {

    GetList(1);

    // 게시물 이미지를 클릭했을 때 실행된다
    // 해당 게시물을 hit+1하는 함수를 호출한다.
    $(document).on('click', '.card-img', function() {
        // 게시물 번호(animal_id)를 idx로 전달받아 저장합니다.
        let animal_id = $(this).attr('idx');

        console.log(animal_id +"에 hit + 1을 함");

        // hit+1하고 그 값을 불러온다.
        $.ajax({
            url : 'picture_view.do',
            type : 'get',
            data : {
                animal_id : animal_id
            },
            success : function(to) {
                let hit = to.hit;

                $('#m_hit'+animal_id).text(hit);
                $('#hit'+animal_id).text(hit);

            },
            error : function() {
                alert('서버 에러');
            }
        });
    });


});

// 창 크기가 변할 때마다 가로세로 길이를 맞춰준다.
$(window).resize(function(){
    $('.box').each(function(){
        $(this).height($(this).width());
    });
}).resize();

//로그인 한 상태에서 하트를 클릭했을 때 (로그인한 상태인 하트의 <a></a> class명: heart-click)
$(".heart-click").click(function() {

    // 게시물 번호(animal_id)를 idx로 전달받아 저장합니다.
    let animal_id = $(this).attr('idx');
    console.log("heart-click");

    // 빈하트를 눌렀을때
    if($(this).children('svg').attr('class') == "bi bi-suit-heart"){
        console.log("빈하트 클릭" + animal_id);

        $.ajax({
            url : 'saveHeart.do',
            type : 'get',
            data : {
                animal_id : animal_id,
            },
            success : function(pto) {
                //페이지 새로고침
                //document.location.reload(true);

                let heart = pto.heart;

                // 페이지, 모달창에 하트수 갱신
                $('#m_heart'+animal_id).text(heart);
                $('#heart'+animal_id).text(heart);

                console.log("하트추가 성공");
            },
            error : function() {
                alert('서버 에러');
            }
        });
        console.log("꽉찬하트로 바껴라!");

        // 꽉찬하트로 바꾸기
        $(this).html("<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-suit-heart-fill' viewBox='0 0 16 16'><path d='M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z'/></svg>");
        $('.heart_icon'+animal_id).html("<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-suit-heart-fill' viewBox='0 0 16 16'><path d='M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z'/></svg>");

    // 꽉찬 하트를 눌렀을 때
    }else if($(this).children('svg').attr('class') == "bi bi-suit-heart-fill"){
        console.log("꽉찬하트 클릭" + animal_id);

        $.ajax({
            url : 'removeHeart.do',
            type : 'get',
            data : {
                animal_id : animal_id,
            },
            success : function(pto) {
                //페이지 새로고침
                //document.location.reload(true);

                let heart = pto.heart;
                // 페이지, 모달창에 하트수 갱신
                $('#m_heart'+animal_id).text(heart);
                $('#heart'+animal_id).text(heart);

                console.log("하트삭제 성공");
            },
            error : function() {
                alert('서버 에러');
            }
        });
        console.log("빈하트로 바껴라!");

        // 빈하트로 바꾸기
        $(this).html('<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart" viewBox="0 0 16 16"><path d="M8 6.236l-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z" /></svg>');

        $('.heart_icon'+animal_id).html('<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart" viewBox="0 0 16 16"><path d="M8 6.236l-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z" /></svg>');
    }



});


// 로그인 안한 상태에서 하트를 클릭하면 로그인해야한다는 알림창이 뜹니다.
// (로그인한 상태인 하트의 <a></a> class명: heart-notlogin)
$(".heart-notlogin").unbind('click');
$(".heart-notlogin ").click(function() {
    alert('로그인 하셔야 하트를 누를수 있습니다!');
});



$.ajax({
    url : 'saveHeart.do',
    type : 'get',
    data : {
        animal_id : animal_id,
    },
    success : function(pto) {
        //페이지 새로고침
        //document.location.reload(true);

        let heart = pto.heart;

        // 페이지, 모달창에animal_id트수 갱신
        $('#m_heart'+animal_id).text(heart);
        $('#heart'+animal_id).text(heart);

        console.log("하트추가 성공");
    },
    error : function() {
        alert('서버 에러');
    }
});


//꽉찬하트로 바꾸기
$(this).html("<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-suit-heart-fill' viewBox='0 0 16 16'><path d='M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z'/></svg>");
$('.heart_icon'+animal_id).html("<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-suit-heart-fill' viewBox='0 0 16 16'><path d='M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z'/></svg>");






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
	      		<option value="all" > 전체 </option>
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
	<div class="list">
		<c:choose>
		<c:when test="${ispost==1}">
							<h2>"과 : 
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
							"에 대한 검색결과</h2></c:when>
						</c:choose>
	
	<c:choose>
		<c:when test="${empty animalList}">
							결과없음</c:when>
						</c:choose>
						
	 <c:forEach var="animal" items="${animalList}">                
	  <span class="card" style="width: 18rem;">
	  	<a href="<c:url value='/animal/view'>
	                     <c:param name='animal_id' value='${animal.animal_id}'/>
	                    </c:url>">	<c:choose>
							<c:when test="${not empty animal.image}">
								<img
									src="${pageContext.request.session.servletContext.contextPath}/upload/${animal.image}"  class="card-img-top" height="270px"/>
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
	        
	        <!-- 여기에 좋아요 넣을라고 -->
	        <c:choose>
	        <c:when test="${user_id=='admin'}">
	        	 <span> <a href="javascript:" class="heart-notlogin">
                <svg class="heart3" xmlns="http://www.w3.org/2000/svg"
                    width="16" height="16" fill="currentColor"
                    class="bi bi-suit-heart" viewBox="0 0 16 16">
                          <path
                        d="M8 6.236l-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z" />
                        </svg>
        </a>
        </span> </c:when>
        
         <c:otherwise>
             <c:choose>
            <c:when test="${empty animal.a_heart_id}">
                <%-- 빈 하트일때 --%>
                <span> <a idx="${animal.animal_id }" href="javascript:"
                    class="heart-click heart_icon${animal.animal_id }"><svg
                            xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                            fill="currentColor" class="bi bi-suit-heart"
                            viewBox="0 0 16 16">
                                  <path
                                d="M8 6.236l-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z" />
                                </svg></a>
                </span>
            </c:when>
            <c:otherwise>
                <%-- 꽉찬 하트일때 --%>
                <span> <a idx="${animal.animal_id }" href="javascript:"
                    class="heart-click heart_icon${animal.animal_id }"><svg
                            xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                            fill="currentColor" class="bi bi-suit-heart-fill"
                            viewBox="0 0 16 16">
                                  <path
                                d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z" />
                                </svg></a>
                </span>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
         </c:choose>
        <span id="heart${animal.animal_id }">${animal.heart }</span>
        
        
        
        
        
        
        
	      </span>
	      
	      
	      
	    </c:forEach> 
	    </div>
</div>
<%@ include file="/WEB-INF/home/mainFooter.jsp" %>