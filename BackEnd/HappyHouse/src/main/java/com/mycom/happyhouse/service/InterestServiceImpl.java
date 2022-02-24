package com.mycom.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.happyhouse.dao.HouseDao;
import com.mycom.happyhouse.dao.InterestDao;
import com.mycom.happyhouse.dto.HouseDto;
import com.mycom.happyhouse.dto.HouseParamDto;
import com.mycom.happyhouse.dto.InterestDto;
import com.mycom.happyhouse.dto.InterestResultDto;

@Service
public class InterestServiceImpl implements InterestService {
	
	@Autowired
	InterestDao dao;
	
	@Autowired
	HouseDao houseDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	// 관심지역 등록
	public InterestResultDto interestRegister(InterestDto interestDto) {

		InterestResultDto interestResultDto = new InterestResultDto();
		
		try {
			dao.interestRegister(interestDto);
			interestResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			interestResultDto.setResult(FAIL);
		}
		
		return interestResultDto;
	}

	// 관심지역 검색
	public InterestResultDto interestSearch(InterestDto interestDto) {
		
		InterestResultDto interestResultDto = new InterestResultDto();
		
		try {
			List<Integer> list = dao.interestSearch(interestDto);
			int count = dao.interestSearchTotalCount(interestDto);
			
			HouseParamDto houseParamDto = new HouseParamDto();
			houseParamDto.setNoList(list);
			List<HouseDto> houseList = houseDao.houseListSearchHouseNo( houseParamDto );
			
			
			interestResultDto.setList(houseList);
			interestResultDto.setCount(count);
			interestResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			interestResultDto.setResult(FAIL);
		}
		
		return interestResultDto;
		
	}
	
	// 관심지역 삭제
	public InterestResultDto interestDelete(InterestDto interestDto) {
		
		InterestResultDto interestResultDto = new InterestResultDto();
		
		try {
			dao.interestDelete(interestDto);
			interestResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			interestResultDto.setResult(FAIL);
		}
		
		return interestResultDto;
		
	}
	
}
