package com.mycom.happyhouse.service;

import java.util.List;

import com.mycom.happyhouse.dto.AddressDto;

public interface AddressService {
	
	// 시도 리스트
	public List<AddressDto> sidoList();
	
	// 구군 리스트
	public List<AddressDto> gugunList(String code);
	
	// 동 리스트
	public List<AddressDto> dongList(String code);
	
	// 동 위도, 경도 얻어오기
	public AddressDto dongLatLng(String dongName);
}
