package com.mycom.happyhouse.dto;

public class HouseDealExpandDto {
	private int no;
	private String city;
	private String gugun;
	private String dong;
	private String AptName;
	private String buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	private String status;
	private String rating;
	
	private String recentDealAmount;
	private String dealDiffPercent;
	private String diffDays;
	
	public HouseDealExpandDto() {}
	
	public HouseDealExpandDto(int no, String city, String gugun, String dong, String aptName, String buildYear,
			String jibun, String lat, String lng, String img, String status, String rating, String recentDealAmount,
			String dealDiffPercent, String diffDays) {
		super();
		this.no = no;
		this.city = city;
		this.gugun = gugun;
		this.dong = dong;
		AptName = aptName;
		this.buildYear = buildYear;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
		this.img = img;
		this.status = status;
		this.rating = rating;
		this.recentDealAmount = recentDealAmount;
		this.dealDiffPercent = dealDiffPercent;
		this.diffDays = diffDays;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getAptName() {
		return AptName;
	}

	public void setAptName(String aptName) {
		AptName = aptName;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRecentDealAmount() {
		return recentDealAmount;
	}

	public void setRecentDealAmount(String recentDealAmount) {
		this.recentDealAmount = recentDealAmount;
	}

	public String getDealDiffPercent() {
		return dealDiffPercent;
	}

	public void setDealDiffPercent(String dealDiffPercent) {
		this.dealDiffPercent = dealDiffPercent;
	}

	public String getDiffDays() {
		return diffDays;
	}

	public void setDiffDays(String diffDays) {
		this.diffDays = diffDays;
	}

	@Override
	public String toString() {
		return "HouseDealExpandDto [no=" + no + ", city=" + city + ", gugun=" + gugun + ", dong=" + dong + ", AptName="
				+ AptName + ", buildYear=" + buildYear + ", jibun=" + jibun + ", lat=" + lat + ", lng=" + lng + ", img="
				+ img + ", status=" + status + ", rating=" + rating + ", recentDealAmount=" + recentDealAmount
				+ ", dealDiffPercent=" + dealDiffPercent + ", diffDays=" + diffDays + "]";
	}
	
}
