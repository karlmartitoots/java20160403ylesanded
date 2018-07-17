
<%
	UserBean currentUser = (UserBean) (session
			.getAttribute("currentSessionUser"));
	session.setMaxInactiveInterval(9000);
	response.setDateHeader("Expires", 0);
	// Set standard HTTP/1.1 no-cache headers.  
	response.setHeader("Cache-Control",
			"no-store, no-cache, must-revalidate");
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
<%@ page import="main.details.SuccessStoryDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/favicon.ico">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="js/jquery.Char.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/widgetstyle.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/style.css" />
	<style type="text/css">
	#header {
	position: relative;
	background: url(images/main_header_bg.png) no-repeat center top;
	height:150px;
	}
	</style>
<script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  

 </script>
 <script type="text/javascript">
	$(document).ready(function(){			
		$('#countable1').jqEasyCounter({
			'maxChars': 300,
			'maxCharsWarning': 280
		});	
});
</script>
<title>Õigusportaal</title>


</head>

<body>
	<div id="wrapper">
		<!-- include header -->
		<%@include file='/header2.jsp'%>
		<%
			SuccessStoryDetails story = (SuccessStoryDetails) request
					.getAttribute("story");
			boolean isNew = false;
			if (story == null) {
				isNew = true;
			}
		%>

		<!-- end #menu -->
		<div class="page">
			<form action="<%=request.getContextPath()%>/BureauProfileServlet" method="post" id="back">
			<input type="submit" class="button" value="Tagasi" id="backButton">
			</form>
			<h1>Redigeerimine</h1>
			<% if (request.getAttribute("fail") != null){ %>
			<div class="failure">
			<h3>Faili ei salvestatud! See peab olema .odt, .pdf, .doc või .docx faililaiendiga!</h3>
			</div>
			<% } %>
			<% if (!isNew) { %>
			<form action="<%=request.getContextPath()%>/ImageUploadServlet" method="post" enctype="multipart/form-data" id="upload">
			<table>
			<tr id="logo">			
			  	<td><p>Lae üles uus dokument: <input type="file" name="img" ></p><br>
			  	<input type="submit" class="button" value="Lae üles" id="upladIt">
			  	<input type="hidden" name="generalId" value="<%= story.getSuccessStoryId()%>">
			  	<input type="hidden" name="action" value="3">						  	
			  	</td>
			  	<% if (story.getFilepath() != null) { %>
			  	<td><a href="<%=story.getFilepath()%>">Lae dokument alla</a></td>	
			  	<% } %>					  	
			 </tr>
			 </table>
			 </form>
			 <% } else { %>
			 <p> Faili saate lisada peale eduloo lisamist. </p>
			 <% } %>
			<div class="entry">
				<form
					action="<%=request.getContextPath()%><%if (isNew) {%>/StoryAddServlet<%} else {%>/StoryEditServlet<%}%>"
					method="post" id="edit_form">
					<table id="storyProfileTable">
						<tr>
							<td><label for="storyParticipants">Osalejad</label></td>
							<td><input type="text" autofocus id="newParticipants"
								name="newParticipants"
								value="<%if (!isNew) {%><%=story.getParticipants()%><%}%>"></td>
						</tr>
						<tr>
							<td><label for="storyReference">Viide:</label></td>
							<td><input type="text" id="newReference" name="newReference"
								value="<%if (!isNew) {%><%=story.getReference()%><%}%>"></td>
						</tr>						
						<tr>
							<td><label for="storyDate">Kuupäev:</label></td>
							<td><input type="text" name="newDate" id="datepicker" value="<%if (!isNew) {%><%=story.getDate()%><%}%>"></td>
						</tr>
						
						<tr>
							<td><label for="storyConclusion">Kokkuvõte:</label>
							<td>
							<textarea id="countable1" rows="4" cols="50" maxlength="300" name="newConclusion"><%if (!isNew) {%><%=story.getConclusion()%><%}%></textarea>
							</td>
						</tr>
						</table>
						<div id="fieldsCheckBox">
	                        <p><b>Valdkonnad</b></p>
	                        <table>
	                        <tr>
	                        <td>
	                        <%
	                                ArrayList<String> fields = (ArrayList<String>) request
	                                        .getAttribute("fieldRows");
	                        
	                                for (int i=0; i<fields.size(); i++){
	                        %>
	                        
	                        <%= fields.get(i) %>
	                        <% if ((i+1) == (fields.size()/2)) { %>
	                        </td>
	                        <td>
	                        <% }} %>
	                        </td>        
	                        </tr>                                                                  
	                        </table>
	                	</div>
											
					<input type="hidden" name="bureauId"
						value="<%=request.getParameter("bureauId")%>" /> <input type="hidden"
						name="storyId" value="<%if (!isNew) {%><%=story.getSuccessStoryId()%><%}%>" /> <input
						type="submit" class="button" value="<%if (isNew) {%>Lisa<%} else%>Muuda<%;%>"
						id="addEdit" />
						<% if (!isNew) {%>
						 <input type="submit" class="button" value="Kustuta" id="delete"
						onclick="form.action='<%=request.getContextPath()%>/StoryDeleteServlet';">
						<%} %>
				</form>
			</div>
		</div>
		<!-- include footer -->
		<%@include file='/footer.jsp'%>
	</div>




</body>
</html>