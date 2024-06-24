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
    body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f8f9fa; }
    header { background: #f8f9fa; padding: 10px 20px; border-bottom: 1px solid #dee2e6; display: flex; justify-content: space-between; align-items: center; }
    .logo img { cursor: pointer; }
    .signup { color: #000; text-decoration: none; }
    .container { max-width: 500px; margin: 50px auto; padding: 20px; background: #fff; border: 1px solid #dee2e6; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center; }
    .container h2 { margin-bottom: 20px; }
    .container form { display: flex; flex-direction: column; }
    .container input[type="text"], .container input[type="password"], .container input[type="button"] { margin-bottom: 10px; padding: 10px; font-size: 16px; }
    .container a { display: block; margin-top: 10px; color: #007bff; text-decoration: none; }
    .container a:hover { text-decoration: underline; }
</style>
<script>
    //로딩 완료시 함수 실행.
    document.addEventListener("DOMContentLoaded", function(){
        //로그인 버튼
        const loginBtn = document.querySelector("#login");
        const userId = document.querySelector("#user_id");
        const userPw = document.querySelector("#user_pw");
        
        loginBtn.disabled = true;
        function checkData() {
            const userIdValue = userId.value.trim();
            const userPwValue = userPw.value.trim();
            
            if (userIdValue !== '' && userPwValue !== '') {
                loginBtn.disabled = false; // 버튼 활성화
            } else {
                loginBtn.disabled = true; // 버튼 비활성화
            }
        }
        //이벤트 핸들러
        userId.addEventListener('input', checkData);
        userPw.addEventListener('input', checkData);
        
        loginBtn.addEventListener('click',function(){
            console.log('loginBtn click');
            
            $.ajax({
                type: "POST", 
                url:"/SEOUL_TRAVEL/user/login.do",
                asyn:"true",
                dataType:"html",
                data:{
                    "work_div":"login",
                    "user_id": user_id.value,
                    "user_pw": user_pw.value
            },success:function(data){//통신 성공
                console.log("success data:"+data);
                
                if(data == '성공'){
                	  
                    window.location.href="/SEOUL_TRAVEL/resources/pages/main/mainpage.jsp";
                    
                }else if(data == ''){
                  alert('아이디를 확인하세요.');
                }else{
                    alert(data);
                }
            
            },
            error:function(data){
                    console.log("error:"+data);
            }
            });
            
        }); //로그인
        
    }); //DOMContentLoaded
</script>
</head>
<body>
<header>
    <div class="logo" onclick="location.href='/SEOUL_TRAVEL/resources/pages/main/mainpage.jsp'">
        <img src="/SEOUL_TRAVEL/images/logo.png" alt="SEOUL TRAVEL" width="130" height="80">
    </div>
    <a href="/SEOUL_TRAVEL/resources/pages/user/userSave.jsp" class="signup" style="color: #000000; font-size: 30px;text-decoration: none;margin-right: 20px;">회원가입</a>
</header>
<div class="container">
    <h2>회원 로그인</h2>
    <form action="#" method="post" id="login_form">
        <input type="hidden" name="work_div" id="work_div">
        <input type="text" id="user_id" name="user_id" required placeholder="아이디"><br>
        <input type="password" id="user_pw" name="user_pw" required placeholder="비밀번호"><br>
        <input type="button" value="로그인" id="login">
    </form>
    <a href="/SEOUL_TRAVEL/resources/pages/user/findUserId.jsp">아이디 찾기</a>
    <a href="/SEOUL_TRAVEL/resources/pages/user/findUserPw.jsp">비밀번호 찾기</a>
</div>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
