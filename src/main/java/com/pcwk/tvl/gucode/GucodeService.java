package com.pcwk.tvl.gucode;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.tvl.content.ContentDTO;
import com.pcwk.tvl.content.ContentDao;

public class GucodeService {

	private GucodeDao dao;
	
	public GucodeService() {
		dao = new GucodeDao();
	}

	public GucodeDTO doSelectOne(GucodeDTO search) {
		return dao.doSelectOne(search);
	}
	
	/**
	 * 목록 조회
	 * @param search
	 * @return
	 */
	public List<GucodeDTO> doRetrieve(DTO search) {
		return dao.doRetrieve(search);
	}
}
