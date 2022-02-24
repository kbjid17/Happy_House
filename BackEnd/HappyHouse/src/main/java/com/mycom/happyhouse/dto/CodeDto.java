package com.mycom.happyhouse.dto;

public class CodeDto {
	private String code;
	private String codeName;
	private String groupCode;
	
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public CodeDto(String code, String codeName, String groupCode) {
		super();
		this.code = code;
		this.codeName = codeName;
		this.groupCode = groupCode;
	}
	
	public CodeDto() {}
}
