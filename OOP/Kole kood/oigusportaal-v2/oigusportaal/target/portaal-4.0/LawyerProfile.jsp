
<%
	UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));
	session.setMaxInactiveInterval(9000);
	response.setDateHeader("Expires", 0);
	// Set standard HTTP/1.1 no-cache headers.  
	response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	// Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
	response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	// Set standard HTTP/1.0 no-cache header.  
	response.setHeader("Pragma", "no-cache");
	if (!currentUser.isValid()) {
		System.out.println("Not from this session");
	}
	//	if (!currentUser.isValid() || currentUser.getCategory() == null ){ 
	//		pageContext.forward("Login.jsp");
	//	}
%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@ page import="main.details.BureauDetails"%>
<%@ page import="main.login.UserBean"%>
<%@ page import="main.details.AttorneyDetails"%>
<%@ page import="main.details.SuccessStoryDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/favicon.ico">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/style.css" />
<style type="text/css">
#header {
	position: relative;
	background: url(images/main_header_bg.png) no-repeat center top;
	height: 150px;
}
</style>
<script src="./js/LawyerProfile.js" type="text/javascript"></script>
<title>Õigusportaal</title>


</head>

<body>
	<div id="wrapper">
		<!-- include header -->
		<%@include file='/header2.jsp'%>
		<%
			AttorneyDetails attorney = (AttorneyDetails) request.getAttribute("attorney");
			boolean isNew = false;
			if (attorney == null) {
				isNew = true;
			}
		%>

		<!-- end #menu -->
		<div class="page">
			<form action="<%=request.getContextPath()%>/BureauProfileServlet" method="post" id="back">
				<input type="submit" class="button" value="Tagasi" id="backButton">
			</form>
			<h1>Redigeerimine</h1>
			<div class="entry">
				<%
					if (request.getAttribute("fail") != null) {
				%>
				<div class="failure">
					<h3>Pilti ei salvestatud! See peab olema .png, .jpg, .jpeg või .gif faililaiendiga!</h3>
				</div>
				<%
					}
				%>
				<%
					if (!isNew) {
				%>
				<form action="<%=request.getContextPath()%>/ImageUploadServlet" method="post" enctype="multipart/form-data" id="upload">
					<table>
						<tr id="logo">
							<td><p>
									Lae üles uus logo: <input type="file" name="img">
								</p> <br> <input type="hidden" name="generalId" value="<%=attorney.getAttorneyId()%>"> <input type="hidden" name="action" value="2"> <input type="submit" class="button" value="Lae üles" id="upladIt"></td>
							<%
								if (attorney.getPicturePath() != null) {
							%>
							<td><img src="<%if (!isNew) {%><%=attorney.getPicturePath()%><%}%>" class="profilepic" alt="Pilti ei leitud"></td>
							<%
								}
							%>
						</tr>
					</table>
				</form>
				<%
					} else {
				%>
				<p>Pildi saate lisada peale juristi lisamist</p>
				<%
					}
				%>
				<form action="<%=request.getContextPath()%><%if (isNew) {%>/LawyerAddServlet<%} else {%>/LawyerEditServlet<%}%>" method="post" id="edit_form" name="edit_form" onsubmit="return validateForm()">
					<table id="lawyerProfileTable">
						<tr>
							<td><label for="lawyerName">Nimi:</label></td>
							<td><input type="text" id="newName" name="newName" onkeyup="validateLawyerName()" autocomplete="off" value="<%if (!isNew) {%><%=attorney.getName()%><%}%>"></td>
							<td><span id="lawyerNamePicture"></span></td>
							<td width="5"></td>
							<td class="errorMessage"><span id="lawyerNameMessage"></span></td>
						</tr>

						<tr>
							<td><label for="lawyerEmail">E-mail:</label></td>
							<td><input type="text" id="newEmail" name="newEmail" onkeyup="validateEmail()" autocomplete="off" value="<%if (!isNew) {%><%=attorney.getEmail()%><%}%>"></td>
							<td><span id="emailErrorPicture"></span></td>
							<td width="5"></td>
							<td class="errorMessage"><span id="emailErrorMessage"></span></td>
						</tr>

						<tr>
							<td>
								<%
									if (!isNew) {
								%>
								<input type="submit" class="button" value="Kustuta" id="delete" onclick="form.action='<%=request.getContextPath()%>/LawyerDeleteServlet';"> <% } %>
							</td>
							<td><input type="hidden" name="bureauId" value="<%=request.getParameter("bureauId")%>" /> <input type="hidden" name="attorneyId" value="<%if (!isNew) {%><%=attorney.getAttorneyId()%><%}%>" /> <input type="submit" class="button" value="<%if (isNew) {%>Lisa<%} else%>Muuda<%;%>" id="addEdit" /></td>
							<td><span id="confirmInput"></span></td>							
						</tr>
					</table>


				</form>
			</div>
		</div>
		<!-- include footer -->
		<%@include file='/footer.jsp'%>
	</div>




</body>
</html>