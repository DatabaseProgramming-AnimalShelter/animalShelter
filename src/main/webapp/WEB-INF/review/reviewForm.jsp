<!-- 후기 작성하는 화면 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="EUC-KR">
<title>후기 작성 폼 화면</title>
</head>
<body>
<h2>후기 작성 폼 화면 </h2>
<br>

<form method="post" action=".jsp">
        <!-- 제목 -->
        제목:	<input type="text" name="title" placeholder="제목을 입력하시오." />
        <br><br>
        <!-- 후기 내용 -->
        내용:<br>
        <textarea id="body" name="body" rows=15" cols="50" placeholder="내용을 입력하시오."></textarea>
        <br><br>

        <!-- 사진( 할까 말까 ) -->
        
        
        <input type="submit" value="등록" />
    </form>


</body>
</html>
 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>