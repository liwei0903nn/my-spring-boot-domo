package com.leon.demo.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.leon.demo.DTO.Result;
import com.leon.demo.enums.ResultEnum;
import com.leon.demo.exception.CustomException;
import com.leon.demo.util.JsonUtil;
import com.leon.demo.util.ResultUtil;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExcpetionHandler {

	@ExceptionHandler(value = Exception.class)
	public Object handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
		Result result = null;
		// 1. 获取错误信息
		CustomException customException = null;
		if (e instanceof CustomException) {
			customException = (CustomException) e;
		} else {
			e.printStackTrace();
			log.error("【系统异常】{}", e.getMessage());
			customException = new CustomException(ResultEnum.UNKOWN_ERROR);
		}

		result = ResultUtil.error(customException.getCode(), customException.getMessage());
		// 2. 根据请求类型不同返回不同结果
		String acceptType = request.getHeader("Accept");
		if (StringUtils.containsIgnoreCase(acceptType, "html")) { // 页面请求 跳转到错误页面
			ModelAndView modelAndView = new ModelAndView("/error/custom_error");
			modelAndView.addObject("error", result);
			return modelAndView;
		} else { // 数据请求 返回错误json数据
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
			} catch (IOException e1) {
				log.error("【系统异常】{}", e1);
				return null;
			}

			writer.write(JsonUtil.objectToJson(result));
			writer.flush();
			return null;
		}

	}

}
