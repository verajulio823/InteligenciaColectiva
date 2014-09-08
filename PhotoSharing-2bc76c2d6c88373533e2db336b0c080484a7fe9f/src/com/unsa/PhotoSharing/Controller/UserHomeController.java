package com.unsa.PhotoSharing.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unsa.PhotoSharing.Business.UserHomeManager;
import com.unsa.PhotoSharing.persistence.entity.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/UserHomeController")
public class UserHomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private UserHomeManager manager;
	
    public UserHomeController() {
        super();
        // TODO Auto-generated constructor stub
        manager = new UserHomeManager();
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
	}

	public Usuario getUser (int user_id)
	{
		return manager.getUsuario(user_id);
	}
}
