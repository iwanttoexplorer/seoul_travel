<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 작성/수정</title>
</head>
<body>
    <h1>리뷰 작성/수정</h1>
    <form action="ReviewServlet?action=update" method="post">
        <input type="hidden" name="aboardSeq" value="${review.aboardSeq}">
        <label for="title">제목:</label>
        <input type="text" id="title" name="title" value="${review.title}">
        <br>
        
