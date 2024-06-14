package com.pcwk.tvl.member;

import com.pcwk.ehr.cmn.DTO;

public class MemberDTO extends DTO{
	private String userId     ; //유저 아이디
    private int gradesSeq     ; //회원 1 관리자0
    private String userName   ; //이름
    private String userPw     ; //비밀번호
    private String userEmail  ; //이메일
    private String regDt      ; //생성일
    
    public MemberDTO() {
    	
	}

	public MemberDTO(String userId, int gradesSeq, String userName, String userPw, String userEmail, String regDt) {
		super();
		this.userId = userId;
		this.gradesSeq = gradesSeq;
		this.userName = userName;
		this.userPw = userPw;
		this.userEmail = userEmail;
		this.regDt = regDt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getGradesSeq() {
		return gradesSeq;
	}

	public void setGradesSeq(int gradesSeq) {
		this.gradesSeq = gradesSeq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", gradesSeq=" + gradesSeq + ", userName=" + userName + ", userPw="
				+ userPw + ", userEmail=" + userEmail + ", regDt=" + regDt + ", toString()=" + super.toString() + "]";
	}
	
}
	
	

    