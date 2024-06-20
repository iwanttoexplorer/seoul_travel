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
	  const findIdButton = document.querySelector('#find_id_button');
	  
	  //이벤트
	  findIdButton.addEventListener('click',function(){
		  console.log('findIdButton click');
		  
		  const frm = document.querySelector('#findIdForm');
		  
		  frm.work_div.value = "findUserId";
		  
		  frm.action = "/SEOUL_TRAVEL/user/login.do";
		  
		  frm.submit();
		  
		  
		  /* $.ajax({
	            type: "GET", 
	            url:"/SEOUL_TRAVEL/user/login.do",
	            asyn:"true",
	            dataType:"html",
	            data:{
	                "work_div":"findUserId",
	                "user_email": userEmail.value
	        },success:function(data){
	            console.log("success data:"+data);
	            alert(data);
	        },
	        error:function(data){
	                console.log("error:"+data);
	        }
	      });  */
		  
		  
	  });//findIdButton
	  
  }); //DOMContentLoaded
</script>
</head>
<body>
    <div class="container">
    <h2>아이디 찾기</h2>
    <form id="findIdForm">
        <input type="hidden" id="work_div" name="work_div">
        <label for="user_email">이메일</label>
        <input type="email" id="user_email" name="user_email" placeholder="이메일을 입력하세요." required>
        
        <button type="button" id="find_id_button" >아이디 찾기</button>
    </form>

</div>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>