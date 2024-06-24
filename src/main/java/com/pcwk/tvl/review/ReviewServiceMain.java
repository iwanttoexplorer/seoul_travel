package com.pcwk.tvl.review;

import java.util.List;


import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.tvl.like.LikeDTO;

public class ReviewServiceMain implements PLog{
	ReviewService service;
	ReviewDTO review;
	LikeDTO like;
	public ReviewServiceMain() {
		service = new ReviewService();
		review = new ReviewDTO("1605990","1kqD993","http://tong.visitkorea.or.kr/cms/resource/88/3077788_image2_1.JPG","어쩌고","title","20240618","20240618",1);
		like = new LikeDTO("2kqD993", 1);
		
	}
	public void doSave() {
		log.debug(" doSave() ");
		int flag = service.doSave(review);
		if(1==flag) {
			log.debug("저장 성공 :{}",flag);
		}else {
			log.debug("저장 실패 :{}",flag);
		}
	}
	public void doRetrieve(){
		log.debug("doRetrieve()");
		SearchDTO searchVO = new SearchDTO();
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		List<ReviewDTO> list = service.doRetrieve(searchVO);
		int i=1;
		for(ReviewDTO vo : list) {
			log.debug("i:{}, vo:{}",i++,vo);
		}

	}
	public void doLike() {
		log.debug("doLike()");
		log.debug("like: "+like);
		int likeCount = service.doLike(like);
		if(0<likeCount) {
			log.debug("추천수가 " + likeCount + "입니다.");
		}else {
			log.debug("추천수 :{}",likeCount);
		}
	}
	public void doLikeSave() {
		log.debug("doLikeSave()");
		int flag = service.doLikeSave(like);
		if(1==flag) {
			log.debug("추천수 저장 성공 :{}",flag);
		}else {
			log.debug("추천수 저장 실패 :{}",flag);
		}
	}
	public void getTopLikeCounts() {
		log.debug(" getTopLikeCounts() ");
		LikeDTO search = new LikeDTO();
        // 특정 aboardSeq로 검색

        List<LikeDTO> list = service.getTopLikeCounts();
        for (LikeDTO like : list) {
            log.debug("like: {}", like);
        }
	}
	public void getReviewsByAboardSeq() {
		List<LikeDTO> likes = service.getTopLikeCounts();
		log.debug("getReviewsByAboardSeq()");
		log.debug("likes: {}",likes);
		for (LikeDTO like : likes) {
            List<ReviewDTO> reviews = service.getReviewsByAboardSeq(like.getAboardSeq());
            for (ReviewDTO review : reviews) {
                log.debug("Review: {}", review);
            }
        }
	}
	public static void main(String[] args) {
		ReviewServiceMain m = new ReviewServiceMain();
		//m.doSave();
		//m.doRetrieve();
		
		//m.doLike();
		//m.doLikeSave();
		m.getReviewsByAboardSeq();
	}

}
