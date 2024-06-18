package com.pcwk.tvl.gucode;

import java.util.List;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;

public class GucodeServiceMain implements PLog{

	GucodeService service;
	GucodeDTO Gucode01;
	
	public GucodeServiceMain() {
		
		service = new GucodeService();
		
		Gucode01 = new GucodeDTO("7", "구로구");
		
	}
	
	/**
	 * 단건 조회
	 */
	public void doSelectOne() {
		
		log.debug(" doSelectOne() ");
		
		GucodeDTO outVO = service.doSelectOne(Gucode01);
		
		if(null != outVO) {
			log.debug("단건조회 성공 :{}",outVO);
		}else {
			log.debug("단건조회 실패 :{}",outVO);
		}
	}
	
	/**
	 * 다건 조회
	 */
	public void doRetrieve() {
		log.debug(" doRetrieve() ");
		SearchDTO  searchVO = new SearchDTO();
		
		List<GucodeDTO> list = service.doRetrieve(searchVO);
		
		int i=0;
		for(GucodeDTO vo :list) {
			log.debug("i: {}, vo: {}", ++i, vo);
		}
	}
	
	public static void main(String[] args) {
		GucodeServiceMain gcM = new GucodeServiceMain();
		//gcM.doRetrieve();
		gcM.doSelectOne();

	}

}
