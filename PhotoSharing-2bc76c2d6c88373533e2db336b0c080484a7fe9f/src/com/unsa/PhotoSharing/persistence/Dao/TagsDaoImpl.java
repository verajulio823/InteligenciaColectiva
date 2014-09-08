package com.unsa.PhotoSharing.persistence.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.unsa.PhotoSharing.persistence.entity.Tag;

public class TagsDaoImpl implements TagsDao {
	private SessionFactory session;
	
	public TagsDaoImpl()
	{
		session = SessionFactoryUtil.getInstance();
	}
	
	@Override
	public void add(Tag tags) {
		// TODO Auto-generated method stub
		Session s = session.getCurrentSession();
		Transaction trans=s.beginTransaction();
		this.session.getCurrentSession().save(tags);
		trans.commit();
	}

	@Override
	public void edit(Tag tags) {
		// TODO Auto-generated method stub
		this.session.getCurrentSession().update(tags);
	}

	@Override
	public void delete(int tagsId) {
		// TODO Auto-generated method stub
		Tag tagsDell = (Tag)this.session.getCurrentSession().get(Tag.class,tagsId);
		if(tagsDell != null)
		{
			this.session.getCurrentSession().delete(tagsDell);
		}
	}

	@Override
	public Tag getTag(int tagsId) {
		// TODO Auto-generated method stub
		return (Tag)this.session.getCurrentSession().get(Tag.class,tagsId);
	}

	@Override
	public List<Tag> getAllTags() {
		// TODO Auto-generated method stub

		Session s = session.getCurrentSession();
		Transaction trans=s.beginTransaction();
		
		List<Tag> listTags = (List<Tag>)s.createQuery("from Tag").list();
		trans.commit();
		return listTags;
	}

	@Override
	public Tag existsTag(String tag) 
	{
		Session s = session.getCurrentSession();
		Transaction trans=s.beginTransaction();
		Query query= s.createQuery("FROM Tag T WHERE T.nombreTag = :tag");
		query.setParameter("tag", tag);
		
		List result = query.list();
		trans.commit();
		if(result.size() > 0)
			return (Tag)result.get(0);
		else return null;
	}

	@Override
	public List<String> getUrlsForTag(String tag) {
		Session s = session.getCurrentSession();
		Transaction trans = s.beginTransaction();
		String queryString = "CALL GetUrlsForTag('" + tag + "')";
		Query query = s.createSQLQuery(queryString);
		List<String> result = query.list();
		trans.commit();
		return result;
	}

}
