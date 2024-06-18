<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/cmn/common.jsp" %>
<%@page import="java.util.List"%>
<%@page import="com.pcwk.tvl.review.ReviewDTO"%>
<%
    // Dummy data for demonstration. Replace with actual data from your servlet/controller.
    List<ReviewDTO> reviewList = (List<ReviewDTO>) request.getAttribute("reviewList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<title>리뷰 목록</title>
</head>
<body>
<div class="container">
    <h2>리뷰 목록</h2>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
        <% if (reviewList != null) { 
            for (ReviewDTO review : reviewList) { %>
            <tr>
                <td><%= review.getAboardSeq() %></td>
                <td><a href="reviewDetail.jsp?aboardSeq=<%= review.getAboardSeq() %>"><%= review.getTitle() %></a></td>
                <td><%= review.getUserId() %></td>
                <td><%= review.getRegDt() %></td>
                <td><%= review.getReadCnt() %></td>
            </tr>
        <% } } else { %>
            <tr>
                <td colspan="5">등록된 리뷰가 없습니다.</td>
            </tr>
        <% } %>
        </tbody>
    </table>
</div>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
