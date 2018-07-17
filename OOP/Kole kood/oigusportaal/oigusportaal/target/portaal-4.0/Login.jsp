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
	<title>Õigusportaal</title>
	</head>
	
	<body>
		<div id="wrapper">
			<!-- include header -->
			<%@include file='/header2.jsp'%>
		
			<div class="page">
							<!--  login  -->
			<div id="login">
				<h2>Logi sisse</h2>
				<form action="<%=request.getContextPath()%>/LoginServlet" method="post">
					<tabel>
					
						<tr>
							<td><input type="text" name="email" placeholder="email@email.com" class="loginInput"><br></td>
						</tr>
							
						<tr>
							<td><input type="password" name="password" placeholder="Parool" class="loginInput"><br></td>
						</tr>
							
					
						<tr>
							<td><input type="submit" class="button" id="loginInputSpecial" value="Logi sisse" /></td>
						</tr>
						<br>		
						<tr>
							<td colspan="2"><a href="<%=request.getContextPath()%>/ForgotPassword.jsp" style="">Unustasid parooli?</a>
							<a href="<%=request.getContextPath()%>/BureauRegister.jsp">Registreeru</a></td>
						</tr>
				</tabel>
				</form>
			</div>
			</div>
			<!-- include footer -->
			<%@include file='/footer.jsp'%>
		</div>
	</body>
</html>