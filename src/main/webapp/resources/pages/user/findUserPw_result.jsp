<%@page import="com.pcwk.tvl.user.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원 로그인</title>
<script src="/SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
<script src="/SEOUL_TRAVEL/assets/js/common.js"></script>
<script>
	//로딩 완료시 함수 실행.
  document.addEventListener("DOMContentLoaded", function(){
	
	  
  }); //DOMContentLoaded
</script>
</head>
<body>
    <div class="container">
    <h2>비밀번호 찾기 결과</h2>
    <div class="result">
        <p>당신의 비밀번호: <strong id="usernameDisplay">
        <%
          String resultPw = (String)request.getAttribute("outVO");
          if(resultPw != null){
        	  out.print(resultPw);
          }else{
        	  out.print("없는 아이디 입니다.");
          }
        %>
        </strong></p>
    </div>
    
    <form action="/SEOUL_TRAVEL/resources/pages/user/login.jsp">
	    <div class="back-button">
	        <input type="submit" value="돌아가기" >
	    </div>
    </form>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>