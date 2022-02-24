package com.mycom.happyhouse.dto;

import java.util.List;

//response를 할 때, 응답을 내려 줄때
//모든 응답은 이 Dto를 내려주면 된다.
public class UserResultDto {
	private int result; // 결과, 성공 실패 응답
	private UserDto dto; // 유저 정보
	private List<UserDto> list; // 유저 목록
	private int count; // 총 유저수

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public UserDto getDto() {
		return dto;
	}

	public void setDto(UserDto dto) {
		this.dto = dto;
	}

	public List<UserDto> getList() {
		return list;
	}

	public void setList(List<UserDto> list) {
		this.list = list;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "UserResultDto [result=" + result + ", dto=" + dto + ", list=" + list + ", count=" + count + "]";
	}
}
