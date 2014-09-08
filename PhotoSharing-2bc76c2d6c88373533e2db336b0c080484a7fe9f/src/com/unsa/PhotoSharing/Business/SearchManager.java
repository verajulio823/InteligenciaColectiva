package com.unsa.PhotoSharing.Business;

import java.util.ArrayList;
import java.util.List;

import com.unsa.PhotoSharing.persistence.Dao.TagsDao;
import com.unsa.PhotoSharing.persistence.Dao.TagsDaoImpl;
import com.unsa.PhotoSharing.persistence.Dao.UsuarioDao;
import com.unsa.PhotoSharing.persistence.Dao.UsuarioDaoImpl;
import com.unsa.PhotoSharing.persistence.entity.Tag;
import com.unsa.PhotoSharing.persistence.entity.Usuario;



public class SearchManager 
{
	UsuarioDao userDao;
	TagsDao tagDao;
	
	public SearchManager()
	{
		userDao = new UsuarioDaoImpl();
		tagDao = new TagsDaoImpl();
	}
	
	public List<String> getSearchResults(String like)
	{
		List<String> results = new ArrayList<String>();
		List<Usuario> usuarios = userDao.getAllUsuario();
		for (Usuario u : usuarios)
		{
			if(u.getNombre().toLowerCase().contains(like.toLowerCase()) ||
					u.getApellidos().toLowerCase().contains(like.toLowerCase()) ||
					u.getNickname().toLowerCase().contains(like.toLowerCase())
					)
			{
				String user = u.getNombre() +" " + u.getApellidos() + " (" + u.getNickname() + ")";
				String link = "<li> <a href=\"home.jsp?user_id=" + u.getIdUsuario() + "\">" + user + "</a></li>";
				results.add(link);
			}
		}
		
		List<Tag> tags = tagDao.getAllTags();
		
		for (Tag t : tags)
		{
			if(t.getNombreTag().toLowerCase().contains(like.toLowerCase()) )
			{
				String tag = t.getNombreTag();
				String link = "<li> <a href=\"viewphoto.jsp?tag_id=" + t.getIdTag() + "\">" + tag + "</a></li>";
				results.add(link);
			}
		}
		return results;
	}

	public List<String> getTagsResult(String like)
	{
		List<String> results = new ArrayList<String>();
		List<Tag> tags = tagDao.getAllTags();
		
		for (Tag t : tags)
		{
			if(t.getNombreTag().toLowerCase().contains(like.toLowerCase()) )
			{
				String tag = t.getNombreTag();
				results.add(tag);
			}
		}
		return results;
	}

}
