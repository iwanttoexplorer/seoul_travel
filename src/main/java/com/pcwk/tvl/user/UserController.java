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
