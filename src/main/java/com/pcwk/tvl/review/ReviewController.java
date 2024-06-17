package com.pcwk.tvl.review;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.tvl.like.LikeDTO;
import com.pcwk.tvl.like.LikeDao;

public class ReviewController extends HttpServlet implements ControllerV,PLog {
    private static final long serialVersionUID = 1L;
    private ReviewDao reviewDAO = new ReviewDao();
    private LikeDao likeDao = new LikeDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doWork(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doWork(request, response);
    }

    @Override
    public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "list":
                    listReviews(request, response);
                    break;
                case "delete":
                    deleteReview(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateReview(request, response);
                    break;
                case "like":
                    likeReview(request, response);
                    break;
                default:
                    listReviews(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        return null; // JView 객체를 반환하도록 설계되었지만, JSP 페이지로 포워딩하기 때문에 null 반환
    }

    private void listReviews(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String category = request.getParameter("category");
        if (category == null) {
            category = "all";
        }

        ReviewDTO searchParam = new ReviewDTO();
        searchParam.setContentId(category);
        List<ReviewDTO> reviews = reviewDAO.doRetrieve(searchParam);
        request.setAttribute("reviews", reviews);
        request.getRequestDispatcher("/reviews.jsp").forward(request, response);
    }

    private void deleteReview(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int aboardSeq = Integer.parseInt(request.getParameter("aboardSeq"));
        ReviewDTO review = new ReviewDTO(aboardSeq);
        reviewDAO.doDelete(review);
        response.sendRedirect("ReviewController?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int aboardSeq = Integer.parseInt(request.getParameter("aboardSeq"));
        ReviewDTO existingReview = reviewDAO.doSelectOne(new ReviewDTO(aboardSeq));
        request.setAttribute("review", existingReview);
        request.getRequestDispatcher("/review-form.jsp").forward(request, response);
    }

    private void updateReview(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int aboardSeq = Integer.parseInt(request.getParameter("aboardSeq"));
        String title = request.getParameter("title");
        String comments = request.getParameter("comments");

        ReviewDTO review = new ReviewDTO();
        review.setAboardSeq(aboardSeq);
        review.setTitle(title);
        review.setComments(comments);

        reviewDAO.doUpdate(review);
        response.sendRedirect("ReviewController?action=list");
    }

    private void likeReview(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int aboardSeq = Integer.parseInt(request.getParameter("aboardSeq"));
        String userId = (String)request.getSession().getAttribute("userId");
        LikeDTO like = new LikeDTO(userId, aboardSeq);
        int flag = likeDao.doSave(like);
        if (flag == 1) {
            // 좋아요 기록이 성공적으로 저장된 경우, 해당 리뷰의 좋아요 수를 업데이트
            int likeCount = likeDao.doLike(aboardSeq);
            response.sendRedirect("ReviewController?action=list"); // 리스트 화면으로 리다이렉트
        } else {
        	String errorMessage = "추천 실패. 다시 시도해주세요."; // 사용자에게 표시할 메시지
            log.debug("추천 실패: {}", flag);
            request.setAttribute("errorMessage", errorMessage);
            response.sendRedirect("ReviewController?action=list");
        }
    }
}
