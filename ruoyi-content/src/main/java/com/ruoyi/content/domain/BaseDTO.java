package com.ruoyi.content.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseDTO {
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
