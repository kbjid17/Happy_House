package com.mycom.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.happyhouse.dto.UserDto;
import com.mycom.happyhouse.dto.UserResultDto;
import com.mycom.happyhouse.service.UserService;

@CrossOrigin(
		origins = "http://localhost:5500", // 5500에서만 허락
		allowCredentials = "true", // axios가 세션ID를 계속 다른 것을 보낸다.
		allowedHeaders = "*", // 구글에서 검색하면 allowedHeaders = "*" 이것만 넣으면 된다고 나오는데 그렇지 않다.
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS } )

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	private static final int SUCCESS = 1;
	
	// 회원 가입
	@PostMapping(value="/users")
	public ResponseEntity<UserResultDto> userRegister(@RequestBody UserDto userDto) {
		System.out.println(userDto);
		
		UserResultDto userResultDto = new UserResultDto();

		userResultDto.setResult( userService.userRegister(userDto) );
		
		// 응답도 response http의 status code를 이용하자. -> 사용하는 곳도 있고 사용 하지 않는 곳도 있다.
		// 응답을 담기 위해 ResponseEntity<>를 사용
		// 200 OK, 500 internal server error
		
		if( userResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}
	
	// 로그인
	@PostMapping(value="/users/login")
	public ResponseEntity<UserResultDto> userLogin(@RequestBody UserDto userDto,  HttpSession session) {
		UserResultDto userResultDto = new UserResultDto();

		userResultDto.setDto( userService.userLogin(userDto) );
		
		System.out.println(userResultDto.getDto());
		
		if( userResultDto.getDto() != null ) { // 넘어온 결과에 따라 
			session.setAttribute("userDto", userResultDto.getDto() );
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}
	
	// 회원정보 수정
	@PutMapping(value="/users/{userSeq}")
	public ResponseEntity<UserResultDto> userUpdate(@PathVariable int userSeq, @RequestBody UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		
		System.out.println("update" + userSeq);

		userDto.setUserSeq(userSeq);
		userResultDto.setResult( userService.userUpdate(userDto) );
		
		if( userResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}
	
	// 로그아웃
	@DeleteMapping(value="/users")
	public ResponseEntity<UserResultDto> userLogout(HttpSession session) {
		UserResultDto userResultDto = new UserResultDto();
		session.invalidate();
		
		userResultDto.setResult( SUCCESS );
		
		return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK); // 성공
	}
	
	// 회원 탈퇴
	@DeleteMapping(value="/users/{userSeq}") 
	public ResponseEntity<UserResultDto> userDelete(@PathVariable int userSeq, HttpSession session) {
		UserResultDto userResultDto = new UserResultDto();
		
		System.out.println("회원탈퇴" + userSeq);

		userResultDto.setResult( userService.userDelete(userSeq) );
		
		if( userResultDto.getResult() == SUCCESS ) { // 넘어온 결과에 따라 
			session.invalidate();
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK); // 성공
		}else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR); // 실패
		}
	}

}
