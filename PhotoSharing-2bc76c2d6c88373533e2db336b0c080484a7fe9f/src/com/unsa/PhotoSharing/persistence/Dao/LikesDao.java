package com.unsa.PhotoSharing.persistence.Dao;

import java.util.List;

import com.unsa.PhotoSharing.persistence.entity.Like;

public interface LikesDao {
	public void add(Like likes);
	public void edit(Like likes);
	public void delete(int likesId);
	public Like getLike(int likesId);
	public List<Like> getAllLikes();
}
