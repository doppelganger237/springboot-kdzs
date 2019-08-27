package com.zhang.kdzs.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Result<T> {

	private Integer code;
	private String message;

	private T data;

	public Result() {
		this(StatusCode.UKNOWN);
	}

	public Result(Integer code) {
		this(code, null,null);
	}

}
