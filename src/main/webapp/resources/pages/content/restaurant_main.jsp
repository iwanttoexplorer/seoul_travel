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
<%@ include file="/cmn/common.jsp" %>    
<%
    List<ContentDTO> list = (List<ContentDTO>)request.getAttribute("list");
    ContentsSearchDTO searchCon = (ContentsSearchDTO)request.getAttribute("vo");
    List<ContentsSearchDTO> pageCode = (List<ContentsSearchDTO>)request.getAttribute("page");
    List<ContentsSearchDTO> searchCode = (List<ContentsSearchDTO>)request.getAttribute("boardSearchList");
    
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    .img {
           width: 100%; 
           height: 200px; 
           object-fit: cover;
    }
</style>

<title>SEOUL_TRAVEL</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<script src="/SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function(){
  console.log('DOMContentLoaded');
  
  //조회 버튼 
  const doRetrieve2Btn = document.querySelector("#doRetrieve2");
  console.log('doRetrieve2Btn');
  
  //table 자식모두 tbody선택
  const rows = document.querySelectorAll("#boardList tbody tr");

  const buttons = document.querySelectorAll(".btn-outline-success") ; 
  
  //이벤트 핸들러
  doRetrieve2Btn.addEventListener('click',function(){
    console.log('doRetrieve2Btn click');
  // 폼 객체 생성
    const frm = document.querySelector('#board_frm');
    console.log(frm);
    
    // 폼 데이터 설정   //doRetrieve2
    frm.work_div.value = "doRetrieve2";
    
    // 서버로 보낼 url 설정
      frm.action = "/SEOUL_TRAVEL/content/content.do";
      
      //서버로 폼 전송
      frm.submit();
    
  });//doRetrieve2Btn end -------
  
  buttons.forEach(function(button){
    button.addEventListener('click',function(){
      let hiddenInfo = this.getAttribute('data-hidden-info');
      console.log('hiddenInfo:'+hiddenInfo);
      doSelectOne(hiddenInfo);
    });
    
  }); 
  
  //이벤트 헨들러(더블 클릭시 이동)
  rows.forEach(function(row){
      //double click
      row.addEventListener('dblclick',function(){
        console.log('row click');
        //this(tr) 자식 (td: 마지막 위치)
        let contentIdValue = this.querySelector('td:last-child').textContent.trim();
        console.log('contentIdValue:'+contentIdValue);
        
        //폼 요소 선택
        let frm = document.getElementById("board_frm");
        
        //폼 데이터 설정
        frm.work_div.value = "doSelectOne2";
        
        //contentId
        frm.contentId.value = contentIdValue;
        frm.action = "<%=cPath%>" + "/content/content.do";
        
        // 폼 제출
        frm.submit();
        
      });
      
    });   
  
  
}); //DOMContentLoaded

//페이징 조회
function pageRetrieve(url, pageNo){
    console.log("url:"+url);
    console.log("pageNo:"+pageNo);
    
      // 폼 요소 선택
      let frm = document.getElementById("board_frm");
      frm.work_div.value = "doRetrieve2";
      
      // 폼 데이터 설정
      frm.page_no.value = pageNo;
      
      // url
      frm.action = url;
      
      // 폼 제출
      frm.submit();

} 


 function doRetrieve2() {
    console.log("doRetrieve2()");
    
    // 폼 요소 선택
    let frm = document.getElementById("board_frm");
    
    // 폼 데이터 설정
    frm.work_div.value = "doRetrieve2";
    frm.page_no.value = "1";
    frm.page_size.value = "10";
    
    // 각 입력 요소 값 출력
    /* console.log("frm.search_div.value: " + frm.search_div.value); */
    /* console.log("frm.search_word.value: " + frm.search_word.value); */
    console.log("frm.page_size.value: " + frm.page_size.value);
    
    // 서버로 보낼 액션 설정
    console.log("frm.action: " + "<%=cPath%>" + "/content/content.do");
    frm.action = "<%=cPath%>" + "/content/content.do";
    
    // 폼 제출
    frm.submit();
} 

</script>
</head>
<body>

  
<!-- container -->
<div class="container">
  <!-- menu -->
  <jsp:include page="/cmn/menu.jsp"></jsp:include>
  <!-- menu end --------------------------------------------------------------->
  <!-- 제목 -->
  
  <img class="img" src="/SEOUL_TRAVEL/images/restaurant.png" alt="이미지">
  
  <!--// 제목 end ------------------------------------------------------------->
  
   <!-- 검색 -->  
    <form action="#" name="board_frm" method="get" id="board_frm"  class="row g-2 align-items-center">
        <div class="col-sm-1">
          <input type="hidden" name="work_div"  id="work_div" placeholder="작업구분">
          <input type="hidden" name="page_no"   id="page_no"  placeholder="페이지 번호">        
          <input type="hidden" name="page_size"   id="page_size"  placeholder="페이지 사이즈">        
          <input type="hidden" name="contentId" id="contentId"  placeholder="콘텐츠 아이디">
          <input type="button" align="right" value="조회" class="btn btn-primary" id="doRetrieve2" >
        </div>

        <div class="col-sm-2">
          <select name="category_word" id="category_word"  class="form-control">
            <option value="">전체</option>
            <option value="한식">한식</option>
            <option value="서양식">서양식</option>
            <option value="일식">일식</option>
            <option value="중식">중식</option>
            <option value="카페">카페</option>
            <option value="이색음식점">이색음식점</option>
          </select>  
        </div>
        <div class="col-sm-2">
          <select name="g_name_word" id="g_name_word"  class="form-control">
            <option value="">전체</option>
            <option value="강남구">강남구   </option>
            <option value="강동구">강동구   </option>
            <option value="강동구">강북구   </option>
            <option value="강서구">강서구   </option>
            <option value="관악구">관악구   </option>
            <option value="광진구">광진구   </option>
            <option value="구로구">구로구   </option>
            <option value="금천구">금천구   </option>
            <option value="노원구">노원구   </option>
            <option value="도봉구">도봉구   </option>
            <option value="동대문구">동대문구 </option>
            <option value="동작구">동작구   </option>
            <option value="마포구">마포구   </option>
            <option value="서대문구">서대문구 </option>
            <option value="서초구">서초구   </option>
            <option value="성동구">성동구   </option>
            <option value="성북구">성북구   </option>
            <option value="송파구">송파구   </option>
            <option value="양천구">양천구   </option>
            <option value="영등포구">영등포구 </option>
            <option value="용산구">용산구   </option>
            <option value="은평구">은평구   </option>
            <option value="종로구">종로구   </option>
            <option value="중구">중구     </option>
            <option value="중랑구">중랑구   </option>
          </select>  
        </div>
        <div class="col-sm-2">
          <input type="search"  class="form-control" name="search_word" id="search_word" placeholder="검색어" 
                 >
        </div>
          
    </form>
     <!--// 검색 end ------------------------------------------------------------->
 
    <!-- content -->
    <!-- 행(row)을 만드는 태그는 <tr>와 셀을 만드는 <td>, <th>태그: -->
    <table class="table table-striped table-hover table-bordered" id="boardList">
      <tbody>
         <%   
         if(null != list && list.size()>0){
          for(ContentDTO vo   :list){ 
         
         %>   
        <tr>
          <td class="text-center"><%=vo.getImgLink() %></td>
          <td><%=vo.getTitle() %></td>
          <td><%=vo.getAddr() %></td>
          <td style="display: none;"><%=vo.getContentId() %></td>
        </tr>     
        <%  
            }//for
         }//--if 
        %> 
      </tbody>
  
    </table>
    <!-- paging -->
    <nav aria-label="Page navigation example">
    <%
        //총글수
        ContentsSearchDTO pageingVO = (ContentsSearchDTO)request.getAttribute("vo");
        int totalCnt = pageingVO.getTotalCnt();
               
        //페이지 번호
        int pageNo = pageingVO.getPageNo();    
        
        //페이지 사이즈
        int pageSize = pageingVO.getPageSize();
        
        //바닥 글수      
        int bottomCnt = pageingVO.getBottomCount();
        
        //pageRetrieve(url, 2(페이지 번호))
        out.print(StringUtil.renderingPaging(totalCnt, pageNo, pageSize, bottomCnt, "/SEOUL_TRAVEL/content/content.do", "pageRetrieve"));
    %>
    </nav>
    <!-- //paging end ------------------------------------------------------------->
    
    <jsp:include page="/cmn/footer.jsp"></jsp:include>
    
  </div>
<!--// container end ---------------------------------------------------------->
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>   
</body>
</html>