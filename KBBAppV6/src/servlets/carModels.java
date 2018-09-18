package servlets;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import client.*;
import model.*;


@WebServlet("/cars")
public class carModels extends HttpServlet {
	private defaultSocketClient use;
	private static final long serialVersionUID = 1L;

	public carModels() {
		super();
		String strLocalHost = "";
		try {
			strLocalHost = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.err.println("Unable to find local host");
		}
		use = new defaultSocketClient(strLocalHost, 4444);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><title>Available Models</title><body>");
		out.println("<form action=/KBBAppV6/configure>");
		out.println("<header><h1>Choose a car model to configure</h1><br><br><select name=mode>");
		use.openConnection();
		ArrayList<String> models = use.getModels();
		use.closeSession();
		if(models!=null){
			for (int i=0;i<models.size();i++) {
				out.println("<option value ="+models.get(i)+">"+models.get(i)+"</option>");
			}
			out.println("</select><br>");
			out.println("<input type=submit value=Choose></form>");
		}
		out.println("</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
