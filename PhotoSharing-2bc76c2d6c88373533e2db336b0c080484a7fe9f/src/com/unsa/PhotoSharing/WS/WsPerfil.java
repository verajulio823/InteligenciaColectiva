package com.unsa.PhotoSharing.WS;

import java.util.ArrayList;
import java.util.List;
import com.unsa.PhotoSharing.Business.LoginManager;
import com.unsa.PhotoSharing.persistence.entity.Usuario;

public class WsPerfil {

	public List<String> obtenerPerfil(String nick, String pass){
		List<String> listDataUser = new  ArrayList<String>();
		String nickname = nick;
		String password = pass;
		
		LoginManager manager = new LoginManager();
		Usuario user =manager.findUser(nickname, password); 
		if(user !=null){
			listDataUser.add(user.getNombre());
			listDataUser.add(user.getApellidos());
			listDataUser.add(user.getEmail());
			listDataUser.add(user.getNickname());
			listDataUser.add(user.getProfilePhoto());		
		}
		else{
			listDataUser=null;
		}
		
		
		return listDataUser;
	}

}
