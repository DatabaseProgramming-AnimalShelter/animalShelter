<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내품으로-유기동물입양</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<style>
        .navbar-ul{
            margin-left: 70%;
            list-style: none;
            
        }
        .nav-li{
            text-align: right;
            width: 100px;
            font-size: 20px;
            display: inline;
            border: 1px black solid;
        }
        .main{
            margin: auto;
            width:1200px;
        }
        .logo{
          margin-left: 50px;
        }
        .searchInput{
          width:200px;
        }
        .searchForm{
          margin-left: 70%
        }
        footer{
        clear:both;}
        a{
            font-weight: 900;
            color: rgb(54, 27, 27);
            text-decoration: none;
        }
        a:hover{
            color: rgb(54, 27, 27);
            text-decoration: none;
        }
        a:visited{
            color: rgb(54, 27, 27);
        }
    </style>
<body>
  <div class="main">
    <br>
    <!--  
    <form class="d-flex searchForm">
      <input class="form-control me-2 searchInput" type="search" placeholder="Search" width="200px"aria-label="Search">
      <button class="btn btn-outline-success" type="submit">검색</button>
    </form>-->
    <a  class="navbar-brand " href="<c:url value='/index.jsp' />"><img  class ="logo" src="<c:url value='/images/mainLogo.png'/>"  height="140px"></a>
    <br><br>
    <nav class="py-2 bg-light border-bottom">
      <div class=" d-flex flex-wrap">
        <ul class="nav me-auto">
          <li class="nav-item"><a href="<c:url value="/animal/list"></c:url>" class="nav-link link-dark px-2 active" aria-current="page">유기동물</a></li>
          <li class="nav-item"><a href="<c:url value="/review/list"></c:url>" class="nav-link link-dark px-2">후기</a></li>
          <c:choose>
	        <c:when test="${user_id=='admin'}">
	          	<li class="nav-item"><a href="<c:url value="/animal/register"></c:url>" class="nav-link link-dark px-2">유기동물등록</a></li>
	          	<li class="nav-item"><a href="<c:url value="/adopt/list" />" class="nav-link link-dark px-2">입양신청목록</a></li>
	          	<li class="nav-item"><a href="<c:url value="/adopt/approved_list" />" class="nav-link link-dark px-2">입양결과</a></li>
	   		</c:when>
          </c:choose> 
        </ul>
        <ul class="nav">
        <c:choose>
	        <c:when test="${user_id==NULL}">
	        <li class="nav-item"><a href="<c:url value="/user/login"></c:url>" 
          class="nav-link link-dark px-2">Login</a></li>
           <li class="nav-item"><a href="<c:url value="/user/register"></c:url>" class="nav-link link-dark px-2">Sign up</a></li>
	   		</c:when>
	   		<c:when test="${user_id!=NULL}">
	         <li class="nav-item"><a href="<c:url value="/user/logout"></c:url>" class="nav-link link-dark px-2">Logout</a></li>
          <li class="nav-item"><a href="<c:url value="/user/mypage"> <c:param name='user_id' value='${user.user_id}'/></c:url>" class="nav-link link-dark px-2">MyPage</a></li>
	   		</c:when>
	     </c:choose> 
         
         
        </ul>
      </div>
    </nav>
    <br>