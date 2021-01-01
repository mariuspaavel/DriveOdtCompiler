package com.mariuspaavel.driveodtcompiler;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/authredirect")
public class AuthRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthRedirect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String error;
			if((error = request.getParameter("error")) != null)throw new AuthException(error);
			String state = request.getParameter("state");
			String code = request.getParameter("code");
			if(code == null)throw new AuthException("invalid auth response");
			String scope = request.getParameter("scope");
			if(scope == null)throw new AuthException("invalid auth response");
			
			
			var googlereqparams = new HashMap<String, String>();
			googlereqparams.put("code", code);
			googlereqparams.put("client_id", Auth.client_id);
			googlereqparams.put("client_secret", Auth.client_secret);
			googlereqparams.put("redirect_uri", "http://mariuspaavel.com/driveodtcompiler/authredirect");
			googlereqparams.put("grant_type", "authorization_code");
			
			String json = HttpReqSync.post("https://oauth2.googleapis.com/token", googlereqparams);
			
			String key = SessionInfo.addSession(new SessionInfo(json));
			
			request.getSession().setAttribute("appsessionkey", key);
			
			//response.getWriter().append(SessionInfo.getSession(key).toString());
			
			response.sendRedirect("http://mariuspaavel.com/driveodtcompiler/");
			
		}catch(AuthException e) {
			response.getWriter().append(e.getMessage());
		} catch (Exception e) {
			response.getWriter().append(e.getMessage());
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
