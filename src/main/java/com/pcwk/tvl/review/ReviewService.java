package com.pcwk.tvl.review;

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
    
}