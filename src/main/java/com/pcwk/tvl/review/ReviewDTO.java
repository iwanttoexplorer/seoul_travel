package com.pcwk.tvl.review;

public class ReviewDTO {
    private String aboardSeq; // 리뷰 순서번호
    private String contentId; // 콘텐츠ID
    private String userId; // 리뷰 작성자
    private String imgLink; // 사진
    private String comments; // 리뷰 내용
    private String title; // 제목
    private String regDt; // 작성일
    private String modDt; // 수정일
    public ReviewDTO() {}
	@Override
	public String toString() {
		return "ReviewDTO [aboardSeq=" + aboardSeq + ", contentId=" + contentId + ", userId=" + userId + ", imgLink="
				+ imgLink + ", comments=" + comments + ", title=" + title + ", regDt=" + regDt + ", modDt=" + modDt
				+ "]";
	}
	public String getAboardSeq() {
		return aboardSeq;
	}
	public void setAboardSeq(String aboardSeq) {
		this.aboardSeq = aboardSeq;
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
	public String getImgLink() {
		return imgLink;
	}
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public ReviewDTO(String aboardSeq, String contentId, String userId, String imgLink, String comments, String title,
			String regDt, String modDt) {
		super();
		this.aboardSeq = aboardSeq;
		this.contentId = contentId;
		this.userId = userId;
		this.imgLink = imgLink;
		this.comments = comments;
		this.title = title;
		this.regDt = regDt;
		this.modDt = modDt;
	}
    
}
