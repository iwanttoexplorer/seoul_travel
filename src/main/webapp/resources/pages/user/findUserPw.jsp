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
  document.addEventListener("DOMContentLoaded", function(){
	  const findPw = document.querySelector("#find_pw");
	  const userId = document.querySelector("#user_id");
	  
	  findPw.disabled = true;
	    function checkData() {
	      const userIdValue = userId.value.trim();
	        
	      if (userIdValue !== '') {
	    	  findPw.disabled = false; // 버튼 활성화
	      } else {
	    	  findPw.disabled = true; // 버튼 비활성화
	      }
	    }
	    //이벤트 핸들러
	    userId.addEventListener('input', checkData);
	    
	    findPw.addEventListener('click', function(){
	    	console.log('findPw click');
	    	
	    	const frm = document.querySelector("#pwForm");
	    	
	    	frm.work_div.value = "findUserPw";
	    	
	    	frm.action = "/SEOUL_TRAVEL/user/login.do";
	    	
	    	frm.submit();
	    	
	    }); //찾기 버튼
	  
  }); //DOMContentLoaded
</script>
</head>
<body>
    <div class="container">
    <h2>비밀번호 찾기</h2>
    <form id="pwForm" method="post">
        <label for="user_id">아이디를 입력하세요.:</label><br>
        <input type="hidden" id="work_div" name="work_div">
        <input type="text" id="user_id" name="user_id" required><br><br>
        <input type="button" id="find_pw" value="비밀번호 찾기">
    </form>

</div>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>