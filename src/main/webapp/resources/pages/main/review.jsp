<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 페이지</title>
    <style>
        body 
        { 
        font-family: Arial, sans-serif; margin: 0; padding: 0; 
        }
        header { background: #333; color: #fff; padding: 10px 0; display: flex; justify-content: space-between; align-items: center; }
        .logo { margin-left: 20px; cursor: pointer; }
        nav ul { list-style: none; display: flex; margin: 0; padding: 0; }
        nav ul li { margin: 0 15px; }
        nav ul li a { color: #fff; text-decoration: none; }
        .signup { margin-right: 20px; background-color: #444; padding: 10px 15px; border-radius: 5px; text-decoration: none; color: #fff; }
        .signup:hover { background-color: #555; }
        .container { max-width: 1200px; margin: 20px auto; padding: 0 20px; }
        .filter-bar { display: flex; justify-content: center; margin-bottom: 20px; }
        .filter-bar button { margin: 0 10px; padding: 10px 20px; border: none; background-color: #ddd; cursor: pointer; }
        .filter-bar button.active { background-color: #333; color: #fff; }
        .review-list { display: flex; flex-direction: column; }
        .review-item { display: flex; border: 1px solid #ddd; padding: 10px; margin-bottom: 10px; }
        .review-item img { width: 150px; height: 100px; object-fit: cover; margin-right: 10px; }
        .review-content { display: flex; flex-direction: column; justify-content: space-between; }
        .review-title { font-size: 18px; margin: 0 0 5px 0; }
        .review-meta { font-size: 14px; color: #555; }
        .pagination { display: flex; justify-content: center; margin-top: 20px; }
        .pagination button { margin: 0 5px; padding: 10px; border: none; background-color: #ddd; cursor: pointer; }
        .pagination button.active { background-color: #333; color: #fff; }
    </style>
</head>
<body>
    <header>
        <div class="logo" onclick="location.href='index.html'">사이트 이름</div>
        <a href="signup.html" class="signup">회원가입</a>
    </header>
    <div class="container">
        <div class="filter-bar">
            <button class="active" onclick="filterReviews('all')">전체</button>
            <button onclick="filterReviews('restaurants')">식당</button>
            <button onclick="filterReviews('attractions')">관광지</button>
        </div>
        <div class="review-list">
            <c:forEach var="review" items="${reviews}">
                <div class="review-item">
                    <img src="${review.imgLink}" alt="Review Image">
                    <div class="review-content">
                        <div>
                            <h3 class="review-title">${review.title}</h3>
                            <p class="review-meta">작성자: ${review.userId} | 작성일: ${review.regDt} | 추천수: ${review.readCnt}</p>
                        </div>
                        <p class="review-body">${review.comments}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="pagination">
            <button class="active">1</button>
            <button>2</button>
            <button>3</button>
        </div>
    </div>
    <script>
        function filterReviews(category) {
            const buttons = document.querySelectorAll('.filter-bar button');
            buttons.forEach(button => {
                if (button.textContent === category || (category === 'all' && button.textContent === '전체')) {
                    button.classList.add('active');
                } else {
                    button.classList.remove('active');
                }
            });
            // 실제 데이터 필터링 로직 추가 필요
        }
    </script>
</body>
</html>