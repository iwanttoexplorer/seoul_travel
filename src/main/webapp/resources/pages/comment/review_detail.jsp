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
		const saveCommentBtn = document.querySelector("#saveComment");
		const deleteCommentBtn = document.querySelector("#deleteComment");
		const updateCommentBtn = document.querySelector("#updateComment");
		const getCommentsBtn = document.querySelector("#getComments");
	});
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
            <div class="comment-form mb-4">
                <form action="/SEOUL_TRAVEL/comment/add" method="post">
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
    </div>
    <!-- //리뷰 내용 끝 -->

    <script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
