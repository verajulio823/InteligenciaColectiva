package com.unsa.PhotoSharing.persistence.Dao;

import java.util.List;

import com.unsa.PhotoSharing.persistence.entity.Foto;
import com.unsa.PhotoSharing.persistence.entity.Usuario;

public interface UsuarioDao {
	public void add(Usuario usuario);
	public void edit(Usuario usuario);
	public void delete(int usuarioId);
	public Usuario getUsuario(int usuarioId);
	public List<Usuario> getAllUsuario();
	public Usuario loginUsuario (String nickname, String password);
	public List<Foto> getUsuarioFotos(Usuario usuario);
	public List<String> getLastNews (Usuario usuario);
	public List<Usuario> getAmigos (Usuario usuario);
	public void addFriend(Usuario current, Usuario friend);
}
