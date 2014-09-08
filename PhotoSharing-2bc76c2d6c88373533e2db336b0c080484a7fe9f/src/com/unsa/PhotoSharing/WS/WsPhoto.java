package com.unsa.PhotoSharing.WS;

import java.util.ArrayList;
import java.util.List;

import com.unsa.PhotoSharing.Business.UserHomeManager;
import com.unsa.PhotoSharing.Controller.UserHomeController;
import com.unsa.PhotoSharing.persistence.entity.Foto;
import com.unsa.PhotoSharing.persistence.entity.Usuario;

public class WsPhoto {

	public List<String> getPhotos(int user_id){
		List<String> listFotosUrl = new ArrayList<String>();
		Usuario user = null;
		UserHomeController controller = new UserHomeController(); 
		
		user= controller.getUser(user_id);
		UserHomeManager manager = new UserHomeManager();
		List<Foto> userFotos = manager.getUsuarioFotos(user);
		
		
		for (Foto f : userFotos)
		{         			
			
			listFotosUrl.add("http://localhost:8080/PhotoSharingPro/"+f.getPhotoUrl().replace('\\','/'));
		}
		return listFotosUrl;
	}

}
