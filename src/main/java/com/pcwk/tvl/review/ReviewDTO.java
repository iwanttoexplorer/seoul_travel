package com.pcwk.tvl.review;

import java.util.Date;

import com.pcwk.ehr.cmn.DTO;

public class ReviewDTO extends DTO {
    private int aboardSeq;
    private String contentId;
    private String userId;
    private String imgLink;
    private String comments;
    private String title;
    private String regDt;   
    private String modDt;
    private int readCnt;
 // Getters and Setters
    
    public ReviewDTO() {
    	
    }
    
    public ReviewDTO(int aboardSeq) {
        this.aboardSeq = aboardSeq;
    }
    
	public int getReadCnt() {
		return readCnt;
	}
	
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAboardSeq() {
		return aboardSeq;
	}

	public void setAboardSeq(int aboardSeq) {
		this.aboardSeq = aboardSeq;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public ReviewDTO(int aboardSeq, String contentId, String userId, String imgLink, String comments, String title,
			String regDt, String modDt, int readCnt) {
		super();
		this.aboardSeq = aboardSeq;
		this.contentId = contentId;
		this.userId = userId;
		this.imgLink = imgLink;
		this.comments = comments;
		this.title = title;
		this.regDt = regDt;
		this.modDt = modDt;
		this.readCnt = readCnt;
	}


	@Override
	public String toString() {
		return "ReviewDTO [aboardSeq=" + aboardSeq + ", contentId=" + contentId + ", userId=" + userId + ", imgLink="
				+ imgLink + ", comments=" + comments + ", title=" + title + ", regDt=" + regDt + ", modDt=" + modDt
				+ ", readCnt=" + readCnt + ", toString()=" + super.toString() + "]";
	}
	
	

}  
	
    
    