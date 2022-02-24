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

import com.mycom.happyhouse.dto.HouseParamDto;
import com.mycom.happyhouse.dto.HouseResultDto;
import com.mycom.happyhouse.service.HouseService;

@CrossOrigin(
		origins = "http://localhost:5500", // 5500에서만 허락
		allowCredentials = "true", // axios가 세션ID를 계속 다른 것을 보낸다.
		allowedHeaders = "*", // 구글에서 검색하면 allowedHeaders = "*" 이것만 넣으면 된다고 나오는데 그렇지 않다.
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS } )

@RestController
public class HouseController {

	@Autowired
	HouseService service;
	
	private static final int SUCCESS = 1;
	
	// 리스트 가져오기
	@GetMapping(value="/houses")
	public ResponseEntity<HouseResultDto> houseList(HouseParamDto houseParamDto) {
		
		HouseResultDto houseResultDto;
		
		if( houseParamDto.getSearchWord().isEmpty() ) {
			houseResultDto = service.houseList(houseParamDto);
			// System.out.println(houseResultDto.getList());
		} else {
			houseResultDto = service.houseListSearchWord(houseParamDto);
			System.out.println(houseResultDto.getList());
		}
		
		if( houseResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}

	}
	
	// 동으로 검색하기
	@PostMapping(value="/houses/dong")
	public ResponseEntity<HouseResultDto> houseListSearchDong(@RequestBody HouseParamDto houseParamDto) {
		
		System.out.print(houseParamDto);
		
		HouseResultDto houseResultDto = service.houseListSearchDong(houseParamDto);
		System.out.println(houseResultDto.getList());
		
		if( houseResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}

	}
	
	// 거래 상세 정보
	@GetMapping(value = "/houses/{no}")
	public ResponseEntity<HouseResultDto> HouseDealDetail(HouseParamDto houseParamDto) {
		System.out.print(houseParamDto);

		HouseResultDto houseResultDto = service.houseDealDetail(houseParamDto);
		System.out.println(houseResultDto.getDealList());

		if (houseResultDto.getResult() == SUCCESS) { // 넘어온 결과에 따라
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK); // 성공
		} else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}
	
	// 판매중이거나 취소됨 상태의 별점 4점이상의 랜덤하게 4개
	@GetMapping(value = "/houses/rand{limit}")
	public ResponseEntity<HouseResultDto> houseListRand(HouseParamDto houseParamDto) {
		
		HouseResultDto houseResultDto;
		
		houseResultDto = service.houseListRand(houseParamDto);
		System.out.println(houseResultDto.getHouseDealExpandList());
		
		if( houseResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}
	
	// 상태로 검색하기
	@PostMapping(value = "/houses/status")
	public ResponseEntity<HouseResultDto> houseListSearchStatus(@RequestBody HouseParamDto houseParamDto) {
		
		HouseResultDto houseResultDto;
		
		houseResultDto = service.houseListSearchStatus(houseParamDto);
		System.out.println(houseResultDto.getList());
		
		if( houseResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}
	
	// 최근 거래목록 2개
	@GetMapping(value = "/houses/recent{no}")
	public ResponseEntity<HouseResultDto> houseDealRecentList(HouseParamDto houseParamDto) {
		
		HouseResultDto houseResultDto;
		
		houseResultDto = service.houseDealRecentList(houseParamDto);
		System.out.println(houseResultDto.getDealList());
		
		if( houseResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}
}
