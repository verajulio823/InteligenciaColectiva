package com.unsa.PhotoSharing.persistence.Dao;

import java.util.List;

import com.unsa.PhotoSharing.persistence.entity.Comentario;
import com.unsa.PhotoSharing.persistence.entity.Foto;

public interface FotosDao {
	public void add(Foto foto);
	public void edit(Foto foto);
	public void delete(int fotoId);
	public Foto getFoto(int fotoId);
	public List<Foto> getAllFotos();
	public int getLastInsertedId();
	public List<Comentario> getFotoComentario(Foto foto);
	public void addFotoTag(int idFoto, int idTag);
	public List<Foto> getFotosByTag(int idTag);
}
