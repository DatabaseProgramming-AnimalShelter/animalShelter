<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내품으로-유기동물입양</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
        .container{
          height: 700px;
        }
    </style>
</head>
<body>
  <div class="main">
    <br>
    
    <form class="d-flex searchForm">
      <input class="form-control me-2 searchInput" type="search" placeholder="Search" width="200px"aria-label="Search">
      <button class="btn btn-outline-success" type="submit">검색</button>
    </form>
    <a class="navbar-brand " href="#"><img  class ="logo" src="images/mainLogo.png"  height="140px"></a>
    <br><br>
    <nav class="py-2 bg-light border-bottom">
      <div class=" d-flex flex-wrap">
        <ul class="nav me-auto">
          <li class="nav-item"><a href="#" class="nav-link link-dark px-2 active" aria-current="page">Home</a></li>
          <li class="nav-item"><a href="#" class="nav-link link-dark px-2">Features</a></li>
          <li class="nav-item"><a href="#" class="nav-link link-dark px-2">Pricing</a></li>
          <li class="nav-item"><a href="#" class="nav-link link-dark px-2">FAQs</a></li>
          <li class="nav-item"><a href="#" class="nav-link link-dark px-2">About</a></li>
        </ul>
        <ul class="nav">
          <li class="nav-item"><a href="#" class="nav-link link-dark px-2">Login</a></li>
          <li class="nav-item"><a href="#" class="nav-link link-dark px-2">Sign up</a></li>
        </ul>
      </div>
    </nav>
    <br>