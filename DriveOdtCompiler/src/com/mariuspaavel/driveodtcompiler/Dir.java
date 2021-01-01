package com.mariuspaavel.driveodtcompiler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Cd
 */
@WebServlet("/dir")
public class Dir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Get get = (params, info)->{

			String folderid = params.get("id")[0];
			if(folderid.equals(".."))info.change_to_parent_dir();
			else if(folderid.equals(".")) {}
			else {
				Item dircheck = Utils.get_item(info.token.getAccess_token(), folderid);
				if(!dircheck.getMimeType().equals("application/vnd.google-apps.folder"))throw new Exception("directory not found");
				info.change_directory(dircheck);
			}
			StatusResponse stResp = new StatusResponse();
			stResp.setFiles(Utils.list_dir(info, info.getWorkDir()));
			stResp.setList(info.compilelist);
			stResp.setPath(info.getPathString());
			
			return stResp;
		};
		get.run(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
