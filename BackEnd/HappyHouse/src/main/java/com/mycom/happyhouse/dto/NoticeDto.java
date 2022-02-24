package com.mycom.happyhouse.dto;

import java.time.LocalDateTime;
import java.util.List;

public class NoticeDto {
	private int noticeSeq; //글번호
	private int userSeq; // 작성자 번호
	private String userName; // 작성자 이름
	private String noticeTitle; //제목
	private String noticeContent; //내용
	private LocalDateTime noticeRegDt; // 작성일자
	private int noticeReadcount; // 열람 횟수
	private String userProfileImageUrl; //(로그인 한 상태) 공지 확인할 때 보일 유저의 이미지
	private List<NoticeFileDto> fileList;
	private String userClsf;
	

	public NoticeDto() { }

	public NoticeDto(int noticeSeq, int userSeq, String userName, String noticeTitle, String noticeContent,
			LocalDateTime noticeRegDt, int noticeReadcount, String userProfileImageUrl, List<NoticeFileDto> fileList) {
		super();
		this.noticeSeq = noticeSeq;
		this.userSeq = userSeq;
		this.userName = userName;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeRegDt = noticeRegDt;
		this.noticeReadcount = noticeReadcount;
		this.userProfileImageUrl = userProfileImageUrl;
		this.fileList = fileList;
	}

	public int getNoticeSeq() {
		return noticeSeq;
	}

	public void setNoticeSeq(int noticeSeq) {
		this.noticeSeq = noticeSeq;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public LocalDateTime getNoticeRegDt() {
		return noticeRegDt;
	}

	public void setNoticeRegDt(LocalDateTime noticeRegDt) {
		this.noticeRegDt = noticeRegDt;
	}

	public int getNoticeReadcount() {
		return noticeReadcount;
	}

	public void setNoticeReadcount(int noticeReadcount) {
		this.noticeReadcount = noticeReadcount;
	}

	public String getUserProfileImageUrl() {
		return userProfileImageUrl;
	}

	public void setUserProfileImageUrl(String userProfileImageUrl) {
		this.userProfileImageUrl = userProfileImageUrl;
	}

	public List<NoticeFileDto> getFileList() {
		return fileList;
	}

	public void setFileList(List<NoticeFileDto> fileList) {
		this.fileList = fileList;
	}
	
	public String getUserClsf() {
		return userClsf;
	}

	public void setUserClsf(String userClsf) {
		this.userClsf = userClsf;
	}


	@Override
	public String toString() {
		return "NoticeDto [noticeSeq=" + noticeSeq + ", userSeq=" + userSeq + ", userName=" + userName
				+ ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent + ", noticeRegDt=" + noticeRegDt
				+ ", noticeReadcount=" + noticeReadcount + ", userProfileImageUrl=" + userProfileImageUrl
				+ ", fileList=" + fileList + "]";
	}
	
}
