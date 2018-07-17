<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Õigusportaal</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/style.css" />
	<style type="text/css">
	#header {
	position: relative;
	background: url(images/main_header_bg.png) no-repeat center top;
	height:150px;
	}
	</style>
</head>
<body>
	<div id="wrapper" >
		<!-- include header -->
		<%@include file='/header2.jsp'%>
		
		<div class="page">
			<p>Sellist kasutajat ei leitud või sisestati vale parool. Palun proovige uuesti või <a href="<%=request.getContextPath()%>/BureauRegister.jsp">registreeruge.</a></p>
		</div>
		
		<!-- include footer -->
		<%@include file='/footer.jsp'%>
</body>
</html>