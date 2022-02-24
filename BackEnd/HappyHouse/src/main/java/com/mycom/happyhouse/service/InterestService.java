package com.mycom.happyhouse.service;

import com.mycom.happyhouse.dto.InterestDto;
import com.mycom.happyhouse.dto.InterestResultDto;

public interface InterestService {
	
	// 관심지역 등록
	public InterestResultDto interestRegister(InterestDto interestDto);

	// 관심지역 검색
	public InterestResultDto interestSearch(InterestDto interestDto);
	
	// 관심지역 삭제
	public InterestResultDto interestDelete(InterestDto interestDto);
	
}
