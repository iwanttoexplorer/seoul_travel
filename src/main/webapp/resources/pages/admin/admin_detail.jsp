<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.pcwk.tvl.user.UserDTO"%>
<%@ include file="/cmn/common.jsp" %>    
<%
    UserDTO user = (UserDTO) request.getAttribute("userDetail");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<title>✈︎관리자_회원 상세정보✈︎</title>
<script src="/SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
<script src="/SEOUL_TRAVEL/assets/js/common.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function(){
  const moveToListBtn = document.querySelector("#moveUserList");
  const doUpdateBtn = document.querySelector("#doUpdate");
  const doDeleteBtn = document.querySelector("#doDelete");
  
  const userName = document.querySelector("#userName");   //이름
  const userEmail = document.querySelector("#userEmail"); //이메일
  const userId = document.querySelector("#userId");       //아이디
  const userPw = document.querySelector("#userPw");       //비밀번호
  const regDt = document.querySelector("#regDt");         //가입일
  
  //이벤트 핸들러 
  moveToListBtn.addEventListener("click", function(){
	  moveUserList();
  });
  
  doUpdateBtn.addEventListener("click", function(){
    doUpdate();
  });
  
  doDeleteBtn.addEventListener("click", function(){
    doDelete();
  });

  function moveUserList(){
    window.location.href= "http://localhost:8080/SEOUL_TRAVEL/resources/pages/admin/admin_list.jsp";
  }
  
  function doUpdate(){
    if(isEmpty(userName.value) || isEmpty(userEmail.value) || isEmpty(userId.value) || isEmpty(userPw.value)){
        alert('빈 정보를 확인해주세요!');
        return;
    }
    
    $.ajax({
        type: "POST", 
        url:"/WEB02/admin/updateUser.do",
        asyn:"true",
        dataType:"json",
        data:{
              "userName": userName.value,
              "userEmail": userEmail.value,
	          "userId": userId.value,  
	          "userPw": userPw.value
        },
        success:function(response){
            const messageVO = JSON.parse(response);
            if("1" === messageVO.messageId){
                alert(messageVO.msgContents);
                window.location.href= "http://localhost:8080/SEOUL_TRAVEL/resources/pages/admin/admin_list.jsp";
            }else{
                alert(messageVO.msgContents);
            }
        },
        error:function(data){
            console.log("error:"+data);
        }
    });
  }
  
  function doDelete(){
    if(confirm('삭제 하시겠어요?')){
        $.ajax({
            type: "POST", 
            url:"/WEB02/admin/deleteUser.do",
            asyn:"true",
            dataType:"json",
            data:{"userId": userId.value},
            success:function(response){
                const messageVO = JSON.parse(response);
                if("1" === messageVO.messageId){
                    alert(messageVO.msgContents);
                    window.location.href= "http://localhost:8080/SEOUL_TRAVEL/resources/pages/admin/admin_list.jsp";
                }else{
                    alert(messageVO.msgContents);
                }
            },
            error:function(data){
                console.log("error:"+data);
            }
        });
    }
  }
});
</script>
</head>
<body>
<div class="container">
  <div class="page-header mb-4">
    <h2>✎회원 상세정보</h2>
  </div>

  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
      <input type="button" value="목록" class="btn btn-primary" id="moveUserList">
      <input type="button" value="수정" class="btn btn-primary" id="doUpdate">
      <input type="button" value="삭제" class="btn btn-primary" id="doDelete">
  </div>
  
  <form class="form-horizontal">
    <div class="row mb-3">
        <label for="userName" class="col-sm-2 col-form-label">이름</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" name="userName" id="userName" value="${user.userName}" required>
        </div>
    </div>

    <div class="row mb-3">
        <label for="userEmail" class="col-sm-2 col-form-label">이메일</label>
        <div class="col-sm-10">
          <input type="email" class="form-control" name="userEmail" id="userEmail" value="${user.userEmail}" required>
        </div>
    </div>

    <div class="row mb-3">
        <label for="userId" class="col-sm-2 col-form-label">아이디</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" name="userId" id="userId" value="${user.userId}" required>
        </div>
    </div>

    <div class="row mb-3">
        <label for="userPw" class="col-sm-2 col-form-label">비밀번호</label>
        <div class="col-sm-10">
          <input type="password" class="form-control" name="userPw" id="userPw" value="${user.userPw}" required>
        </div>
    </div>
    
    <div class="row mb-3">
        <label for="regDt" class="col-sm-2 col-form-label">가입일</label>
        <div class="col-sm-10">
          <input type="date" class="form-control" name="regDt" id="regDt" value="${user.regDt}" readonly>
        </div>
    </div>
  </form>
</div>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script> 
</body>
</html>
