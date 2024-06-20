package com.pcwk.ehr.cmn;

public class ContentsSearchDTO extends DTO {

	private int pageNo  ; //페이지 번호:1,2,3..
	private int pageSize; //페이지 사이즈:10,20,50,100,200..
	
	
	private String searchDiv;  //지역별 검색구분
	
	private String gNameWord; // 구 조건
	
	private String categoryWord; //카테고리 조건
	
	private String searchWord; //제목기준 검색어
	
	
	public ContentsSearchDTO() {}


	public ContentsSearchDTO(int pageNo, int pageSize, String searchDiv, String gNameWord, String categoryWord,
			String searchWord) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.searchDiv = searchDiv;
		this.gNameWord = gNameWord;
		this.categoryWord = categoryWord;
		this.searchWord = searchWord;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public String getSearchDiv() {
		return searchDiv;
	}


	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}


	public String getgNameWord() {
		return gNameWord;
	}


	public void setgNameWord(String gNameWord) {
		this.gNameWord = gNameWord;
	}


	public String getCategoryWord() {
		return categoryWord;
	}


	public void setCategoryWord(String categoryWord) {
		this.categoryWord = categoryWord;
	}


	public String getSearchWord() {
		return searchWord;
	}


	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}


	@Override
	public String toString() {
		return "ContentsSearchDTO [pageNo=" + pageNo + ", pageSize=" + pageSize + ", searchDiv=" + searchDiv
				+ ", gNameWord=" + gNameWord + ", categoryWord=" + categoryWord + ", searchWord=" + searchWord
				+ ", toString()=" + super.toString() + "]";
	}
	
	

}