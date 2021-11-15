<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<title>���⵿�� ��� ��ȸ</title>
<script type="text/javascript">
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script>
    $( document ).ready(function(){
       
        //�׽�Ʈ�� ������
        var type = {
            " ":"�� ����",
            "��":"��",
            "�����":"�����"
        };
        
        //type�� �������ϰ��
        var species_1 = {
            " ":"��",
            1: "�ͽ���",
            2: "������",
            3: "ǳ�갳",
            4: "ġ�Ϳ�",
            5: "��ũ���׸���",
            6: "���",
            7: "Ǫ��",
            8: "��Ƽ��",
            9: "��縮Ʈ����"
        };
        
        //type�� ������ϰ��
        var species_2 = {
            " ":"�����",
            10: "���þȺ��",
            11: "��ġŲ",
            12: "��",
            13: "����",
            14: "�ڸ��ȼ����",
            15: "����ũ��"
        };
       //type�� �������� �޾ƿ� ���� �ֱ�����..
       // map�迭�� select �±� id�� �Ѱ��ָ� option �±׸� �ٿ���.
       // map[Ű�̸�] = �� Ű�� �ش��ϴ� value�� ��ȯ�Ѵ�.
       //retOption(�����͸�, select�Լ� id)
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
            if(option == "��") {
                subSelName = "species_1";
            } else if(option == "�����"){
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

f
       
}
   
</script>

<form name="form" action="<c:url value='/animal/search' />">
   <div id="menu">
     ��:
        <select name="type" id="type">
        </select>
        <br><br>
        
        <!-- ��: species (���޶�Ͼ�, ��ũ���׸���, ġ�Ϳ�) -->
      ��:
        <select name="species" id="species" style="">
        </select>
        <br><br>
      
    
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
               <h3 class="fw-bolder">${animal.type} > ${animal.species}</h5><br>
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