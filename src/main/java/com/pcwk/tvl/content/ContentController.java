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
import com.pcwk.ehr.cmn.MessageVO;
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
		
		
		
		return null;
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
		
		return null;
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