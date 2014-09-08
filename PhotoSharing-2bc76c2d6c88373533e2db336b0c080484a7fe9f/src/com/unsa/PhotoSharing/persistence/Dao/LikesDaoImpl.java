package com.unsa.PhotoSharing.persistence.Dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.unsa.PhotoSharing.persistence.entity.Like;

public class LikesDaoImpl implements LikesDao {
	private SessionFactory session;
	@Override
	public void add(Like likes) {
		// TODO Auto-generated method stub
		this.session.getCurrentSession().save(likes);
	}

	@Override
	public void edit(Like likes) {
		// TODO Auto-generated method stub
		this.session.getCurrentSession().update(likes);
	}

	@Override
	public void delete(int likesId) {
		// TODO Auto-generated method stub
		Like likesDell = (Like)this.session.getCurrentSession().get(Like.class,likesId);
		if(likesDell != null)
		{
			this.session.getCurrentSession().delete(likesDell);
		}
	}

	@Override
	public Like getLike(int likesId) {
		// TODO Auto-generated method stub
		return (Like)this.session.getCurrentSession().get(Like.class,likesId);

	}

	@Override
	public List<Like> getAllLikes() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Like> listLikes = (List<Like>)this.session.getCurrentSession().createQuery("from Like").list();
		return listLikes;
	}

}
