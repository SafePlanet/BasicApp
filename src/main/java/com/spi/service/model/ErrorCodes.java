package com.spi.service.model;

public enum ErrorCodes {

	BAD_REQUEST("badRequest");

	private String code;

	ErrorCodes(final String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
