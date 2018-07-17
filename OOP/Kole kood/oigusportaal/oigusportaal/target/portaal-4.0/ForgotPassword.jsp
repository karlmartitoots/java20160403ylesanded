<% response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
// Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
   response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
// Set standard HTTP/1.0 no-cache header.  
    response.setHeader("Pragma", "no-cache");  
    %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/favicon.ico">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/style.css" />
	<style type="text/css">
	#header {
	position: relative;
	background: url(images/main_header_bg.png) no-repeat center top;
	height:150px;
	}
	</style>
		<title>�igusportaal</title>
		
	</head>
	
	<body>
		<div id="wrapper">
			<!-- include header -->
			<%@include file='/header2.jsp'%>
		
			<div class="page">
				<div class="content4">
					<h2>Sisesta oma e-maili aadress</h2>
					<p>Sisesta oma e-maili aadress, et saaksime saata sulle sinu uue parooli</p>
					<form action="<%=request.getContextPath()%>/LostPassServlet" method="get" id="registration_form2">
						E-mail: <input type="text" name="email"><br>
				<input type="submit" class="button" value="Saada e-mail" id="registering"/>
					</form>
				</div>
			</div>
			<!-- include footer -->
			<%@include file='/footer.jsp'%>
		</div>
	</body>
</html>