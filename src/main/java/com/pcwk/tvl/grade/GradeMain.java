package com.pcwk.tvl.grade;

import java.util.List;

import com.pcwk.ehr.cmn.PLog;

public class GradeMain  implements PLog{
	GradeDao dao;
	
	public GradeMain() {
		dao = new GradeDao();
	}
	
	public void doRetrieve() {
		log.debug("doRetrieve()");
		GradeDTO inVO = new GradeDTO();
		List<GradeDTO> list = dao.doRetrieve(inVO);
		
		for(GradeDTO vo : list) log.debug(vo);
		
	}

	public static void main(String[] args) {
		GradeMain m = new GradeMain();
		m.doRetrieve();

	}

}
