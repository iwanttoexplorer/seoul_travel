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
    <h2>아이디 찾기 결과</h2>
    <div class="result">
        <p>당신의 아이디: <strong id="usernameDisplay">
        <%
        String resultId = (String) request.getAttribute("outVO");
        if(resultId != ""){
          out.print(resultId);
        }else{
          out.print("없는 이메일 입니다.");
        }
        %>
        </strong></p>
    </div>
    
    <form action="/SEOUL_TRAVEL/assets/js/login.jsp">
	    <div class="back-button">
	        <input type="submit" value="돌아가기">
	    </div>
    </form>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>