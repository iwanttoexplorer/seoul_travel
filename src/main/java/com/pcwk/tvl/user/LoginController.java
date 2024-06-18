package com.pcwk.tvl.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.internal.NonNullElementWrapperList;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet implements ControllerV,PLog{
	private static final long serialVersionUID = 1L;
       
	private UserService service;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        log.debug("---------------------");
        log.debug("LoginController()");
        log.debug("---------------------");
        
        service = new UserService(); 
    }
    
    public JView login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("login()");
        log.debug("---------------------");
    	UserDTO inVO = new UserDTO();
        String userId = StringUtil.nvl(request.getParameter("user_id"),"");
        String userPw = StringUtil.nvl(request.getParameter("user_pw"),"");
        String viewName = "";
        
        inVO.setUserId(userId);
        
        UserDTO outVO = service.doSelectOne(inVO);
    	log.debug(outVO);
    	if(userId.equals(outVO.getUserId()) && userPw.equals(outVO.getUserPw())) {
    		//세션 객체 생성
    		HttpSession session = request.getSession();
        	log.debug("session : {}",session);
        	
        	//세션에 데이터 저장
        	session.setAttribute("user", outVO);
        	log.debug("세션 생성");
        	viewName = "";
    	}
    	
    	return null;
    }

    
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
        log.debug("doWork()");
        log.debug("---------------------");
        
        JView viewName = null;
        
        String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
    	log.debug("workDiv : {}",workDiv);
    	
    	switch (workDiv) {
		case "login":
			viewName = login(request,response);
			break;
//		case "logout":
//			viewName = logout(request,response);
//			break;
		default : 
			log.debug("LoginController work_div를 확인하세요. : {}",workDiv);
    	}
        
		return viewName;
	}


}

