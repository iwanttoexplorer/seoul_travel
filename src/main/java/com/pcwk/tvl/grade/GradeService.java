package com.pcwk.tvl.grade;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;

public class GradeService {
	private GradeDao dao;
	
	public GradeService() {
		dao = new GradeDao();
	}
	
	/**
	 * 목록 조회
	 * @param search
	 * @return List<GradeDTO>
	 */
	public List<GradeDTO> doRetrieve(DTO search) {
		return dao.doRetrieve(search);
	}
}
