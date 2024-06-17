package com.pcwk.tvl.like;

import com.pcwk.ehr.cmn.PLog;

public class LikeMain implements PLog{
	LikeDao dao;
	LikeDTO like;
	public LikeMain() {
		dao = new LikeDao();
<<<<<<< HEAD
		like = new LikeDTO("4kqD945", 1);
=======
		like = new LikeDTO(null, 0, null);
>>>>>>> 229e90e9499623fb0dee7b80d20ad385d94343f5
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
<<<<<<< HEAD
	public void doLike() {
		log.debug("doLike()");
		int likeCount = dao.doLike(like.getAboardSeq());
		log.debug("추천수: {}",likeCount);
	}
	public static void main(String[] args) {
		LikeMain m = new LikeMain();
//		m.doSave();
		m.doLike();
	}

}
=======
	public static void main(String[] args) {
		LikeMain m = new LikeMain();
		m.doSave();
	}

}
>>>>>>> 229e90e9499623fb0dee7b80d20ad385d94343f5
