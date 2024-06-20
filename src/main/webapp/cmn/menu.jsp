<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>SEOUL_TRAVEL</title>
    <script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
        }
        .navbar {
            display: flex;
            align-items: center;
            padding: 10px;
            background-color: #f8f9fa;
            border-bottom: 1px solid #dee2e6;
        }
        .navbar .container {
            display: flex;
            align-items: center;
            flex-grow: 1;
        }
        .navbar-brand {
            display: flex;
            align-items: center;
        }
        .navbar-brand img {
            margin-right: 20px;
        }
        .nav {
            display: flex;
            align-items: center;
            list-style: none;
            padding: 0;
            margin: 0;
            flex-grow: 1;
        }
        .nav-item {
            margin-right: 20px;
        }
        .nav-item h4 {
            font-weight: bold;
            margin: 0;
        }
        .nav-link {
            text-decoration: none;
            color: inherit;
        }
        .login {
            margin-left: auto;
            color: inherit;
        }
    </style>
</head>
<body>
    <nav class="navbar">
        <div class="container">
            <a class="navbar-brand" href="/SEOUL_TRAVEL/resources/pages/main/mainpage.jsp">
                <img src="/SEOUL_TRAVEL/images/logo.png" alt="Bootstrap" width="130" height="80" >
            </a>
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/SEOUL_TRAVEL/resources/pages/content/travel_main.jsp"><h4>관광</h4></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/SEOUL_TRAVEL/resources/pages/content/restaurant_main.jsp"><h4>맛집</h4></a>
                </li>
            </ul>
            <div class="login">
                <a class="nav-link" href="/SEOUL_TRAVEL/resources/pages/user/login.jsp"><h4>로그인</h4></a>
            </div>
        </div>
    </nav>
</body>
</html>