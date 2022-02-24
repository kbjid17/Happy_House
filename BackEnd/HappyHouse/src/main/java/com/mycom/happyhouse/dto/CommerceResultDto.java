package com.mycom.happyhouse.dto;

import java.util.List;

public class CommerceResultDto {
	private int result;
	private int count;
	private CommerceDto commerceDto;
	private List<CommerceDto> list;
	
	public CommerceResultDto() {}
	
	public CommerceResultDto(int result, int count, CommerceDto commerceDto, List<CommerceDto> list) {
		this.result = result;
		this.count = count;
		this.commerceDto = commerceDto;
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

	public CommerceDto getCommerceDto() {
		return commerceDto;
	}

	public void setCommerceDto(CommerceDto commerceDto) {
		this.commerceDto = commerceDto;
	}

	public List<CommerceDto> getList() {
		return list;
	}

	public void setList(List<CommerceDto> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "CommerceResultDto [result=" + result + ", count=" + count + ", commerceDto=" + commerceDto + ", list="
				+ list + "]";
	}
	
}
