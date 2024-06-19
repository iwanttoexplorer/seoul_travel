package com.pcwk.tvl.content;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.tvl.category.CategoryDTO;
import com.pcwk.tvl.category.CategoryService;
import com.pcwk.tvl.gucode.GucodeService;
import com.pcwk.ehr.cmn.DTO;


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
	
	public JView doRetrieve(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doRetrieve()");
		log.debug("-----------------");
		
		HttpSession session = request.getSession();

		// JSP viewName저장
		JView viewName = null;
		
		//Category 식당 코드 조회
		CategoryDTO pageCode = new CategoryDTO();
		pageCode.setCategory("A05020100");
		pageCode.setCategory("A05020200");
		pageCode.setCategory("A05020300");
		pageCode.setCategory("A05020400");
		pageCode.setCategory("A05020700");
		pageCode.setCategory("A05020900");
		
		List<CategoryDTO> restaurantList = categoryService.doRetrieve(pageCode);
		log.debug("restaurantList:{}", restaurantList);
		
		//검색조건 UI로 전달
		request.setAttribute("page", restaurantList);
		
		//Category 여행지 코드 조회
		pageCode.setCategory("A0101");
		pageCode.setCategory("A0102");
		pageCode.setCategory("A0201");
		pageCode.setCategory("A0202");
		pageCode.setCategory("A0203");
		pageCode.setCategory("A0204");
		pageCode.setCategory("A0205");
		
		List<CategoryDTO> travelList = categoryService.doRetrieve(pageCode);
		log.debug("travelList:{}", travelList);
		
		//검색조건 UI로 전달
		request.setAttribute("travelList", travelList);
		
		SearchDTO inVO = new SearchDTO();
		// page_no
		// page_size
		String pageNo = StringUtil.nvl(request.getParameter("page_no"), "1");
		String pageSize = StringUtil.nvl(request.getParameter("page_size"), "10");

		String searchWord = StringUtil.nvl(request.getParameter("search_word"), "");
		String searchDiv = StringUtil.nvl(request.getParameter("search_div"), "");

		log.debug("page_no:{}", pageNo);
		log.debug("page_size:{}", pageSize);
		log.debug("searchWord:{}", searchWord);
		log.debug("searchDiv:{}", searchDiv);

		inVO.setPageNo(Integer.parseInt(pageNo));
		inVO.setPageSize(Integer.parseInt(pageSize));
		inVO.setSearchWord(searchWord);
		inVO.setSearchDiv(searchDiv);

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

		return viewName = new JView("/content/travel_main.jsp");
	}
	
	public JView doSelectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doSelectOne()");
		log.debug("-----------------");
		
		ContentDTO inVO = new ContentDTO();
		log.debug("inVO: ", inVO);
		String contentId = StringUtil.nvl(request.getParameter("contentId"), "0");
		
		inVO.setContentId(contentId);
		log.debug("inVO:" + inVO);
		
		ContentDTO outVO = this.service.doSelectOne(inVO);
		log.debug("outVO:" + outVO);
		
		//UI 데이터 전달
		request.setAttribute("outVO", outVO);
		
		return new JView("/content/trevel_sel.jsp");
	}

	public JView moveToReg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("moveToReg()");
		log.debug("-----------------");

		return new JView("/content/trevel_sel.jsp");
	}
	
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doWork()");
		log.debug("-----------------");
		
		JView viewName = null;
		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
		log.debug("workDiv : {}", workDiv);
		
		switch(workDiv) {
		case "doRetrieve":
			viewName = doRetrieve(request, response);
			break;
		case "doSelectOne":
			viewName = doSelectOne(request, response);
			break;
		case "moveToReg":
			viewName = moveToReg(request, response);
			break;
		default:
			log.debug("ContentController을 확인 하세요. workDiv:{}" + workDiv);
			break;
		}
		
		return viewName;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doGet()");
		log.debug("-----------------");
		doWork(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doPost()");
		log.debug("-----------------");
		doWork(request, response);
	}

}