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


/**
 * Servlet implementation class AddCommentController
 */

@WebServlet("/AddCommentController")
public class AddCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentController() {
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
		String user_id = request.getParameter("user_id");
		String foto_id = request.getParameter("foto_id");
		String comment = request.getParameter("comment");
		
		System.out.println("COMENTARIO " + user_id + " " + foto_id + " " + comment);
		CommentManager manager = new CommentManager();
		if(manager.addComentario(Integer.parseInt(user_id), Integer.parseInt(foto_id), comment))
		{
	        RequestDispatcher rd = request.getRequestDispatcher("viewPhotoId.jsp?foto_id="+foto_id);
	        rd.forward(request, response);
		}
		else
		{
			System.out.println("NADA");
		}
   	}


}
