package com.pcwk.tvl.comment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;
import com.google.gson.Gson;
/**
 * Servlet implementation class CommentController
 */
public class CommentController extends HttpServlet implements ControllerV,PLog{
	private static final long serialVersionUID = 1L;
    CommentService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
    	log.debug("--------------------");
		log.debug("CommentController()");
		log.debug("--------------------");
        service = new CommentService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public JView saveComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
        log.debug("saveComment()");
        log.debug("---------------------");
        CommentDTO comment = new CommentDTO();
        String userId = StringUtil.nvl(request.getParameter("userId"), "");
        String aboardSeq = StringUtil.nvl(request.getParameter("aboardSeq"),"");
        String content = StringUtil.nvl(request.getParameter("content"), "");
        log.debug("userId: {}",userId);
		log.debug("aboardSeq: {}",aboardSeq);
		log.debug("content: {}",content);
        comment.setUserId(userId);
        comment.setAboardSeq(Integer.parseInt(aboardSeq));
        comment.setContent(content);
        log.debug("comment: "+comment);
        int flag = service.doSave(comment);
        log.debug("Save flag: {}", flag);
        
//        if(flag ==1) {
//        	response.sendRedirect("comment/review_detail?aboard_id=" + aboardSeq);
//        } else {
//            request.setAttribute("errorMessage", "댓글 저장에 실패했습니다.");
//           request.getRequestDispatcher("error.jsp").forward(request, response);
//        }
        
		return null;
	}
    public JView updateComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("updateComment()");
        log.debug("---------------------");
        String comSeq = StringUtil.nvl(request.getParameter("comSeq"),"");
        String content = StringUtil.nvl(request.getParameter("content"), "");
        log.debug("comSeq: "+comSeq);
        log.debug("content: "+content);
          
        CommentDTO comment = new CommentDTO();
        comment.setComSeq(Integer.parseInt(comSeq));
        comment.setContent(content);
        log.debug("comment: "+comment);
        int flag = service.doUpdate(comment);
        log.debug("Update flag: {}", flag);
//
//        if (flag > 0) {
//            response.sendRedirect("comment/review_detail?aboard_id=" + comment.getAboardSeq());
//        } else {
//            request.setAttribute("errorMessage", "댓글 수정에 실패했습니다.");
//            request.getRequestDispatcher("error.jsp").forward(request, response);
//        }
    	return null;
    }
    public JView deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("deleteComment()");
        log.debug("---------------------");
        String comSeq = StringUtil.nvl(request.getParameter("comSeq"),"");
        log.debug("comSeq: "+comSeq);
        CommentDTO comment = new CommentDTO();
        comment.setComSeq(Integer.parseInt(comSeq));
        log.debug("comment: "+comment);
        int flag = service.doDelete(comment);
        log.debug("Delete flag: {}", flag);
        
//        if (flag > 0) {
//            response.sendRedirect("comment/review_detail?aboard_id=" + comment.getAboardSeq());
//        } else {
//            request.setAttribute("errorMessage", "댓글 삭제에 실패했습니다.");
//            request.getRequestDispatcher("error.jsp").forward(request, response);
//        }
    	return null;
    }
    public JView getComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("---------------------");
        log.debug("getComments()");
        log.debug("---------------------");
        CommentDTO comment = new CommentDTO();
        String aboardSeq = StringUtil.nvl(request.getParameter("aboardSeq"),"");
        log.debug("aboardSeq: {}",aboardSeq);
        comment.setAboardSeq(Integer.parseInt(aboardSeq));
        log.debug("comment: {}",comment);
        List<CommentDTO> comments = service.doRetrieve(comment);
        log.debug("comments: "+comments);
        request.setAttribute("comments", comments);

        return new JView("/resources/pages/comment/review_detail.jsp");
    }
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		log.debug("---------------------");
        log.debug("doWork()");
        log.debug("---------------------");

        JView viewName = null;
		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
    	log.debug("workDiv : {}",workDiv);
    	switch(workDiv) {
		case "getComments":
			viewName = getComments(request,response);
			break;
		case "saveComment":
			viewName = saveComment(request, response);
			break;
		case "deleteComment":
			viewName = deleteComment(request, response);
			break;
		case "updateComment":
			viewName = updateComment(request, response);
			break;
		default:
			log.debug("작업 구분을 확인하세요. workDiv: {}",workDiv);
			break;
		}
		return viewName;
	}

}
