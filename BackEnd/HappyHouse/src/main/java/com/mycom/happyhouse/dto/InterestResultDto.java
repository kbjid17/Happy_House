package com.mycom.happyhouse.dto;

import java.util.List;

public class InterestResultDto {
	private int result;
	private int count;
	private InterestDto interestDto;
	private List<Integer> noList;
	private List<HouseDto> list;
	
	public InterestResultDto() {}
	
	public InterestResultDto(int result, int count, InterestDto interestDto, List<Integer> noList,
			List<HouseDto> list) {
		super();
		this.result = result;
		this.count = count;
		this.interestDto = interestDto;
		this.noList = noList;
		this.list = list;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public InterestDto getInterestDto() {
		return interestDto;
	}

	public void setInterestDto(InterestDto interestDto) {
		this.interestDto = interestDto;
	}

	public List<Integer> getNoList() {
		return noList;
	}

	public void setNoList(List<Integer> noList) {
		this.noList = noList;
	}

	public List<HouseDto> getList() {
		return list;
	}

	public void setList(List<HouseDto> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "InterestResultDto [result=" + result + ", count=" + count + ", interestDto=" + interestDto + ", noList="
				+ noList + ", list=" + list + "]";
	}
	
}
