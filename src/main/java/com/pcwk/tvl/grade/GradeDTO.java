package com.pcwk.tvl.grade;

import com.pcwk.ehr.cmn.DTO;

public class GradeDTO extends DTO{
	private String grades_seq;
	private String grade;
	
	public GradeDTO() {}
	public String getGrades_seq() {
		return grades_seq;
	}
	public void setGrades_seq(String grades_seq) {
		this.grades_seq = grades_seq;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "GradeDTO [grades_seq=" + grades_seq + ", grade=" + grade + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
