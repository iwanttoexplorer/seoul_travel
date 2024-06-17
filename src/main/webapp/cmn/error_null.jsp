<%@ page language="java" contentType="text/html; charset=UTF-8"
    isErrorPage="true"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
    <h2>Error Null 페이지</h2>
    <hr/>
    <p>요청 처리 과정에 에러가 발생 했습니다.<br/> 빠른 시간에 처리 하도록 하겠습니다.</p>
    
    에러타입:<%= exception.getClass().getName() %><br/>
    에러메시지:<%= exception.getMessage() %><br/>
    
    
</body>
</html>