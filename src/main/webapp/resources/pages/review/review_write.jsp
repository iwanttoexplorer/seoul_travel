<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/cmn/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<title>리뷰 작성</title>
<script src = "/SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
<script src = "/SEOUL_TRAVEL/assets/js/common.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function(){
    const saveBtn = document.querySelector("#saveBtn");
    
    saveBtn.addEventListener("click",function(){
        ajaxDoSave();
    });
    
    function ajaxDoSave(){
        if(!confirm('저장 하시겠습니까?')){
            return;
        }
        $.ajax({
            type: "POST", 
            url:"/SEOUL_TRAVEL/review/review.do",
            dataType:"json",
            data:{
                "contentid": document.querySelector("#contentid").value,
                "user_id": document.querySelector("#user_id").value,
                "img_link": document.querySelector("#img_link").value,
                "comments": document.querySelector("#comments").value,
                "title": document.querySelector("#title").value
            },
            success:function(response){
                if(response.success){
                    alert("리뷰가 저장되었습니다.");
                    window.location.href = "/SEOUL_TRAVEL/review/review_list.jsp";
                } else {
                    alert("리뷰 저장에 실패했습니다.");
                }
            },
            error:function(){
                alert("오류가 발생했습니다.");
            }
        });
    }
});
</script>
</head>
<body>
<div class="container">
    <h2>리뷰 작성</h2>
    <form id="reviewForm">
        <div class="mb-3">
            <label for="contentid" class="form-label">Content ID</label>
            <input type="text" class="form-control" id="contentid" name="contentid">
        </div>
        <div class="mb-3">
            <label for="user_id" class="form-label">User ID</label>
            <input type="text" class="form-control" id="user_id" name="user_id">
        </div>
        <div class="mb-3">
            <label for="img_link" class="form-label">Image Link</label>
            <input type="text" class="form-control" id="img_link" name="img_link">
        </div>
        <div class="mb-3">
            <label for="comments" class="form-label">Comments</label>
            <textarea class="form-control" id="comments" name="comments"></textarea>
        </div>
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title">
        </div>
        <button type="button" class="btn btn-primary" id="saveBtn">Save</button>
    </form>
</div>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
