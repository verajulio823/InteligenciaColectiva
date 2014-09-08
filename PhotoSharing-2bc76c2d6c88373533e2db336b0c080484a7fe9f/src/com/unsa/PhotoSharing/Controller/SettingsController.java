package com.unsa.PhotoSharing.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.unsa.PhotoSharing.Business.UploadManager;
import com.unsa.PhotoSharing.persistence.entity.Usuario;

/**
 * Servlet implementation class SettingsController
 */
@WebServlet("/SettingsController")
public class SettingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private static String DATA_DIRECTORY;
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024;

	private UploadManager manager;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
		        String imageDescription = "";
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
		               
		                if(manager.uploadProfilePhoto(uploadFolder, fileName, currentUser, item)) 		            	
		        	        request.setAttribute("done", "YES");
		                else
		                	request.setAttribute("done", "NO");

		                	
		            }
		            
		        }

		        RequestDispatcher rd = request.getRequestDispatcher("settings.jsp");
		        rd.forward(request, response);

		    } catch (Exception ex) {
		        throw new ServletException(ex);
		    } 
		    
	}

}
