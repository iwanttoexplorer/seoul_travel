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
	  
  //등록 버튼
  /* const doSelectOneBtn = document.querySelector("#doSelectOneBtn"); */
  
  //조회 버튼 
  const doRetrieveBtn = document.querySelector("#doRetrieve");
  
  //html객체를 자바스크리트에서 생성
  const searchWord = document.querySelector("#search_word");
  
  
  //table 자식모두 tbody선택
  const rows = document.querySelectorAll("#boardList tbody tr");

  const buttons = document.querySelectorAll(".btn-outline-success") ; 
  
  //이벤트 핸들러
  buttons.forEach(function(button){
    button.addEventListener('click',function(){
      let hiddenInfo = this.getAttribute('data-hidden-info');
      console.log('hiddenInfo:'+hiddenInfo);
      doSelectOne(hiddenInfo);
    });
    
  }); 

    rows.forEach(function(row){
        //double click
        row.addEventListener('dblclick',function(){
          console.log('row click');
          //this(tr) 자식 (td: 마지막 위치)
          let seqValue = this.querySelector('td:last-child').textContent.trim();
          console.log('seqValue:'+seqValue);
          
          doSelectOne(seqValue);    
        });
        
      });   
  

  
  //jquery선택
/*   $('#boardList>tbody').on('click','tr',function(){
    console.log('#boardList>tbody tr');
    let pTr =$(this);
    let tdArray = pTr.children();
    //let seqValue = tdArray.eq(5).text();
    let seqValue = tdArray.last().text();
    
    console.log('seqValue:'+seqValue);
  });
   */
  
  
  <%-- moveToRegBtn.addEventListener("click", function(event){
     console.log('moveToRegBtn click');
      // 폼 요소 선택
      let frm = document.getElementById("board_frm");
      
      // 폼 데이터 설정
      frm.work_div.value = "moveToReg";
      
      // 각 입력 요소 값 출력
      console.log(" frm.work_div.value: " +  frm.work_div.value);
      
      // 서버로 보낼 액션 설정
      frm.action = "<%=cPath%>" + "/content/content.do";
      
      // 폼 제출
      frm.submit();    
  }); --%>
  
  
  doRetrieveBtn.addEventListener("click", function(event){
    console.log('doRetrieveBtn click');
    doRetrieve();
  }); 
  
  searchWord.addEventListener("keydown", function(event){
    console.log('keydown');
    
    if(event.keyCode === 13){
      doRetrieve();
    }
  }); 
  
});

//페이징 조회
function pageRetrieve(url, pageNo){
    console.log("url:"+url);
    console.log("pageNo:"+pageNo);
    
      // 폼 요소 선택
      let frm = document.getElementById("board_frm");
      frm.work_div.value = "doRetrieve";
      
      // 폼 데이터 설정
      frm.page_no.value = pageNo;
      
      // url
      frm.action = url;
      
      // 폼 제출
      frm.submit();

} 


function doSelectOne(seqValue){
  
    // 폼 요소 선택
    let frm = document.getElementById("board_frm");
    // 폼 데이터 설정
    frm.work_div.value = "doSelectOne";  
    
    //seq
    frm.seq.value = seqValue;
    frm.action = "<%=cPath%>" + "/content/content.do";
    
    // 폼 제출
    frm.submit();   
}

 function doRetrieve() {
    console.log("doRetrieve()");
    
    // 폼 요소 선택
    let frm = document.getElementById("board_frm");
    
    // 폼 데이터 설정
    frm.work_div.value = "doRetrieve";
    frm.page_no.value = "1";
    frm.page_size.value = "10";
    
    // 각 입력 요소 값 출력
    console.log("frm.search_div.value: " + frm.search_div.value);
    console.log("frm.search_word.value: " + frm.search_word.value);
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
  
  <img class="img" src="/SEOUL_TRAVEL/images/travel.png" alt="이미지">
  
  <!--// 제목 end ------------------------------------------------------------->
  
   <!-- 검색 -->  
    <form action="#" name="board_frm" method="get" id="board_frm"  class="row g-2 align-items-center">
        <div class="col-sm-1">
          <input type="hidden" name="work_div"  id="work_div" placeholder="작업구분">
          <input type="hidden" name="page_no"   id="page_no"  placeholder="페이지 번호">        
          <input type="hidden" name="page_size"   id="page_size"  placeholder="페이지 사이즈">        
          <input type="hidden" name="seq"       id="seq"      placeholder="순번">
          <input type="button" align="right" value="조회" class="btn btn-primary" id="doRetrieve" >
        </div>

        <div class="col-sm-2">
          <select name="sea
          rch_div" id="search_div"  class="form-control">
            <option value="">전체</option>
            <option value="A0101">자연관광지</option>
            <option value="A0102">자연관광지</option>
            <option value="A0201">역사관광지</option>
            <option value="A0202">휴양관광지</option>
            <option value="A0203">체험관광지</option>
            <option value="A0204">산업관광지</option>
            <option value="A0205">건축/조형물</option>
          </select>  
        </div>
        <div class="col-sm-2">
          <select name="search_div" id="search_div"  class="form-control">
            <option value="">전체</option>
            <option value="1">강남구   </option>
            <option value="2">강동구   </option>
            <option value="3">강북구   </option>
            <option value="4">강서구   </option>
            <option value="5">관악구   </option>
            <option value="6">광진구   </option>
            <option value="7">구로구   </option>
            <option value="8">금천구   </option>
            <option value="9">노원구   </option>
            <option value="10">도봉구   </option>
            <option value="11">동대문구 </option>
            <option value="12">동작구   </option>
            <option value="13">마포구   </option>
            <option value="14">서대문구 </option>
            <option value="15">서초구   </option>
            <option value="16">성동구   </option>
            <option value="17">성북구   </option>
            <option value="18">송파구   </option>
            <option value="19">양천구   </option>
            <option value="20">영등포구 </option>
            <option value="21">용산구   </option>
            <option value="22">은평구   </option>
            <option value="23">종로구   </option>
            <option value="24">중구     </option>
            <option value="25">중랑구   </option>
          </select>  
        </div>
        <div class="col-sm-2">
          <input type="search"  class="form-control" name="search_word" id="search_word" placeholder="검색어" 
                 value="<%if(null != searchCon){out.print(searchCon.getSearchWord());}%>">
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