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
    .container1 form {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .container1 label {
        text-align: left;
        margin-bottom: 5px;
        width: 100%; /* 레이블의 너비를 100%로 설정하여 왼쪽 정렬을 유지 */
    }
    .container1 input[type="text"],
    .container1 input[type="button"] {
        width: calc(100% - 22px); /* 입력 필드와 버튼의 너비를 계산하여 동일하게 설정 */
        padding: 10px;
        font-size: 16px;
        margin-bottom: 10px;
        box-sizing: border-box; /* 패딩과 보더를 포함한 전체 너비 설정 */
    }
    .container1 input[type="button"] {
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
        padding: 12px 20px;
        border-radius: 5px;
    }
    .container1 input[type="button"]:hover {
        background-color: #0056b3;
    }
</style>
<jsp:include page="/cmn/menu.jsp"></jsp:include>
</head>
<body>
    <div class="container1">
    <h2>비밀번호 찾기</h2>
    <form id="pwForm" method="post">
        <label for="user_id">아이디</label><br>
        <input type="hidden" id="work_div" name="work_div">
        <input type="text" id="user_id" name="user_id" placeholder="아이디를 입력하세요." required><br><br>
        <input type="button" id="find_pw" value="비밀번호 찾기">
    </form>

    </div>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
<jsp:include page="/cmn/footer.jsp"></jsp:include>
</html>