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
    String message = (String) request.getAttribute("message");

    List<CommentDTO> comments = (List<CommentDTO>) request.getAttribute("comments");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<title>Seoul Travel - Review Detail</title>
<style>
        /* 추가적인 CSS 스타일링 */
        .comment-section {
            margin-top: 30px;
        }
        .comment-form {
            margin-bottom: 20px;
        }
        .reply-row {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
        }
        .reply-writer {
            font-weight: bold;
            margin-bottom: 2px;
        }
        .reply-date {
            font-size: 12px;
            color: #666;
        }
        .reply-content {
            margin-top: 5px;
        }
        .reply-btn-area {
            margin-top: 10px;
        }
        .btn {
            margin-right: 5px;
        }
</style>
<script src="/SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
<script>
	document.addEventListener("DOMContentLoaded",function(){
		console.log("commentUploaded");
		ajaxGetComments();
		const workDiv = document.querySelector("#work_div");//작업구분
		const saveCommentBtn = document.querySelector("#saveComment");
		saveCommentBtn.addEventListener("click", function(event) {
			console.log('saveCommentBtn click');
			event.preventDefault();
			let content = document.querySelector("#commentContent").value;
			let userId = "4kqD945"; // User ID should be dynamically set
			let aboardSeq = "1"; // document.querySelector("#aboardSeq").value;

			ajaxDoSave(userId, aboardSeq, content);
		});
		function ajaxGetComments(){
			$.ajax({
				type: "GET",
				url: "/SEOUL_TRAVEL/comment/comment.do",
				dataType:"json",
				data:{
					"work_div": "getComments",
					"aboardSeq": 1, //정적으로 호출기능 만들기
					"ajax": true
				},
				success:function(response){
					console.log("Sucess response",response);
					var replyList = $("#reply-list");
		            replyList.empty(); // 기존 댓글 목록 초기화
		         	// response에서 댓글 목록 배열(rList) 추출
		            var rList = response;

		            
		            if (rList.length>0) {
		                rList.forEach(function(reply) {
		                    // 댓글 행(li) 요소 생성
		                    var replyRow = $("<li></li>");
		                    replyRow.addClass("reply-row");

		                    // 작성자 정보 영역 생성
		                    var replyWriter = $("<p></p>");
		                    replyWriter.addClass("reply-writer");
		                    replyWriter.text( reply.userId);

		                    // 작성일 또는 수정일 정보 생성
		                    var replyDate = $("<p></p>");
		                    replyDate.addClass("reply-date");
		                    var dateLabel = reply.modDt ? "수정 " : "";
		                    var dateValue = reply.modDt ? reply.modDt : reply.regDt;
		                    replyDate.text(dateLabel + "" + dateValue);

		                    // 댓글 내용 정보 생성
		                    var replyContent = $("<p></p>");
		                    replyContent.addClass("reply-content");
		                    replyContent.html(reply.content); // HTML 태그 인식을 위해 innerHTML 대신 html() 사용

		                    // 작성자 정보, 작성일(수정일), 댓글 내용을 댓글 행에 추가
		                    replyRow.append(replyWriter);
		                    replyRow.append(replyContent);
		                    replyRow.append(replyDate);

		                    // 댓글 행을 댓글 목록에 추가
		                    replyList.append(replyRow);
		                    
		                    //수정 버튼
		                    var updateButton = $("<button></button>");
	                        updateButton.text("수정");
	                        updateButton.addClass("btn btn-primary update-comment");
	                        updateButton.on("click", function() {
	                            console.log("Update button clicked for comment:", reply.comSeq);
	                            if(reply.userId !=="4kqD945"){ // 이후에 동적으로 변경
	                            	alert("작성자가 아닙니다.");
	                            	return;
	                            }
	                            
	                            
	                           
	                            var newContent = prompt("수정할 내용을 입력하세요:", reply.content);
	                            if (newContent !== null) {
	                            	$.ajax({
	                        	        type: "POST",
	                        	        url: "/SEOUL_TRAVEL/comment/comment.do",
	                        	        data: {
	                        	            "work_div": "updateComment",
	                        	            "comSeq": reply.comSeq,
	                        	            "aboardSeq":1,
	                        	            "userId" : "4kqD945",
	                        	            "content": newContent,
	                        	            "ajax": true
	                        	        },
	                        	        success: function(response) {
	                        	            alert('수정하였습니다.');
	                        	        },
	                        	        error: function(error) {
	                        	            console.log("Error:", error);
	                        	        }
	                        	    });
	                            }else if(newContent ===null){
	                            	alert("취소하였습니다.");
	                            }
	                        });
	                        
	                     	// 삭제 버튼
	                        var deleteButton = $("<button></button>");
	                        deleteButton.text("삭제");
	                        deleteButton.addClass("btn btn-danger delete-comment");
	                        deleteButton.on("click", function() {
	                            console.log("Delete button clicked for comment:", reply.comSeq);
	                            if(reply.userId !=="4kqD945"){ // 이후에 동적으로 변경
	                            	alert("작성자가 아닙니다.");
	                            	return;
	                            }
	                            $.ajax({
	                    			type: "POST",
	                    			url: "/SEOUL_TRAVEL/comment/comment.do",
	                    			dataType: "text",
	                    			data: {
	                    				"work_div": "deleteComment",
	                    				"comSeq": reply.comSeq,
	                    				"userId": "4kqD945",
	                    				"aboardSeq": 1,
	                    				"ajax": true
	                    				
	                    			},
	                    			success: function(response) {
	                    				alert('삭제하였습니다.');
	                    				location.reload();
	                    				 

	                    			},
	                    			error: function(error) {
	                    				console.log("error:", error);
	                    				alert("댓글 삭제에 실패했습니다.");
	                    			}
	                    		});
	                        });
	                        
	                        replyRow.append(updateButton); // 수정 버튼을 댓글 행에 추가
	                        replyRow.append(deleteButton); // 삭제 버튼을 댓글 행에 추가
		                    
		                    
		                    
		                });
		            } else {
		                // rList가 없거나 배열이 아닌 경우 처리할 로직 추가
		                console.log("댓글 목록이 없습니다.");
		                replyList.append("<p>댓글이 없습니다. 첫 번째 댓글을 달아보세요!</p>");
		            }
		        },
		        error: function(error) {
		            console.log("Error:", error);
		            // 에러 처리 로직 추가
		        }				
				
			});//ajax end
		}
		
		
	});//document end
	function ajaxUpdateComment(comSeq, newContent,replyRow){
		$.ajax({
	        type: "POST",
	        url: "/SEOUL_TRAVEL/comment/comment.do",
	        data: {
	            "work_div": "updateComment",
	            "comSeq": comSeq,
	            "aboardSeq": "1",
	            "content": newContent,
	            "ajax": true
	        },
	        success: function(response) {
	            if (response == 1) { // 성공적으로 업데이트된 경우
	                console.log("댓글 수정");
	                
	            } else {
	                alert("댓글 수정에 실패했습니다.");
	            }
	        },
	        error: function(error) {
	            console.log("Error:", error);
	        }
	    });
	}
	
	function ajaxDoSave(userId, aboardSeq, content) {
		if (isEmpty(content)) {
			alert('댓글을 입력하세요.');
			return;
		}
		
		if (false == confirm('등록 하시겠습니까?')) {
			alert("취소하였습니다.");
			return;
		}

		$.ajax({
			type: "POST",
			url: "/SEOUL_TRAVEL/comment/comment.do",
			dataType: "json",
			data: {
				"work_div": "saveComment",
				"userId": userId,
				"aboardSeq": aboardSeq,
				"content": content
			},
			success: function(response) {
				console.log("success response:", response);
				alert('댓글이 등록되었습니다.');
				location.reload(); // 화면 새로고침
			},
			error: function(error) {
				console.log("error:", error);
				alert("댓글 저장에 실패했습니다.");
			}
		});
	}
		
		
	function isEmpty(str) {
		return (!str || 0 === str.length);
	}
			
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

            <%
                if (message != null) {
            %>
                <div class="alert alert-info" role="alert">
                    <%= message %>
                </div>
            <%
                }
            %>
            <div class="comment-form mb-4">
                <form id="comment_frm" method="post">
                    <div class="form-group">
                        <label for="commentContent">댓글</label>
                        <textarea id="commentContent" name="content" class="form-control" rows="3"></textarea>
                    </div>
                    <input type="button" value="댓글 달기" class="btn btn-primary mt-2" id="saveComment">>
                </form>
            </div>
			<!-- 댓글 목록 -->
             <ul id="reply-list" class="list-unstyled">
                <!-- AJAX를 통해 동적으로 댓글이 추가될 영역 -->
            </ul>
        </div>
        <!-- //댓글 끝 -------------------------------------------->
    <jsp:include page="/cmn/footer.jsp"></jsp:include>
    </div>
    <!-- //리뷰 내용 끝 -->

    <script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
