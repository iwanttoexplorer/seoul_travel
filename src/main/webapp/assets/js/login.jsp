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
	            	window.location.href="/SEOUL_TRAVEL/resources/pages/main/mainpage.html";	            	
	            	
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
    <div class="container">
        <h2>회원 로그인</h2>
        <form action="#" method="post" id="login_form">
            <input type="hidden" name="work_div" id="work_div">
            <input type="text" id="user_id" name="user_id" required><br>

            <input type="password" id="user_pw" name="user_pw" required><br>

            <input type="button" value="로그인" id="login">
        </form>
        
        <a href="/SEOUL_TRAVEL/assets/js/userSave.jsp" class="btn-small">회원가입</a>
        <a href="/SEOUL_TRAVEL/assets/js/findUserId.jsp" class="btn-small">아이디 찾기</a>
        <a href="/SEOUL_TRAVEL/assets/js/findUserPw.jsp" class="btn-small">비밀번호 찾기</a>
    </div>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>