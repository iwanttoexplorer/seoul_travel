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
	const userId = document.querySelector("#userId");
	const doSaveBtn = document.querySelector("#doSave");
	const doDeleteBtn = document.querySelector("#doDelete");
	const doUpdateBtn = document.querySelector("#doUpdate");
	doSaveBtn.addEventListener("click",function(){
		ajaxDoSave();
	});
	
});
function ajaxDoSave(){
	if(isEmpty(content.value)==true){
		content.focus();
		alert('내용을 입력하세요.');
		return;
	}
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