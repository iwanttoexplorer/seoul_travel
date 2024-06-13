package com.pcwk.tvl.gucode;

public class GucodeDTO {
	
	private String guCode; //구 코드
	private String gName;  //구 이름
	
	public GucodeDTO() {}
	
	public GucodeDTO(String guCode, String gName) {
		super();
		this.guCode = guCode;
		this.gName = gName;
	}

	public String getGuCode() {
		return guCode;
	}

	public void setGuCode(String guCode) {
		this.guCode = guCode;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	@Override
	public String toString() {
		return "GucodeDTO [guCode=" + guCode + ", gName=" + gName + ", toString()=" + super.toString() + "]";
	}

	
	
	
}
