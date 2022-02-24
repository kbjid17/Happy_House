package com.mycom.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.happyhouse.dto.CodeDto;
import com.mycom.happyhouse.service.CodeService;


@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true", //axios 사용 시 세션 아이디를 하나로 고정하기 위함(해당 고정을 허락하는 기능)
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,
				RequestMethod.DELETE,RequestMethod.HEAD,RequestMethod.OPTIONS})
@RestController
public class CodeController {
	
	@Autowired
	CodeService service;
	
	@GetMapping(value= "/codes")
	public ResponseEntity<List<CodeDto>> codeList(String groupCode) {
		List<CodeDto> list = service.codeList(groupCode);
		if(list != null) { // 리스트가 있으면
			return new ResponseEntity<List<CodeDto>>(list,HttpStatus.OK);
		} else { // 리스트가 없으면
			return new ResponseEntity<List<CodeDto>>(list,HttpStatus.NOT_FOUND);
		}
	}
}
