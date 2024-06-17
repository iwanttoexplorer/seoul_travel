package com.pcwk.tvl.comment;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
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
	public List<CommentDTO> doRetrieve(DTO search) {
		return dao.doRetrieve(search);
	}
}
