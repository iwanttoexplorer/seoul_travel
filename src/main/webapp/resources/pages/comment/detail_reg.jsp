<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/cmn/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">

<title>Insert title here</title>
<script src = "/SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
<script src = "/SEOUL_TRAVEL/assets/js/common.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function(){
	console.log('DOMContentLoaded ');
	const workDiv= document.querySelector("#work_div");
	const content = document.querySelector("#content");
	const aboardSeq = document.querySelector("#aboardSeq");
	const userId = document.querySelector("#userId");//등록자
	const doSaveBtn = document.querySelector("#saveComment");
	
	doSaveBtn.addEventListener("click",function(){
		ajaxDoSave();
	});
	
});

function ajaxDoSave(){
	if(false==confirm('저장 하시겠습니까?')){
		return;
	}
	$.ajax({
	    type: "GET", 
	    url:"/SEOUL_TRAVEL/comment/comment.do",
	    asyn:"true",  //비동기 통신
	    dataType:"html",
	    data:{
	        "work_div":"ajaxDoSave",
	        "userId": userId.value,
	        "contents": contents.value
	    },
	    success:function(data){//통신 성공
	        console.log("success data:"+data);
	        
	        
	        
	    },
	    error:function(data){//실패시 처리
	        console.log("error:"+data);
	    }
	});  //ajax end
}


function doSave(){
	
}
function doDelete(){
	
}
function doUpdate(){
	
}
</script>
</head>
<body>

</body>
<script src = "/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</html>