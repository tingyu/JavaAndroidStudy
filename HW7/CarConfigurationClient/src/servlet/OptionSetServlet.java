package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adapter.BuildAuto;
import client.CarconfigSocket;
import client.SelectCarOption;

/**
 * Servlet implementation class OptionSetServlet
 */
@WebServlet("/OptionSetServlet")
public class OptionSetServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String modelName = null;
	CarconfigSocket client = null;
	ModelServlet modelServlet;
	BuildAuto buildAuto = new BuildAuto();
	private boolean is_inital = true;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OptionSetServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

    public void init(ServletConfig config)
    {
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
	    HttpSession session = request.getSession(true);
	   //HashMap<> 

		
	    String docType =
	  	      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	  	      "Transitional//EN\">\n";

	  	  String modelName = request.getParameter("modelname");
	  	  if(modelName !=null && is_inital == true){
				out.println(modelName);
				modelServlet.client.handleInput(modelName);
				is_inital = false;
				//modelServlet.client.sendOutput(modelName);
				//modelServlet.client.sendOutput(modelName);
	  	  }


		   // out.println("</BODY></HTML>");

		
	/*	String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
				+ "Transitional//EN\">\n";*/
		out.println(docType + "<HTML>\n"
				+ "<HEAD><TITLE>Car Configuration</TITLE></HEAD>\n"
				+ "<BODY BGCOLOR=\"#FDF5E6\">\n"
				+ "<H1>Basic Car Choice</H1>\n "
				+ "<TABLE BORDER=1 ALIGN=\"CENTER\">\n"
				+ "<TR BGCOLOR=\"#FFAD00\">\n" + "  <TH>Info Type<TH>Value\n"
				+ "<TR>\n" + "  <TD>Make/Model:\n" + "<TD>\n" );

		out.println(request.getParameter("modelname"));
		
	    out.println("<form method=\"post\" action=\"./ShowOutput.jsp\">");
	    
	    int index = 0;

		for (String model : buildAuto.getModelName()) {
			out.println("<option>" + model + "\n");
		
		out.println("</select>\n" );
				//+ "<select>\n");
		
			for (String opsetName : buildAuto.getAllOptionSetName(modelName)) {
			out.println(" <TR>\n" + "  <TD>" + opsetName + ":\n" + "  <TD>"
					+ "<select name =" +  "\"" + opsetName  + "\"" +">\n");
			for (String optName : buildAuto.getAllOptionName(modelName,
					opsetName)) {
				out.println("<option>" +  optName  + "\n");
				session.setAttribute("OptionPrice" + opsetName, buildAuto.getOptionPrice(modelName,opsetName,optName));
			}
			index++;
		}
		}
		/*
		request.setAttribute("ModelName", modelName);
		request.setAttribute("opsetNameList", buildAuto.getAllOptionSetName(modelName));
		request.setAttribute("BasePrice", buildAuto.getBasePrice(modelName));
		*/
		
		session.setAttribute("ModelName", modelName);
		session.setAttribute("opsetNameList", buildAuto.getAllOptionSetName(modelName));
		session.setAttribute("BasePrice", buildAuto.getBasePrice(modelName));
		/*
		out.println(buildAuto.getAllOptionSetName(modelName));
		out.println(buildAuto.getBasePrice(modelName));
		
		System.out.println("buildAuto.getAllOptionSetName(modelName)"+buildAuto.getAllOptionSetName(modelName));
		System.out.println("buildAuto.getBasePrice(modelName)" +buildAuto.getBasePrice(modelName));
		System.out.println("modelName" +modelName);*/

		session.setAttribute("opsetNumber", index+1);
		 out.println("<TR>\n");
		 out.println("<input type=\"submit\" value=\"Submit\"></form>");
		 out.println("</TABLE>");

		 out.println("</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
