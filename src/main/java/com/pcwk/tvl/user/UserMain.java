package com.pcwk.tvl.user;

import java.util.List;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;

public class UserMain implements PLog {
	UserDao dao;
	UserDTO member;
	
	public UserMain() {
		dao = new UserDao();
		member = new UserDTO("1kqD993", 1, "이름", "pw123213#2", "emailamc1@na3231ver.kr", "디폴트");
	}
	
	public void doSave() {
		log.debug(" doSave() ");
		int flag = dao.doSave(member);
		if(1==flag) {
			log.debug("가입 성공 :{}",flag);
		}else {
			log.debug("가입 실패 :{}",flag);
		}
		
	}
	
	public void doDelete() {
		log.debug(" doDelete() ");
		int flag = dao.doDelete(member);
		if(1==flag) {
			log.debug("삭제 성공 :{}",flag);
		}else {
			log.debug("삭제 실패 :{}",flag);
		}
	}
	
	public void doUpdate() {
		log.debug(" doUpdate() ");
		
		member.setGradesSeq(0);
		member.setUserEmail(member.getUserEmail()+"m");
		member.setUserName(member.getUserName()+"수");
		member.setUserPw(member.getUserPw()+"##!!");
		
		int flag = dao.doUpdate(member);
		if(1==flag) {
			log.debug("수정 성공 :{}",flag);
		}else {
			log.debug("수정 실패 :{}",flag);
		}
		
	}
	
	public void doSelectOne() {
		log.debug(" doSelectOne() ");
		UserDTO outVO = dao.doSelectOne(member);
		if(null!=outVO) {
			log.debug("단건조회 성공 :{}",outVO);
		}else {
			log.debug("단건조회 실패 :{}",outVO);
		}
	}
	
	public void doRetrieve() {
		log.debug(" doRetrieve() ");
		SearchDTO searchVO = new SearchDTO();
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		
		//검색구분
		searchVO.setSearchDiv("50");
		searchVO.setSearchWord("2024-06-13");
		
		List<UserDTO> list = dao.doRetrieve(searchVO);
		
		int i=1;
		for(UserDTO vo : list) {
			log.debug("i:{}, vo:{}",i++,vo);
		}
	}
	
	public static void main(String[] args) {
		UserMain m = new UserMain();
//		m.doSave();
//		m.doDelete();
//		m.doUpdate();
//		m.doSelectOne();
//		m.doRetrieve();
	}

}
