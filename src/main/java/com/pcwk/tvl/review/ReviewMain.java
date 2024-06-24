package com.pcwk.tvl.review;

import java.sql.Connection;
import java.util.List;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.tvl.comment.CommentDTO;
import com.pcwk.tvl.like.LikeDTO;

public class ReviewMain implements PLog{
	//seq, contentid, userid, img_link, comment, title, regdt,moddt,readcnt
	ReviewDao dao;
	ReviewDTO review;
	LikeDTO like;
	public ReviewMain() {
		
		dao = new ReviewDao();
		review = new ReviewDTO( "1605990", "1kqD993", "http://tong.visitkorea.or.kr/cms/resource/88/3077788_image2_1.JPG", "comments", "title", "20240614", "20240614", 1);
		
	}
	
	public void doSave() {
		log.debug("doSave() ");
		int flag=dao.doSave(review);
		if(1==flag) {
			log.debug("저장 성공: {}",flag);
		}else {
			log.debug("저장 실패: {}",flag); 	
		}
	}
	public void doDelete() {
		log.debug(" doDelete() ");
		int flag = dao.doDelete(review);
		if(1==flag) {
			log.debug("삭제 성공 :{}",flag);
		}else {
			log.debug("삭제 실패 :{}",flag);
		}
	}
	public void doUpdate() {
		log.debug(" doUpdate() ");
		String updatestr="변경";
		review.setTitle(review.getTitle()+updatestr);
		review.setComments(review.getComments()+updatestr);
		int flag = dao.doUpdate(review);
		if(1==flag) {
			log.debug("수정 성"
					+ "공 :{}",flag);
		}else {
			log.debug("수정 실패 :{}",flag);
		}
	}
	public void doRetrieve() {
		log.debug("doRetrieve()");
		ReviewDTO search = new ReviewDTO();
		List<ReviewDTO> reviews = dao.doRetrieve(search);
		for(ReviewDTO review:reviews) {
			log.debug("Review: {}",review);
		}
	}
	public void getTopLikeCounts() {
		log.debug(" getTopLikeCounts() ");
		LikeDTO search = new LikeDTO();
        // 특정 aboardSeq로 검색

        List<LikeDTO> list = dao.getTopLikeCounts();
        for (LikeDTO like : list) {
            log.debug("like: {}", like);
        }
	}
	public void getReviewsByAboardSeq() {
		List<LikeDTO> likes = dao.getTopLikeCounts();
		log.debug("getReviewsByAboardSeq()");
		log.debug("likes: {}",likes);
		for (LikeDTO like : likes) {
            List<ReviewDTO> reviews = dao.getReviewsByAboardSeq(like.getAboardSeq());
            for (ReviewDTO review : reviews) {
                log.debug("Review: {}", review);
            }
        }
	}
	public static void main(String[] args) {
		ReviewMain m = new ReviewMain();
//		m.doSave();
//		m.doUpdate();
//		m.doDelete();
//		m.doRetrieve();
//		m.getTopLikeCounts();
		m.getReviewsByAboardSeq();
	}

}
