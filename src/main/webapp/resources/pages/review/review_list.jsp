<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/cmn/common.jsp" %>
<%@page import="java.util.List"%>
<%@page import="com.pcwk.tvl.review.ReviewDTO"%>
<%
    // Dummy data for demonstration. Replace with actual data from your servlet/controller.
    List<ReviewDTO> list = (List<ReviewDTO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<title>리뷰 목록</title>
<style>
        /* 추가적인 CSS 스타일링 */
        .comment-section {
            margin-top: 30px;
        }
        .comment-form {
            margin-bottom: 20px;
        }
        .review-table {
            width: 100%;
            border-collapse: collapse;
        }
        .review-table th, .review-table td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        .review-table th {
            background-color: #f2f2f2;
            text-align: left;
        }
</style>
<script src="/SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
<script>
    document.addEventListener("DOMContentLoaded",function(){
      console.log("reviewtUploaded");
      ajaxdoRetrieve();
    }); // riveiwList end
    
    function ajaxdoRetrieve(){
      $.ajax({
        type:"POST",
        url:"/SEOUL_TRAVEL/review/review.do",
        dataType:"json",
        data:{
          "work_div": "doRetrieve",
          "aboardSeq": 1,
          "ajax": true
        },
        success: function(response) {
            console.log("success response: ", response);
            var reviewList = $("#review-list tbody");
            reviewList.empty(); // 기존 리뷰 목록 초기화

            // response에서 댓글 목록 배열(rList) 추출
            var rList = response;

            if (rList.length > 0) {
                rList.forEach(function(review) {
                    // 리뷰 행 요소 생성
                    var reviewRow = $("<tr></tr>");
                    reviewRow.addClass("review-row");

                    // 작성자 정보 영역 생성
                    var reviewWriter = $("<td></td>");
                    reviewWriter.addClass("review-writer");
                    reviewWriter.text(review.userId);

                    // 작성일 정보 생성
                    var reviewDate = $("<td></td>");
                    reviewDate.addClass("review-regDt");
                    reviewDate.text(review.regDt); // 리뷰 작성일 추가

                    // 리뷰 내용 정보 생성
                    var reviewComments = $("<td></td>");
                    reviewComments.addClass("review-comments");
                    reviewComments.html(review.comments); // HTML 태그 인식을 위해 html() 사용

                    // 작성자 정보, 리뷰 내용, 작성일(수정일)을 댓글 행에 추가
                    reviewRow.append(reviewWriter);
                    reviewRow.append(reviewComments);
                    reviewRow.append(reviewDate);

                    // 리뷰 행을 리뷰 목록에 추가
                    reviewList.append(reviewRow);
                });
            }
        },
        error: function(error) {
            console.log("Error:", error);
        }
        });// ajax end
        }// ajaxdoRetrieve() end

</script>
</head>
<body>
<div class="container">
    <h2>리뷰 목록</h2>
    <table id="review-list" class="review-table">
        <thead>
            <tr>
                <th>작성자</th>
                <th>내용</th>
                <th>작성일</th>
                <th>추천수</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
            <!-- AJAX를 통해 동적으로 댓글이 추가될 영역 -->
        </tbody>
    </table>
</div>
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
