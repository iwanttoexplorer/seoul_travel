package com.pcwk.tvl.content;

public class ContentDTO {

	private String contentId; //콘텐츠ID
	private String category;  //카테고리
	private String gucode;    //구코드
	private String tel;       //전화번호
	private String addr;      //주소
	private String imgLink;   //이미지링크
	private String title;     //제목
	private String regDt;     //등록일
	private String modDt;     //수정일
	
	public ContentDTO() {}

	public ContentDTO(String contentId, String category, String gucode, String tel, String addr, String imgLink,
			String title, String regDt, String modDt) {
		super();
		this.contentId = contentId;
		this.category = category;
		this.gucode = gucode;
		this.tel = tel;
		this.addr = addr;
		this.imgLink = imgLink;
		this.title = title;
		this.regDt = regDt;
		this.modDt = modDt;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGucode() {
		return gucode;
	}

	public void setGucode(String gucode) {
		this.gucode = gucode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
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

	@Override
	public String toString() {
		return "ContentDTO [contentId=" + contentId + ", category=" + category + ", gucode=" + gucode + ", tel=" + tel
				+ ", addr=" + addr + ", imgLink=" + imgLink + ", title=" + title + ", regDt=" + regDt + ", modDt="
				+ modDt + ", toString()=" + super.toString() + "]";
	}

	
}
