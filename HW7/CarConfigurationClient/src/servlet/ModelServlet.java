package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.*;

/**
 * Servlet implementation class ModelServlet
 */
@WebServlet("/ModelServlet")
public class ModelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static CarconfigSocket client = null;
	
	String target1 = "GetModel.jsp";
	String target2 = "./OptionSetServlet";
	String selectedModel = " ";
    /**
     * Default constructor. 
     */
    public ModelServlet() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config)
    {
        client = SingleClient.getInstance("localhost", 4448);
        client.openConnection();
		client.start();
		System.out.println("Client is up...");
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    //response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    if (client.getModelName() == null || client.getModelName() != null && client.getModelName().size() == 0) {
	    	return;
	    }
	    
	    
	    request.setAttribute("ModelNameList", client.getModelName());
	    
	    RequestDispatcher dispatcher1 = request.getRequestDispatcher(target1);
	    try{
		    dispatcher1.forward(request, response);
		    out.println("Test forward1");
		    return ;
	    }catch(Exception e){} 
	    

	    /*
	    String docType =
	      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	      "Transitional//EN\">\n";
	    out.println(docType +
	                "<HTML>\n" +
	                "<HEAD><TITLE>Car Configuration</TITLE></HEAD>\n" +
	                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	                "<H1>Car Configuration</H1>\n");
	    out.println("Test");
	    out.println("<form method=\"get\" action=\"./OptionSetServlet\">");
	    for(String s : client.getModelName()){
	    	out.println("<input type=\"radio\" name=\"dish\" value=\"Indian\">");
	    	out.println(s);
	    }
	    out.println("<input type=\"submit\" value=\"Submit\"></form>");
	    out.println("</BODY></HTML>");
	    */
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    /*
	    PrintWriter out = response.getWriter();
	    
	    HttpSession session = request.getSession();
	    
	   
		   String ModelNameList=request.getParameter("modelname");
		  out.println("modelname");
		    String selected_model = (String) session.getAttribute("modelname");
		    out.println("You have selected:" + selected_model);
		    client.handleInput(selectedModel);
		    */
		    doGet(request, response);

		  //  session.setAttribute("CarObject", arg1);
		    /*
		    RequestDispatcher dispatcher2 = request.getRequestDispatcher(target2);
		    try{
			    dispatcher2.forward(request, response);
			    return ;
		    }catch(Exception e){} */
		    

	    //PrintWriter out = response.getWriter();
	  //  selectedModel = request.getParameter("modelname");
	   // out.println("doPost, selectedModel:" + selectedModel);
	    //client.sendOutput(selectedModel);
	   // client.handleInput(selectedModel);
	   // out.println("Finish Handle input");
	}

}
