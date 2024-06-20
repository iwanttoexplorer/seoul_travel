package com.pcwk.tvl.content;

import java.util.List;

import com.pcwk.ehr.cmn.ContentsSearchDTO;
import com.pcwk.ehr.cmn.PLog;


public class ContentMain implements PLog {

	ContentDao dao;
	ContentDTO content01;
	
	public ContentMain() {
		dao = new ContentDao();
		
		content01 = new ContentDTO("2638475","","","","","","","","");
		
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
		
		ContentsSearchDTO searchVO = new ContentsSearchDTO();
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		
		searchVO.setSearchDiv("70");
		searchVO.setCategoryWord("자연관광지");
		searchVO.setgNameWord("강동구");
		searchVO.setSearchWord("일");
		
		//검색구분
		
		
		List<ContentDTO> list = dao.doRetrieve(searchVO);
		
		int i=1;
		for(ContentDTO vo : list) {
			log.debug("i:{}, vo:{}",i++,vo);
		}
	}
	
	public static void main(String[] args) {
		ContentMain m =new ContentMain();
		m.doRetrieve();
//		m.doSelectOne();
	}
	
}
