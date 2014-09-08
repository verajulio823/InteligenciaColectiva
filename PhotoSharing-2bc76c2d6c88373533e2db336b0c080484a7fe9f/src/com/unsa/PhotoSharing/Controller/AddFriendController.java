package com.unsa.PhotoSharing.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unsa.PhotoSharing.Business.CommentManager;
import com.unsa.PhotoSharing.Business.UserHomeManager;


/**
 * Servlet implementation class AddFriendController
 */

@WebServlet("/AddFriendController")
public class AddFriendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String loged_user_id = request.getParameter("loged_user_id");
		String user_id = request.getParameter("user_id");
		
		UserHomeManager manager = new UserHomeManager();
		if( manager.addFriend(Integer.parseInt(loged_user_id), Integer.parseInt(user_id)) )
		{
	        RequestDispatcher rd = request.getRequestDispatcher("home.jsp?user_id="+user_id);
	        rd.forward(request, response);
		}
		else
		{
			System.out.println("NADA");
		}
   	}


}
