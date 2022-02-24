package com.mycom.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.happyhouse.dto.HouseDealDto;
import com.mycom.happyhouse.dto.HouseDto;
import com.mycom.happyhouse.dto.HouseParamDto;

@Mapper
public interface HouseDao {
	// 리스트 가져오기
	public List<HouseDto> houseList(HouseParamDto houseParamDto);
	// 리스트 개수
	public int houseListTotalCount();
	
	// 집 번호로 검색
	public List<HouseDto> houseListSearchHouseNo(HouseParamDto houseParamDto);
	
	// 동 검색 리스트
	public List<HouseDto> houseListSearchDong(HouseParamDto houseParamDto);
	// 동 검색 리스트 개수
	public int houseListSearchDongTotalCount(HouseParamDto houseParamDto);
	
	// 검색 리스트
	public List<HouseDto> houseListSearchWord(HouseParamDto houseParamDto);
	// 검색 리스트 개수
	public int houseListSearchWordTotalCount(HouseParamDto houseParamDto);
	
	// 거래 정보
	public List<HouseDealDto> houseDealDetail(HouseParamDto houseParamDto);
	
	// 동 검색 리스트
	public List<HouseDto> houseListRand(HouseParamDto houseParamDto);
	
	// 상태로 검색
	public List<HouseDto> houseListSearchStatus(HouseParamDto houseParamDto);
	// 상태로 검색 개수
	public int houseListSearchStatusTotalCount(HouseParamDto houseParamDto);
	
	// 최근 거래 내역 검색(가장 최근 두개)
	public List<HouseDealDto> houseDealRecentList(HouseParamDto houseParamDto);
}
