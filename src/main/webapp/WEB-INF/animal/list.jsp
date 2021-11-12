<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���⵿�� ��� ��ȸ</title>
<script type="text/javascript">

function search(targetUri){
	form.action = targetUri;
	form.submit();
}

function setValue(val){ // ���ÿ� ���� �Ķ���� �� setting
	if(val.equals('all'))
		request.setAttribute("all", val);
	else if(val.equals('dog'))
		request.setAttribute("dog", val);
	else
		request.setAttribute("cat", val);
	
	search('<c:url value='/animal/search' />')
}
</script>
<style type="text/css">
 * {
		margin: 0;
		padding: 0;
	} 
/* ul li�±׿� ����Ʈ ��Ÿ���� ���� */
	ul li{
		list-style: none;
	}
/* a�±׿� �ؽ�Ʈ ������ ���ְ� ������ #333 */
	a {
		text-decoration: none;
		color: black;
	}
/* ����ũ�⸦ 16px ���� ��� �����ϰ� width���� 700, ���� 50��ŭ ������ #ccc, ���ڻ��� ������, ���γ���50px
menu�ڽ� �������, ���ڰ�� ���� */
	#menu {
		font:bold 16px "malgun gothic";
		width:300px;
		height:50px;
		background: #FEC8E3;
		color:black;
		line-height: 50px; 
		margin:0 auto;
		text-align: center;
	}

/* menu�±� �ڽ��� ul�� �ڽ� li�� �������İ� ���� 140���� */
	#menu > ul > li {
		
		position:relative;
	}
#menu > ul > li > ul {
		width:130px;
		display:none;
		position: absolute;
		font-size:14px;
		background: #FEE5E3;
	}
#menu > ul > li:hover > ul {
		display:block;
	}
#menu > ul > li > ul > li:hover {
		background: #ccc;
		transition: ease 1s;
		}	

.selection{
	margin-top: 50px;
}

.item{
	width:60%;
	height:150px;
	margin:0 auto;
	padding:10px;
	display:flex;
	margin-top: 20px;
	border: 1px solid;
	border-radius: 1rem;
}

.item > .img{
	text-align:center;
}

.imgParent{
	text-align:center;
	border: 1px solid;
	width:30%;
	line-height:100%;
	margin-left:10px;
	vertical-align:middle;
}

.info{
	padding: 20px;
  width:70%;
 
  float: right;
  margin:auto;
}
</style>
</head>
<body>
<div id="menu">
	<ul>
		<li><a href="#">ǰ�� (�˻��Ͻ� ǰ���� �������ּ���)</a>
			<ul>
				<li onClick="setValue('all')">��ü</li>
				<li onClick="setValue('dog')">��</li>
				<li onClick="setValue('cat')">�����</li>
			</ul>
		
		</li>
	</ul>
</div>

<!-- Section-->
	<section class="selection">
		<!-- search �ؼ� �޾ƿ� animal ����Ʈ ��ŭ �ݺ� -->
       <c:forEach var="animal" items="${animalList}">
			<div class="item" onClick="<c:url value='/animal/view'><c:param name='animal_id' value='${animal.animal_id}'/> </c:url>">        
				<div class="imgParent">
					<img class="img" src="'${animal.image}'" />
				</div>
				<div class="info">
					<h3 class="fw-bolder">1. ǰ�� : ${animal.animal_type} > ${animal.species}</h5><br>
					2. ���� : ${animal.gender}<br><br>
					3. �߰���� : ${animal.location}<br><br> 
				</div>
			</div>
		</c:forEach>
	</section> 
</body>
</html>