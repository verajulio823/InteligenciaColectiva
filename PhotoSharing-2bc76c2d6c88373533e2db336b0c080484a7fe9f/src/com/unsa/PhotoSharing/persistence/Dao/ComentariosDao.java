package com.unsa.PhotoSharing.persistence.Dao;

import java.util.List;

import com.unsa.PhotoSharing.persistence.entity.Comentario;

public interface ComentariosDao {
	public void add(Comentario comentario);
	public void edit(Comentario comentario);
	public void delete(int comentarioId);
	public Comentario getComentario(int comentarioId);
	public List<Comentario> getAllComentarios();

}
