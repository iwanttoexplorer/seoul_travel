package com.pcwk.tvl.like;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.tvl.review.ReviewDTO;

public class LikeMain implements PLog{
	LikeDao dao;
	LikeDTO like;
	public LikeMain() {
		dao = new LikeDao();
		like = new LikeDTO("5kqD996",1);

	}
	public void doSave() {
		log.debug("doSave()");
		int flag = dao.doSave(like);
		if(1==flag) {
			log.debug("저장 성공 :{}",flag);
		}else {
			log.debug("저장 실패 :{}",flag);
		}
	}
	public void doLike() {
		log.debug("doLike()");
		int likeCount = dao.doLike(like);
		log.debug("추천수: {}",likeCount);
	}
	public void doDelete() {
		log.debug("doDelete()");
		int likeCount = dao.doDelete(like);
		log.debug("추천수: {}",likeCount);
	}
	public static void main(String[] args) {
		LikeMain m = new LikeMain();
		m.doSave();
		
		//m.doDelete();
		m.doLike();
	}

}
