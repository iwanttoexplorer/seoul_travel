package com.pcwk.ehr.user;

import com.pcwk.ehr.cmn.DTO;

public class UserDTO extends DTO {
	private String userid;     //사용자
	private int    gradesseq;  //등급번호
	private String username;   //이름
	private String userpw;     //비밀번호(영어,숫자,특문)
	private String useremail;  //이메일
	private int    regDt;      //가입일
	
	public UserDTO() {}
	
	public UserDTO(String userid, int gradesseq, String username
	         ,String userpw, String useremail, int regDt) {
		super();
		this.userid = userid;
		this.gradesseq = gradesseq;
		this.username = username;
		this.userpw = userpw;
		this.useremail = useremail;
		this.regDt = regDt;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getGradesseq() {
		return gradesseq;
	}

	public void setGradesseq(int gradesseq) {
		this.gradesseq = gradesseq;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public int getRegDt() {
		return regDt;
	}

	public void setRegDt(int regDt) {
		this.regDt = regDt;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [userid=" + userid + ", gradesseq=" + gradesseq + ", username=" + username + ", userpw="
				+ userpw + ", useremail=" + useremail + ", regDt=" + regDt + "]";
	}
		
}
 