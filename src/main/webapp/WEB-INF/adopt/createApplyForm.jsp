
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<link rel=stylesheet href="<c:url value='/css/community.css' />"
   type="text/css">
<script>
 function create() {
   <!-- 
   if (form.name.value == "") {
      alert("이름을 입력하십시오.");
      form.name.focus();
      return false;
   } 
   if (form.desc.value == "") {
      alert("설명을 입력하십시오.");
      form.desc.focus();
      return false;
   }      
   -->

}

function animalList(targetUri) {
   form.action = targetUri;
   form.submit();
} 
</script>
<style style type="text/css">
.selection{
   margin-top: 50px;
}

#apply{
   width:70%;
   height:80%;
   margin:0 auto;
   padding:10px;
   display:flex;
   margin-top: 20px;
   border: 1px solid;
   border-radius: 1rem;
}

#imgParent{
   text-align:center;
   border: 1px solid;
   width:50%;
   height:400px;
   margin-left:20px;
   margin-top:50px;
   vertical-align:middle;
}

#info{
   padding-left: 30px;
   padding-right: 10px;
   padding-bottom: 10px;
    width:50%;
   float: right;
   margin:auto;
}

.btnAll{
   width:200px;
   height:40px;
   margin:0 auto;
   display:flex;
   margin-top: 30px;
}

#submit, #cancel{
   text-align:center;
   width:45%;
}

h1{
   text-align:center;
}
.read_only_text{
   text-color:blue;
}

</style>

   <!-- registration form  -->

   <h1>입양 신청 하기</h1>
   <form class="was-validated" name="form" method="POST" action="<c:url value='/adopt/register'>
                        <c:param name='animal_id' value='${apply.animal_id}'/>
                     </c:url>">
      <div id="apply">
      <div id="imgParent">
        <img src="${pageContext.request.session.servletContext.contextPath}/upload/${apply.image}" height="400px" width="400px" />
      </div>
      <div id="info">
         <div class="mb-3">
            <label for="userName" class="form-label">1. 이름</label><br>
            <div class="col-sm-10">
               <input type="text" readonly class="read_only_text"
                  id="userName" value="${apply.user_name}">
            </div>
         </div><br>
         
         
         <div class="mb-3">
            <label for="select_animal" class="form-label">2. 신청 반려동물</label> 
            <div class="col-sm-10">
               <input type="text" readonly class="read_only_text"
                  id="select_animal" value="[${apply.animal_type}] > ${apply.species}">
            </div>
         </div><br>
   
   
         <div class="mb-3">
            <label for="have_pets" class="form-label">3. 반려동물 유무</label><br>
            <div class="col-sm-10">
               <input type="text" class="form-control-plaintext"
                  name="have_pets" id="have_pets" placeholder="예) 고양이(러시안블루)-6년 "
                  value="${apply.have_pets}">
               <c:if test="${creationFailed}"></c:if>
            </div>
            <div class="invalid-feedback">*반려동물 유무 기재는 필수입니다</div>
         </div>
         
         <div class="mb-3">
            <label for="living_conditions" class="form-label">4. 거주 환경</label><br>
            <div class="form-check form-check-inline">
      
               <label class="form-check-label"><input
                  class="form-check-input" type="radio" name="living_conditions"
                  id="living_conditions" value="apartment">아파트</label>
            </div>
            <div class="form-check form-check-inline">
               <label class="form-check-label"><input
                  class="form-check-input" type="radio" name="living_conditions"
                  id="living_conditions" value="villa">빌라</label>
            </div>
            <div class="form-check form-check-inline">
               <label class="form-check-label"><input
                  class="form-check-input" type="radio" name="living_conditions"
                  id="living_conditions" value="withYard">단독주택(마당O)</label>
            </div>
            <div class="form-check form-check-inline">
               <label class="form-check-label"><input
                  class="form-check-input" type="radio" name="living_conditions"
                  id="living_conditions" value="noYard">단독주택(마당X)</label>
            </div>
            <div class="invalid-feedback">*거주환경 기재는 필수입니다</div>
         </div>
         <div class="mb-3">
            <label for="content" class="form-label">5. 나의 조건</label><br>
            <textarea class="form-control is-invalid" id="content"
               name="content" placeholder="분양을 할 수 있는 조건인지 상세히 적어주세요!" required>   
               ${apply.content}
            </textarea>
            <div class="invalid-feedback">*3번, 4번, 5번은  필수항목 입니다</div>
         </div>
         </div>
      </div>
      <div class="btnAll">
            <input type="submit" value="신청" /> 
         &nbsp; 
         <button id="cancel" type="submit">
               <a href="<c:url value='/animal/list'>
                    </c:url>">
                 취소</a>  
         </button>
      </div>
   </form>

<%@ include file="/WEB-INF/home/mainFooter.jsp" %>