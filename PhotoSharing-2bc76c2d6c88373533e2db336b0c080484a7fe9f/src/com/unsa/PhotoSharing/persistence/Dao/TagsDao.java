package com.unsa.PhotoSharing.persistence.Dao;

import java.util.List;

import com.unsa.PhotoSharing.persistence.entity.Tag;


public interface TagsDao {
	public void add(Tag tags);
	public void edit(Tag tags);
	public void delete(int tagsId);
	public Tag getTag(int tagsId);
	public List<Tag> getAllTags();
	public Tag existsTag(String tag);
	public List<String> getUrlsForTag(String tag);
}
