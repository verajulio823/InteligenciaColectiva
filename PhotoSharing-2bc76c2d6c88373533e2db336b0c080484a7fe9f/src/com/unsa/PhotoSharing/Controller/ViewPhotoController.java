package com.unsa.PhotoSharing.Controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unsa.PhotoSharing.Business.ViewPhotoManager;
import com.unsa.PhotoSharing.persistence.entity.Comentario;
import com.unsa.PhotoSharing.persistence.entity.Foto;

/**
 * Servlet implementation class ViewPhotoController
 */
@WebServlet("/ViewPhotoController")
public class ViewPhotoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPhotoController() {
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
		int fotoId =0;
		ViewPhotoManager manager = new ViewPhotoManager();
		Foto foto =manager.getFoto(fotoId);
		
		Set<Comentario> comentarios=manager.getComentarios(fotoId);
		for(Comentario c : comentarios){
			String snombre=c.getUsuario().getNombre();
			String scomentario=c.getComentario();			
		}
		
	}

}
