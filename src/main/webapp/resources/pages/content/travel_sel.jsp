<%@page import="com.pcwk.tvl.user.UserDTO"%>
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
    UserDTO user = (UserDTO)session.getAttribute("user");
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
  
  //리뷰로 가기 버튼
  const moveToReviewBtn = document.querySelector("#moveToReview");
  
  const workDiv = document.querySelector("#work_div"); //작업구분
  const imgLink = document.querySelector("#imgLink");  //imgLink
  const title = document.querySelector("#title");      //title
  const addr = document.querySelector("#addr");        //addr
  
  //이벤트 핸들러 등록
  moveToListBtn.addEventListener("click", function(event){
    console.log('moveToListBtn click event'+event);
    moveToList();
  });
  
  //이벤트 핸들러 등록
  moveToReviewBtn.addEventListener("click", function(event){
    console.log('moveToReviewBtn click event'+event);
    moveToReview();
  });
  
  //--------------------------------------------------------
  function moveToList(){
    console.log('moveToList()');
    alert("게시 목록으로 이동 합니다.");
    window.location.href= "/SEOUL_TRAVEL/content/content.do?work_div=doRetrieve";
  }
  
  function moveToReview(){
    console.log('moveToReview()');
    
     <%if(user != null){ %> 
	    alert("리뷰 쓰기로 이동 합니다.");
	    console.log(${outVO.contentId});
	    
	    $.ajax({
	        type: "GET", 
	        url:"/SEOUL_TRAVEL/content/content.do",
	        asyn:"true",
	        dataType:"html",
	        data:{
	            "work_div":"moveToReview",
	            "contentid": $("#contentid").val()
	        },
	        success:function(response){//통신 성공
	            console.log("success response:"+response);
	        },
	        error:function(response){//실패시 처리
	                console.log("error:"+response);
	        }
	    });
	    
    window.location.href= "/SEOUL_TRAVEL/resources/pages/review/review_write.jsp";
    <%}else{%>
    	alert("로그인 후 작성 가능합니다.");
    	window.location.href= "/SEOUL_TRAVEL/resources/pages/user/login.jsp";
    <%}%>
    
  }
  
  initMap();
  
});//--DOMContentLoaded end 

function initMap() {
  var addr = '<c:out value="${outVO.addr}" escapeXml="true" />';
  
  var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
      level: 3 // 지도의 확대 레벨
    };
  
  // 지도를 생성합니다
  var map = new kakao.maps.Map(mapContainer, mapOption);

  // 주소-좌표 변환 객체를 생성합니다
  var geocoder = new kakao.maps.services.Geocoder();

  // 주소로 좌표를 검색합니다
  geocoder.addressSearch(addr, function(result, status) {
    // 정상적으로 검색이 완료됐으면
    if (status === kakao.maps.services.Status.OK) {
      var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

      // 결과값으로 받은 위치를 마커로 표시합니다
      var marker = new kakao.maps.Marker({
        map: map,
        position: coords
      });

      // 인포윈도우로 장소에 대한 설명을 표시합니다
      var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:6px 0;">' + addr + '</div>'
      });
      infowindow.open(map, marker);

      // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
      map.setCenter(coords);
    }
  });
}
</script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=53ca7ba233962018a7a8996d89d2622a&libraries=services"></script>
</head>
<body>
<!-- container -->
<div class="container">
  <!-- menu -->
  <jsp:include page="/cmn/menu.jsp"></jsp:include>
  <!-- menu end --------------------------------------------------------------->
  <!-- 제목 -->
  <div class="page-header  mb-4">
    <h4>여행지 정보</h4>
  </div>
  <!--// 제목 end ------------------------------------------------------------->
  
  <!-- outVO:${outVO} -->
  
  <!-- 버튼 -->
  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
      <input type="button" value="목록" class="btn btn-primary" id="moveToList">
      <input type="button" value="리뷰" class="btn btn-primary" id="moveToReview">
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
    
    <div class="row mb-3">
        <label for="addr" class="col-sm-2 col-form-label">지도</label>
        <div class="col-sm-10">
            <div id="map" style="width:100%;height:350px;"></div>
        </div>      
    </div>
    
    <div class="row mb-3" style="display: none;">
        <label for="contentId" class="col-sm-2 col-form-label">ContentId</label>
        <div class="col-sm-10">
          <input type="text" disabled="disabled" class="form-control" name="contentId" id="contentId" required="required" value="<c:out value='${outVO.contentId}' escapeXml='true' />">        
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
