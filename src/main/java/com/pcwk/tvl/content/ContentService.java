package com.pcwk.tvl.content;

import java.util.List;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.DTO;

public class ContentService  implements PLog {

	private ContentDao dao;
	
	public ContentService() {
		dao = new ContentDao();
	}
	
	public ContentDTO doSelectOne(ContentDTO search) {
		return dao.doSelectOne(search);
	}
	
	/**
	 * 목록 조회
	 * @param search
	 * @return
	 */
	public List<ContentDTO> doRetrieve(DTO search) {
		return dao.doRetrieve(search);
	}
	
}
