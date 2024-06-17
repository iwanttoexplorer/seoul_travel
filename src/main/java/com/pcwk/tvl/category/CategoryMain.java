package com.pcwk.tvl.category;

import java.util.List;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;

public class CategoryMain implements PLog{

	CategoryDao dao;
	CategoryDTO category01;
	
	public CategoryMain() {
		dao = new CategoryDao();
		
		category01 = new CategoryDTO("A0101", "자연관광지");
		//category01 = new CategoryDTO("A05020200", "서양식");
		
	}
	
	/**
	 * 다건 조회
	 */
	public void doRetrieve() {
		log.debug(" doRetrieve() ");
		SearchDTO  searchVO = new SearchDTO();
		
		List<CategoryDTO> list=dao.doRetrieve(searchVO);
		
		int i=0;
		for(CategoryDTO vo :list) {
			log.debug("i: {}, vo: {}", ++i, vo);
		}
	}
	
	/**
	 * 단건 조회
	 */
	public void doSelectOne() {
		log.debug(" doSelectOne() ");
		CategoryDTO outVO = dao.doSelectOne(category01);
		if(null != outVO) {
			log.debug("단건조회 성공 :{}",outVO);
		}else {
			log.debug("단건조회 실패 :{}",outVO);
		}
	}
	
	public static void main(String[] args) {
		CategoryMain cm = new CategoryMain();
		
		//cm.doSelectOne();
		cm.doRetrieve();

	}

}
