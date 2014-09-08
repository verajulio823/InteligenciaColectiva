<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.unsa.PhotoSharing.Business.SearchManager"%>

<%
    SearchManager manager = new SearchManager();
	String valor = request.getParameter("title");
 	List<String> result = manager.getSearchResults(valor);
 	boolean found = !result.isEmpty();
 	if(found)
 	{
 	    for(int i = 0; i < result.size(); i++)
 	    {
 	    	out.println(result.get(i));
 	    }   
 	 }else
 	 {
 	    out.println("<li>No hubo resultados<li>");
 	 }
%>