package com.unsa.PhotoSharing.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.unsa.PhotoSharing.Business.UploadManager;
import com.unsa.PhotoSharing.persistence.entity.Usuario;

/**
 * Servlet implementation class UploadController
 */
@WebServlet("/UploadController")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	private static String DATA_DIRECTORY;
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024;

	private UploadManager manager;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	    if (!isMultipart) {
	        return;
	    }

	    manager = new UploadManager();
	    DiskFileItemFactory factory = new DiskFileItemFactory();

	    factory.setSizeThreshold(MAX_MEMORY_SIZE);

	    // Directorio temporal para subir archivos
	    factory.setRepository(new File(System.getProperty("java.io.tmpdir")));


	    // Manager del archivo a subir
	    ServletFileUpload upload = new ServletFileUpload(factory);

	    upload.setSizeMax(MAX_REQUEST_SIZE);

	    try {
	        List items = upload.parseRequest(request);
	        Iterator iter = items.iterator();
	        String imageDescription = "", tag = "";
	        int id = 0;
	        while (iter.hasNext()) {
	            FileItem item = (FileItem) iter.next();

	            if (!item.isFormField()) 
	            {
	            	Usuario currentUser = (Usuario)request.getSession().getAttribute("user");
	            	DATA_DIRECTORY = "" + currentUser.getIdUsuario();
	            	id = currentUser.getIdUsuario();
	            	// Construimos el folder donde se guardará
	            	String uploadFolder = getServletContext().getRealPath("")
	        	            + File.separator + DATA_DIRECTORY;
	        	    
	                String fileName = new File(item.getName()).getName();
	               
	                if(manager.uploadFile(uploadFolder, fileName, id, imageDescription, tag, item)) 		            	
	        	        request.setAttribute("done", "YES");
	                else
	                	request.setAttribute("done", "NO");

	                	
	            }
	            else
	            {
	            	String name = item.getFieldName();
	            	if(name == "user_id")
	            	{
	            		String id_user = item.getString();
	            		DATA_DIRECTORY = id_user;
	            		id = Integer.parseInt(id_user);
	            	}
	            	if(name.equals("descripcion"))
	            	{
	            		imageDescription = item.getString();
	            		System.out.println("descrpcion " + imageDescription);
	            	}
	            	if(name.equals("tag"))
	            	{
	            		tag = item.getString();
	            		System.out.println("tag " + tag);
	            	}
	            }
	        }

	        RequestDispatcher rd = request.getRequestDispatcher("upload.jsp");
	        rd.forward(request, response);

	    } catch (Exception ex) {
	        throw new ServletException(ex);
	    } 

	}

}




