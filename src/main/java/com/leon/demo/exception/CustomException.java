package com.leon.demo.exception;

import com.leon.demo.enums.ResultEnum;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

	private Integer code;

	public CustomException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
	}

}
