package com.example.demopugspring.properties;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:/properties/insurersCodes.properties", encoding = "utf-8")
public class InsurersCodes extends Codes {
	private static final String SUFFIX = "";

	public String getSuffix() {
		return SUFFIX;
	}

}
