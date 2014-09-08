package com.unsa.PhotoSharing.WS;

import java.util.List;

import com.unsa.PhotoSharing.persistence.Dao.TagsDao;
import com.unsa.PhotoSharing.persistence.Dao.TagsDaoImpl;

public class WS 
{
	public List<String> servicio (String tag)
	{
		TagsDao tagsDao = new TagsDaoImpl();
		List<String> result = tagsDao.getUrlsForTag(tag);
		for (String r : result)
			System.out.println(r);
		return result;
	}
}
