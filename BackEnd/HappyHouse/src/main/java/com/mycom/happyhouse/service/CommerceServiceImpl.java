package com.mycom.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.happyhouse.dao.CommerceDao;
import com.mycom.happyhouse.dto.CommerceDto;
import com.mycom.happyhouse.dto.CommerceParamDto;
import com.mycom.happyhouse.dto.CommerceResultDto;

@Service
public class CommerceServiceImpl implements CommerceService {
	
	@Autowired
	CommerceDao dao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	public CommerceResultDto commerceSearchDong(CommerceParamDto commerceParamDto) {
		
		CommerceResultDto commerceResultDto = new CommerceResultDto();
		
		try {
			List<CommerceDto> list = dao.commerceSearchDong(commerceParamDto);
			int count = dao.commerceSearchDongTotalCount(commerceParamDto);
			commerceResultDto.setList(list);
			commerceResultDto.setCount(count);
			commerceResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			commerceResultDto.setResult(FAIL);
		}
		
		return commerceResultDto;
		
	}

	@Override
	public CommerceResultDto commerceSearchLatLng(CommerceParamDto commerceParamDto) {
		
		CommerceResultDto commerceResultDto = new CommerceResultDto();
		
		try {
			List<CommerceDto> list = dao.commerceSearchLatLng(commerceParamDto);
			int count = dao.commerceSearchLatLngTotalCount(commerceParamDto);
			commerceResultDto.setList(list);
			commerceResultDto.setCount(count);
			commerceResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			commerceResultDto.setResult(FAIL);
		}
		
		return commerceResultDto;
		
	}

}
