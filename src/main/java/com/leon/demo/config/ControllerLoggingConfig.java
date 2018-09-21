package com.leon.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "controller-logging-config")
@Data
@Component
public class ControllerLoggingConfig {
	private Boolean enable;
	private Map<String, Boolean> requestHeader = new HashMap<>();
}
