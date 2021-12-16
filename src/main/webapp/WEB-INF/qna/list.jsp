<!-- 후기 글 목록 화면 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp"%>

<title>QNA 관리 - 목록</title>

<style>
$(function(){
    $('#orderModal').modal({
        keyboard: true,
        backdrop: "static",
        show:false,
        
    }).on('show', function(){
          var getIdFromRow = $(this).data('orderid');
        //make your ajax call populate items or what even you need
        $(this).find('#orderDetails').html($('<b> Order Id selected: ' + getIdFromRow  + '</b>'))
    });
    
    $(".table-striped").find('tr[data-target]').on('click', function(){
        //or do your operations here instead of on show of modal to populate values to modal.
         $('#orderModal').data('orderid',$(this).data('id'));
    });
    
});
</style>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function clickDel(formName) {
		formName.action = "/board/contentDelAsk";
		formName.method = "post";
		formName.submit();
	}
</script>
<div class="container">
	<br>
	<h4>QNA 목록</h4>
	<br>

	<table class="table table-bordered">
		<thead class="thead-inverse">
			<tr>
				<th>id</th>
				<td>제목</td>
				<td>작성자</td>
				<td>질문종류</td>
				<td>date</td>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="list" items="${QnaList}">
				<tr data-toggle="modal" data-id="${list.qna_id}" data-target="#orderModal">
					<th scope="row">${list.qna_id}</th>
					<td>${list.qna_title}</td>
					<td>${list.qna_writer}</td>
					<td>${list.qna_type}</td>
					<td>${list.qna_date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
</div>

<%@ include file="/WEB-INF/home/mainFooter.jsp"%>