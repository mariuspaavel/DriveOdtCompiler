package com.mariuspaavel.driveodtcompiler;

public class SubmitListRequest extends SerialObj {
	Item[] compilelist;

	public Item[] getCompilelist() {
		return compilelist;
	}

	public void setCompilelist(Item[] compilelist) {
		this.compilelist = compilelist;
	}
}
