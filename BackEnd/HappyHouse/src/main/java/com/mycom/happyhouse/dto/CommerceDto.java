package com.mycom.happyhouse.dto;

public class CommerceDto {
	
	private String num; // 상가업소번호
	private String titleName; // 상호명
	private String locationName; // 지점명
	private String branch; // 상권업종대분류명
	private String branchDetail; // 표준산업분류명
	private String dong; // 법정동명
	private String address; // 지번주소
	private String lng; // 경도
	private String lat; // 위도
	private double rating; // 평점
	
	public CommerceDto() { }

	public CommerceDto(String num, String titleName, String locationName, String branch, String branchDetail, String dong,
			String address, String lng, String lat, double rating) {
		super();
		this.num = num;
		this.titleName = titleName;
		this.locationName = locationName;
		this.branch = branch;
		this.branchDetail = branchDetail;
		this.dong = dong;
		this.address = address;
		this.lng = lng;
		this.lat = lat;
		this.rating = rating;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBranchDetail() {
		return branchDetail;
	}

	public void setBranchDetail(String branchDetail) {
		this.branchDetail = branchDetail;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "NearDto [num=" + num + ", titleName=" + titleName + ", locationName=" + locationName + ", branch="
				+ branch + ", branchDetail=" + branchDetail + ", dong=" + dong + ", address=" + address + ", lng=" + lng
				+ ", lat=" + lat + ", rating=" + rating + "]";
	}
	
}
