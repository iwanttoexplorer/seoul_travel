<%@page import="com.pcwk.tvl.comment.CommentDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/cmn/common.jsp" %>
<%
	List<CommentDTO> list =(List<CommentDTO>)request.getAttribute("list"); 
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel = "stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<title>Seoul_travel</title>
<script src = "SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
	<header>
        <div class="logo" >SEOUL TRAVEL</div>
        <nav>
            <ul class="nav-links">
                <li><a>홈</a></li>
                <li><a>리뷰 목록</a></li>
                <!-- 다른 메뉴 항목들 추가 -->
            </ul>
        </nav>
	</header>
	<!-- 리뷰 내용시작 -->
	<div class = "contatiner">
		<div class = "review-header">
		
			<div class="review-meta">
				
			</div>
		</div>
	</div>
	
	<!-- 댓글 시작 -->
	<div class="comment-form">
		
		
	</div>
	<!-- //댓글 끝 -------------------------------------------->
</body>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>

</html>