package com.mycom.happyhouse.dto;

public class HouseDto {
	private int no;
	private String city;
	private String gugun;
	private String dong;
	private String AptName;
	private String code;
	private String buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	private String status;
	private String rating;
	private String dealAmount;
	private String area;
	
	public HouseDto() {}

	public HouseDto(int no, String city, String gugun, String dong, String aptName, String code, String buildYear,
			String jibun, String lat, String lng, String img, String status, String rating, String dealAmount,
			String area) {
		super();
		this.no = no;
		this.city = city;
		this.gugun = gugun;
		this.dong = dong;
		AptName = aptName;
		this.code = code;
		this.buildYear = buildYear;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
		this.img = img;
		this.status = status;
		this.rating = rating;
		this.dealAmount = dealAmount;
		this.area = area;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "HouseDto [no=" + no + ", city=" + city + ", gugun=" + gugun + ", dong=" + dong + ", AptName=" + AptName
				+ ", code=" + code + ", buildYear=" + buildYear + ", jibun=" + jibun + ", lat=" + lat + ", lng=" + lng
				+ ", img=" + img + ", status=" + status + ", rating=" + rating + ", dealAmount=" + dealAmount
				+ ", area=" + area + "]";
	}
	
}
