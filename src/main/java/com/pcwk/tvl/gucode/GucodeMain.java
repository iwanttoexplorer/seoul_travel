package com.pcwk.tvl.gucode;

import java.util.List;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.tvl.category.CategoryDTO;

public class GucodeMain implements PLog{
	
	GucodeDao dao;
	GucodeDTO gucode01;
	
	public GucodeMain() {
		dao = new GucodeDao();
		
		gucode01 = new GucodeDTO("7", "구로구");
		
	}
	
	/**
	 * 다건 조회
	 */
	public void doRetrieve() {
		log.debug(" doRetrieve() ");
		SearchDTO  searchVO = new SearchDTO();
		
		List<GucodeDTO> list=dao.doRetrieve(searchVO);
		
		int i=0;
		for(GucodeDTO vo :list) {
			log.debug("i: {}, vo: {}", ++i, vo);
		}
	}

	/**
	 * 단건 조회
	 */
	public void doSelectOne() {
		
		log.debug(" doSelectOne() ");
		
		GucodeDTO outVO = dao.doSelectOne(gucode01);
		
		if(null != outVO) {
			log.debug("단건조회 성공 :{}",outVO);
		}else {
			log.debug("단건조회 실패 :{}",outVO);
		}
	}
	
	public static void main(String[] args) {
		GucodeMain gm=new GucodeMain();
		
		//gm.doSelectOne();
		gm.doRetrieve();

	}

}
