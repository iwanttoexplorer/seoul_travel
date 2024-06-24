<%@page import="org.apache.jasper.runtime.PageContextImpl"%>
<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@page import="com.pcwk.tvl.content.ContentDTO"%>
<%@page import="com.pcwk.tvl.category.CategoryDTO"%>
<%@page import="com.pcwk.ehr.cmn.SearchDTO"%>
<%@page import="com.pcwk.ehr.cmn.ContentsSearchDTO"%>
<%@page import="com.pcwk.tvl.content.ContentDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    List<ContentDTO> list = (List<ContentDTO>)request.getAttribute("list");
    ContentsSearchDTO searchCon = (ContentsSearchDTO)request.getAttribute("vo");
    List<ContentsSearchDTO> pageCode = (List<ContentsSearchDTO>)request.getAttribute("page");
    List<ContentsSearchDTO> searchCode = (List<ContentsSearchDTO>)request.getAttribute("boardSearchList");
    
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<script src="/SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function(){
  console.log('DOMContentLoaded---');
  
  //목록 버튼
  const moveToListBtn = document.querySelector("#moveToList");
  
  const workDiv = document.querySelector("#work_div"); //작업구분
  const imgLink = document.querySelector("#imgLink");  //imgLink
  const title = document.querySelector("#title");      //title
  const addr = document.querySelector("#addr");        //addr
  
  //이벤트 핸들러 등록
  moveToListBtn.addEventListener("click", function(event){
    console.log('moveToListBtn click event'+event);
    moveToList();
  });
  
  //--------------------------------------------------------
  function moveToList(){
    console.log('moveToList()');
    alert("게시 목록으로 이동 합니다.");
    window.location.href= "/SEOUL_TRAVEL/content/content.do?work_div=doRetrieve";
  }
  
});//--DOMContentLoaded end 

</script>
</head>
<body>
<!-- container -->
<div class="container">
  <!-- menu -->
  <jsp:include page="/cmn/menu.jsp"></jsp:include>
  <!-- menu end --------------------------------------------------------------->
  <!-- 제목 -->
  <div class="page-header  mb-4">
    <h2>여행지 정보</h2>
  </div>
  <!--// 제목 end ------------------------------------------------------------->
  
  <!-- outVO:${outVO} -->
  
  <!-- 버튼 -->
  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
      <input type="button" value="목록" class="btn btn-primary" id="moveToList">
  </div>
  <!--// 버튼 ----------------------------------------------------------------->
  
	  <!-- form -->
  <form action="#" class="form-horizontal">
    <input type="hidden" name="work_div" id="work_div">
    <div class="row mb-3">
        <label for="imgLink" class="col-sm-2 col-form-label">이미지</label>
        <div class="col-sm-10">
          <img src="<c:url value='${outVO.imgLink}' />" alt="imgLink" />
        </div>      
    </div>
    
    <div class="row mb-3">
        <label for="title" class="col-sm-2 col-form-label">장소명</label>
        <div class="col-sm-10">
          <input disabled="disabled" type="text" class="form-control" name="title" id="title"  value="<c:out value='${outVO.title}' escapeXml='true' />">
        </div>      
    </div>
    
    <div class="row mb-3">
        <label for="addr" class="col-sm-2 col-form-label">주소</label>
        <div class="col-sm-10">
          <input type="text" disabled="disabled" class="form-control" name="addr" id="addr" required="required" value="<c:out value='${outVO.addr}' escapeXml='true' />">        
        </div>      
    </div>  
    
    
  </form>
  
  <!--// form end ------------------------------------------------------------->
  <jsp:include page="/cmn/footer.jsp"></jsp:include>
</div>
<!--// container end ---------------------------------------------------------->    
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>   
</body>
</html>