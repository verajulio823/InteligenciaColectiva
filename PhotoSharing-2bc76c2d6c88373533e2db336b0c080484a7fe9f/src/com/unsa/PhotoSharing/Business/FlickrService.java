package com.unsa.PhotoSharing.Business;

import java.util.ArrayList;
import java.util.List;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photos.SearchParameters;

public class FlickrService {

	public FlickrService() {
		
	}
	
	 public List<String>FlickServiceTag(String tag) throws Exception{
		 String key="e855fd2ffcc205341aba9c55c436620f";
	     String svr="www.flickr.com";
	     String secret="03e011809f0b154c";
	     List<String> listUrlPhoto = new ArrayList<String>();
	     REST rest=new REST();
	     rest.setHost(svr);	   	             
	     Flickr flickr=new Flickr(key, rest);
	     flickr.setSharedSecret(secret);
	     Flickr.debugStream=false;
	     SearchParameters searchParams=new SearchParameters();
	     searchParams.setSort(SearchParameters.INTERESTINGNESS_DESC);	         
	     searchParams.setText(tag);	    
	     PhotosInterface photosInterface=flickr.getPhotosInterface();
	     PhotoList photoList=photosInterface.search(searchParams,20,1);
	     StringBuffer strBuf=new StringBuffer();     
	             
	     if(photoList!=null){          
	        for(int i=0;i<photoList.size();i++){         
	           Photo photo=(Photo)photoList.get(i);	           
	           listUrlPhoto.add(photo.getThumbnailUrl());
	        }	        
	        System.out.println(strBuf.toString());                  
	     } 
		 return listUrlPhoto; 
		 
	 }

}
