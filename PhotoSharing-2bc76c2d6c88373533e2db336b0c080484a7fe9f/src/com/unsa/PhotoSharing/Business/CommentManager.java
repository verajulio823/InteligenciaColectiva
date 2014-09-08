package com.unsa.PhotoSharing.Business;

import java.util.Date;
import java.util.List;

import com.unsa.PhotoSharing.persistence.Dao.ComentarioDaoImpl;
import com.unsa.PhotoSharing.persistence.Dao.ComentariosDao;
import com.unsa.PhotoSharing.persistence.Dao.FotosDao;
import com.unsa.PhotoSharing.persistence.Dao.FotosDaoImpl;
import com.unsa.PhotoSharing.persistence.Dao.UsuarioDao;
import com.unsa.PhotoSharing.persistence.Dao.UsuarioDaoImpl;
import com.unsa.PhotoSharing.persistence.entity.Comentario;
import com.unsa.PhotoSharing.persistence.entity.Foto;

public class CommentManager 
{
	private ComentariosDao comentarioDao;
	public CommentManager()
	{
		comentarioDao = new ComentarioDaoImpl();
	}
	
	public boolean addComentario (int user_id, int foto_id, String comment)
	{
		FotosDao fotoDao = new FotosDaoImpl();
		UsuarioDao userDao = new UsuarioDaoImpl();
		Comentario c = new Comentario();
		try
		{
			c.setUsuario(userDao.getUsuario(user_id));
			c.setFoto(fotoDao.getFoto(foto_id));
			c.setComentario(comment);
			c.setComentDate(new Date());
			comentarioDao.add(c);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	
	public List<Comentario> getComentarios(Foto f)
	{
		FotosDao fotoDao = new FotosDaoImpl();
		return fotoDao.getFotoComentario(f);
	}
}
