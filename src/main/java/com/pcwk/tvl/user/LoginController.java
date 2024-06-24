package com.pcwk.tvl.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
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
        
        inVO.setUserId(userId);
        
        UserDTO outVO = service.doSelectOne(inVO);
        if(outVO == null) return null;
//    	log.debug(outVO);
    	
    	String message = "";
    	
    	if(userId.equals(outVO.getUserId()) && userPw.equals(outVO.getUserPw())) {
    		//세션 객체 생성
    		HttpSession session = request.getSession();
        	log.debug("session : {}",session);
        	outVO.setFlag(1); //로그인성공
        	//세션에 데이터 저장
        	session.setAttribute("user", outVO);
        	log.debug("세션 생성");
        	
        	response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out =  response.getWriter();
    		out.print("성공");
        	
    	}else {
    		
    		response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out =  response.getWriter();
    		out.print("비번이 틀림");
    		
    	}
    	return null;
    }

    public JView checkName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("checkName()");
        log.debug("---------------------");
    	
        String userName = StringUtil.nvl(request.getParameter("user_name"),"");
        
        String result = service.checkName(userName);
        log.debug(result);
        
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out =  response.getWriter();
    	out.print(result);
        
    	return null;
    }
    
    public JView checkId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("checkId()");
        log.debug("---------------------");
    	
        String userId = StringUtil.nvl(request.getParameter("check_id"),"");
        
        String result = service.checkId(userId);
        log.debug(result);
        
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out =  response.getWriter();
    	out.print(result);
        
    	return null;
    }
    
    public JView checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
    	log.debug("checkEmail()");
    	log.debug("---------------------");
    	
    	String userEmail = StringUtil.nvl(request.getParameter("check_id"),"");
    	
    	String result = service.checkEmail(userEmail);
    	log.debug(result);
    	
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out =  response.getWriter();
    	out.print(result);
    	
    	return null;
    }
    
    public JView checkPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
    	log.debug("checkPassword()");
    	log.debug("---------------------");
    	
    	String userPw = StringUtil.nvl(request.getParameter("check_pw"),"");
    	
    	String result = service.checkPassword(userPw);
    	log.debug(result);
    	
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out =  response.getWriter();
    	out.print(result);
    	
    	return null;
    }
    
    public JView doSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("doSave()");
        log.debug("---------------------");
    	
        String userName = StringUtil.nvl(request.getParameter("user_name"), "");
        String userId = StringUtil.nvl(request.getParameter("user_id"), "");
        String userEmail = StringUtil.nvl(request.getParameter("user_email"), "");
        String userPw = StringUtil.nvl(request.getParameter("user_pw"), "");
        
        log.debug("userName: {}",userName);
    	log.debug("userId: {}",userId);
    	log.debug("userEmail: {}",userEmail);
    	log.debug("userPw: {}",userPw);
        
        UserDTO inVO = new UserDTO();
        inVO.setUserName(userName);
        inVO.setUserId(userId);
        inVO.setUserEmail(userEmail);
        inVO.setUserPw(userPw);
        
        int flag = service.doSave(inVO);
        log.debug("flag: {}",flag);
        
        String message = "";
        
        if(1 ==flag) {
    		message = "회원가입을 축하합니다.";
    	}else {
    		message = "가입실패 회원정보를 확인 하세요.";
    	}
        
        response.setContentType("text/html; charset=UTF-8");
    	
    	PrintWriter out =  response.getWriter();
    	out.print(message);
        
    	return null;
    }
    
    public JView findUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("findUserId()");
        log.debug("---------------------");
    	
        String userEmail = StringUtil.nvl(request.getParameter("user_email"), "");
    	log.debug("userEmail: {}",userEmail);
        
        String result = service.findUserId(userEmail);
        log.debug("result: {}",result);
        
//        if(result !=null && result != "") {
//        	response.setContentType("text/html; charset=UTF-8");
//        	
//        	PrintWriter out =  response.getWriter();
//        	out.print(result);
//        }
        
        request.setAttribute("outVO", result);
    	
        return new JView("/resources/pages/user/findUserId_result.jsp");
    }
    
    public JView findUserPw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("findUserPw()");
        log.debug("---------------------");
    	
        UserDTO inVO = new UserDTO();
    	String userId = StringUtil.nvl(request.getParameter("user_id"), "");
    	
    	inVO.setUserId(userId);
    	
    	UserDTO outVO = service.doSelectOne(inVO);
    	
    	if(outVO!=null) request.setAttribute("outVO", outVO.getUserPw());
    	
    	return new JView("/resources/pages/user/findUserPw_result.jsp");
    }
    
    public JView logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("logout()");
        log.debug("---------------------");
    	
        
        String viewName = "";
        
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
			log.debug("session : {}",session);
			session.invalidate(); //세션값 삭제
			viewName = "/resources/pages/main/mainpage.jsp";
		}
		
		return new JView(viewName);
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
    	case "logout":
    		viewName = logout(request,response);
    		break;
    	case "findUserPw":
    		viewName = findUserPw(request,response);
    		break;
    	case "findUserId":
    		viewName = findUserId(request,response);
    		break;
    	case "doSave":
    		viewName = doSave(request,response);
    		break;
    	case "checkPassword":
    		viewName = checkPassword(request,response);
    		break;
    	case "checkEmail":
    		viewName = checkEmail(request,response);
    		break;
    	case "checkId":
    		viewName = checkId(request,response);
    		break;
    	case "checkName":
    		viewName = checkName(request,response);
    		break;
		case "login":
			viewName = login(request,response);
			break;
		default : 
			log.debug("LoginController work_div를 확인하세요. : {}",workDiv);
    	}
        
		return viewName;
	}

}
