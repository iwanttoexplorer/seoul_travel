<%@page import="com.pcwk.tvl.comment.CommentDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/cmn/common.jsp" %>
<%
    // Dummy data for demonstration. Replace with actual data from your servlet/controller.
    String reviewTitle = "Sample Review Title";
    String reviewContent = "This is the content of the sample review. It has some details about the review.";
    String reviewAuthor = "Author Name";
    String reviewDate = "2024-06-18";
    String message = (String) request.getAttribute("message");

    List<CommentDTO> comments = (List<CommentDTO>) request.getAttribute("comments");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<title>Seoul Travel - Review Detail</title>
<script src="/SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
<script>
	document.addEventListener("DOMContentLoaded",function(){
		console.log("commentUploaded");
		const workDiv = document.querySelector("#work_div");//작업구분
		const saveCommentBtn = document.querySelector("#saveComment");
		const deleteCommentBtn = document.querySelector("#deleteComment");
		const updateCommentBtn = document.querySelector("#updateComment");
		const getCommentsBtn = document.querySelector("#getComments");
		const content = document.querySelector("#content");
		const aboardSeq = document.querySelector("#aboardSeq");
		const userId = document.querySelector("#userId");
		const comSeq = document.querySelector("#comSeq");
		saveCommentBtn.addEventListener("click",function(){
			console.log('saveCommentBtn click');
			let frm = document.getElementById("comment_frm");
			frm.work_div.value = "saveComment";
			frm.aboard_Seq.value = "aboardSeq";
			frm.content.value = "content";
			console.log("frm.user_id.value: "+frm.user_id.value);
			console.log("frm.reg_dt.value: "+frm.reg_dt.value);
			frm.action = "/SEOUL_TRAVEL/comment/comment.do";
			frm.submit();
		});
		deleteCommentBtn.addEventListener("click",function(){
			ajaxDoDelete();
		});
		updateCommentBtn.addEventListener("click",function(){
			ajaxDoUpdate();
		});
		
	});
	
	function ajaxDoSave(){
		if(isEmpty(content.value)==true){
			content.focus();
			alert('댓글을 입력하세요.')
			return;
		}
		if(false==confirm('등록 하시겠습니까?')){
			return;
		}
		$.ajax({
		    type: "GET", 
		    url:"/SEOUL_TRAVEL/comment/comment.do",
		    asyn:"true",  //비동기 통신
		    dataType:"html",
		    data:{
		        "work_div":"saveComment",
		        "userId": userId.value,
		        "aboardSeq": aboardSeq.value,
		        "content": content.value
		    },
		    success:function(data){//통신 성공
		        console.log("success data:"+data);
		        const messageVO = JSON.parse(data);
		        console.log("messageId:"+messageVO.messageId);
		        console.log("msgContents:"+messageVO.msgContents);
		        
		        if(message === '등록 성공'){
		        	alert(data);
		        	window.location.href ="/SEOUL_TRAVEL/comment/comment.do?work_div=saveComment";
		        }else{
		        	alert(data);
		        }
		        
		    },
		    error:function(data){//실패시 처리
		        console.log("error:"+data);
		    }
		})
	}
	function doSave(){
		console.log("doSave()");
		let frm = document.getElementById("comment_frm");
		frm.work_div.value = "saveComment";
		frm.aboard_Seq.value = "aboardSeq";
		frm.content.value = "content";
		console.log("frm.user_id.value: "+frm.user_id.value);
		console.log("frm.reg_dt.value: "+frm.reg_dt.value);
		console.log("frm.action: ");
	}
	function ajaxDoDelete(){
		if(false==confirm('삭제 하시겠습니까?')){
			return;
		}
	}
	function ajaxDoUpdate(){
		if(false==confirm('수정 하시겠습니까?')){
			return;
		}
	}
	function ajaxDoGet(){
		
	}
		
</script>
</head>
<body>
    <header>
        <div class="logo">SEOUL TRAVEL</div>
        <nav>
            <ul class="nav-links">
                <li><a href="/SEOUL_TRAVEL/home">홈</a></li>
                <li><a href="/SEOUL_TRAVEL/reviewList">리뷰 목록</a></li>
                <!-- 다른 메뉴 항목들 추가 -->
            </ul>
        </nav>
    </header>

    <!-- 리뷰 내용 시작 -->
    <div class="container mt-5">
        <div class="review-header">
            <h1><%= reviewTitle %></h1>
            <div class="review-meta">
                <span>작성자: <%= reviewAuthor %></span>
                <span>작성일: <%= reviewDate %></span>
            </div>
            <div class="review-content mt-4">
                <p><%= reviewContent %></p>
            </div>
        </div>

        <!-- 댓글 시작 -->
        <div class="comment-section mt-5">
            <h2>댓글</h2>
            <%
                if (message != null) {
            %>
                <div class="alert alert-info" role="alert">
                    <%= message %>
                </div>
            <%
                }
            %>
            <div class="comment-form mb-4">
                <form action="/SEOUL_TRAVEL/comment/comment.do?work_div=saveComment" method="post">
                    <div class="form-group">
                        <label for="commentContent">댓글 내용</label>
                        <textarea id="commentContent" name="content" class="form-control" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">댓글 달기</button>
                </form>
            </div>

            <div class="comment-list">
                <%
                    if (comments != null && !comments.isEmpty()) {
                        for (CommentDTO comment : comments) {
                %>
                <div class="comment-item mb-3">
                    <div class="comment-meta">
                        <span>작성자: <%= comment.getUserId() %></span>
                        <span>작성일: <%= comment.getRegDt() %></span>
                    </div>
                    <div class="comment-content">
                        <p><%= comment.getContent() %></p>
                    </div>
                </div>
                <%
                        }
                    } else {
                %>
                <p>댓글이 없습니다. 첫 번째 댓글을 달아보세요!</p>
                <%
                    }
                %>
            </div>
        </div>
        <!-- //댓글 끝 -------------------------------------------->
    <jsp:include page="/cmn/footer.jsp"></jsp:include>
    </div>
    <!-- //리뷰 내용 끝 -->

    <script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>