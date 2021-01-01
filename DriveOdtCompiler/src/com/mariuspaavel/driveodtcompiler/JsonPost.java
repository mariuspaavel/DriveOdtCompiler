package com.mariuspaavel.driveodtcompiler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface JsonPost {
	public default void run(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		try {
			SessionInfo sessionInfo = SessionInfo.getSession(request.getSession().getAttribute("appsessionkey").toString());
			String postBody = Utils.getPostBody(request);
			/*
			SerialObj inputType = Utils.objectMapper.readValue(postBody, SerialObj.class);
			SerialObj input = Utils.objectMapper.readValue(postBody, inputType.getClass());
			*/
			SerialObj output = execute(postBody, sessionInfo);
			String outputStr = Utils.objectMapper.writeValueAsString(output);
			writer.append(outputStr);
		}catch(Exception e) {
			writer.append(Utils.objectMapper.writeValueAsString(new ErrorReport(e)));
		}	
	}
	SerialObj execute(String input, SessionInfo info) throws Exception;
}
