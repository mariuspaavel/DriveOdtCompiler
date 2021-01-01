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

	public static Item[] list_dir(SessionInfo sess, Item dir) throws Exception {
		String q = "'root'+in+parents+and+trashed+=+false";
		if (dir != null) q = String.format("'%s'+in+parents", dir.getId());
		return filesystem_query(sess.token.getAccess_token(), q);
	}
	
	public static Item[] filesystem_query(String access_token, String q) throws Exception {
		var params = new HashMap<String, String>();
		params.put("access_token", access_token);
		params.put("q", q);
		//String fields = "'id,+name,+mimeType'";
		//params.put("fields", fields);
		String httpresponse = HttpReqSync.get("https://www.googleapis.com/drive/v3/files", params);
		
		try {
			DriveQuery rs = objectMapper.readValue(httpresponse, DriveQuery.class);
			return rs.getFiles();
		}
		catch(Exception e) {
			var e1 = new DriveErrorException(httpresponse, e);
			e1.initCause(e);
			throw e1;
		}
		
	}
	public static Item get_item(String access_token, String id) throws Exception {
		var params = new HashMap<String, String>();
		params.put("access_token", access_token);
		//String fields = "'id,+name,+mimeType'";
		//params.put("fields", fields);
		String httpresponse = HttpReqSync.get("https://www.googleapis.com/drive/v3/files/"+id, params);
		try {
			Item resp = objectMapper.readValue(httpresponse, Item.class);
			return resp;
		}catch(Exception e) {
			var e1 = new DriveErrorException(httpresponse, e);
			e1.initCause(e);
			throw e1;
		}
		
	}
	
	
}
