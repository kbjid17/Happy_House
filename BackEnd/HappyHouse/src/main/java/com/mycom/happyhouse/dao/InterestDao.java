package com.mycom.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.happyhouse.dto.InterestDto;

@Mapper
public interface InterestDao {
	
	// 관심지역 등록
	public int interestRegister(InterestDto interestDto);

	// 관심지역 검색
	public List<Integer> interestSearch(InterestDto interestDto);
	
	// 관심지역 검색 총 개수
	public int interestSearchTotalCount(InterestDto interestDto);
	
	// 관심지역 삭제
	public int interestDelete(InterestDto interestDto);
	
}
