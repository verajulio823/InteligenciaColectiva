<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.unsa.PhotoSharing.Controller.*" %>
<%@ page import="com.unsa.PhotoSharing.persistence.entity.*" %>
<html>
	<head>
		<title>Buscar usuarios y fotos</title>
		
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300italic,600|Source+Code+Pro" rel="stylesheet" />
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



            function search()
            {

                var title=$("#search").val();

                if(title!="")
                {
                  $("#result").html("<img src='images/ajax-loader.gif'/>");
                   $.ajax({
                      type:"post",
                      url:"getdatasearch.jsp",
                      data:"title="+title,
                      success:function(data){
                          $("#result").html(data);
                       }
                    });
                }
                
	           }

            $("#button").click(function(){
            	 search();
            });

            $('#search').keyup(function(e) {
                  search();

            });
       });
		    
		</script>	
		
		
		
		<!-- script para la busqueda con jQuery y ajax -->
                
		
		<style type="text/css">
            #container{
               width:800px;
               margin:0 auto;
            }

            #search{
               width:700px;
               padding:10px;
            }


            #result ul{
            	margin-left:-40px;
            }

            ul li{
            	border-bottom: dotted 1px black;
              	height: 20px;
            }

            #result li:hover{
            	background: #a3caf6;
            }

            #result li a{
	           margin: 0px;
	           display: block;
	           width: 100%;
	           height: 100%;
               text-decoration: none;
               font-size: 18px;
            }
  	    </style> 
  	    
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
				<div id="sidebar">	
				
				
					<div id="container">
				     <input type="text" id="search" placeholder="Search Users or Photo Tags..."/>
				     <style>
				     
input[type=button] {
  padding: 0 18px;
  height: 29px;
  font-size: 12px;
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
				     <input type="button" id="button" value="Search" />
				     <ul id="result">
				     
				     </ul>
					</div>
			
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
