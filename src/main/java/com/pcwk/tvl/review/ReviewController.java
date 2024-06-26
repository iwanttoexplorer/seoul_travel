package com.pcwk.tvl.review;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.tvl.comment.CommentDTO;
import com.pcwk.tvl.like.LikeDTO;
import com.pcwk.ehr.cmn.SearchDTO;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/review/review.do")
public class ReviewController extends HttpServlet implements ControllerV, PLog {
    private static final long serialVersionUID = 1L;

    private ReviewService reviewService;

    public ReviewController() {
        log.debug("---------------------");
        log.debug("ReviewController()");
        log.debug("---------------------");
        reviewService = new ReviewService(); // 인스턴스 생성
    }
    
    public JView doLikeSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("doLikeSave()");
        log.debug("---------------------");
        String userId = StringUtil.nvl(request.getParameter("userId"), "");
        String aboardSeq = StringUtil.nvl(request.getParameter("aboardSeq"),"");
        log.debug("userId: {}",userId);
        log.debug("aboardSeq: {}",aboardSeq);
        LikeDTO like = new LikeDTO();
        like.setUserId(userId);
        like.setAboardSeq(Integer.parseInt(aboardSeq));
        int flag = reviewService.doLikeSave(like);
        log.debug("save flag:{}", flag);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out =  response.getWriter();
		out.print(flag);
    	return null;
    }
    public JView doLikeDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("doLikeDelete()");
        log.debug("---------------------");
        
        String userId = StringUtil.nvl(request.getParameter("userId"), "");
        String aboardSeq = StringUtil.nvl(request.getParameter("aboardSeq"),"");
        log.debug("userId: {}",userId);
        log.debug("aboardSeq: {}",aboardSeq);
        LikeDTO like = new LikeDTO();
        like.setUserId(userId);
        like.setAboardSeq(Integer.parseInt(aboardSeq));
        int flag = reviewService.doLikeDelete(like);
        log.debug("Delete flag:{}", flag);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out =  response.getWriter();
		out.print(flag);
    	return null;
        
    }
    public JView doLikeCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("doLikeCount()");
        log.debug("---------------------");
        String aboardSeq = StringUtil.nvl(request.getParameter("aboardSeq"),"");
        log.debug("aboardSeq: {}",aboardSeq);
        LikeDTO like = new LikeDTO();
        like.setAboardSeq(Integer.parseInt(aboardSeq));
        int flag = reviewService.doLike(like);
        log.debug("Count flag:{}", flag);
        response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =  response.getWriter();
		out.print(flag);
		
        
    	return null;
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doWork(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doWork(request, response);
    }

    public JView TopLikeReviews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("---------------------");
        log.debug("TopLikeReviews()");
        log.debug("---------------------");
        
        	List<LikeDTO> likes = reviewService.getTopLikeCounts();
        	log.debug("TopLikeReviews - likes: {}", likes);

	        // 좋아요 순으로 정렬된 리뷰 목록을 저장할 리스트
	        List<ReviewDTO> topReviews = new ArrayList<>();
	
	        for (LikeDTO like : likes) {
	            int aboardSeq = like.getAboardSeq();
	            List<ReviewDTO> reviews = reviewService.getReviewsByAboardSeq(aboardSeq);
	            topReviews.addAll(reviews);
	        }
	        log.debug("topReviews {}", topReviews);
            Gson gson = new Gson();
            String json = gson.toJson(topReviews);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        
    	return null;
    }
    public JView getReviews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("---------------------");
        log.debug("getReviews()");
        log.debug("---------------------");

        SearchDTO inVO = new SearchDTO();
        
        // 페이지 번호와 페이지 크기 파라미터 추가
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        try {
            // 페이징된 리뷰 목록과 전체 리뷰 수 가져오기
            List<ReviewDTO> list = reviewService.getReviews(pageNumber, pageSize);
            int totalReviews = reviewService.getTotalReviews();
            for (ReviewDTO review : list) {
                int aboardSeq = review.getAboardSeq(); // 리뷰의 aboardSeq 가져오기
                log.debug("aboardSeq: "+aboardSeq);
                LikeDTO likeDTO = new LikeDTO();
                likeDTO.setAboardSeq(aboardSeq);
                
                int likeCount = reviewService.doLike(likeDTO);
                log.debug("likeCount: "+likeCount);
                // ReviewDTO에 likeCount 설정
                review.setLikeCount(likeCount);
                log.debug("review: "+review);
                
            }
            
            
            
            
            

            // JSON 응답 준비
            Map<String, Object> result = new HashMap<>();
            result.put("reviews", list);
            result.put("totalReviews", totalReviews);

            Gson gson = new Gson();
            String json = gson.toJson(result);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
		return null;
    }


    public JView doSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("---------------------");
        log.debug("doSave()");
        log.debug("---------------------");

        ReviewDTO review = new ReviewDTO();
        review.setAboardSeq(Integer.parseInt(StringUtil.nvl(request.getParameter("aboardSeq"), "0")));
        review.setContentId(StringUtil.nvl(request.getParameter("contentId"), ""));
        review.setUserId(StringUtil.nvl(request.getParameter("userId"), ""));
        review.setImgLink(StringUtil.nvl(request.getParameter("imgLink"), ""));
        review.setComments(StringUtil.nvl(request.getParameter("comments"), ""));
        review.setTitle(StringUtil.nvl(request.getParameter("title"), ""));
        review.setRegDt(StringUtil.nvl(request.getParameter("regDt"), ""));
        review.setModDt(StringUtil.nvl(request.getParameter("modDt"), ""));
        review.setReadCnt(Integer.parseInt(StringUtil.nvl(request.getParameter("readCnt"), "0")));

        int flag = reviewService.doSave(review);

        try {
            if (flag > 0) {
                log.debug("리뷰 저장 성공");
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("success");
            } else {
                log.debug("리뷰 저장 실패");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "리뷰 저장 실패");
            }
        } catch (IOException e) {
            log.error("IO 예외 발생: ", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return null;
    }

    /*
    public JView doRetrieveDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("---------------------");
        log.debug("doRetrieveDetail()");
        log.debug("---------------------");

        String aboardSeq = StringUtil.nvl(request.getParameter("aboardSeq"), "");
        ReviewDTO inVO = new ReviewDTO();
        inVO.setAboardSeq(aboardSeq);

        ReviewDTO outVO = reviewService.doSelectOne(inVO);
        request.setAttribute("review", outVO);

        JView viewName = new JView("/SEOUL_TRAVEL/review/review_detail.jsp");
        return viewName;
    }
    */
    
    public JView doSelectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("-----------------");
        log.debug("doSelectOne()");
        log.debug("-----------------");
        
        ReviewDTO inVO = new ReviewDTO();
        String userId = StringUtil.nvl(request.getParameter("userId"), "0");
        
        inVO.setUserId(userId);
        log.debug("inVO:" + inVO);
        
        ReviewDTO outVO = this.reviewService.doSelectOne(inVO);
        log.debug("outVO:" + outVO);
        
        // UI 데이터 전달
        request.setAttribute("outVO", outVO);
        
        return new JView("/resources/pages/review/review_detail.jsp");
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
            case "getReviews":
                viewName = getReviews(request, response);
                break;
            case "doSave":
                viewName = doSave(request, response);
                break;
            case "doLikeSave":
                viewName = doLikeSave(request, response);
                break;
            case "doLikeCount":
                viewName = doLikeCount(request, response);
                break;
            case "doSelectOne":
            	viewName = doSelectOne(request, response);
            	break;
            case "TopLikeReviews":
            	viewName = TopLikeReviews(request, response);
            	break;
            case "doLikeDelete":
            	viewName = doLikeDelete(request, response);
            	break;
                /*
            case "doRetrieveDetail":
                viewName = doRetrieveDetail(request, response);
                break;
                */
            default:
                log.debug("ReviewController work_div를 확인하세요. : {}", workDiv);
                break;
        }

        if (viewName != null) {
            viewName.render(request, response);
        }
        
        return viewName;
    }
    
}
