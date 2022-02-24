package com.mycom.happyhouse.service;

import com.mycom.happyhouse.dto.CommerceParamDto;
import com.mycom.happyhouse.dto.CommerceResultDto;

public interface CommerceService {
	
	// 동 검색
	public CommerceResultDto commerceSearchDong(CommerceParamDto commerceParamDto);
	
	// 위도 경도 검색
	public CommerceResultDto commerceSearchLatLng(CommerceParamDto commerceParamDto);
	
}
