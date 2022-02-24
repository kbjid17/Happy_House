package com.mycom.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.happyhouse.dto.AddressDto;
import com.mycom.happyhouse.service.AddressService;

@CrossOrigin(
		origins = "http://localhost:5500", // 5500에서만 허락
		allowCredentials = "true", // axios가 세션ID를 계속 다른 것을 보낸다.
		allowedHeaders = "*", // 구글에서 검색하면 allowedHeaders = "*" 이것만 넣으면 된다고 나오는데 그렇지 않다.
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS } )

@RestController
public class AddressController {
	
	@Autowired
	AddressService service;

	@GetMapping(value= "/sido")
	public ResponseEntity<List<AddressDto>> sidoList() {
		List<AddressDto> list = service.sidoList();
		
		System.out.println(list);
		
		if(list != null) { // 리스트가 있으면
			return new ResponseEntity<List<AddressDto>>(list,HttpStatus.OK);
		} else { // 리스트가 없으면
			return new ResponseEntity<List<AddressDto>>(list,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value= "/gugun/{code}")
	public ResponseEntity<List<AddressDto>> gugunList(@PathVariable String code) {
		List<AddressDto> list = service.gugunList(code);
		
		if(list != null) { // 리스트가 있으면
			return new ResponseEntity<List<AddressDto>>(list,HttpStatus.OK);
		} else { // 리스트가 없으면
			return new ResponseEntity<List<AddressDto>>(list,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value= "/dong/{code}")
	public ResponseEntity<List<AddressDto>> dongList(@PathVariable String code) {
		List<AddressDto> list = service.dongList(code);
		
		if(list != null) { // 리스트가 있으면
			return new ResponseEntity<List<AddressDto>>(list,HttpStatus.OK);
		} else { // 리스트가 없으면
			return new ResponseEntity<List<AddressDto>>(list,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value= "/latlng/{dongName}")
	public ResponseEntity<AddressDto> dongLatLng(@PathVariable String dongName) {
		AddressDto addressDto = service.dongLatLng(dongName);
		
		if(addressDto != null) { // 리스트가 있으면
			return new ResponseEntity<AddressDto>(addressDto,HttpStatus.OK);
		} else { // 리스트가 없으면
			return new ResponseEntity<AddressDto>(addressDto,HttpStatus.NOT_FOUND);
		}
	}
}
