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

/**
 * Servlet implementation class CommentController
 */
@WebServlet("/CommentController")
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
        String userId = StringUtil.nvl(request.getParameter("user_id"), "");
        int aboardId = Integer.parseInt(request.getParameter("aboard_id"));
        String content = StringUtil.nvl(request.getParameter("content"), "");
        CommentDTO comment = new CommentDTO();
        comment.setUserId(userId);
        comment.setAboardSeq(aboardId);
        comment.setContent(content);
        int flag = service.doSave(comment);
        log.debug("Save flag: {}", flag);
        if(flag ==1) {
        	response.sendRedirect("comment/review_detail?aboard_id=" + aboardId);
        } else {
            request.setAttribute("errorMessage", "댓글 저장에 실패했습니다.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
		return null;
	}
    public JView updateComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	log.debug("---------------------");
        log.debug("updateComment()");
        log.debug("---------------------");
        int comSeq = Integer.parseInt(request.getParameter("com_seq"));
        String content = StringUtil.nvl(request.getParameter("content"), "");
        
        CommentDTO comment = new CommentDTO();
        comment.setComSeq(comSeq);
        comment.setContent(content);
        int flag = service.doUpdate(comment);
        log.debug("Update flag: {}", flag);

        if (flag > 0) {
            response.sendRedirect("comment/review_detail?aboard_id=" + comment.getAboardSeq());
        } else {
            request.setAttribute("errorMessage", "댓글 수정에 실패했습니다.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    	return null;
    }
    public JView deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	log.debug("---------------------");
        log.debug("deleteComment()");
        log.debug("---------------------");
        int comSeq = Integer.parseInt(request.getParameter("com_seq"));
        CommentDTO comment = new CommentDTO();
        comment.setComSeq(comSeq);
        
        int flag = service.doDelete(comment);
        log.debug("Delete flag: {}", flag);
        if (flag > 0) {
            response.sendRedirect("comment/review_detail?aboard_id=" + comment.getAboardSeq());
        } else {
            request.setAttribute("errorMessage", "댓글 삭제에 실패했습니다.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    	return null;
    }
    public JView getComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        log.debug("---------------------");
        log.debug("getComments()");
        log.debug("---------------------");

        int aboardSeq = Integer.parseInt(request.getParameter("aboard_Seq"));
        CommentDTO comment = new CommentDTO();
        comment.setAboardSeq(aboardSeq);
        List<CommentDTO> comments = service.doRetrieve(comment);
        request.setAttribute("comments", comments);

        return new JView("SEOUL_TRABEL/resources/pages/comment/review_detail.jsp");
    }
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("---------------------");
        log.debug("doWork()");
        log.debug("---------------------");

        JView viewName = null;
        String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
    	log.debug("workDiv : {}",workDiv);
    	
		return null;
	}

}
