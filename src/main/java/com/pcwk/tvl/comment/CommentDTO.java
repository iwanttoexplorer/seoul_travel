package com.pcwk.tvl.comment;

import com.pcwk.ehr.cmn.DTO;

public class CommentDTO extends DTO{
	private int comSeq;
	private int aboardSeq;
	private String userId;
	private String content;
	private String regDt;
	private String modDt;
	public CommentDTO() {}
	
	@Override
	public String toString() {
		return "CommentDTO [comSeq=" + comSeq + ", aboardSeq=" + aboardSeq + ", userId=" + userId + ", content="
				+ content + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}

	public CommentDTO(int comSeq, int aboardSeq, String userId, String content, String regDt, String modDt) {
		super();
		this.comSeq = comSeq;
		this.aboardSeq = aboardSeq;
		this.userId = userId;
		this.content = content;
		this.regDt = regDt;
		this.modDt = modDt;
	}

	public int getComSeq() {
		return comSeq;
	}
	public void setComSeq(int comSeq) {
		this.comSeq = comSeq;
	}
	public int getAboardSeq() {
		return aboardSeq;
	}
	public void setAboardSeq(int aboardSeq) {
		this.aboardSeq = aboardSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
}
