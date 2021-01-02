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
	
	
	public static final String client_id = "1084305052714-j7jpnfpoqc0cnqmgp2spjminag992shd.apps.googleusercontent.com";
	public static final String client_secret = "h602wp2tOF3kh56tO_xhJIMt";
	
	public static final String api_key="AIzaSyBdSFvYCSccSdB3yRVorXRoYxoFgBCO-Us";
	
	private String generateAuthUrl() {
		return "https://accounts.google.com/o/oauth2/v2/auth?"
				+ "scope=https%3A//www.googleapis.com/auth/drive.readonly&"
				+ "access_type=offline&"
				+ "include_granted_scopes=true&"
				+ "response_type=code&"
				+ "state=state_parameter_passthrough_value&"
				+ "redirect_uri=http%3A//mariuspaavel.com/driveodtcompiler/authredirect&"
				+ "client_id=" + client_id;
	}

}
