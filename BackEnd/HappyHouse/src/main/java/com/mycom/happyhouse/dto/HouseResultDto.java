package com.mycom.happyhouse.dto;

import java.util.List;

public class HouseResultDto {
	private int result;
	private int count;
	private HouseDto housedto;
	private HouseDealDto houseDealDto;
	private HouseDealExpandDto houseDealExpand;
	private List<HouseDto> list;
	private List<HouseDealDto> dealList;
	private List<HouseDealExpandDto> houseDealExpandList;
	
	public HouseResultDto() {}
	
	public HouseResultDto(int result, int count, HouseDto housedto, HouseDealDto houseDealDto,
			HouseDealExpandDto houseDealExpand, List<HouseDto> list, List<HouseDealDto> dealList,
			List<HouseDealExpandDto> houseDealExpandList) {
		super();
		this.result = result;
		this.count = count;
		this.housedto = housedto;
		this.houseDealDto = houseDealDto;
		this.houseDealExpand = houseDealExpand;
		this.list = list;
		this.dealList = dealList;
		this.houseDealExpandList = houseDealExpandList;
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

	public HouseDto getHousedto() {
		return housedto;
	}

	public void setHousedto(HouseDto housedto) {
		this.housedto = housedto;
	}

	public HouseDealDto getHouseDealDto() {
		return houseDealDto;
	}

	public void setHouseDealDto(HouseDealDto houseDealDto) {
		this.houseDealDto = houseDealDto;
	}

	public HouseDealExpandDto getHouseDealExpand() {
		return houseDealExpand;
	}

	public void setHouseDealExpand(HouseDealExpandDto houseDealExpand) {
		this.houseDealExpand = houseDealExpand;
	}

	public List<HouseDto> getList() {
		return list;
	}

	public void setList(List<HouseDto> list) {
		this.list = list;
	}

	public List<HouseDealDto> getDealList() {
		return dealList;
	}

	public void setDealList(List<HouseDealDto> dealList) {
		this.dealList = dealList;
	}

	public List<HouseDealExpandDto> getHouseDealExpandList() {
		return houseDealExpandList;
	}

	public void setHouseDealExpandList(List<HouseDealExpandDto> houseDealExpandList) {
		this.houseDealExpandList = houseDealExpandList;
	}

	@Override
	public String toString() {
		return "HouseResultDto [result=" + result + ", count=" + count + ", housedto=" + housedto + ", houseDealDto="
				+ houseDealDto + ", houseDealExpand=" + houseDealExpand + ", list=" + list + ", dealList=" + dealList
				+ ", houseDealExpandList=" + houseDealExpandList + "]";
	}
	
}
