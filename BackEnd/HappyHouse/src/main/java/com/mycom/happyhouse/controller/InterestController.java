package com.mycom.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.happyhouse.dto.InterestDto;
import com.mycom.happyhouse.dto.InterestResultDto;
import com.mycom.happyhouse.service.InterestService;

@CrossOrigin(
		origins = "http://localhost:5500", // 5500에서만 허락
		allowCredentials = "true", // axios가 세션ID를 계속 다른 것을 보낸다.
		allowedHeaders = "*", // 구글에서 검색하면 allowedHeaders = "*" 이것만 넣으면 된다고 나오는데 그렇지 않다.
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS } )

@RestController
public class InterestController {

	@Autowired
	InterestService service;

	private static final int SUCCESS = 1;
	
	@PostMapping(value="/interests")
	public ResponseEntity<InterestResultDto> interestRegister(@RequestBody InterestDto interestDto) {
		
		InterestResultDto InterestResultDto;
		
		InterestResultDto = service.interestRegister(interestDto);
		System.out.println(InterestResultDto.getList());
		
		if( InterestResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			return new ResponseEntity<InterestResultDto>(InterestResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<InterestResultDto>(InterestResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}
	
	@GetMapping(value="/interests")
	public ResponseEntity<InterestResultDto> interestSearch(InterestDto interestDto) {
		
		InterestResultDto InterestResultDto;
		
		InterestResultDto = service.interestSearch(interestDto);
		System.out.println(InterestResultDto.getList());
		
		if( InterestResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			return new ResponseEntity<InterestResultDto>(InterestResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<InterestResultDto>(InterestResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}
	
	@DeleteMapping(value="/interests")
	public ResponseEntity<InterestResultDto> interestDelete(@RequestBody InterestDto interestDto) {
		
		InterestResultDto InterestResultDto;
		
		System.out.println(interestDto);
		
		InterestResultDto = service.interestDelete(interestDto);
		System.out.println(InterestResultDto.getList());
		
		if( InterestResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			return new ResponseEntity<InterestResultDto>(InterestResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<InterestResultDto>(InterestResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}
}
