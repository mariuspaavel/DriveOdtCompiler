package com.mariuspaavel.driveodtcompiler;

public class SerialObj {
	private Class requestType;

	public Class getRequestType() {
		return requestType;
	}
	public void setRequestType(Class requestType) {
		this.requestType = requestType;
	}
	public SerialObj() {
		requestType = getClass();
	}
}
