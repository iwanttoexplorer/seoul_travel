package com.pcwk.tvl.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.cmn.StringUtil;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/admin.do")
public class UserController extends HttpServlet implements ControllerV,PLog{
	private static final long serialVersionUID = 1L;
       
	UserService service;
	
	
    public UserController() {
    	log.debug("-----------------");
	log.debug("UserController()");
	log.debug("-----------------");
	
	service = new UserService();
       
    }
    
    public JView doRetrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("-----------------");
        log.debug("doRetrieve()");
        log.debug("-----------------");
        
		/*
		 * // JSP viewName저장 JView viewName = null;
		 */
       
        SearchDTO inVO = new SearchDTO();
        
        String pageNo = StringUtil.nvl(request.getParameter("page_no"), "1");
        String pageSize = StringUtil.nvl(request.getParameter("page_size"), "10");
        String searchWord = StringUtil.nvl(request.getParameter("search_word"), "");
        String searchDiv = StringUtil.nvl(request.getParameter("search_div"), "");

        log.debug("pageNo: {}", pageNo);
        log.debug("pageSize: {}", pageSize);
        log.debug("searchWord: {}", searchWord);
        log.debug("searchDiv: {}", searchDiv);

        try {
            inVO.setPageNo(Integer.parseInt(pageNo));
            inVO.setPageSize(Integer.parseInt(pageSize));
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid page number or page size format", e);
        }

        inVO.setSearchDiv(searchDiv);
        inVO.setSearchWord(searchWord);

        log.debug("inVO: {}", inVO);
		
        // service call
        List<UserDTO> list = service.doRetrieve(inVO);
        

        // return 데이터 확인
        int i = 0;
        for (UserDTO vo : list) {
            log.debug("i: {}, vo: {}", i++, vo);
        }

        // UI 데이터 전달
        request.setAttribute("userList", list);

        
        int totalCnt = 0; // 총글수

        if (list != null && !list.isEmpty()) {
            UserDTO pagingVO = list.get(0);
            totalCnt = pagingVO.getTotalCnt();
            log.debug("totalCnt: {}", totalCnt);

            inVO.setTotalCnt(totalCnt);
        }
        log.debug("inVO: {}"+inVO);
        // 검색조건 UI로 전달
        Gson gson = new Gson();
        String json = gson.toJson(inVO);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }
    
    
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doWork()");
		log.debug("-----------------");
		
		JView viewName = null;

		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
		log.debug("workDiv : {} ", workDiv);
		
		switch (workDiv) {
		case "doRetrieve":
			viewName = doRetrieve(request,response);
			break;
		default:
			log.debug("작업구분을 확인 하세요. workDiv : {} ", workDiv);
		}
		
		return viewName;
	}

}
