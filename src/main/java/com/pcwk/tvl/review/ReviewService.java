package com.pcwk.tvl.review;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.tvl.like.LikeDTO;
import com.pcwk.tvl.like.LikeDao;

public class ReviewService {

    private ReviewDao reviewDao;
    private LikeDao likeDao;
    public ReviewService() {
        reviewDao = new ReviewDao();
        likeDao = new LikeDao();
    }

    public List<ReviewDTO> doRetrieve(DTO search) {
        return reviewDao.doRetrieve(search);
    }

    public int doSave(ReviewDTO review) {
        return reviewDao.doSave(review);
    }
    public int doLike(LikeDTO like) {
    	return likeDao.doLike(like);
    }
    public int doLikeSave(LikeDTO like) {
    	return likeDao.doSave(like);
    }
    
    // 페이징된 리뷰 목록을 가져오는 메서드
    public List<ReviewDTO> getReviews(int pageNumber, int pageSize) throws SQLException {
        return reviewDao.getReviews(pageNumber, pageSize);
    }

    // 전체 리뷰 수를 가져오는 메서드
    public int getTotalReviews() throws SQLException {
        return reviewDao.getTotalReviews();
    }
    
    public List<ReviewDTO> getAllReviews() {
        return reviewDao.getAllReviews();
    }

    public ReviewDTO getReviewById(String id) {
        return reviewDao.getReviewById(id);
    }
    
    public ReviewDTO doSelectOne(ReviewDTO inVO) {
    	ReviewDTO outVO = null;
    	outVO = reviewDao.doSelectOne(inVO);
        return outVO;
    }
    public List<ReviewDTO> getReviewsByAboardSeq(int aboardSeq) {
        return reviewDao.getReviewsByAboardSeq(aboardSeq);
    }
    public List<LikeDTO> getTopLikeCounts(){
    	return reviewDao.getTopLikeCounts();
    }

	
}