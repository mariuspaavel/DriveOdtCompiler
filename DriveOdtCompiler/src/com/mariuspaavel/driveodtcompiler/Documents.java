package com.mariuspaavel.driveodtcompiler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Documents {
	
	private static String path;
	
	private static String home;
	
	
	
	static {
		
		try {
			home = Files.readString(Paths.get(getPath() + "home.html"));
			
		} catch (IOException e) {
			StringWriter out = new StringWriter();
			PrintWriter errorWriter = new PrintWriter(out);
			e.printStackTrace(errorWriter);
			errorWriter.flush();
			home = out.toString();
		}
	}
	public static String getHomePage() {
		return home;
	}
	
	
	public static String getPath() {
		if(path == null) {
			path = new PathObtainer().obtain();
		}
		return path;
	}
	
	private static class PathObtainer {
		 String obtain() {
			return getClass().getResource("/").getPath().replace("WEB-INF/classes/", "");
		}
	}
	
}
