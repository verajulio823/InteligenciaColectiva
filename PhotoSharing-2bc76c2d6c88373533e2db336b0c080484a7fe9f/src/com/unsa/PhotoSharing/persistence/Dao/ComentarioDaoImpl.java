package com.unsa.PhotoSharing.persistence.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.unsa.PhotoSharing.persistence.entity.Comentario;

public class ComentarioDaoImpl implements ComentariosDao {
	private SessionFactory session;
	
	public ComentarioDaoImpl() 
	{
		session = SessionFactoryUtil.getInstance();
	}
	
	@Override
	public void add(Comentario comentario) 
	{
		Session s = this.session.getCurrentSession();
		Transaction trans = s.beginTransaction();
		s.save(comentario);
		System.out.println("adding comment");
		trans.commit();
	}

	@Override
	public void edit(Comentario comentario) {
		// TODO Auto-generated method stub
		this.session.getCurrentSession().update(comentario);
	}

	@Override
	public void delete(int comentarioId) {
		// TODO Auto-generated method stub
		Comentario comentarioDell = (Comentario)this.session.getCurrentSession().get(Comentario.class,comentarioId);
		if(comentarioDell != null)
		{
			this.session.getCurrentSession().delete(comentarioDell);
		}
	}

	
	@Override
	public Comentario getComentario(int comentarioId) {
		// TODO Auto-generated method stub
		return (Comentario)this.session.getCurrentSession().get(Comentario.class,comentarioId);

	}

	@Override
	public List<Comentario> getAllComentarios() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Comentario> listComentario = (List<Comentario>)this.session.getCurrentSession().createQuery("from Comentario").list();
		return listComentario;

	}

}
