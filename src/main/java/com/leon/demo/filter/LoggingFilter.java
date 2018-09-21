package com.leon.demo.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.leon.demo.config.ControllerLoggingConfig;
import com.leon.demo.wrapper.LoggingServletResponseWrapper2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Order(1)
@WebFilter(filterName = "loggingFilter", urlPatterns = { "/*" })
public class LoggingFilter implements Filter {

	@Autowired
	private ControllerLoggingConfig controllerLoggingConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (!controllerLoggingConfig.getEnable()
				|| !StringUtils.containsIgnoreCase(response.getContentType(), "json")) {
			chain.doFilter(request, response);
		} else {// 只记录json数据
			Long beginTime = System.currentTimeMillis();
			LoggingServletResponseWrapper2 responseWrapper = new LoggingServletResponseWrapper2(
					(HttpServletResponse) response);
			chain.doFilter(request, responseWrapper);
			Long endTime = System.currentTimeMillis();

			HttpServletRequest httpRequest = (HttpServletRequest) request;
			Map<String, String> requestHeader = new HashMap<>(); // 记录请求头数据
			for (Map.Entry<String, Boolean> entry : controllerLoggingConfig.getRequestHeader().entrySet()) { // 获取指定的
																												// header信息
				if (entry.getValue()) {
					String headerKey = entry.getKey();
					requestHeader.put(headerKey, httpRequest.getHeader(headerKey));
				}
			}

			String requestURI = httpRequest.getRequestURI();
			String responseString = responseWrapper.getContent();
			Integer responseStatus = responseWrapper.getStatus();
			log.info("【接口请求完成】url={}, costTime={}ms, requestHeader={}, responseStatus={}, response={}", requestURI,
					endTime - beginTime, requestHeader, responseStatus, responseString);
		}
	}

	@Override
	public void destroy() {
	}

}
