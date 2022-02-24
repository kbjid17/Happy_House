package com.mycom.happyhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.happyhouse.dao.UserDao;
import com.mycom.happyhouse.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public int userRegister(UserDto userDto) {
		UserDto resultDto = userDao.userLogin(userDto.getUserEmail()); // userEmail이 DB에 존재하는지 검색
		
		// 유저 Email이 존재하지 않으면
		if( resultDto == null ) {
			return userDao.userRegister(userDto); // 회원 등록
		}
		
		return 0; // return null => controller 단에서 null 로그인 실패 처리
	}

	@Override
	public UserDto userLogin(UserDto userDto) {
		UserDto resultDto = userDao.userLogin(userDto.getUserEmail()); // userEmail이 DB에 존재하는지 검색
		
		// 유저 Email이 존재하고 && 사용자가 입력한 패스워드와 DB에서 가져온 사용자 패스워드와 일치하면
		if( resultDto != null && userDto.getUserPassword().equals(resultDto.getUserPassword())) {
			return resultDto; // 로그인 성공 return userDto
		}
		
		return null; // return null => controller 단에서 null 로그인 실패 처리
	}

	@Override
	public int userUpdate(UserDto userDto) {
		return userDao.userUpdate(userDto);
	}

	@Override
	public int userDelete(int userSeq) {
		return userDao.userDelete(userSeq);
	}

}
