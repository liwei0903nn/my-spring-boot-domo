package com.leon.demo.util;

import com.leon.demo.DTO.Result;

public class ResultUtil {

	public static Result success() {
		return success(null);
	}

	public static Result success(Object data) {
		return getResult(0, "success", data);
	}

	public static Result getResult(Integer code, String message, Object data) {
		Result result = new Result<>();
		result.setCode(code);
		result.setMessage(message);
		result.setData(data);
		return result;
	}

	public static Result error(Integer code, String message) {
		return getResult(code, message, null);
	}

}
