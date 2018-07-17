

<%@ page import="main.login.UserBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="main.details.BureauDetails"%>
<%@ page import="main.details.AttorneyDetails"%>
<%@ page import="main.details.SuccessStoryDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/favicon.ico">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/widgetstyle.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/style.css" />
<style type="text/css">
#header {
	position: relative;
	background: url(images/main_header_bg.png) no-repeat center top;
	height: 150px;
}
</style>
<title>Õigusportaal</title>
<script>
	$(function() {
		$("#accordion").accordion();
	});
</script>
</head>

<body>
	<div id="wrapper">
		<!-- include header -->
		<%@include file='/header.jsp'%>

		<!-- end #menu -->
		<div class="page">
			<%
				UserBean user = (UserBean) (request.getAttribute("user"));
			%>
			<div class="content">
				<h1 class="centerHeading"><%=user.getBureauName()%></h1>
				<div class="entry">
					<div class="viewProfile">
						<table>
							<%
								if (user.getImage() != null) {
							%>
							<tr>
								<td colspan="2"><img src="<%=user.getImage()%>" class="profilepic" alt="Pilti ei leitud!"></td>
							</tr>
							<%
								}
							%>
							<tr>
								<td><label for="profileBureauRegistycode"><b>Registrikood:</b></label></td>
								<td><%=user.getRegistryCode()%></td>
							</tr>

							<tr>
								<td><label for="profileBureauPhone"><b>Telefon:</b></label></td>
								<td><%=user.getPhone()%></td>
							</tr>

							<tr>
								<td><label for="profileBureauEmail"><b>E-mail:</b></label></td>
								<td><a href="mailto:<%=user.getEmail()%>"><%=user.getEmail()%></a></td>
							</tr>

							<tr>
								<td><label for="profileBureauAddress"><b>Aadress:</b></label></td>
								<td><%=user.getStreet()%></td>
							<tr>
								<td><label for="profilePostalCode"><b>Postiindeks:</b></label>
								<td><%=user.getPostalcode()%>
							</tr>

							<tr>
								<td><label for="profileRegion"><b>Regioon:</b></label>
								<td><%=user.getRegionName()%></td>
							</tr>

							<tr>
								<td><label for="profileCounties"><b>Maakond:</b></label></td>
								<td><%=user.getCountyName()%></td>
							</tr>

							<tr>
								<td><label for="profileCities"><b>Linn:</b></label>
								<td><%=user.getCityName()%></td>
							</tr>

							<tr>
								<td><label for="profileBureauAveragePrize"><b>Keskmine tunnihind:</b></label></td>
								<td><%=user.getAveragePrice()%></td>
							</tr>
							<tr>
								<td><b>Valdkonnad:</b></td>


								<%
									ArrayList<String> fields = (ArrayList<String>) request.getAttribute("fields");
								%>

								<td>
									<%
										for (int i = 0; i < fields.size(); i++) {
									%> <%=fields.get(i)%><br> <%
 	}
 %>
								</td>
							</tr>
						</table>
					</div>
					<%
						ArrayList<AttorneyDetails> att = (ArrayList<AttorneyDetails>) request.getAttribute("attorneys");
					%>
					<div class="viewAttorneys">
						<table>
							<tr>
								<td><h2 class="noMargins">Juristid:</h2></td>
							</tr>


							<%
								for (int i = 0; i < att.size(); i++) {
							%>
							<tr>
								<td>
									<div class="viewpic">
										<%
											if (att.get(i).getPicturePath() != null) {
										%>
										<img src="<%=att.get(i).getPicturePath()%>" class="profilepic" alt="Pilti ei leitud!">
										<%
											}
										%>
										<h3><%=att.get(i).getName()%></h3>
										<p>
											<a href="mailto:<%=att.get(i).getEmail()%>"><%=att.get(i).getEmail()%></a>
										</p>
									</div>
								</td>
							</tr>

							<%
								}
							%>
						</table>
					</div>
				</div>
				<%
					ArrayList<SuccessStoryDetails> stor = (ArrayList<SuccessStoryDetails>) request.getAttribute("successStories");
				%>
				<div class="stories">
					<p>
					<h2 class="centerHeading">Edulood:</h2>
					</p>
					<div class="viewStories" id="accordion">

						<%
							for (int i = 0; i < stor.size(); i++) {
						%>
						<h3><%=stor.get(i).getParticipants()%></h3>
						<div>
							<p>
								<b>Kuupäev: </b>
								<%=stor.get(i).getDateString()%></p>
							<p>
								<b>Valdkond: </b>
								<%=stor.get(i).getFieldName()%></p>
							<p>
								<b>Kokkuvõte: </b>
								<%=stor.get(i).getConclusion()%></p>
							<%if (stor.get(i).getReference() != null){ %>
							<p>
								<b>Viide: </b> <a href="<%=stor.get(i).getReference()%>">Vaata</a>
							</p>
							<%} %>
							<%if (stor.get(i).getFilepath()!= null){ %>
							<p>
								<b>Fail:</b> <a href="<%=stor.get(i).getFilepath()%>">Lae alla</a>
							</p>
							<%} %>
						</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>


		<!-- include footer -->
		<%@include file='/footer.jsp'%>
	</div>
</body>
</html>