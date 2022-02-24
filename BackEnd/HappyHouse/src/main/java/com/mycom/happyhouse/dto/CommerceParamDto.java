package com.mycom.happyhouse.dto;

public class CommerceParamDto {
	
	private int num; // 상가업소번호 검색시
	private String searchWord;
	private String order;
	private String orderColumn;
	
	private double startLng, endLng; // 시작 경도, 끝 경도
	private double startLat, endLat; // 시작 위도, 끝 위도
	
	public CommerceParamDto() {}

	public CommerceParamDto(int num, String searchWord, String order, String orderColumn, double startLng,
			double endLng, double startLat, double endLat) {
		super();
		this.num = num;
		this.searchWord = searchWord;
		this.order = order;
		this.orderColumn = orderColumn;
		this.startLng = startLng;
		this.endLng = endLng;
		this.startLat = startLat;
		this.endLat = endLat;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public double getStartLng() {
		return startLng;
	}

	public void setStartLng(double startLng) {
		this.startLng = startLng;
	}

	public double getEndLng() {
		return endLng;
	}

	public void setEndLng(double endLng) {
		this.endLng = endLng;
	}

	public double getStartLat() {
		return startLat;
	}

	public void setStartLat(double startLat) {
		this.startLat = startLat;
	}

	public double getEndLat() {
		return endLat;
	}

	public void setEndLat(double endLat) {
		this.endLat = endLat;
	}

	@Override
	public String toString() {
		return "CommerceParamDto [num=" + num + ", searchWord=" + searchWord + ", order=" + order + ", orderColumn="
				+ orderColumn + ", startLng=" + startLng + ", endLng=" + endLng + ", startLat=" + startLat + ", endLat="
				+ endLat + "]";
	}
	
}
