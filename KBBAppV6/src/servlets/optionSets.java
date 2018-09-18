package servlets;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import client.*;
import model.*;

/**
 * Servlet implementation class optionSets
 */
@WebServlet("/configure")
public class optionSets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private defaultSocketClient use;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public optionSets() {
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
		String mode = (String)request.getParameter("mode");//wont receive full model name just up to 1st space but i can't add quotes
		use.openConnection();
		Vehicle mod = use.getAModel(mode);
		use.closeSession();
		PrintWriter out = response.getWriter();
		out.println("<html><title>Configuration</title><body><header><h1>Configure your "+mode+"</h1><br><br>");
		
		if (mod != null) {
			//request.setAttribute("car", mode);
			//request.getRequestDispatcher("custom.jsp").forward(request, response);
			out.println("<form action=/KBBAppV6/custom.jsp>");
			out.println("<input type=hidden name=car value="+mode+">");
			for(int i =0;i<mod.numComponents();i++){
				out.println("<select name=comp"+ i +">");
				for(int j = 0;j<mod.numOptions(i);j++){
					out.println("<option value ="+mod.getOptionName(i,j)+">"+mod.getOption(i,j)+"</option>");
				}
				out.println("</select><br><br>");
				
			}
			out.println("<input type=submit value=Configure></form>");
		}
		else{
			out.print("<p>Server sent no car over, wrong model name?</p>");
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
