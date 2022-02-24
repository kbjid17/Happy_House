package com.mycom.happyhouse.dto;

public class InterestDto {
	private String no;
	private String userSeq;
	private String interestTime;
	
	public InterestDto() {}
	
	public InterestDto(String no, String userSeq, String interestTime) {
		this.no = no;
		this.userSeq = userSeq;
		this.interestTime = interestTime;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String getInterestTime() {
		return interestTime;
	}

	public void setInterestTime(String interestTime) {
		this.interestTime = interestTime;
	}

	@Override
	public String toString() {
		return "InterestDto [no=" + no + ", userSeq=" + userSeq + ", interestTime=" + interestTime + "]";
	}
	
}
