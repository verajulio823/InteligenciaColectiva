<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Registro exitoso</title>
 <link rel="stylesheet" href="css/login.css">
</head>

<body>

<%
	String status = request.getParameter("status");
	
	if(status.equals("failed"))
	{
	%>
		<section class="container">
	    <div class="login">
	      <h1>Error en el registro</h1>
		<form id="registered" action="index.html">
	        <p>Un usuario con su email o nickname ya existe!</p>
	        <p class="submit"><input type="submit" id="submit" value="Intentar nuevamente"></p>
	      </form>
	    </div>
	  </section>
	<%
	}
	else
	{
	%>
  <section class="container">
    <div class="login">
      <h1>Registrado en PhotoSharing</h1>
	<form id="registered" action="index.html">
        <p>El registro fue exitoso!</p>
        <p class="submit"><input type="submit" id="submit" value="Volver"></p>
      </form>
    </div>
  </section>
  <%
	}
  %>
</body>
</html>