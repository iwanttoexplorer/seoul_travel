package com.pcwk.tvl.category;

import java.util.List;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.tvl.content.ContentDTO;
import com.pcwk.tvl.content.ContentServiceMain;

public class CategoryServiceMain implements PLog {

	CategoryService service;
	CategoryDTO category01;

	public CategoryServiceMain() {
		service = new CategoryService();
		
		category01 = new CategoryDTO("A0101", "자연관광지");
		//category01 = new CategoryDTO("A05020200", "서양식");
	}
	
	public void doSelectOne() {
		log.debug(" doSelectOne() ");
		CategoryDTO outVO = service.doSelectOne(category01);
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
		
		List<CategoryDTO> list=service.doRetrieve(searchVO);
		
		int i=0;
		for(CategoryDTO vo :list) {
			log.debug("i: {}, vo: {}", ++i, vo);
		}
	}
	
	public static void main(String[] args) {
		CategoryServiceMain caM = new CategoryServiceMain();
		//caM.doRetrieve();
		caM.doSelectOne();
	}
}
