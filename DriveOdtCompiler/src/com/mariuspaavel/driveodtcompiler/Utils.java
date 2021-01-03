package com.mariuspaavel.driveodtcompiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	public static String getPostBody(HttpServletRequest request) throws IOException{
		 StringBuilder buffer = new StringBuilder();
		 BufferedReader reader = request.getReader();
		 String line;
		 while ((line = reader.readLine()) != null) {
			 buffer.append(line);
			 buffer.append(System.lineSeparator());
		 }
		 return buffer.toString();
	}
	public static ObjectMapper objectMapper = new ObjectMapper();

	public static final String approot = "https://mariuspaavel.com/odtapp/";
	
}
