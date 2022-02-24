package com.mycom.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.happyhouse.dao.AddressDao;
import com.mycom.happyhouse.dto.AddressDto;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressDao dao;
	
	@Override
	public List<AddressDto> sidoList() {
		return dao.sidoList();
	}

	@Override
	public List<AddressDto> gugunList(String code) {
		return dao.gugunList(code);
	}

	@Override
	public List<AddressDto> dongList(String code) {
		return dao.dongList(code);
	}

	@Override
	public AddressDto dongLatLng(String dongName) {
		return dao.dongLatLng(dongName);
	}

}
