<%

String email = "admin@admin.com";
try{
UserBean currentUser = (UserBean) (session.getAttribute("user"));
if (!currentUser.getEmail().equals(email)  ){ 
	pageContext.forward("Login.jsp");
}

session.setMaxInactiveInterval(9000);
response.setDateHeader("Expires", 0);  
// Set standard HTTP/1.1 no-cache headers.  
   response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
// Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
   response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
// Set standard HTTP/1.0 no-cache header.  
    response.setHeader("Pragma", "no-cache");  
if (!currentUser.isValid() ){ 
	pageContext.forward("Login.jsp");
}
 %>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@ page import="main.details.BureauDetails"%>
<%@ page import="main.login.UserBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
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
	height:150px;
	}
	</style>
		<script src="js/jquery.js"></script>
		<script src="js/functions.js"></script>
		<title>Õigusportaal</title>
</head>
	
<body>
	
	<%
		ArrayList<BureauDetails> br = (ArrayList<BureauDetails>) request
				.getAttribute("bureau");
	%>
	
	<div id="wrapper">
		<!-- inlude header -->
		<%@include file='/header2.jsp'%>
		
		
		<div class="page">
			
	
			<div class="page">
			<h2 class="centerHeading">Bürood</h2>
			<table width="95%" id="adminSearchResults">
				<tr height="10%">
				  <td class="firstColumn" align="center">Bureau Name</td>
				  <td class="middleColumn">Status</td>
				  <td class="lastColumn">Manage</td>
				</tr>
				
				<tr>
					<%
						for (int i = 0; i < br.size(); i++) {
					%>

					<td width="20" align="center" class="firstColumn">
						<h1><a href="<%=request.getContextPath()%>/BureauViewServlet?bureauId=<%=br.get(i).getBureauId()%>"><%=br.get(i).getBureauName() %></a></h1>
					</td>
					 
					<td width="100" class="middleColumn">
						<%  String dummy=null;	
							if(br.get(i).active==1)
								dummy="Active";
							else if(br.get(i).active==0)
								dummy="Inactive";
						 %>
						<h3><%=dummy %> </h3>
					</td>
				
					<td width="100" class="lastColumn">				
						<a href="<%=request.getContextPath()%>/DeleteBureauServlet?param=<%=br.get(i).getEmail() %>&paramTwo=<%=br.get(i).getBureauId() %>" ><span style="color:#FE062F;">Delete <%=br.get(i).getBureauName() %></span></a>
				
						<% 	
							if(br.get(i).active==1) {
							dummy="Active";
						%>
						
						<a href="<%=request.getContextPath()%>/ActivateInActivateServlet?param=<%=br.get(i).getActive() %>&paramTwo=<%=br.get(i).getBureauId() %>" ><span style="color:#4C06FE;">Inactivate <%=br.get(i).getBureauName() %></span></a>
				
						<% } %>
				
						<%	if(br.get(i).active==0) {
							dummy="Inactive";
				 		%>		
				 		<a href="<%=request.getContextPath()%>/ActivateInActivateServlet?param=<%=br.get(i).getActive() %>&paramTwo=<%=br.get(i).getBureauId() %>" ><span style="color:#12FE06;">Activate <%=br.get(i).getBureauName() %></span></a>
						<% } %>
						 
					</td>
				</tr>
				<tr>
					<%
						}
					%>

				</tr>
			</table>
			</div>
			<button onclick="location.href='a.jsp'">Logout</button>			
			<!-- include footer -->
			<%@include file='/footer.jsp'%>
		</div>
	</body>

</html>
   <%}catch(Exception e){
	 
	 System.out.println("ERRORR FOR SESSION");
		pageContext.forward("Login.jsp");
 } %>
   