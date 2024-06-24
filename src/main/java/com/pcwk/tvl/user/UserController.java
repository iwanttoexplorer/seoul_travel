package com.pcwk.tvl.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pcwk.tvl.user.UserDTO;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.tvl.comment.CommentDTO;

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
        JView viewName = null;
        UserDTO user = new UserDTO();
        List<UserDTO> users = service.doRetrieveUser();
        int i = 0;
        for(UserDTO vo : users) {
        	log.debug("i: {}, vo: {}", i++,vo);
        }
        Gson gson = new Gson();
        String json = gson.toJson(users);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }
    

    public JView doDeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("-----------------");
        log.debug("doDeleteUser()");
        log.debug("-----------------");
    
        UserDTO inVO = new UserDTO();
        
        String userId = StringUtil.nvl(request.getParameter("userId"), "");
        
        // 'userId' 값을 DTO에 설정
        inVO.setUserId(userId);
        log.debug("inVO:" + inVO);

        // 서비스 객체를 통해 삭제 작업 수행
        int flag = service.doDeleteUser(inVO);
        log.debug("flag:{}", flag);

        // 삭제 결과에 따라 메시지 설정
        String message = "";
        if (1 == flag) {
            message = "삭제 성공";
        } else {
            message = "삭제 실패";
        }

        // 삭제 결과 메시지를 담기 위한 VO 객체 생성
        MessageVO messageVO = new MessageVO();
        messageVO.setMessageId(String.valueOf(flag));
        messageVO.setMsgContents(message);
        log.debug("messageVO:{}", messageVO);

        Gson gson = new Gson();
        String json = gson.toJson(userId);
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
		case "doSelectOne":
			viewName = doDeleteUser(request,response);
			break;
		default:
			log.debug("작업구분을 확인 하세요. workDiv : {} ", workDiv);
		}
		
		return viewName;
	}

}
