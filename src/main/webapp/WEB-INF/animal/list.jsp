<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<title>���⵿�� ��� ��ȸ</title>
<script type="text/javascript">

<!-- �˻� ��ư ������ �Ķ���� settings -->
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

<form name="form" action="<c:url value='/animal/search' />">
   <div id="menu">
      <span>��</span>
      <select id="type" name="type" onchange="type()">
         <option value="typeAll" selected>��ü</option>
         <option value="dog">��</option>
         <option value="cat">�����</option>      
      </select>
      <div id="dogSpecies">
         <span>ǰ��</span>
         <select name="dogSpecies">
            <option value="dogSpeciesAll" selected>��ü</option>
            
            <!-- db�� ������ �ݺ������� ���� -->
            <%-- <c:forEach var="animal" items="${searchAnimalList}">
               <option value="${animal.category_id}">${animal.category_id}</option>
            </c:forEach> --%>
         
            <option value="02">������</option>
            <option value="03">ǳ�갳</option>
            <option value="04">ġ�Ϳ�</option>
            <option value="05">��ũ���׸���</option>
            <option value="06">���</option>
            <option value="07">Ǫ��</option>
            <option value="08">��Ƽ��</option>
            <option value="09">��縮Ʈ����</option>
         </select>
      </div>
      
      <div id="catSpecies">
         <span>ǰ��</span>
         <select name="catSpecies">
            <option value="catSpeciesAll" selected>��ü</option>
            
            <!-- db�� ������ �ݺ������� ���� -->
            <!--
            <c:forEach var="animal" items="${searchAnimalList}">
               <option value="${animal.category_id}">${animal.category_id}</option>
            </c:forEach> 
             -->
            
            <option value="10">��</option>
            <option value="11">��</option>
            <option value="12">�ڸ��ȼ����</option>
            <option value="13">����ũ��</option>
         </select>
      </div>
      
      <span>�Ծ�����</span>
      <select name="matched">
         <option value="matchedAll" selected>��ü</option>
         <option value="notMatched">��ȣ��</option>
         <option value="matched">�Ծ�Ϸ�</option>
      </select>
   <input type="button" value="�˻�" onClick="search()">
   </div>
</form>


<!-- Section-->
   <section class="selection">
      <!-- search �ؼ� �޾ƿ� animal ����Ʈ ��ŭ �ݺ� -->
       <c:forEach var="animal" items="${searchAnimalList}">
         <div class="item" onClick="<c:url value='/animal/view'><c:param name='animal_id' value='${animal.animal_id}'/> </c:url>">        
            <div class="imgParent">
               <img class="img" src="'${animal.image}'" />
            </div>
            <div class="info">
               <h3 class="fw-bolder">${animal.animal_type} > ${animal.species}</h5><br>
               1. ���� : ${animal.gender}<br><br>
               2. �߰���� : ${animal.location}<br><br> 
            </div>
         </div>
      </c:forEach>
   </section> 

   <table class="table table-bordered">
      <thead class="thead-inverse">
         <tr>
        <td>���� ����</td>
        <td>���� ���̵�</td>
        <td>���</td>
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