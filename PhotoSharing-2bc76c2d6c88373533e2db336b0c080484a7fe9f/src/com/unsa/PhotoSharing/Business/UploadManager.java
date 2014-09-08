package com.unsa.PhotoSharing.Business;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;

import com.unsa.PhotoSharing.persistence.Dao.FotosDao;
import com.unsa.PhotoSharing.persistence.Dao.FotosDaoImpl;
import com.unsa.PhotoSharing.persistence.Dao.TagsDao;
import com.unsa.PhotoSharing.persistence.Dao.TagsDaoImpl;
import com.unsa.PhotoSharing.persistence.Dao.UsuarioDao;
import com.unsa.PhotoSharing.persistence.Dao.UsuarioDaoImpl;
import com.unsa.PhotoSharing.persistence.entity.Foto;
import com.unsa.PhotoSharing.persistence.entity.Tag;
import com.unsa.PhotoSharing.persistence.entity.Usuario;

public class UploadManager 
{
	private FotosDao fotoDao;
	private Foto foto;
	private UsuarioDao userDao;
	private TagsDao tagsDao;
	
	public UploadManager ()
	{
		fotoDao = new FotosDaoImpl();
		userDao = new UsuarioDaoImpl();
		tagsDao = new TagsDaoImpl();
	}
	public boolean uploadFile (String uploadFolder, String fileName, int user_id, String description, String tag, FileItem item)
	{
		if(fileName == "") return false;
		int foto_id = fotoDao.getLastInsertedId () + 1;
		String extension = fileName.substring(fileName.indexOf('.'),fileName.length());
		if(extension == ".jpeg") extension = ".jpg";
		boolean folderCreated = new File (uploadFolder).mkdir();
        System.out.println("cree el folder"+ folderCreated);
        String filePath = uploadFolder + File.separator + foto_id + extension;
        File uploadedFile = new File(filePath);
        System.out.println(filePath);
        // saves the file to upload directory
    	try 
    	{
			item.write(uploadedFile);
			foto = new Foto();
			foto.setIdFoto(foto_id);
			foto.setUsuario(userDao.getUsuario(user_id));
			foto.setDescripcion(description);
			foto.setUploadDate(new Date());
			foto.setPhotoUrl(user_id + File.separator + foto_id + extension);
			fotoDao.add(foto);
			Tag inputTag = tagsDao.existsTag(tag);
			if(inputTag == null)
			{
				Tag newTag = new Tag();
				newTag.setNombreTag(tag);
				Set<Foto> set = new HashSet<Foto>();
				set.add(foto);
				
				tagsDao.add(newTag);
				System.out.println("tag añadido a BD");
				fotoDao.addFotoTag(foto.getIdFoto(),newTag.getIdTag());
				System.out.println("tag añadido a foto");
				
			}
			else
			{
				fotoDao.addFotoTag(foto.getIdFoto(),inputTag.getIdTag());
				System.out.println("tag ya existente añadido a foto");
				
			}
		}
    	catch (Exception e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        return true;
	}

	
	public boolean uploadProfilePhoto (String uploadFolder, String fileName, Usuario user, FileItem item)
	{
		if(fileName == "") return false;
		String profile = "profile";
		String extension = fileName.substring(fileName.indexOf('.'),fileName.length());
		if(extension == ".jpeg") extension = ".jpg";
		boolean folderCreated = new File (uploadFolder).mkdir();
        System.out.println("cree el folder"+ folderCreated);
        String filePath = uploadFolder + File.separator + profile + extension;
        File uploadedFile = new File(filePath);
        System.out.println(filePath);
        // saves the file to upload directory
    	try 
    	{
			item.write(uploadedFile);
			user.setProfilePhoto(user.getIdUsuario() + File.separator + profile + extension);
			userDao.edit(user);
		}
    	catch (Exception e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        return true;
	}
	
}
