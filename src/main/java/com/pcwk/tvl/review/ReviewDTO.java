package com.pcwk.tvl.review;

import java.util.Date;

import com.pcwk.ehr.cmn.DTO;

public class ReviewDTO extends DTO {
    private int aboard_seq;
    private String comment;
    private String imgLink;
    private String contentId;
    private String user_id;
    private String title;
    private int readCnt;
    private Date regDt;   
    private Date modDt;
    
 // Getters and Setters
    
    public ReviewDTO() {
    	
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

	public int getAboard_seq() {
		return aboard_seq;
	}

	public void setAboard_seq(int aboard_seq) {
		this.aboard_seq = aboard_seq;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public Date getModDt() {
		return modDt;
	}

	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}
	
	public ReviewDTO(int aboard_seq, String comment, String imgLink, String contentId, String user_id, String title,
			int readCnt, Date regDt, Date modDt) {
		super();
		this.aboard_seq = aboard_seq;
		this.comment = comment;
		this.imgLink = imgLink;
		this.contentId = contentId;
		this.user_id = user_id;
		this.title = title;
		this.readCnt = readCnt;
		this.regDt = regDt;
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "ReviewDTO [aboard_seq=" + aboard_seq + ", comment=" + comment + ", imgLink=" + imgLink + ", contentId="
				+ contentId + ", user_id=" + user_id + ", title=" + title + ", readCnt=" + readCnt + ", regDt=" + regDt
				+ ", modDt=" + modDt + "]";
	}

}  
	
    
    