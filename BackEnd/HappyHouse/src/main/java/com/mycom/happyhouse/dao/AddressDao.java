package com.mycom.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.happyhouse.dto.AddressDto;

@Mapper
public interface AddressDao {
	
	// 시도 리스트
	public List<AddressDto> sidoList();
	
	// 구군 리스트
	public List<AddressDto> gugunList(String code);
	
	// 동 리스트
	public List<AddressDto> dongList(String code);
	
	// 동 위도, 경도 얻어오기
	public AddressDto dongLatLng(String dongName);
}
