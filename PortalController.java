package controller;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CallSMScAPI;
import service.LoginService;
import bean.ContactBean;

/**
 * Servlet implementation class Business
 */

public class PortalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PortalController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPath="";

		HttpSession session = request.getSession(true);
		String action=request.getParameter("action");
		
		if("ChangePWD".equals(action)){
		LoginService ls = new LoginService();
		int noofrowsaffected=ls.changePwd(request,(String)session.getAttribute("emailid"));
		System.out.println(session.getAttribute("emailid"));
		if(noofrowsaffected>0){
			request.setAttribute("message", "Password Change Successfully");
		}
		else{
			request.setAttribute("message", "Current Password does not match");
		}

		nextPath="/JSP/Changepassword.jsp";
		}
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher(nextPath);
		rd.forward(request, response);
		}
	}


