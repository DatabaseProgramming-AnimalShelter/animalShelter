<!-- ���⵿�� ��� ȭ�� -->

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
    <form method="post" action=".jsp">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script>
            $( document ).ready(function(){
               
                //�׽�Ʈ�� ������
                var sel1 = {
                    " ":"�� ����",
                    "��":"��",
                    "C":"�����"
                };
                
                //sel1�� �������ϰ��
                var sel2_1 = {
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
                
                //sel1�� ������ϰ��
                var sel2_2 = {
                    " ":"�����",
                    10: "���þȺ��",
                    11: "��ġŲ",
                    12: "��",
                    13: "����",
                    14: "�ڸ��ȼ����",
                    15: "����ũ��"
                };
                
               //sel1�� �������� �޾ƿ� ���� �ֱ�����..
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

        <!-- ��: type (��, �����, ��Ÿ) -->
        ��:
        <select name="sel1" id="sel1">
        </select>
        <br><br>
        
        <!-- ǰ��: species (���޶�Ͼ�, ��ũ���׸���, ġ�Ϳ�) -->
      ǰ��:
        <select name="sel2" id="sel2" style="">
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
        <input type="text" name="age" placeholder="���� �Է�" />
        <br><br>
        
        <!-- ������: weight -->
        ������:
        <input type="text" name="weight" placeholder="������ �Է�" />
        <br><br>
        
        <!-- �����ȣ: notice_number -->
        
        <!-- �߰����: location -->
        �߰����:
        <input type="text" name="location" placeholder="�߰���� �Է�" />
        <br><br>
        
        <!-- Ư�̻���: etc -->
        Ư�̻���:
        <textarea id="etc" name="etc" rows=10" cols="30" placeholder="Ư�̻����� �Է��Ͻÿ�."></textarea>
        <br><br>
        
        <!-- ���� -->
        <input type="file" id="image" name="image" accept="image/png, image/jpeg">
         <br><br>
        
        <input type="submit" value="���" />
    </form>
</body>
</html>

 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>