package com.mariuspaavel.driveodtcompiler;

public class DriveErrorException extends Exception {
	String errorResponse;

	public DriveErrorException(String errorResponse, Exception parsingException) {
		this.errorResponse = errorResponse;
	}
	@Override
	public String getMessage() {
		return errorResponse;
	}
}
