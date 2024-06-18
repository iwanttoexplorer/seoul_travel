package com.pcwk.tvl.comment;

import java.util.List;

import com.pcwk.ehr.cmn.PLog;

public class CommentServiceMain implements PLog{
	CommentService service;
	CommentDTO comment;
	
	public void doSave() {
		log.debug(" doSave() ");
		int flag = service.doSave(comment);
		if(1==flag) {
			log.debug("저장 성공 :{}",flag);
		}else {
			log.debug("저장 실패 :{}",flag);
		}
	}
	public void doDelete() {
		
	}
	public void doUpdate() {
		log.debug(" doUpdate() ");
		String updateStr="_변경dd";
		comment.setContent(comment.getContent()+updateStr);
		int flag = service.doUpdate(comment);
		if(1==flag) {
			log.debug("갱신 성공 :{}",flag);
		}else {
			log.debug("갱신 실패 :{}",flag);
		}
	}
	public void doRetrieve() {
		log.debug("doRetrieve()");
        CommentDTO search = new CommentDTO();
        search.setAboardSeq(1); // 특정 aboardSeq로 검색

        List<CommentDTO> comments = service.doRetrieve(search);
        for (CommentDTO comment : comments) {
            log.debug("Comment: {}", comment);
        }
	}
	public CommentServiceMain() {
		service = new CommentService();
		comment = new CommentDTO(1, "1kqD993", "댓글내용", "사용X", "사용X");
	}
	
	public static void main(String[] args) {
		CommentServiceMain m = new CommentServiceMain();
		//m.doUpdate();
		//m.doRetrieve();
		m.doSave();
	}

}
