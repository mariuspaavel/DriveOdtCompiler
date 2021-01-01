package com.mariuspaavel.driveodtcompiler;

public class DriveQuery {
	private String kind;
	private String nextPageToken;
	private boolean incompleteSearch;
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getNextPageToken() {
		return nextPageToken;
	}
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	public boolean isIncompleteSearch() {
		return incompleteSearch;
	}
	public void setIncompleteSearch(boolean incompleteSearch) {
		this.incompleteSearch = incompleteSearch;
	}
	public Item[] getFiles() {
		return files;
	}
	public void setFiles(Item[] files) {
		this.files = files;
	}
	Item[] files;
}
