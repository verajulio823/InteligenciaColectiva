package com.unsa.PhotoSharing.Business;
import java.io.File;

import com.unsa.PhotoSharing.persistence.Dao.UsuarioDao;
import com.unsa.PhotoSharing.persistence.Dao.UsuarioDaoImpl;
import com.unsa.PhotoSharing.persistence.entity.Usuario;

public class NewUserManager 
{
	private UsuarioDao userDao;
	
	public void insert(String nombre, String apellidos, String email, String nickname, String password)
	{
		Usuario user = new Usuario();
		user.setNombre(nombre);
		user.setApellidos(apellidos);
		user.setEmail(email);
		user.setNickname(nickname);
		user.setPassword(password);
		user.setProfilePhoto("images" + File.separator + "no_user_image.png");
		userDao = new UsuarioDaoImpl();
		userDao.add(user);
		
	}
}
