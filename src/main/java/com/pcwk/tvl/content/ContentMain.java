package com.pcwk.tvl.content;

import java.util.List;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;

public class ContentMain implements PLog {

	ContentDao dao;
	ContentDTO content01;
	
	public ContentMain() {
		dao = new ContentDao();
		
		content01 = new ContentDTO("2638475");
		
		log.debug("AnswerController 생성자");
		log.debug("dao:{}", dao);
		log.debug("answer01:{}", content01);
	}

	public void doSelectOne() {
		log.debug(" doSelectOne() ");
		ContentDTO outVO = dao.doSelectOne(content01);
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
		searchVO.setSearchDiv("40");
		searchVO.setSearchWord("A0201");
		
		List<ContentDTO> list=dao.doRetrieve(searchVO);
		
		int i=0;
		for(ContentDTO vo :list) {
			log.debug("i: {}, vo: {}", ++i, vo);
		}
	}
	
	public static void main(String[] args) {
		ContentMain m =new ContentMain();
		m.doRetrieve();
		//m.doSave();
		//m.doSelectOne();
	}
	
}
