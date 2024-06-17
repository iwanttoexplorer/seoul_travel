<%@page import="org.apache.jasper.runtime.PageContextImpl"%>
<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@page import="com.pcwk.tvl.content.ContentDTO"%>
<%@page import="com.pcwk.tvl.category.CategoryDTO"%>
<%@page import="com.pcwk.ehr.cmn.SearchDTO"%>
<%@page import="com.pcwk.tvl.content.ContentDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>
<%
		List<ContentDTO> list = (List<ContentDTO>)request.getAttribute("list");
		
		SearchDTO searchCon = (SearchDTO)request.getAttribute("vo");
		
		List<CategoryDTO> pageCode = (List<CategoryDTO>)request.getAttribute("page");
	    
    List<CategoryDTO> searchCode = (List<CategoryDTO>)request.getAttribute("boardSearchList");
		
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>동가홍상</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
    <!-- container -->
    <div class="container">
        <!-- menu -->
			  <jsp:include page="/cmn/menu.jsp"></jsp:include>
			  <!-- menu end --------------------------------------------------------------->
			  
			  <!-- 제목 -->
			  <div class="page-header mb-4">
			    <h2>게시-목록</h2>
			  </div>
			  <!--// 제목 end ------------------------------------------------------------->
			  
			  <!-- 버튼 -->
			  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
			      <input type="button" value="조회" class="btn btn-primary" id="doRetrieve" >
			      <input type="button" value="등록" class="btn btn-primary"  id="moveToReg">
			  </div>
			  <!--// 버튼 ----------------------------------------------------------------->
			
			   <!-- 검색 --> 
			    <form action="#" name="board_frm" method="get" id="board_frm"  class="row g-2 align-items-center">
			        <div class="col-sm-4">
			          <input type="hidden" name="work_div"  id="work_div" placeholder="작업구분">
			          <input type="hidden" name="page_no"   id="page_no"  placeholder="페이지 번호">        
			          <input type="hidden" name="seq"       id="seq"      placeholder="순번">
			        </div>
			
			        <div class="col-sm-2 text-end g-2">
			          <label for="search_div"  class="form-label">구분</label>
			        </div>  
			        <div class="col-sm-2">
			            <select name="search_div" id="search_div"  class="form-control">
			              <option value="">전체</option>
			              <%
			                   out.print(StringUtil.getCodeList(searchCode, searchCon.getSearchDiv()+""));
			               %>
			            </select>  
			          </div>
			          <div class="col-sm-2">
			            <input type="search"  class="form-control" name="search_word" id="search_word" placeholder="검색어" 
			                   value="<%if(null != searchCon){out.print(searchCon.getSearchWord());}%>">
			          </div>
			          
			          <div class="col-sm-2">       
			            <select name="page_size" id="page_size" class="form-control">
			               <%
			                   out.print(StringUtil.getCodeList(pageCode, searchCon.getPageSize()+""));
			               %>
			            </select>
			          </div>
			    </form>
			     <!--// 검색 end ------------------------------------------------------------->
			 
			    
			    
			    <!-- content -->
			    <!-- 행(row)을 만드는 태그는 <tr>와 셀을 만드는 <td>, <th>태그: -->
			    <table class="table table-striped table-hover table-bordered" id="boardList">
			      <thead>
			        <tr class="table-success">
			          <th class="text-center col-sm-1">이미지</th>
			          <th class="text-center col-sm-6">제목</th>
			          <th class="text-center col-sm-1">관리</th>
			          <th style="display: none;">SEQ</th>
			        </tr>
			      </thead>
			      <tbody>
			         <%
			         if(null != list && list.size() > 0) {
			           for(ContentDTO vo   :list){ 
			         %>   
			        <tr>
			          <td><%=vo.getImgLink() %></td>
			          <td><%=vo.getTitle() %></td>
			        </tr>     
			        <% 
			           } //for
			         }//if
			        %> 
			      </tbody>
			  
			    </table>
			    <!-- paging -->
			    <nav aria-label="Page navigation example">
			    <%
			        //총글수
			        SearchDTO pageingVO = (SearchDTO)request.getAttribute("vo");
			        int totalCnt = pageingVO.getTotalCnt();
			               
			        //페이지 번호
			        int pageNo = pageingVO.getPageNo();    
			        
			        //페이지 사이즈
			        int pageSize = pageingVO.getPageSize();
			        
			        //바닥 글수      
			        int bottomCnt = pageingVO.getBottomCount();
			        
			        //pageRetrieve(url, 2(페이지 번호))
			        out.print(StringUtil.renderingPaging(totalCnt, pageNo, pageSize, bottomCnt, "/WEB02/board/board.do", "pageRetrieve"));
			    %>
			    </nav>
    
    <!-- //paging end --------------------------------------------------------->
        <jsp:include page="/cmn/footer.jsp"></jsp:include>
    </div>
<!--// container end ---------------------------------------------------------->
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>   
</body>
</html>