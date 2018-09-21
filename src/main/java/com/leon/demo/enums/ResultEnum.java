package com.leon.demo.enums;

public enum ResultEnum {
	SUCCESS(0, "success"), UNKOWN_ERROR(-1, "未知错误"), USER_NOT_EXIST(1, "用户不存在");

	private Integer code;
	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

}
