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
	<div id="wrapper">
		<!-- Päis -->
		<!-- include header -->
		<%@include file='/header2.jsp'%>
		
		<!-- content -->
		<div >
		
			<!--  login  -->
			<div id="login" style="width:250px; padding-left:50px; float:left;  margin-top:100px; margin-right:25px;background: #f0f0f0;">
				<h2>Logi sisse</h2>
				<form action="<%=request.getContextPath()%>/LoginServlet" method="post">
					<tabel>
					
						<tr>
							<td><input type="text" name="email" value="Email" style="height:25px; width:200px;"><br></td>
						</tr>
							
						<tr>
							<td><input type="password" name="password" value="Password" style="height:25px; width:200px;"><br></td>
						</tr>
							
						<tr>
							<td>
								<select name="userCategories" class="citySelect" style="height:25px; width:205px;">
									<option value="0" class="static">--Select User Type--</option>
									<option value="1" class="sub_1">Admin</option>
									<option value="2" class="sub_2">Bureau</option>
								</select>
							</td>
						</tr>
					
						<tr>
							<td><input type="submit" class="button" value="Logi sisse" id="registering" style="height:25px; width:205px; margin-left:0px;"/></td>
						</tr>
								
						<tr>
							<td colspan="2"><a href="<%=request.getContextPath()%>/ForgotPassword.jsp">Unustasid parooli?</a>
							<a href="<%=request.getContextPath()%>/BureauRegister.jsp">Registreeru</a></td>
						</tr>
				</tabel>
				</form>
			</div>
			
			<!-- Middle line 
			<div id="line"  style="margin-top:20px; height:350px; width:10px; float:left; background-image:url('./images/register_shadow.png');">
			</div>-->
			
			<!-- Register -->
			<div id="registering" style="width:407px; padding-left:50px; background: #f0f0f0; height:450px; float:left;">
				<h1>Registreeru</h1>
				<form action="BureauRegistrationServlet" method="get" onsubmit="return validateForm()">
					<table>
						<tr>
					  		<td><label for="registerEmail">E-mail:</label></td>
							<td><input type="text" name="email" id='registerEmail' onkeyup="validateEmail()"/></td>
							<td><span id ="emailErrorMessage"></span></td> 
						</tr>
							
						<tr>
							<td><label for="registerEmailConfirmation">Kinnita E-mail:</label></td>
							<td><input type="text" name="registerEmailConfirmation" id="registerEmailConfirmation" onkeyup="confirmEmail()"/></td>
							<td><span id ="confirmEmailMessage"></span> </td>
						</tr>
							
						<tr>
							<td><label for="registerPassword">Parool:</label></td>
							<td><input type="password" name="password" id="registerPassword" onkeyup="validatePassword()" /></td>
							<td><span id = "passwordMessage"></span> </td>
						</tr>
							
						<tr>
							<td><label for="registerPasswordConfirmation">Parool uuesti:</label></td>
							<td><input type="password" name="register_password_confirmation" id="registerPasswordConfirmation" onkeyup="confirmPassword()" /></td>
							<td><span id="confirmPasswordError"></span> </td>
						</tr>
							
						<tr>
							<td><label for="registerBureauName">Büroo nimi:</label></td>
							<td><input type="text" name="bureauname" id="registerBureauName" onkeyup="validateBureauName()"/></td>
							<td><span id="bureauNameMessage"></span> </td>
						</tr>
							
						<tr>
							<td><label for="registerRegcode">Registrikood:</label></td>
							<td><input type="text" name="regcode" id="registerRegcode" onkeyup="validateRegistrycode()"/></td>
							<td><span id="regcodeMessage"></span> </td>
						</tr>
							
						<tr>
							<td><label for="registerStreetAddress">Tänav:</label></td>
							<td><input type="text" name="street_address" id="registerStreetAddress" onkeyup="validateStreetAddress()" /></td>
							<td><span id="streetAddressMessage"></span> </td>
						</tr>
							
						<tr>
							<td><label for="registerPostalcode">Postiindeks:</label></td>
							<td><input type="text" name="postal_code" id="registerPostalcode" onkeyup="validatePostalCode()"/></td>
							<td><span id="postalcodeMessage"></span> </td>
						</tr>
							
						<tr>
							<td><label for="registerPhone">Telefoninumber:</label></td>
							<td><input type="text" name="phone" id="registerPhone" onkeyup="validatePhoneNumber()"/></td>
							<td><span id="phoneMessage"></span> </td>
						</tr>
							
						<tr>
							<td><label for="registerRegion">Regioon:</label></td>
							<td>
								<select name="regions" id="registerRegion" onchange="validateRegion()" >
									<option value="Region">-- Vali regioon --</option>
									<option value="Pohja-Eesti">Põhja-Eesti</option> <!-- /*Harjumaa, , Järvamaa, Raplamaa */ -->
  									<option value="Ida-Eesti">Ida-Eesti</option>    <!-- Ida-Virumaa, Lääne-Virumaa, Jõgevamaa -->
  									<option value="Laane-Eesti">Lääne-Eesti</option> <!--  Läänemaa, Hiiumaa, Saaremaa, Pärnumaa -->
  									<option value="Louna-Eesti">Lõuna-Eesti</option> <!--  Viljandimaa, Tartumaa, Põlvamaa, Valgamaa, Võrumaa -->
								</select>
							</td>
							<td><span id="registerRegionMessage"></span></td>
						</tr>
							
						<tr>
							<td><label for="registerCounty">Maakond:</label></td>
							<td>
								<select name="counties" id="registerCounty" onchange="validateCounty()" >
									<option value="County">-- Vali Maakond--</option>
								</select>
							</td>
							<td><span id="registerCountyMessage"></span></td>
						</tr>
							
						<tr>
							<td><label for="registerCity">Linn:</label></td>
							<td>
								<select name="cities" id="registerCity" onblur="validateForm()" onchange="validateCity()" >
									<option value="City">-- Vali Linn --</option>
								</select>
							</td>
							<td><span id = "registerCityMessage"></span></td>
						</tr>								
					
						<tr>
							<td><input type="submit" class="button" value="Registreeru" name="submit" id="Registreeru" /></td>
							<td></td>
							<td><span id="confirmInput"></span></tr>
						<tr>
					</table>
				</form>
			</div>
		
		</div>
		<!-- include footer -->
		<%@include file='/footer.jsp'%>
	</div>
</body>
</html>