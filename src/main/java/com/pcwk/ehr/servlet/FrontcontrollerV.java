package com.pcwk.ehr.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.tvl.comment.CommentController;
import com.pcwk.tvl.content.ContentController;
import com.pcwk.tvl.review.ReviewController;
import com.pcwk.tvl.user.LoginController;

/**
 * Servlet implementation class FrontcontrollerV
 */
@WebServlet("*.do")
public class FrontcontrollerV extends HttpServlet implements PLog {
	private static final long serialVersionUID = 1L;
	private Map<String, ControllerV> controllerMap = new HashMap<String, ControllerV>();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontcontrollerV() {
    	log.debug("FrontControllerV()");
    	//controllerMap.put("/SEOUL_TRAVEL/connect/connect.journey", new ConnectController());
    	controllerMap.put("/SEOUL_TRAVEL/user/login.do", new LoginController());
    	controllerMap.put("/SEOUL_TRAVEL/comment/comment.do", new CommentController());
    	controllerMap.put("/SEOUL_TRAVEL/review/review.do", new ReviewController());
    	controllerMap.put("/SEOUL_TRAVEL/content/content.do", new ContentController());
    	controllerMap.put("/SEOUL_TRAVEL/admin/admin.do", new UserController());
    	


    }
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		log.debug("requestURI: {}",requestURI);
		
		ControllerV controller = controllerMap.get(requestURI);
		log.debug("controller: {}",controller);
		
		//request 인코딩 처리:
    	request.setCharacterEncoding("UTF-8");
		
		//controller가 null이면 404예외처리
		if(null == controller) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		JView jview = controller.doWork(request, response);
		
		if(null != jview && jview.getViewName().length()>0) {
		jview.render(request, response);
		log.debug("jview: {}",jview);
		}
	}

}
