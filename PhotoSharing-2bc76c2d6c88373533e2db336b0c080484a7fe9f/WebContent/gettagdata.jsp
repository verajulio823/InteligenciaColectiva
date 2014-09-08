<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.unsa.PhotoSharing.Business.SearchManager"%>


<%
	System.out.println("buscando ");

	SearchManager searchManager = new SearchManager();
	String query = request.getParameter("q");
	List<String> tags = searchManager.getTagsResult(query);

	Iterator<String> iterator = tags.iterator();
	while(iterator.hasNext()) 
	{
		String tag = (String)iterator.next();
		out.print(tag+"\n");
	}
%>