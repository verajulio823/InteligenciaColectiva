package com.unsa.PhotoSharing.persistence.Dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.unsa.PhotoSharing.persistence.entity.Comentario;
import com.unsa.PhotoSharing.persistence.entity.Foto;
import com.unsa.PhotoSharing.persistence.entity.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
	private SessionFactory session;
	
	public UsuarioDaoImpl ()
	{
		session = SessionFactoryUtil.getInstance();
	}
	
	@Override
	public void add(Usuario usuario) {
		// TODO Auto-generated method stub
		Session s = session.getCurrentSession();
		System.out.println("añadir");
		Transaction trans=s.beginTransaction();
		s.save(usuario);
		trans.commit();
	}

	@Override
	public void edit(Usuario usuario) {
		// TODO Auto-generated method stub
		Session s = session.getCurrentSession();
		System.out.println("añadir");
		Transaction trans=s.beginTransaction();
		s.update(usuario);
		trans.commit();

	}

	@Override
	public void delete(int usuarioId) {
		// TODO Auto-generated method stub
		Usuario usuarioDell = (Usuario)this.session.getCurrentSession().get(Usuario.class,usuarioId);
		if(usuarioDell != null)
		{
			this.session.getCurrentSession().delete(usuarioDell);
		}
	}

	@Override
	public Usuario getUsuario(int usuarioId) {
		// TODO Auto-generated method stub
		
		Session s = session.getCurrentSession();
		System.out.println("getUser");
		Transaction trans=s.beginTransaction();
		Usuario u = (Usuario)s.get(Usuario.class,usuarioId);
		trans.commit();
		
		return u;
	}

	@Override
	public List<Usuario> getAllUsuario() {
		// TODO Auto-generated method stub
		Session s = session.getCurrentSession();
		Transaction trans=s.beginTransaction();

		List<Usuario> listUsuario = (List<Usuario>)s.createQuery("from Usuario").list();
		trans.commit();

		return listUsuario;
	}

	@Override
	public Usuario loginUsuario(String nickname, String password) {
		// TODO Auto-generated method stub
		
		Usuario u = null;
		Session s = session.getCurrentSession();
		System.out.println("añadir");
		Transaction trans=s.beginTransaction();
		Query query= s.createQuery("FROM Usuario U WHERE U.nickname = :nickname and U.password = :password");
		query.setParameter("nickname", nickname);
		query.setParameter("password", password);
		
		List user = query.list();
		Iterator listIterator = user.iterator();
        while(listIterator.hasNext())
        {
            Usuario rd = (Usuario) listIterator.next();
            System.out.println("UserId :" +rd.getNombre());
            System.out.println("Mail Id :" +rd.getEmail());
            u = rd;
        }
		
		trans.commit();
		return u;
	}

	@Override
	public List<Foto> getUsuarioFotos(Usuario usuario) 
	{
		Session s = session.getCurrentSession();
		List<Foto> result = new ArrayList<Foto>();
		Set <Foto> setResult;
		
		setResult = usuario.getFotos();
		
		for (Foto f : setResult)
		{
			result.add(f);
		}
		
	    
	    Collections.sort(result, new Comparator<Foto>()
	    {
	        @Override 
	        public int compare(Foto f1, Foto f2) {
	            return f1.getIdFoto() - f2.getIdFoto();
	        }
	    });
		return result;
	}

	@Override
	public List<String> getLastNews(Usuario usuario) 
	{
		
		Session s = session.getCurrentSession();
		List<String> result = new ArrayList<String>();
		Set <Comentario> setResult;
		
		setResult = usuario.getComentarios();
		for (Comentario c : setResult)
		{
			String tmp = "Comentaste \"" + c.getComentario() + "\" el " + (new SimpleDateFormat("yyyy-MM-dd").format(c.getComentDate()));
			result.add(tmp);
		}
		
		System.out.println(result);

		return result;
	}

	@Override
	public List<Usuario> getAmigos(Usuario usuario) 
	{
		Session s = session.getCurrentSession();
		Transaction t = s.beginTransaction();
		Query q = s.createSQLQuery("SELECT idUsuario2 FROM usuario join amigo"
										+ " on amigo.idUsuario1 = usuario.idUsuario where usuario.idUsuario = :userid");
		q.setParameter("userid", usuario.getIdUsuario());
		List<Usuario> friends = new ArrayList<>();
		List<Integer> result = q.list();
		t.commit();
		for (Integer  id : result)
		{
			friends.add(getUsuario(id));
		}
		
		return friends;
	}

	@Override
	public void addFriend(Usuario current, Usuario friend) {
		Session s = session.getCurrentSession();
		System.out.println("añadir amigo");
		Transaction trans=s.beginTransaction();
		Query q = s.createSQLQuery("INSERT INTO amigo (idUsuario1, idUsuario2) VALUES (:current, :newFriend)");
		q.setParameter("current", current.getIdUsuario());
		q.setParameter("newFriend", friend.getIdUsuario());
		q.executeUpdate();
		trans.commit();	
	}
	

}
