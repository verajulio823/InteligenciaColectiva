package com.unsa.PhotoSharing.Business;

import com.unsa.PhotoSharing.persistence.Dao.ComentarioDaoImpl;
import com.unsa.PhotoSharing.persistence.Dao.ComentariosDao;
import com.unsa.PhotoSharing.persistence.Dao.UsuarioDao;
import com.unsa.PhotoSharing.persistence.Dao.UsuarioDaoImpl;
import com.unsa.PhotoSharing.persistence.entity.Comentario;
import com.unsa.PhotoSharing.persistence.entity.Usuario;

public class submitComentControllerManager {

	private  ComentariosDao comentariodao;
	private UsuarioDao usuariodao;
	public void setComentario(Comentario c){
		
		comentariodao = new ComentarioDaoImpl();
		comentariodao.add(c);
	
		
	}
	
	public Usuario getUsuario(int id){
	usuariodao = new UsuarioDaoImpl();
	return usuariodao.getUsuario(id);
		
	}
	
}
