

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.unsa.PhotoSharing.Controller.*" %>
<%@ page import="com.unsa.PhotoSharing.persistence.entity.*" %>
<%@ page import="com.unsa.PhotoSharing.Business.*" %>

<%@ page import="java.util.*" %>


<html>
	<head>
		<title>Página de inicio</title>
		
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300italic,600|Source+Code+Pro" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		<script src="js/modernizr.custom.js"></script>
		<!--[if lte IE 8]><script src="html5shiv.js" type="text/javascript"></script><![endif]-->
		<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
		<script src="js/jquery.dropotron.min.js"></script>		
		<script src="skel.min.js">
		{
			prefix: 'css/style',
			preloadStyleSheets: true,
			resetCSS: true,
			boxModel: 'border',
			grid: { gutters: 30 },
			breakpoints: {
				wide: { range: '1200-', containers: 1140, grid: { gutters: 50 } },
				narrow: { range: '481-1199', containers: 960 },
				mobile: { range: '-480', containers: 'fluid', lockViewport: true, grid: { collapse: true } }
			}
		}
		</script>
		<script>
			$(function() {

				// Note: make sure you call dropotron on the top level <ul>
				$('#main-nav > ul').dropotron({ 
					offsetY: -10 // Nudge up submenus by 10px to account for padding
				});

			});
		</script>
		<script>
			// DOM ready
			$(function() {
    
			// Create the dropdown base
			$("<select />").appendTo("nav");
   
			// Create default option "Go to..."
			$("<option />", {
				"selected": "selected",
				"value"   : "",
				"text"    : "Menu"
			}).appendTo("nav select");
   
			// Populate dropdown with menu items
			$("nav a").each(function() {
			var el = $(this);
			$("<option />", {
				"value"   : el.attr("href"),
				"text"    : el.text()
			}).appendTo("nav select");
			});
   
			// To make dropdown actually work
			// To make more unobtrusive: http://css-tricks.com/4064-unobtrusive-page-changer/
			$("nav select").change(function() {
				window.location = $(this).find("option:selected").val();
			});
  
			});
		</script>	
		
	</head>
	<body>
	
	<%
	
	Usuario logedUser = null;
	Usuario user = null;
	UserHomeController controller = new UserHomeController(); 
	logedUser = (Usuario)request.getSession().getAttribute("user");
	
	int user_id = 0;
	boolean showPage = true;

	if (logedUser == null) 
	{
		showPage = false;
	}
	else
	{
		System.out.println("USUARIO es " + logedUser.getIdUsuario());
		System.out.println("USUARIO es " + logedUser.getNickname());
		try
		{
			user_id = Integer.parseInt(request.getParameter("user_id"));
			user = controller.getUser(user_id);
			System.out.println("USUARIO XXes " + user);
			System.out.println("USUARIO XXes " + logedUser.getIdUsuario());
			System.out.println("USUARIO XXes " + logedUser.getNickname());
			if(user == null) showPage = false;
		}
		catch(Exception e)
		{
			user_id = logedUser.getIdUsuario();
			if(user == null) showPage = false;
		}
	}
	%>
		
	<% if (!showPage) 
		{
			if (logedUser == null)
			{
				%>
				Debes estar logeado para ver el contenido! 
				<a href="index.html">Inicia sesión o registra un nuevo usuario</a><br>
				<% 
			}
			else
			{
				%>
				Página no encontrada! 
				<a href="home.jsp?user_id=<%=logedUser.getIdUsuario()%>">Volver a mi pantalla de inicio</a><br>
				<% 
			}
		} 
		else 
		{
		%>


		<div id="header_container">		
		    <div class="container">
			<!-- Header -->
				<div id="header" class="row">
					<div class="5u">
						<div class="transparent">
							<p style="float: left;"> <img src="<%=user.getProfilePhoto() %>?time=<%=new java.util.Date().getTime() %>" width="130" height="130"> </p>
						
							<h1> &emsp; <a href="home.jsp?user_id=<%= user_id %>">
							<%= (user.getNombre() + " " + user.getApellidos())
							%>
							</a></h1>
							<h2> &emsp; <%="(" + user.getNickname() + ")" %></h2>
						</div>
							
					</div>

					
										
					<nav id="main-nav" class="7u">
						<ul>
							<li><a class="active" href="home.jsp?user_id=<%=logedUser.getIdUsuario()%>">Home</a></li>
							<li><a href="friends.jsp">Mis amigos</a>
							<li><a href="upload.jsp">Subir foto</a></li>
							<li>
								<a href="search.jsp">Buscar</a>
							</li>
							<li><a href="settings.jsp">Ajustes de perfil</a></li>
							<li><a href="LogoutController">Log Out</a></li>
							
						</ul>
					</nav>
				</div>
			</div>	
        </div>		

		<div id="site_content">
			<div class="container">			
			
				<!-- Features -->			
				<div class="row">	
				
				<section class="3u">
				<%
				
				UserHomeManager userManager = new UserHomeManager();
				List<Usuario> amigos = userManager.getAmigos(logedUser);
				boolean isFriend = false;
				System.out.println("USUARIO " + logedUser.getIdUsuario() + " " + user.getIdUsuario());
				for (Usuario u : amigos)
				{
					System.out.println("AMIGO " + u.getIdUsuario());
					if(u.getIdUsuario().equals(user.getIdUsuario()))
						isFriend = true;
				}
				
				if(user.getIdUsuario().equals(logedUser.getIdUsuario()))
					isFriend = true;
				
			%>	
				<style>
			     
				input[type=submit] {
				  padding: 0 22px;
				  height: 29px;
				  font-size: 18px;
				  font-weight: bold;
				  color: #527881;
				  text-shadow: 0 1px #e3f1f1;
				  background: #cde5ef;
				  border: 1px solid;
				  border-color: #b4ccce #b3c0c8 #9eb9c2;
				  border-radius: 16px;
				  outline: 0;
				  -webkit-box-sizing: content-box;
				  -moz-box-sizing: content-box;
				  box-sizing: content-box;
				  background-image: -webkit-linear-gradient(top, #edf5f8, #cde5ef);
				  background-image: -moz-linear-gradient(top, #edf5f8, #cde5ef);
				  background-image: -o-linear-gradient(top, #edf5f8, #cde5ef);
				  background-image: linear-gradient(to bottom, #edf5f8, #cde5ef);
				  -webkit-box-shadow: inset 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.15);
				  box-shadow: inset 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.15);
				}
		     </style>
		     
		     <%
				if(!isFriend)
				{
				%>
					 <br><br>
				     <form method="post" action="AddFriendController">
						<input type="hidden" name="loged_user_id" value="<%=logedUser.getIdUsuario() %>">
						<input type="hidden" name="user_id" value="<%=user.getIdUsuario() %>">
						<input type="submit" id="button" value="Agregar a mis amigos" />
					 </form>
				<%
				}
				else
				{
					if(!user.getIdUsuario().equals(logedUser.getIdUsuario()))
					{
					
					%>
					 <br><br>
				     <input type="submit" id="button" value="<%=user.getNickname() %> ya es tu amigo!" />
					<%	
					}
				}
					%>
						<div id="sidebar">
						
							<section class="12u">
								<h3>Últimas novedades</h3>
								
								<%
									UserHomeManager manager = new UserHomeManager();
									List<String> userLastNews = manager.getLastNews(user);

								%>
								<ul>
								
								<%
									for(String s : userLastNews)
									{
								%>
									<li> <%=s %> </li>
								<%
									}
								%>
								</ul>
								
							</section>
						</div>
					</section>			
													
					<section class="9u">				
						
						<!-- Banner -->								
						<div id="banner">
							<a href="#"><img src="images/banner.png" alt="banner image" /></a>
						</div>
						

						
						<div id="grid-gallery" class="grid-gallery">
							<section class="grid-wrap">
								<ul class="grid">
									
									<% 
										
										List<Foto> userFotos = manager.getUsuarioFotos(user);
										for (Foto f : userFotos)
										{
											
									%>
											<li>
											<figure>
												<img src="<%=f.getPhotoUrl()%>" width="400" height="260" alt="<%= f.getIdFoto()%>"/>
												<figcaption><p><%=f.getDescripcion() %></p></figcaption>
											</figure>
										</li>
										
									<%
										}
									%>
									
									
								</ul>
							</section>
							
							<section class="slideshow">
							
							<ul style="overflow: auto">
							<% 
										
										
					         for (Foto f : userFotos)
							{
									%>
							
							<li>
							<figure>
								<figcaption>
									<h3><%
									out.println( "<a href=viewPhotoId.jsp?foto_id=" + f.getIdFoto() + ">" + 
												f.getDescripcion() + "</a>");
									%></h3>

								
								</figcaption>
								<img src="<%=f.getPhotoUrl()%>" alt="<%= f.getIdFoto()%>"/>
								
								<%
						
								CommentManager commentManager = new CommentManager();
								List<Comentario> comentarios = commentManager.getComentarios(f);
							System.out.println(comentarios);
							out.println("<br><br><p style=\"font-size: large; font-weight: bold \"> Comentarios </p>");
								for (Comentario c : comentarios)
								{
									Usuario tmpUser = c.getUsuario();
									String comment = "<p> <a href=home.jsp?user_id=" + tmpUser.getIdUsuario() + 
													 " style=\"color: rgb(0,0,255); font-weight: bold\">" + 
														tmpUser.getNickname() + "</a>: " + c.getComentario() +
														"<br> <em style=\"font-size:x-small\"> Escrito el" + c.getComentDate().toString() + " </em> <p>";
									out.println(comment);
								}
						    %>
							</figure>
							
							
						    </li>
						    <%} %>
						    </ul>
						    <nav>
									<span class="icon nav-prev"></span>
									<span class="icon nav-next"></span>
									<span class="icon nav-close"></span>
							</nav>
							<div class="info-keys icon">Navigate with arrow keys</div>
						</section><!-- // slideshow -->
						    
						    
						</div>
								
				</section>
					

				</div>
			</div>
        </div>		
		
		<script src="js/imagesloaded.pkgd.min.js"></script>
		<script src="js/masonry.pkgd.min.js"></script>
		<script src="js/classie.js"></script>
		<script src="js/cbpGridGallery.js"></script>
		<script>
			new CBPGridGallery( document.getElementById( 'grid-gallery' ) );
		</script>
		
		
			<br><div class="container">			
			<!-- Footer -->
				<footer>
					
				<%
				LuceneTextAnalyzer lucene = new LuceneTextAnalyzer();
				
				out.println(lucene.getTags(logedUser));
				%>
				
				
					<p><a class="active" href="home.jsp?user_id=<%=logedUser.getIdUsuario()%>">Home</a> | <a href="upload.jsp">Subir foto</a> | <a href="settings.jsp">Ajustes de perfil</a> | <a href="LogoutController">Log Out</a>
					<p>Copyright &copy; UNSA | Jan Llerena | Julio Vera | Jhon Monroy<br></p>
				</footer>		
			</div><br>		
			
	</body>
	<% } %>
</html>
