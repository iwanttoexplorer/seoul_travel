package com.pcwk.tvl.category;

public class CategoryDTO {
	
	private String category; //카테고리
	private String cat_name; //케테고리 이름
	
	public CategoryDTO() {}

	public CategoryDTO(String category, String cat_name) {
		super();
		this.category = category;
		this.cat_name = cat_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	@Override
	public String toString() {
		return "CategoryDTO [category=" + category + ", cat_name=" + cat_name + ", toString()=" + super.toString()
				+ "]";
	}
	
	

	
}
