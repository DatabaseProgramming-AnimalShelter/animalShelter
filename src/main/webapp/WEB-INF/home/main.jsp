<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/home/mainHeader.jsp" %>
<div class="container">
      <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active" data-bs-interval="2000">
            <img src="images/main2.png" class="d-block w-100"  height= "700px" alt="...">
          </div>
          <div class="carousel-item" data-bs-interval="2000">
            <img src="images/main1.png" class="d-block w-100" height= "700px"   alt="...">
          </div>
          <div class="carousel-item" data-bs-interval="2000">
            <img src="images/main3.png" class="d-block w-100"  height= "700px"  alt="...">
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>
 <%@ include file="/WEB-INF/home/mainFooter.jsp" %>