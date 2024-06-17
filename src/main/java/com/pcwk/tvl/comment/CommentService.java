package com.pcwk.tvl.comment;

import com.pcwk.ehr.cmn.PLog;

public class CommentService implements PLog{
	private CommentDao dao;
	public CommentService() {
		dao = new CommentDao();
	}
	/**
	 * 저장
	 * @param param
	 * @return 성공(1) 실패(0)
	 */
	public int doSave(CommentDTO param) {
		return dao.doSave(param);
	}
	public int doDelete(CommentDTO param) {
		return dao.doDelete(param);
	}
	public int doUpdate(CommentDTO param) {
		return dao.doUpdate(param);
	}
}
