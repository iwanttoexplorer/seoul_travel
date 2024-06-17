package com.pcwk.tvl.category;

import com.pcwk.ehr.cmn.DTO;

public class CategoryDTO extends DTO{
	
	private String category; //카테고리
	private String catName;  //케테고리 이름
	
	public CategoryDTO() {}

	public CategoryDTO(String category, String catName) {
		super();
		this.category = category;
		this.catName  = catName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	@Override
	public String toString() {
		return "CategoryDTO [category=" + category + ", catName=" + catName + ", toString()=" + super.toString() + "]";
	}

	
}
