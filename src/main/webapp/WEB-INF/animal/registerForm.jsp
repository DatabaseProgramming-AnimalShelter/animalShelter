<!-- ���⵿�� ��� ȭ�� -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>���⵿�� ��� ȭ�� - ������</title>
    <style>
        body {
            text-align: center;
        }
    </style>
</head>
<body>
    <h2>���⵿�� ��� - ������</h2>
    <br>
    <form class="applyForm"name="form" method="POST" action="<c:url value='/animal/register'/>" enctype="multipart/form-data">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script>
            $( document ).ready(function(){
                //�׽�Ʈ�� ������
                var animal_type = {
                    " ":"�� ����",
                    "��":"��",
                    "�����":"�����"
                };
                //animal_type�� �������ϰ��
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
                //animal_type�� ������ϰ��
                var species_2 = {
                    " ":"�����",
                    10: "���þȺ��",
                    11: "��ġŲ",
                    12: "��",
                    13: "����",
                    14: "�ڸ��ȼ����",
                    15: "����ũ��"
                };
               //animal_type�� �������� �޾ƿ� ���� �ֱ�����..
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
               $("select[id='animal_type']").on("change", function(){
                    var option = $("#animal_type option:selected").val();
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
               retOption(animal_type, "animal_type");
            });
            </script>
        <!-- ��: type (��, �����, ��Ÿ) -->
        ��:
        <select name="animal_type" id="animal_type">
        </select>
        <br><br>
        <!-- ��: species (���޶�Ͼ�, ��ũ���׸���, ġ�Ϳ�) -->
      ��:
        <select name="species" id="species" style="">
        </select>
        <br><br>
        <!-- ����: gender  -->
        ����:
        <select name="gender" id="gender" >
        	<option value="female" selected>����</option>
        	<option value="male">����</option>
        </select>
        <br><br>
        <!-- ����: age -->
        ����:
         <select name="age" >
		    <option value="0" > 1��̸� </option>
		    <option value="1"> 1�� </option>
		    <option value="2" > 2�� </option>
		    <option value="3"> 3�� </option>
		    <option value="4" > 4��</option>
		    <option value="5"> 5�� </option>
		    <option value="6" > 6�� </option>
		    <option value="7"> 7�� </option>
		    <option value="8" > 8�� </option>
		    <option value="9"> 9�� </option>
		    <option value="10" > 10�� </option>
		    <option value="11"> 11�� </option>
		    <option value="12" > 12�� </option>
		    <option value="13"> 13�� </option>
		    <option value="14" > 14�� </option>
		    <option value="15"> 15�� </option>
		    <option value="16" > 16�� </option>
		    <option value="17"> 17�� </option>
		    <option value="18" > 18�� </option>
		    <option value="19"> 19�� </option>
	  	</select> <br><br>
        <!-- ������: weight -->
        ������:
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
        <!-- �����ȣ: notice_number -->
        <!-- �߰����: location -->
        �߰����:
         <select name="location" >
		    <option value="seoul" > ���� </option>
		    <option value="gyeonggi"> ��� </option>
		    <option value="incheon" > ��õ </option>
		    <option value="etc"> ��Ÿ </option>
	  	</select>
        <br><br>
        <!-- Ư�̻���: etc -->
        Ư�̻���:
        <textarea id="etc" name="etc" rows=10" cols="30" placeholder="Ư�̻����� �Է��Ͻÿ�."></textarea>
        <br><br>
        <!-- ����
        <input type="file" id="image" name="image" accept="image/png, image/jpeg">-->
         <input type="file" id="image" name="image">
         <br><br>
        <input type="submit" value="���" />
    </form>
</body>
</html>
 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>