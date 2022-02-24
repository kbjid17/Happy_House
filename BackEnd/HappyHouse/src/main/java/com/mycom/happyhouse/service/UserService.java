package com.mycom.happyhouse.service;

import com.mycom.happyhouse.dto.UserDto;

public interface UserService {
	// 회원 가입
	public int userRegister(UserDto userDto);
	
	// 로그인
	public UserDto userLogin(UserDto userDto);
	
	// 회원정보 수정
	public int userUpdate(UserDto userDto);
	
	// 회원 탈퇴
	public int userDelete(int userSeq);
}
