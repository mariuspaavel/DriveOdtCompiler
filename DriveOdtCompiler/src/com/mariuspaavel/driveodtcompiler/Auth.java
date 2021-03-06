package com.mariuspaavel.driveodtcompiler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Index
 */
@WebServlet("/auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		try {
			String sessionKey = request.getSession().getAttribute("appsessionkey").toString();
			SessionInfo session = SessionInfo.getSession(sessionKey);
			if(session == null)throw new NullPointerException();
			
			writer.append("{\"result\":\"success\"}");
			//response.getWriter().append(session.toString());
		}catch(NullPointerException e) {
			writer.append("{\"result\":\"failure\", \"url\":\"" + generateAuthUrl() + "\"}");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		PrintWriter writer = response.getWriter();
		Map<String, String[]> params = request.getParameterMap();
		for(String key : params.keySet()) {
			writer.append(String.format("%s : %s\n", key, params.get(key)[0]));
		}
		writer.flush();
		*/
	}
	
	
	public static final String client_id = "753451643595-kf4osl9beq8ifo4o449dp0cd4rpenhg0.apps.googleusercontent.com";
	public static final String client_secret = "TtpyR68EQ1wgJdQdqd-BQh4y";
	
	public static final String api_key="AIzaSyAgEqHWdVNcuyDERYcOIwd1pFMAY8qVh8o";
	
	private String generateAuthUrl() {
		return "https://accounts.google.com/o/oauth2/v2/auth?"
				+ "scope=https%3A//www.googleapis.com/auth/drive.readonly&"
				+ "access_type=offline&"
				+ "include_granted_scopes=true&"
				+ "response_type=code&"
				+ "state=state_parameter_passthrough_value&"
				+ "redirect_uri=https%3A//mariuspaavel.com/odtapp/authredirect&"
				+ "client_id=" + client_id;
	}

}
