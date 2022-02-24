package com.mycom.happyhouse.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.happyhouse.dto.UserDto;

@Mapper
public interface UserDao {
	// 회원 가입
	public int userRegister(UserDto userDto);
	
	// 로그인
	public UserDto userLogin(@Param("userEmail") String userEmail);
	
	// 회원정보 수정
	public int userUpdate(UserDto userDto);
	
	// 회원 탈퇴
	public int userDelete(@Param("userSeq") int userSeq); // mybatis sql문에서 사용하기 위해 파라미터 세팅
}

