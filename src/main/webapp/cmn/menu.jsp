<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SEOUL_TRAVEL</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
<style>
    h4 {
      font-weight : bold;
      line-height: 50px;
    }
    
    ul {
      background-color: #FAFAFA;
    }
   
</style>
</head>
<body>
   
    <ul class="nav">
      <nav class="navbar bg-body-tertiary">
	      <div class="container">
	        <a class="navbar-brand" href="/SEOUL_TRAVEL/resources/pages/main/mainpage.html">
	          <img src="/SEOUL_TRAVEL/images/logo.png" alt="Bootstrap" width="60" height="40">
	        </a>
	      </div>
      </nav>
		  <li class="nav-item">
		    <a class="nav-link active" aria-current="page" href="/SEOUL_TRAVEL/content/content.do?work_div=doRetrieve"><h4>여행정보</h4></a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link" href="/SEOUL_TRAVEL/content/content.do?work_div=doRetrieve2"><h4>맛집 소개</h4></a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link" href="#"><h4>로그인</h4></a>
		  </li>
  </ul>
</body>
</html>