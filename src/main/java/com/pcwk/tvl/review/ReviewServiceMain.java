package com.pcwk.tvl.review;

import java.util.List;


import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;

public class ReviewServiceMain implements PLog{
	ReviewService service;
	ReviewDTO review;
	public ReviewServiceMain() {
		service = new ReviewService();
		review = new ReviewDTO("1605990","1kqD993","http://tong.visitkorea.or.kr/cms/resource/88/3077788_image2_1.JPG","어쩌고","title","20240618","20240618",1);
	}
	public void doSave() {
		log.debug(" doSave() ");
		int flag = service.doSave(review);
		if(1==flag) {
			log.debug("저장 성공 :{}",flag);
		}else {
			log.debug("저장 실패 :{}",flag);
		}
	}
	public void doRetrieve(){
		log.debug("doRetrieve()");
		SearchDTO searchVO = new SearchDTO();
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		List<ReviewDTO> list = service.doRetrieve(searchVO);
		int i=1;
		for(ReviewDTO vo : list) {
			log.debug("i:{}, vo:{}",i++,vo);
		}

	}
	public static void main(String[] args) {
		ReviewServiceMain m = new ReviewServiceMain();
		//m.doSave();
		m.doRetrieve();

	}

}
