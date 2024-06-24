package com.pcwk.tvl.like;

public class LikeDTO {
	private String userId;
	private int aboardSeq;
	private String crtDt;
	public String getUserId() {
		return userId;
	}
	public LikeDTO() {
		
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getAboardSeq() {
		return aboardSeq;
	}
	public void setAboardSeq(int aboardSeq) {
		this.aboardSeq = aboardSeq;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	public LikeDTO(String userId, int aboardSeq) {
		super();
		this.userId = userId;
		this.aboardSeq = aboardSeq;
		
	}
	@Override
    public String toString() {
        return "LikeDTO{aboardSeq=" + aboardSeq + '}';
    }

	
}
