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

public class ReviewController extends HttpServlet implements ControllerV {
    private static final long serialVersionUID = 1L;
    private ReviewDao reviewDAO = new ReviewDao();

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
        reviewDAO.like(aboardSeq);
        response.sendRedirect("ReviewController?action=list");
    }
}
