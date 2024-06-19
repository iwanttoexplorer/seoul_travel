<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>    
<%@page import="java.util.List"%>
<%@page import="com.pcwk.tvl.user.UserDao"%>
<%@page import="com.pcwk.tvl.user.UserDTO"%>
<%@page import="com.pcwk.ehr.cmn.SearchDTO"%>
<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%
    List<UserDTO> userList = (List<UserDTO>)request.getAttribute("userList");
    SearchDTO searchCon = (SearchDTO)request.getAttribute("searchCon");
    
    // 디버깅: userList가 null인지 확인
    if(userList == null) {
        out.println("<p>userList is null</p>");
    } else if(userList.isEmpty()) {
        out.println("<p>userList is empty</p>");
    } else {
        out.println("<p>userList size: " + userList.size() + "</p>");
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/ProJ/assets/css/bootstrap.css">
<title>✈︎관리자_회원 목록✈︎</title>
<link rel="stylesheet" href="/ProJ/css/poster.css">
<script src="/ProJ/assets/js/jquery_3_7_1.js"></script>
<script>
    // 회원 상세 보기 함수
    function viewUser(userId) {
        let frm = document.getElementById("userForm");
        frm.action = "<%=request.getContextPath()%>/admin/Admin_MemDetail.jsp";
        frm.userId.value = userId;
        frm.submit();
    }
</script>
</head>
<body>
<div class="container">
    <div class="page-header mb-4">
        <h2>🜸회원 목록</h2>
    </div>   
    <!-- 회원 목록 테이블 -->
    <table class="table table-striped table-hover table-bordered">
        <thead>
            <tr>
                <th>이름</th>
                <th>이메일</th>
                <th>아이디</th>
            </tr>
        </thead>
        <tbody>
            <% 
            if (userList != null && !userList.isEmpty()) {
                for (UserDTO user : userList) {
            %>
            <tr>
                <td><%= user.getUserName() %></td>
                <td><%= user.getUserEmail() %></td>
                <td><%= user.getUserId() %></td>
                <td>
                    <button class="btn btn-outline-primary btn-sm" onclick="viewUser('<%= user.getUserId() %>')">상세정보</button>
                </td>  
            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="4">회원이 없습니다.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
    
    <!-- 페이징 -->
    <nav aria-label="Page navigation example">
        <%-- 페이징 로직 추가하기 --%>
    </nav>
</div>

<!-- Hidden form for viewUser function -->
<form id="userForm" method="post" style="display:none;">
    <input type="hidden" name="userId" id="userId">
</form>

<script src="/WEB02/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
