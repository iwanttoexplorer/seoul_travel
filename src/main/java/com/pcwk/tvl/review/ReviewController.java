package com.pcwk.tvl.review;

import java.io.IOException;
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
import com.pcwk.ehr.cmn.SearchDTO;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/review.do")
public class ReviewController extends HttpServlet implements ControllerV, PLog {
    private static final long serialVersionUID = 1L;

    private ReviewService reviewService;

    public ReviewController() {
        log.debug("---------------------");
        log.debug("ReviewController()");
        log.debug("---------------------");

        reviewService = new ReviewService(); // 인스턴스 생성
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doWork(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doWork(request, response);
    }

    public JView doRetrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("---------------------");
        log.debug("doRetrieve()");
        log.debug("---------------------");

        SearchDTO inVO = new SearchDTO();
        
        // JSP viewName 전달
        JView viewName = null;
        // page_no
        // page_size
        String pageNo = StringUtil.nvl(request.getParameter("pageNo"), "");
        String pageSize = StringUtil.nvl(request.getParameter("pageSize"), "");
        log.debug("pageNo"+pageNo);
        log.debug("pageSize"+pageSize);
        inVO.setPageNo(Integer.parseInt(pageNo));
        inVO.setPageSize(Integer.parseInt(pageSize));
/*
        String searchDiv = StringUtil.nvl(request.getParameter("search_div"), "");
        String searchWord = StringUtil.nvl(request.getParameter("search_word"), "");

        log.debug("pageNo: {}", pageNo);
        log.debug("pageSize: {}", pageSize);
        inVO.setPageNo(Integer.parseInt(pageNo));
        inVO.setPageSize(Integer.parseInt(pageSize));

        inVO.setSearchDiv(searchDiv);
        inVO.setSearchWord(searchWord);

        log.debug("inVO: {}", inVO);

        // service call
        List<ReviewDTO> list = reviewService.doRetrieve(inVO);

        int i = 0;
        for (ReviewDTO vo : list) {
            log.debug("i:{}, vo:{}", ++i, vo);
        }

        // UI 데이터 전달
        request.setAttribute("list", list);
        request.setAttribute("vo", inVO);
*/
        return null;//viewName = new JView("/review-board.jsp");
    }

    @Override
    public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("---------------------");
        log.debug("doWork()");
        log.debug("---------------------");

        JView viewName = null;

        String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
        log.debug("workDiv : {}", workDiv);

        switch (workDiv) {
            case "doRetrieve":
                viewName = doRetrieve(request, response);
                break;
            case "saveReview":
                viewName = saveReview(request, response);
                break;
            default:
                log.debug("ReviewController work_div를 확인하세요. : {}", workDiv);
        }

        return viewName;
    }

    public JView saveReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("---------------------");
        log.debug("saveReview()");
        log.debug("---------------------");

        ReviewDTO inVO = new ReviewDTO();
        String contentId = StringUtil.nvl(request.getParameter("content_id"), "");
        String userId = StringUtil.nvl(request.getParameter("user_id"), "");
        String imgLink = StringUtil.nvl(request.getParameter("img_link"), "");
        String comments = StringUtil.nvl(request.getParameter("comments"), "");
        String title = StringUtil.nvl(request.getParameter("title"), "");

        inVO.setContentId(contentId);
        inVO.setUserId(userId);
        inVO.setImgLink(imgLink);
        inVO.setComments(comments);
        inVO.setTitle(title);

        int flag = reviewService.doSave(inVO);
        log.debug("flag : {}", flag);

        if (flag == 1) {
            log.debug("Review saved successfully.");
        } else {
            log.debug("Failed to save review.");
        }

        response.sendRedirect("review.do?work_div=doRetrieve"); // 저장 후 리뷰 게시판으로 리다이렉트
        return null; // JSP 페이지로 포워딩하지 않기 때문에 null을 반환
    }
}
