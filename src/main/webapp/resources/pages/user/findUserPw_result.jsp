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
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f8f9fa;
    }
    .container1 {
        max-width: 500px;
        margin: 50px auto;
        padding: 20px;
        background: #fff;
        border: 1px solid #dee2e6;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
    }
    .container1 h2 {
        margin-bottom: 20px;
    }
    .result {
        margin-bottom: 20px;
    }
    .result p {
        font-size: 18px;
    }
    .result strong {
        color: #007bff;
    }
    .back-button {
        text-align: center;
        margin-top: 20px;
    }
    .back-button input[type="submit"] {
        padding: 10px 20px;
        font-size: 16px;
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
        border-radius: 5px;
    }
    .back-button input[type="submit"]:hover {
        background-color: #0056b3;
    }
    footer {
        position: fixed; /* 고정 위치 */
        bottom: 0; /* 아래쪽으로 고정 */
    }
</style>
<jsp:include page="/cmn/menu.jsp"></jsp:include>
</head>
<body>
    <div class="container1">
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
<footer>
<jsp:include page="/cmn/footer.jsp"></jsp:include>
</footer>
</html>