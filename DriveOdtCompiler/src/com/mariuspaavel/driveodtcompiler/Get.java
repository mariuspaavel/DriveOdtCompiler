package com.mariuspaavel.driveodtcompiler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Get {
	public default void run(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		try {
			SessionInfo sessionInfo = SessionInfo.getSession(request.getSession().getAttribute("appsessionkey").toString());
			Map<String, String[]> params = request.getParameterMap(); 
			SerialObj output = execute(params, sessionInfo);
			String outputStr = Utils.objectMapper.writeValueAsString(output);
			writer.append(outputStr);
		}catch(Exception e) {
			writer.append(Utils.objectMapper.writeValueAsString(new ErrorReport(e)));
		}	
	}
	SerialObj execute(Map<String, String[]> params, SessionInfo info) throws Exception;
}
