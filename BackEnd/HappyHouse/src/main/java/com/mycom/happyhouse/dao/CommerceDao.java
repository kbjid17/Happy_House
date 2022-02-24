package com.mycom.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.happyhouse.dto.CommerceDto;
import com.mycom.happyhouse.dto.CommerceParamDto;

@Mapper
public interface CommerceDao {
	
	// 동 검색
	public List<CommerceDto> commerceSearchDong(CommerceParamDto commerceParamDto);
	
	// 총 검색 개수
	public int commerceSearchDongTotalCount(CommerceParamDto commerceParamDto);
	
	// 위도, 경도 기준 검색
	public List<CommerceDto> commerceSearchLatLng(CommerceParamDto commerceParamDto);
	
	// 총 검색 개수
	public int commerceSearchLatLngTotalCount(CommerceParamDto commerceParamDto);
}
