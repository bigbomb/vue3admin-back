package com.loafer.springboot.common.exception;

import com.loafer.springboot.common.utils.Constant;

import java.io.Serializable;

/**
 * 自定义异常
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class RunException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	private int code;
    private String message;

    public RunException(int code) {
		super();
		Constant.StatusCode statusCode = Constant.StatusCode.getStatusCode(code);
		if (statusCode != null) {
			this.code = code;
			this.message = statusCode.getMessage();
		}
	}

	public RunException(int code, Throwable e) {
		super(e);
		Constant.StatusCode statusCode = Constant.StatusCode.getStatusCode(code);
		if (statusCode != null) {
			this.code = code;
			this.message = statusCode.getMessage();
		}
	}

	public RunException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
