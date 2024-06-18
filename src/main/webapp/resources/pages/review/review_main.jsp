<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Review Page</title>
    <style>
        .nav-bar {
            display: flex;
            justify-content: center;
            background-color: #333;
            padding: 10px;
        }
        .nav-bar a {
            color: white;
            padding: 10px;
            text-decoration: none;
            font-size: 20px;
            margin: 0 5px;
        }
        .nav-bar a:hover {
            background-color: #ddd;
            color: black;
        }
        .content {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="nav-bar">
        <a href="#">전체</a>
        <a href="#">식당</a>
        <a href="#">관광지</a>
        <a href="review-board.jsp">리뷰</a>
    </div>
    <div class="content">
        <!-- 리뷰 내용이 여기에 표시될 것입니다. -->
    </div>
</body>
</html>
