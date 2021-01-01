package com.mariuspaavel.driveodtcompiler;

public class StatusResponse extends SerialObj {
	private Item[] files;
	private String path;
	private Item[] list;
	public Item[] getFiles() {
		return files;
	}
	public void setFiles(Item[] files) {
		this.files = files;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Item[] getList() {
		return list;
	}
	public void setList(Item[] list) {
		this.list = list;
	}
}
