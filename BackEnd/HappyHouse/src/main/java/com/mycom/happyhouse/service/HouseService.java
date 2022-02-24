package com.mycom.happyhouse.service;

import com.mycom.happyhouse.dto.HouseParamDto;
import com.mycom.happyhouse.dto.HouseResultDto;

public interface HouseService {
	// 리스트 가져오기
	public HouseResultDto houseList(HouseParamDto houseParamDto);
	
	// 동 검색 리스트
	public HouseResultDto houseListSearchDong(HouseParamDto houseParamDto);

	// 검색 리스트
	public HouseResultDto houseListSearchWord(HouseParamDto houseParamDto);
	
	// 거래 정보
	public HouseResultDto houseDealDetail(HouseParamDto houseParamDto);
	
	// 랜덤하게 가져오기 
	public HouseResultDto houseListRand(HouseParamDto houseParamDto);
	
	// 상태로 검색
	public HouseResultDto houseListSearchStatus(HouseParamDto houseParamDto);
	
	// 최근 거래 내역 검색(가장 최근 두개)
	public HouseResultDto houseDealRecentList(HouseParamDto houseParamDto);
}
