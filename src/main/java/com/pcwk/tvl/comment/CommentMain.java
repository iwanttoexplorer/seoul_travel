package com.pcwk.tvl.comment;

import com.pcwk.ehr.cmn.PLog;

public class CommentMain implements PLog{
	CommentDao dao;
	CommentDTO comment;
	public CommentMain() {
		dao = new CommentDao();
		comment = new CommentDTO(1, 1, "1kqD993", "댓글내용", "사용X", "사용X");
	}
	public void doSave() {
		log.debug(" doSave() ");
		int flag = dao.doSave(comment);
		if(1==flag) {
			log.debug("저장 성공 :{}",flag);
		}else {
			log.debug("저장 실패 :{}",flag);
		}
	}
	public void doDelete() {
		log.debug(" doDelete() ");
		int flag = dao.doDelete(comment);
		if(1==flag) {
			log.debug("삭제 성공 :{}",flag);
		}else {
			log.debug("삭제 실패 :{}",flag);
		}
	}
	public void doUpdate() {
		log.debug(" doUpdate() ");
		String updateStr="_변경";
		comment.setContent(comment.getContent()+updateStr);
		int flag = dao.doUpdate(comment);
		if(1==flag) {
			log.debug("갱신 성공 :{}",flag);
		}else {
			log.debug("갱신 실패 :{}",flag);
		}
	}
	public static void main(String[] args) {
		CommentMain m = new CommentMain();
		//m.doSave();
		//m.doUpdate();
		m.doDelete();

	}

}
