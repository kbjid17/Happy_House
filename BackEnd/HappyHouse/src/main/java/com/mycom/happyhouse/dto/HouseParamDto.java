package com.mycom.happyhouse.dto;

import java.util.List;

public class HouseParamDto {
	
	private int limit;
	private int offset;
	private String gugunCode; // 구/군 코드
	private String searchWord;
	private String status; // 상태
	
	private String order;
	private String orderColumn;
	
	private int no;
	private int userSeq;
	
	private List<Integer> noList;

	public HouseParamDto() {}
	
	public HouseParamDto(int limit, int offset, String gugunCode, String searchWord, String status, String order,
			String orderColumn, int no, int userSeq, List<Integer> noList) {
		super();
		this.limit = limit;
		this.offset = offset;
		this.gugunCode = gugunCode;
		this.searchWord = searchWord;
		this.status = status;
		this.order = order;
		this.orderColumn = orderColumn;
		this.no = no;
		this.userSeq = userSeq;
		this.noList = noList;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public List<Integer> getNoList() {
		return noList;
	}

	public void setNoList(List<Integer> noList) {
		this.noList = noList;
	}

	@Override
	public String toString() {
		return "HouseParamDto [limit=" + limit + ", offset=" + offset + ", gugunCode=" + gugunCode + ", searchWord="
				+ searchWord + ", status=" + status + ", order=" + order + ", orderColumn=" + orderColumn + ", no=" + no
				+ ", userSeq=" + userSeq + ", noList=" + noList + "]";
	}
	
}
