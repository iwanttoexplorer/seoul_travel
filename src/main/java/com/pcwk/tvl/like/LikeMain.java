package com.pcwk.tvl.like;

import com.pcwk.ehr.cmn.PLog;

public class LikeMain implements PLog{
	LikeDao dao;
	LikeDTO like;
	public LikeMain() {
		dao = new LikeDao();
		like = new LikeDTO(null, 0, null);
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
	public static void main(String[] args) {
		LikeMain m = new LikeMain();
		m.doSave();
	}

}
