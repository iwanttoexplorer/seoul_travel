package com.pcwk.tvl.content;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pcwk.ehr.cmn.ContentsSearchDTO;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.tvl.category.CategoryService;
import com.pcwk.tvl.gucode.GucodeService;


public class ContentController implements ControllerV, PLog{
	private static final long serialVersionUID = 1L;
	
	ContentService service;
	
	CategoryService categoryService;
	GucodeService gucodeService;
	
	public ContentController() {
		log.debug("-----------------");
		log.debug("ContentController()");
		log.debug("-----------------");
		
		service = new ContentService();
	}
	
	public JView doSelectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doSelectOne()");
		log.debug("-----------------");
		
		ContentDTO inVO = new ContentDTO();
		String contentId = StringUtil.nvl(request.getParameter("contentId"), "");
		log.debug("contentId:" + contentId);
		
		inVO.setContentId(contentId);
		log.debug("inVO:" + inVO);
		
		DTO dto = service.doSelectOne(inVO);
		
		MessageVO message = new MessageVO();
		
		if( dto instanceof ContentDTO) {
			ContentDTO outVO = (ContentDTO) dto;
			log.debug("성공 outVO :{}",  outVO);
			
		}else {
			message = (MessageVO) dto;
			log.debug("실패 message :{}",  message);
		}
		
		return null;
	}

	public JView doRetrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doRetrieve()");
		log.debug("-----------------");
		
		HttpSession session = request.getSession();
		
		// JSP viewName저장
		JView viewName = null;
		
		ContentsSearchDTO inVO = new ContentsSearchDTO();
		
		// page_no
		// page_size
		String pageNo = StringUtil.nvl(request.getParameter("page_no"), "1");
		String pageSize = StringUtil.nvl(request.getParameter("page_size"), "10");

		
		String categoryWord = StringUtil.nvl(request.getParameter("category_word"), "");
		String gNameWord = StringUtil.nvl(request.getParameter("g_name_word"), "");
		String searchWord = StringUtil.nvl(request.getParameter("search_word"), "");
		
		log.debug("categoryWord:{}", categoryWord);
		log.debug("gNameWord:{}", gNameWord);
		log.debug("searchWord:{}", searchWord);
		
		if(categoryWord =="" && gNameWord == "" && searchWord != "" ) {
			inVO.setSearchDiv("10");
			inVO.setSearchWord(searchWord);
		}else if (categoryWord !="" && gNameWord == "" && searchWord == "" ) {
			inVO.setSearchDiv("20");
			inVO.setCategoryWord(categoryWord);
		}else if (categoryWord =="" && gNameWord != "" && searchWord == "" ) {
			inVO.setSearchDiv("30");
			inVO.setgNameWord(gNameWord);
		}else if (categoryWord =="" && gNameWord != "" && searchWord != "" ) {
			inVO.setSearchDiv("40");
			inVO.setgNameWord(gNameWord);
			inVO.setSearchWord(searchWord);
		}
		

		log.debug("page_no:{}", pageNo);
		log.debug("page_size:{}", pageSize);

		inVO.setPageNo(Integer.parseInt(pageNo));
		inVO.setPageSize(Integer.parseInt(pageSize));
		
		inVO.setCategoryWord(categoryWord);
		inVO.setgNameWord(gNameWord);
		
		log.debug("categoryWord:{}", categoryWord);
		log.debug("gNameWord:{}", gNameWord);
		log.debug("searchWord:{}", searchWord);

		log.debug("inVO:{}", inVO);

		// service call
		List<ContentDTO> list = service.doRetrieve(inVO);

		// return 데이터 확인
		int i = 0;
		for (ContentDTO vo : list) {
			log.debug("i: {}, vo: {}", ++i, vo);
		}

		// UI 데이터 전달
		request.setAttribute("list", list);
		
		//paging : 총글수 : totalCnt,
		//currentPageNo   : pagNo
		//rowPerPage      : pageSize
		//bottomCount     : 10
		int bottomCount = 10;
		int totalCnt = 0; //총 글수
		
		if(null != list && list.size() > 0) {
			ContentDTO pagingVO = list.get(0);
			totalCnt = pagingVO.getTotalCnt();
			log.debug("totalCnt: {}", totalCnt);
			
			inVO.setTotalCnt(totalCnt);
		}
		
		inVO.setBottomCount(bottomCount);
		
		// 검색조건 UI로 전달
		request.setAttribute("vo", inVO);
		log.debug("inVO: {}", inVO);

		return viewName = new JView("/resources/pages/content/travel_main.jsp");
	}
	
	
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doWork()");
		log.debug("-----------------");
		
		JView viewName = null;
		
		//작업구분
		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
		log.debug("workDiv : {}", workDiv);
		
		switch(workDiv) {
		case "doRetrieve":
			viewName = doRetrieve(request, response);
			break;
		case "doSelectOne":
			viewName = doSelectOne(request, response);
			break;
		default:
			log.debug("ContentController을 확인 하세요. workDiv:{}" + workDiv);
			break;
		}
		
		return viewName;
	}
	
}