package com.pcwk.tvl.review;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;

public class ReviewService {

    private ReviewDao reviewDao;

    public ReviewService() {
        reviewDao = new ReviewDao();
    }

    public List<ReviewDTO> doRetrieve(DTO search) {
        return reviewDao.doRetrieve(search);
    }

    public int doSave(ReviewDTO review) {
        return reviewDao.doSave(review);
    }
}