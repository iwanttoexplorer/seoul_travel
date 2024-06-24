<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@page import="java.util.List"%>
<%@page import="com.pcwk.tvl.user.UserDao"%>
<%@page import="com.pcwk.tvl.user.UserDTO"%>
<%@page import="com.pcwk.ehr.cmn.SearchDTO"%>
<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@ include file="/cmn/common.jsp" %>  
<%
    List<UserDTO> list = (List<UserDTO>)request.getAttribute("userList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<title>✈︎관리자_회원 목록✈︎</title>
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/poster.css">
<script src="/SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
<style>
    #user-list {
        width: 100%; /* 테이블 전체 너비를 100%로 설정 */
    }

    #user-list th, #user-list td {
        text-align: center; /* 열의 텍스트 가운데 정렬 */
        padding: 10px; /* 셀의 패딩 설정 */
    }

    #user-list .userName {
        width: 20%; /* 이름 열의 너비를 20%로 설정 */
    }

    #user-list .userEmail {
        width: 30%; /* 이메일 열의 너비를 30%로 설정 */
    }
</style>

<script>
document.addEventListener("DOMContentLoaded", function() {
    doRetrieve();
});

function doRetrieve() {
    $.ajax({
        type: "POST",
        url: "/SEOUL_TRAVEL/admin/admin.do",
        dataType: "json",
        data: {
            "work_div": "doRetrieve",
            "ajax": true
        },
        success: function(response) {
            console.log("success response:", response);
            var uList = $('#user-list tbody');
            uList.empty();

            var userList = response;

            if (userList.length > 0) {
                userList.forEach(function(user) {
                    var userRow = $("<tr></tr>");
                    userRow.addClass("user-row");

                    var userName = $("<td></td>").addClass("userName").text(user.userName);
                    var userEmail = $("<td></td>").addClass("userEmail").text(user.userEmail);
                    var userId = $("<td></td>").addClass("userId").html(user.userId);
                    var userPw = $("<td></td>").addClass("userPw").html(user.userPw);
                    var regDt = $("<td></td>").addClass("regDt").html(user.regDt);

                    var editButton = $("<button>수정</button>").addClass("btn btn-primary btn-sm ml-1");
                    editButton.click(function() {
                        // 편집 동작 구현
                        doUpdate(user.userId); 
                    });

                    var deleteButton = $("<button>삭제</button>").addClass("btn btn-danger btn-sm ml-1");
                    deleteButton.click(function() {
                        // 삭제 동작 구현
                        doDelete(user.userId); 
                    });

                    var buttonCell = $("<td></td>").append(editButton).append(deleteButton);

                    userRow.append(userName, userEmail, userId, userPw, regDt, buttonCell);
                    uList.append(userRow);
                });
            }
        },
        error: function(data) {
            console.error("error:", data);
            var tbody = $('#user-list tbody');
            var row = '<tr><td colspan="5">회원 목록을 불러오는 데 실패했습니다.</td></tr>';
            tbody.append(row);
        }
    });
}

function doUpdate(userId){
	if(confirm('회원 정보가 수정됩니다!')){
    $.ajax({
        type: "POST", 
        url:"/SEOUL_TRAVEL/admin/admin.do",
        asyn:"true",
        dataType:"json",
        data:{
	        	"work_div": "doUpdate",
	            "ajax": true
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

function doDelete(usersId){
    if(confirm('회원 정보가 삭제됩니다!')){
        $.ajax({
            type: "POST", 
            url:"/SEOUL_TRAVEL/admin/admin.do",
            dataType:"json",
            data:{
	            	"work_div": "doDelete",
	                "ajax": true
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
  }

/* function doSelectOne(){
    // 폼 요소 선택
    let frm = document.getElementById("userForm");
    if (frm) {
        // 폼 데이터 설정
        frm.work_div.value = "doSelectOne";
        frm.user_id.value = "";  // 유저 아이디 설정
        frm.action = "/admin/admin.do";
        
        // 폼 제출
        frm.submit();
    } else {
        console.error("Element with id 'board_frm' not found.");
    }

} */

</script>
</head>
<body>
<div class="container">
    <div class="page-header mb-4">
        <h2>회원 목록</h2>
    </div>   
   <!-- 회원 목록을 표시할 테이블 -->
    <table id="user-list" class="user-table">
        <thead>
            <tr>
                <th>이름</th>
                <th>이메일</th>
                <th>아이디</th>
                <th>비밀번호</th>
                <th>가입일</th>
                <th>정보관리</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
    
<!-- 회원 상세 정보를 전달할 폼 -->
<form id="userForm" method="post" style="display:none;">
    <input type="hidden" name="userId" id="userId">
    <input type="hidden" name="work_div" id="work_div">
</form>

<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
