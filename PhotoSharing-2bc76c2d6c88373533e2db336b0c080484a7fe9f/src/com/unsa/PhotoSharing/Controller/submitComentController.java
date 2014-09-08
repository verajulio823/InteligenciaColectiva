package com.unsa.PhotoSharing.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unsa.PhotoSharing.Business.submitComentControllerManager;
import com.unsa.PhotoSharing.persistence.entity.Comentario;
import com.unsa.PhotoSharing.persistence.entity.Usuario;

/**
 * Servlet implementation class submitComentController
 */
@WebServlet("/submitComentController")
public class submitComentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public submitComentController() {
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
		String scomentario = request.getParameter("comentario");
		submitComentControllerManager manager = new submitComentControllerManager();
		
		String id =request.getParameter("id_user");
		String idFoto = request.getParameter("");
		
		
		Integer integer = new Integer(id);
		
		Usuario usuario =manager.getUsuario(integer.intValue());
		
		
		
		Comentario comentario = new Comentario();
		comentario.setComentario(scomentario);
		comentario.setUsuario(usuario);
		//comentario.setFoto();
		
		manager.setComentario(comentario);
		
	}

}
