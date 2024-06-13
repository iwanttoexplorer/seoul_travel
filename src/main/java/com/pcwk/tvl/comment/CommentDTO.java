package com.pcwk.tvl.comment;

public class CommentDTO {
	private String comSeq;
	private String aboardSeq;
	private String userId;
	private String content;
	private String regDt;
	private String modDt;
	public CommentDTO() {}
	public CommentDTO(String comSeq, String aboardSeq, String userId, String content, String regDt, String modDt) {
		super();
		this.comSeq = comSeq;
		this.aboardSeq = aboardSeq;
		this.userId = userId;
		this.content = content;
		this.regDt = regDt;
		this.modDt = modDt;
	}
	public String getComSeq() {
		return comSeq;
	}
	public void setComSeq(String comSeq) {
		this.comSeq = comSeq;
	}
	public String getAboardSeq() {
		return aboardSeq;
	}
	public void setAboardSeq(String aboardSeq) {
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
