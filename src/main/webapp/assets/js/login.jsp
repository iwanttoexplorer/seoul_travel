<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/WEB02/assets/css/bootstrap.css">
<title>회원 로그인</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        text-align: center;
        margin: 50px;
    }
    .container {
        max-width: 400px;
        margin: auto;
        background-color: #ffffff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    input[type="text"], input[type="password"], input[type="button"] {
        width: 100%;
        padding: 10px;
        margin: 5px 0 10px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 16px;
    }
    input[type="button"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        cursor: pointer;
    }
    input[type="button"]:hover {
        background-color: #45a049;
    }
    .btn-small {
        font-size: 14px;
        color: #007bff;
        text-decoration: none;
        margin-top: 10px;
        display: inline-block;
    }
</style>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
<script>
	//로딩 완료시 함수 실행.
  document.addEventListener("DOMContentLoaded", function(){
	  
	  //로그인 버튼
	  const loginBtn = document.querySelector("#login");
	  
	  
	  //이벤트 핸들러
	  loginBtn.addEventListener('click',function(){
		  console.log('loginBtn click');
		  
		  let frm = document.querySelector("#login_form");
		  
		  frm.work_div.value = "login";
		  
		  console.log(frm.work_div.value);
		  
		  frm.action = "/SEOUL_TRAVEL/user/login.do";
		  
		  frm.submit();
		  
	  }); //로그인
	  
  }); //DOMContentLoaded
</script>
</head>
<body>
    <div class="container">
        <h2>회원 로그인</h2>
        <form action="#" method="post" id="login_form">
            <input type="hidden" name="work_div" id="work_div">
            <input type="text" id="user_id" name="user_id" required><br>

            <input type="password" id="user_pw" name="user_pw" required><br>

            <input type="button" value="로그인" id="login">
        </form>
        
        <a href="/signup" class="btn-small">회원가입</a>
        <a href="/find-username" class="btn-small">아이디 찾기</a>
        <a href="/find-password" class="btn-small">비밀번호 찾기</a>
    </div>
<script src="/WEB02/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>