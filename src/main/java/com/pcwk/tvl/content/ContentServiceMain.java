package com.pcwk.tvl.content;

import java.util.List;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.tvl.gucode.GucodeDTO;

public class ContentServiceMain implements PLog{
	
	ContentService service;
	ContentDTO content01;

	public ContentServiceMain() {
		service = new ContentService();
		
		content01 = new ContentDTO("2638475");
	}
	
	public void doSelectOne() {
		log.debug(" doSelectOne() ");
		ContentDTO outVO = service.doSelectOne(content01);
		if(null != outVO) {
			log.debug("단건조회 성공 :{}",outVO);
		}else {
			log.debug("단건조회 실패 :{}",outVO);
		}
	}
	
	public void doRetrieve() {
		log.debug(" doRetrieve() ");
		
		SearchDTO  searchVO = new SearchDTO();
		
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		
		//검색구분
		searchVO.setSearchDiv("20");
		searchVO.setSearchWord("A0201");
		
		List<ContentDTO> list=service.doRetrieve(searchVO);
		
		int i=0;
		for(ContentDTO vo :list) {
			log.debug("i: {}, vo: {}", ++i, vo);
		}
	}
	
	public static void main(String[] args) {
		ContentServiceMain mService = new ContentServiceMain();
		//mService.doRetrieve();
		mService.doSelectOne();

	}

}
