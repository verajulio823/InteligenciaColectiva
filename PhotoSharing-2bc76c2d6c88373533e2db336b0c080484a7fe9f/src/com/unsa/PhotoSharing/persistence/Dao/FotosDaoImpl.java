package com.unsa.PhotoSharing.persistence.Dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.unsa.PhotoSharing.persistence.entity.Comentario;
import com.unsa.PhotoSharing.persistence.entity.Foto;

public class FotosDaoImpl implements FotosDao {
	private SessionFactory session;
	
	public FotosDaoImpl()
	{
		session = SessionFactoryUtil.getInstance();
	}
	
	@Override
	public void add(Foto fotos) {
		// TODO Auto-generated method stub
		Session s = session.getCurrentSession();
		System.out.println("adding foto");
		Transaction trans=s.beginTransaction();
		s.save(fotos);
		trans.commit();
	}

	@Override
	public void edit(Foto fotos) {
		// TODO Auto-generated method stub
		this.session.getCurrentSession().update(fotos);
	}

	@Override
	public void delete(int fotosId) {
		// TODO Auto-generated method stub
		Foto fotosDell = (Foto)this.session.getCurrentSession().get(Foto.class,fotosId);
		if(fotosDell != null)
		{
			this.session.getCurrentSession().delete(fotosDell);
		}
	}

	@Override
	public Foto getFoto(int fotosId) {
		// TODO Auto-generated method stub
        Session s = session.getCurrentSession();
		Transaction trans=s.beginTransaction();
		System.out.println("getFoto");
		Foto f = (Foto)s.get(Foto.class,fotosId);
		trans.commit();
		return f;

	}

	@Override
	public List<Foto> getAllFotos() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Foto> listFotos = (List<Foto>)this.session.getCurrentSession().createQuery("from Foto").list();
		return listFotos;
	}

	@Override
	public int getLastInsertedId()
	{
		Session s = session.getCurrentSession();
		System.out.println("last id");
		Transaction trans=s.beginTransaction();
		Query query= s.createSQLQuery("SELECT COUNT(idFoto) AS id FROM foto").addScalar("id");
		List<BigInteger> result = query.list();
		int lastInserted = result.get(0).intValue();
		trans.commit();
		return lastInserted;
	}

	@Override
	public List<Comentario> getFotoComentario(Foto foto) {
		Session s = session.getCurrentSession();
		List<Comentario> result = new ArrayList<Comentario>();
		Set <Comentario> setResult;

		setResult = foto.getComentarios();
		for (Comentario c : setResult)
		{
			result.add(c);
		}
			    
	    Collections.sort(result, new Comparator<Comentario>()
	    {
	        @Override 
	        public int compare(Comentario c1, Comentario c2) {
	            return c1.getComentDate().compareTo(c2.getComentDate());
	        }
	    });
	    
		System.out.println(result);

		return result;
	}

	@Override
	public void addFotoTag(int idFoto, int idTag) 
	{
		Session s = session.getCurrentSession();
		Transaction trans=s.beginTransaction();
		Query query= s.createSQLQuery("INSERT INTO foto_tag (idFoto,idTag) VALUES ('"+idFoto+"','"+idTag+"')");
		System.out.println(query.toString());
		query.executeUpdate();
		trans.commit();
		
	}

	@Override
	public List<Foto> getFotosByTag(int idTag) {
		
		Session s = session.getCurrentSession();
		System.out.println("id");
		Transaction trans=s.beginTransaction();
		Query query= s.createSQLQuery("SELECT idFoto FROM foto_tag WHERE idTag = :idTag");
		query.setParameter("idTag", idTag);
		List<Integer> idResults = query.list();
		trans.commit();

		List<Foto> result = new ArrayList<Foto>();
		for(Integer fotoId : idResults)
		{
			Foto f = getFoto(fotoId);
			result.add(f);
		}
		
		return result;
	}
}
