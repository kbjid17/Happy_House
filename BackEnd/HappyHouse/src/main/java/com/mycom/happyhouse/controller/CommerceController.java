package com.mycom.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.happyhouse.dto.CommerceParamDto;
import com.mycom.happyhouse.dto.CommerceResultDto;
import com.mycom.happyhouse.service.CommerceService;

@CrossOrigin(
		origins = "http://localhost:5500", // 5500에서만 허락
		allowCredentials = "true", // axios가 세션ID를 계속 다른 것을 보낸다.
		allowedHeaders = "*", // 구글에서 검색하면 allowedHeaders = "*" 이것만 넣으면 된다고 나오는데 그렇지 않다.
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS } )

@RestController
public class CommerceController {

	@Autowired
	CommerceService service;

	private static final int SUCCESS = 1;
	
	@GetMapping(value="/commerce/dong")
	public ResponseEntity<CommerceResultDto> commerceSearchDong(CommerceParamDto commerceParamDto) {
		
		CommerceResultDto commerceResultDto;
		
		commerceResultDto = service.commerceSearchDong(commerceParamDto);
		System.out.println(commerceResultDto.getList());
		
		if( commerceResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			return new ResponseEntity<CommerceResultDto>(commerceResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<CommerceResultDto>(commerceResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}
	
	@PostMapping(value="/commerce/latlng")
	public ResponseEntity<CommerceResultDto> commerceSearchLatLng(@RequestBody CommerceParamDto commerceParamDto) {
		
		CommerceResultDto commerceResultDto;
		
		System.out.println(commerceParamDto);
		
		commerceResultDto = service.commerceSearchLatLng(commerceParamDto);
		System.out.println(commerceResultDto.getList());
		
		if( commerceResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			return new ResponseEntity<CommerceResultDto>(commerceResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<CommerceResultDto>(commerceResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}
}
