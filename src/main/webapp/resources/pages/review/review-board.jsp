<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Review Board</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .pagination {
            text-align: center;
            margin-top: 20px;
        }
        .pagination a {
            margin: 0 5px;
            text-decoration: none;
            color: #333;
        }
        .pagination a.active {
            font-weight: bold;
            color: blue;
        }
        .buttons {
            text-align: right;
            margin-top: 20px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 14px;
            font-weight: bold;
            text-align: center;
            color: #fff;
            background-color: #007BFF;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            cursor: pointer;
            margin-left: 5px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>리뷰 게시판</h2>
    <div class="buttons">
        <a href="#" class="btn">수정</a>
        <a href="review-form.jsp" class="btn">등록</a>
    </div>
    <table>
        <thead>
            <tr>
                <th>no</th>
                <th>제목</th>
                <th>등록자</th>
                <th>등록일</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="review" items="${list}">
                <tr>
                    <td>${review.aboardSeq}</td>
                    <td>${review.title}</td>
                    <td>${review.userId}</td>
                    <td>${review.regDt}</td>
                    <td>${review.readCnt}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination">
        <a href="#">&#171;</a>
        <a href="#">1</a>
        <a href="#" class="active">2</a>
        <a href="#">3</a>
        <a href="#">&#187;</a>
    </div>
</body>
</html>
