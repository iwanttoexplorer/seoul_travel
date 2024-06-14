package com.pcwk.tvl.review;

import java.sql.Connection;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.PLog;

public class ReviewMain implements PLog{
	//seq, contentid, userid, img_link, comment, title, regdt,moddt,readcnt
	ReviewDao dao;
	ReviewDTO review;
	public ReviewMain() {
		
		dao = new ReviewDao();
		review = new ReviewDTO(2, "1605990", "1kqD993", "http://tong.visitkorea.or.kr/cms/resource/88/3077788_image2_1.JPG", "comments", "title", "20240614", "20240614", 1);
		
	}
	
	public void doSave() {
		log.debug("doSave() ");
		int flag=dao.doSave(review);
		if(1==flag) {
			log.debug("저장 성공: {}",flag);
		}else {
			log.debug("저장 실패: {}",flag);
		}
	}
	public void doDelete() {
		log.debug(" doDelete() ");
		int flag = dao.doDelete(review);
		if(1==flag) {
			log.debug("삭제 성공 :{}",flag);
		}else {
			log.debug("삭제 실패 :{}",flag);
		}
	}
	public void doUpdate() {
		log.debug(" doUpdate() ");
		String updatestr="변경";
		review.setTitle(review.getTitle()+updatestr);
		review.setComments(review.getComments()+updatestr);
		int flag = dao.doUpdate(review);
		if(1==flag) {
			log.debug("수정 성공 :{}",flag);
		}else {
			log.debug("수정 실패 :{}",flag);
		}
	}
	
	public static void main(String[] args) {
		ReviewMain m = new ReviewMain();
//		m.doSave();
//		m.doUpdate();
//		m.doDelete();

	}

}
