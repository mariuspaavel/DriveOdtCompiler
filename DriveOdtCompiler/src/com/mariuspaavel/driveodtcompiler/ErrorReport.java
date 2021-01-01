package com.mariuspaavel.driveodtcompiler;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorReport extends SerialObj {
	private String print;
	
	public String getPrint() {
		return print;
	}
	public void setPrint(String print) {
		this.print = print;
	}
	public ErrorReport(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();
		print = sw.toString();
	}
}
