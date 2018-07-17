<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/favicon.ico">
<title>Õigusportaal</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<div id="wrapper">
		<!-- include header -->
		<%@include file='/header2.jsp'%>
		
		<!-- end #menu -->
		<div class="page">
			<div class="content">
				<div class="regSuccess">
					<p>Teie e-mailiga konto on juba loodud. Palun logige sisse. Kui te olete oma parooli unustanud, vajutage <a href="<%=request.getContextPath()%>/ForgotPassword.jsp">siia</a></p>
					</div>
				</div>
		</div>
		<!-- end #content -->
		<div style="clear: both;">&nbsp;</div>
		<!-- end #page -->
		<!-- include footer -->
		<%@include file='/footer.jsp'%>
		<!-- end #footer -->
	</div>
</div>
</body>

</html>