package com.unsa.PhotoSharing.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unsa.PhotoSharing.Business.NewUserManager;

/**
 * Servlet implementation class NewUserController
 */
@WebServlet("/NewUserController")
public class NewUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserController() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		
		NewUserManager manager = new NewUserManager();
		manager.insert(nombre, apellidos, email, nickname, password);
		
		PrintWriter out = response.getWriter();
		out.println("<!-------------------------------------------------------------------->"+
	    "<!-- TITULO                                                         -->"+
	    "<!-------------------------------------------------------------------->"+
				"<h1><br/>" + "Inserción correcta!" + "<br/>&nbsp;</h1>");
    	
    	out.println("<h4><br/><font size=\"+1\" <a href=\"index.html\" onClick=\"history.go(-1); return false;\">VOLVER</a><br/>&nbsp;</h4><br/>"); 
	}

}
