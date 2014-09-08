<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.unsa.PhotoSharing.Controller.*" %>
<%@ page import="com.unsa.PhotoSharing.Business.*" %>
<%@ page import="com.unsa.PhotoSharing.persistence.entity.*" %>
<%@ page import="java.util.*" %>

<html>
	<head>
		<title>Fotos</title>
		
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
	Usuario user = (Usuario)request.getSession().getAttribute("user");
	System.out.println(user.getProfilePhoto() + "---->>>>");
	boolean showPage = true;
	boolean showMessage = false;
	int user_id = 0;
	if (user == null) 	
		showPage = false;
	else
		user_id = user.getIdUsuario();


	%>
		
	<% if (!showPage) 
		{
		%>
		No se puede mostrar la página! 
		<a href="index.html">Inicia sesión o registra un nuevo usuario</a><br>
		<% 
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
							<li><a class="active" href="home.jsp?user_id=<%=user_id%>">Home</a></li>
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
				
													
					<section class="9u">				
						
						<div id="grid-gallery" class="grid-gallery">
							<section class="grid-wrap">
									
									<% 
										ViewPhotoManager fotoManager = new ViewPhotoManager();
										String foto_id = request.getParameter("foto_id");
										Foto foto = fotoManager.getFoto(Integer.parseInt(foto_id));
									%>
									<h3>
									<%
										out.println( "<p><a href=viewPhotoId.jsp?foto_id=" + foto.getIdFoto() + ">" + 
													foto.getDescripcion() + "</a></p>");
									%>
									</h3>
											
											<figure>
												<img src="<%=foto.getPhotoUrl()%>" alt="<%= foto.getIdFoto()%>"/>
												<figcaption><br><p><%=foto.getDescripcion() %></p></figcaption>
											</figure>
										
							</section>
							
						</div>
								
					</section>
					
				<section class="3u">
						<div id="sidebar">
							<section class="12u">
							<%
								CommentManager commentManager = new CommentManager();
								List<Comentario> comentarios = commentManager.getComentarios(foto);
								out.println("<p style=\"font-size: large; font-weight: bold \"> Comentarios </p>");
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
							</section>
							
							<form method="post" action="AddCommentController">
								<input type="hidden" name="user_id" value="<%=user_id %>">
								<input type="hidden" name="foto_id" value="<%=foto.getIdFoto() %>">
								
								<p>
								<textarea rows="4" cols="20" placeholder="Add a comment" name="comment" style="resize:none"></textarea>
								</p>
								
								<input type="submit" value="Añadir comentario" />
							</form>
								
							
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
					

				</div>
			</div>
        </div>		
		
			<div class="container">			
			<!-- Footer -->
				<footer>
					<p><a class="active" href="home.jsp?user_id=<%=user_id%>">Home</a> | <a href="upload.jsp">Subir foto</a> | <a href="settings.jsp">Ajustes de perfil</a> | <a href="LogoutController">Log Out</a>
					<p>Copyright &copy; UNSA | Jan Llerena | Julio Vera | Jhon Monroy</p>
				</footer>		
			</div>		
			
	</body>
	<% } %>
</html>
