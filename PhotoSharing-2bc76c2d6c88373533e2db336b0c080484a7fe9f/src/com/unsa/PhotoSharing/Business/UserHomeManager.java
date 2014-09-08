package com.unsa.PhotoSharing.Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.unsa.PhotoSharing.persistence.Dao.FotosDao;
import com.unsa.PhotoSharing.persistence.Dao.FotosDaoImpl;
import com.unsa.PhotoSharing.persistence.Dao.UsuarioDao;
import com.unsa.PhotoSharing.persistence.Dao.UsuarioDaoImpl;
import com.unsa.PhotoSharing.persistence.entity.Comentario;
import com.unsa.PhotoSharing.persistence.entity.Foto;
import com.unsa.PhotoSharing.persistence.entity.Usuario;

public class UserHomeManager 
{
	private UsuarioDao userDao;
	private Usuario user;
	
	public UserHomeManager ()
	{
		userDao = new UsuarioDaoImpl();
	}

	public Usuario getUsuario (int user_id)
	{
		user = userDao.getUsuario(user_id);
		if (user != null) return user;
		return null;
	}
	
	public List<Foto> getUsuarioFotos(Usuario usuario) 
	{
		return userDao.getUsuarioFotos(usuario);
	}
	
	public List<String> getLastNews(Usuario usuario)
	{
		return userDao.getLastNews(usuario);
	}
	
	public void editUsuario(Usuario usuario)
	{
		userDao.edit(usuario);
	}
	
	public List<Usuario> getAmigos(Usuario usuario)
	{
		return userDao.getAmigos(usuario);
	}
	
	public boolean addFriend(int c, int nF)
	{
		UsuarioDao userDao = new UsuarioDaoImpl();
		try
		{
			Usuario current = userDao.getUsuario(c);
			Usuario newFriend = userDao.getUsuario(nF);
			userDao.addFriend(current, newFriend);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}

	}
}
