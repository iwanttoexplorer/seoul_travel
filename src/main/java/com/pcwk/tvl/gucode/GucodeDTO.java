package com.pcwk.tvl.gucode;

import com.pcwk.ehr.cmn.DTO;

public class GucodeDTO extends DTO{
	
	private String guCode; //구 코드
	private String gname;  //구 이름
	
	public GucodeDTO() {}

	public GucodeDTO(String guCode, String gname) {
		super();
		this.guCode = guCode;
		this.gname = gname;
	}

	public String getGuCode() {
		return guCode;
	}

	public void setGuCode(String guCode) {
		this.guCode = guCode;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	@Override
	public String toString() {
		return "GucodeDTO [guCode=" + guCode + ", gname=" + gname + ", toString()=" + super.toString() + "]";
	}

	
}
