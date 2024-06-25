<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>동가홍상</title>
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<script src="/SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
<script src="/SEOUL_TRAVEL/assets/js/common.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function(){
	const checkName = document.querySelector('#user_name'); //이름
	const checkId = document.querySelector('#user_id'); //아이디
	const checkEmail = document.querySelector('#user_email'); //이메일
	const checkPw = document.querySelector('#user_pw'); //비밀번호
	const checkConfirm = document.querySelector('#confirm_password'); //비밀번호확인
	const doSaveBtn = document.querySelector('#doSave'); //회원가입 버튼
	
	//이벤트
	doSaveBtn.addEventListener("click",function(event){
		console.log('doSaveBtn: '+doSaveBtn);
		
		if(false === confirm('가입 하시겠습니까?')) return;
		
		$.ajax({
            type: "POST", 
            url:"/SEOUL_TRAVEL/user/login.do",
            asyn:"true",
            dataType:"html",
            data:{
                "work_div":"doSave",
                "user_name": user_name.value,
                "user_id": user_id.value,
                "user_email": user_email.value,
                "user_pw": user_pw.value
        },success:function(data){//통신 성공
            console.log("success data:"+data);
            
            if(data === '회원가입을 축하합니다.'){
            	alert(data);
            	window.location.href="/SEOUL_TRAVEL/resources/pages/main/mainpage.jsp";
            }else{
            	alert(data);
            }
        
        },
        error:function(data){
                console.log("error:"+data);
        }
      }); 
		
	}); //doSaveBtn
	
	
	checkConfirm.addEventListener("blur",function(event){
		console.log('checkConfirm: '+checkConfirm.value);
		
		const confirmPw = document.querySelector('#confirm_pw');
		if(checkConfirm.value === checkPw.value) {
			confirm_pw.textContent = '비밀번호 확인완료';
		}else{
			confirm_pw.textContent = '비밀번호를 다시 확인하세요';
		}
		
	}); //checkConfirm
	
	checkPw.addEventListener("blur",function(event){
		console.log('checkPw: '+checkPw.value);
		
		$.ajax({
            type: "POST", 
            url:"/SEOUL_TRAVEL/user/login.do",
            asyn:"true",
            dataType:"text",
            data:{
                "work_div":"checkPassword",
                "check_pw": checkPw.value
            },
            success:function(data){//통신 성공
                console.log("success data:"+data);
                
                $('#check_password').text(data);
            
            },
            error:function(data){//실패시 처리
                    console.log("error:"+data);
            }
    });
		
	}); // checkPw
	
	checkEmail.addEventListener("blur",function(event){
		console.log('checkEmail: '+checkEmail.value);
		
		$.ajax({
	          type: "POST", 
	          url:"/SEOUL_TRAVEL/user/login.do",
	          asyn:"true",
	          dataType:"html",
	          data:{
	              "work_div":"checkEmail",
	              "check_id": checkEmail.value
	          },
	          success:function(data){//통신 성공
	              console.log("success data:"+data);
	              $('#check_email').text(data);
	          
	          },
	          error:function(data){//실패시 처리
	                  console.log("error:"+data);
	          }
    });
		
	});//checkEmail
	
	checkId.addEventListener("blur",function(event){
		console.log('checkId: '+checkId.value);
		
		$.ajax({
	        type: "POST", 
	        url:"/SEOUL_TRAVEL/user/login.do",
	        asyn:"true",
	        dataType:"html",
	        data:{
	            "work_div":"checkId",
	            "check_id": checkId.value
	        },
	        success:function(data){//통신 성공
	            console.log("success data:"+data);
	            $('#check_id').text(data);
	        
	        },
	        error:function(data){//실패시 처리
	                console.log("error:"+data);
	        }
    });
		
	}); // checkId
	
	
	
	checkName.addEventListener("blur",function(event){
		console.log('checkName: '+checkName.value);
		
		$.ajax({
		    type: "POST", 
		    url:"/SEOUL_TRAVEL/user/login.do",
		    asyn:"true",
		    dataType:"html",
		    data:{
		        "work_div":"checkName",
		        "user_name": checkName.value
		    },
		    success:function(data){//통신 성공
		        console.log("success data:"+data);
		        $('#check_name').text(data);
		    
		    },
		    error:function(data){//실패시 처리
		            console.log("error:"+data);
		    }
		});
	}); //checkName event
	
	
}); // DOMContentLoaded end
</script>
<style>
	body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f8f9fa; }
	    .container1 { max-width: 500px; margin: 50px auto; padding: 20px; background: #fff; border: 1px solid #dee2e6; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center; }
	    .container1 h2 { margin-bottom: 10px; }
	    .container1 form { display: flex; flex-direction: column; }
	    .container1 label { text-align: left; margin-bottom: 5px; }
	    .container1 input[type="text"], .container1 input[type="email"], .container1 input[type="password"], .container1 input[type="button"] { margin-bottom: 10px; padding: 10px; font-size: 16px; }
	    .container1 input[type="button"] { background-color: #007bff; color: white; border: none; cursor: pointer; }
	    .container1 input[type="button"]:hover { background-color: #0056b3; }
	    .container1 span { color: red; font-size: 14px; }
</style>
<jsp:include page="/cmn/menu.jsp"></jsp:include>
</head>
<body>
	<div class="container1">
    <h2>회원 가입</h2>
    <form action="#" method="post">
      <input type="hidden" id="work_div" name="work_div">
      <label for="user_name">이름</label><br>
      <input type="text" id="user_name" name="user_name" required><br>
      <span id="check_name" style="color: red;"></span><br>
      
      <label for="user_id">아이디</label><br>
      <input type="text" id="user_id" name="user_id" required><br>
      <span id="check_id" style="color: red;"></span><br>

      <label for="user_email">이메일</label><br>
      <input type="email" id="user_email" name="user_email" required><br>
      <span id="check_email" style="color: red;"></span><br>
      
      <label for="user_pw">비밀번호</label><br>
      <input type="password" id="user_pw" name="user_pw" required><br>
      <span id="check_password" style="color: red;"></span><br>

      <label for="confirm_password">비밀번호 확인</label><br>
      <input type="password" id="confirm_password" name="confirm_password" required><br>
      <span id="confirm_pw" style="color: red;"></span><br>


      <input type="button" value="가입하기" id="doSave" name="doSave">
    </form>
  </div>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
<jsp:include page="/cmn/footer.jsp"></jsp:include>
</html>