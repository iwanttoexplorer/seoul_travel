package com.pcwk.tvl.category;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.tvl.content.ContentDTO;
import com.pcwk.tvl.gucode.GucodeDTO;

public class CategoryService {

	private CategoryDao dao;

	public CategoryService() {
		dao = new CategoryDao();
		
	}
	
	public CategoryDTO doSelectOne(CategoryDTO search) {
		return dao.doSelectOne(search);
	}
	
	/**
	 * 목록 조회
	 * @param search
	 * @return
	 */
	public List<CategoryDTO> doRetrieve(DTO search) {
		return dao.doRetrieve(search);
	}

}
