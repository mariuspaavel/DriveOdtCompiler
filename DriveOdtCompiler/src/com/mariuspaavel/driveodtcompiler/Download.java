package com.mariuspaavel.driveodtcompiler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */
@WebServlet("/download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OutputStream os = response.getOutputStream();
		try {
			SessionInfo sessionInfo = SessionInfo.getSession(request.getSession().getAttribute("appsessionkey").toString());
			Map<String, String[]> params = request.getParameterMap(); 
			
			InputStream[] inputfiles = new InputStream[sessionInfo.compilelist.length];
			
			for(int i = 0; i < inputfiles.length; i++) {
				byte[] file = sessionInfo.getDriveConnection().downloadFile(sessionInfo.compilelist[i]);
				inputfiles[i] = new ByteArrayInputStream(file);
			}
			byte[] output = Cat.cat(inputfiles);
			os.write(output);
			os.flush();
			
		}catch(Exception e) {
			os.write(Utils.objectMapper.writeValueAsString(new ErrorReport(e)).getBytes());
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
