<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 작성</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; }
        .container { max-width: 800px; margin: 20px auto; padding: 0 20px; }
        form { display: flex; flex-direction: column; }
        label { margin-bottom: 5px; font-weight: bold; }
        input[type="text"], textarea { margin-bottom: 10px; padding: 10px; font-size: 16px; width: 100%; }
        button { padding: 10px 20px; background-color: #333; color: #fff; border: none; cursor: pointer; }
        button:hover { background-color: #555; }
    </style>
</head>
<body>
    <div class="container">
        <h1>리뷰 작성</h1>
        <form action="/WEB02/review.do" method="post">
            <input type="hidden" name="work_div" value="saveReview">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" required>
            
            <label for="content_id">내용 ID</label>
            <input type="text" id="content_id" name="content_id" required>
            
            <label for="user_id">사용자 ID</label>
            <input type="text" id="user_id" name="user_id" required>
            
            <label for="img_link">이미지 링크</label>
            <input type="text" id="img_link" name="img_link">
            
            <label for="comments">내용</label>
            <textarea id="comments" name="comments" rows="10" required></textarea>
            
            <button type="submit">저장</button>
        </form>
    </div>
</body>
</html>
