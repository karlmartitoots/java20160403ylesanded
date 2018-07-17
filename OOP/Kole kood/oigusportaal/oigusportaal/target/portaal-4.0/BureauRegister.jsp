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
<script src="./js/RegistrationForm.js" type="text/javascript" ></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
<script type="text/javascript" defer="defer">
		function cascadeSelect(parent, child){
				var childOptions = child.find('option:not(.static)');
					child.data('options',childOptions);
 
				parent.change(function(){
					childOptions.remove();
					child
						.append(child.data('options').filter('.sub_' + this.value))
						.change();
				})
 
				childOptions.not('.static, .sub_' + parent.val()).remove();
 
		}
 
		$(function(){
			cascadeForm = $('.cascade');
			orgSelect = cascadeForm.find('.regSelect');
			terrSelect = cascadeForm.find('.countySelect');
			locSelect = cascadeForm.find('.citySelect');
 
			cascadeSelect(orgSelect, terrSelect);
			cascadeSelect(terrSelect, locSelect);
		});
	</script>

</head>

<!-- Keha -->
<body>
<div id="wrapper">
		<!-- Päis -->
		<!-- include header -->
		<%@include file='/header2.jsp'%>
		
		<!-- end #menu -->
		<div class="page" >
			<div id="registering" >
				<h1>Registreerimine</h1>
					<form action="BureauRegistrationServlet" method="post" class="cascade" id="registration_form" onsubmit="return validateForm()">
					 	<table>
					 		<!-- email -->
						  	<tr>
						 		<td><input type="text" name="email" id='registerEmail' placeholder="email@email.com" onchange="validateEmail()" onkeyup="validateEmail()"/></td>
								<td><span id ="emailErrorPicture" ></span></td> 
							</tr>
							<tr>
								<td class="errorMessage"><span id="emailErrorMessage" ></span></td>
							</tr>
							
							<!-- email confirm -->
							<tr>
								<td><input type="text" name="registerEmailConfirmation" placeholder="Kinnita E-mail!" id="registerEmailConfirmation" onkeyup="confirmEmail()" autocomplete="off"/></td>
								<td><span id ="confirmEmailPicture"></span> </td>
							</tr>
							
							<tr>
								<td class="errorMessage"><span id="confirmEmailMessage" ></span></td>
							</tr>
							
							<!-- password -->
							<tr>
								<td><input type="password" name="password" id="registerPassword" placeholder="Parool" onkeyup="validatePassword()" /></td>
								<td><span id = "passwordPicture"></span> </td>
							</tr>
							
							<tr>
								<td class="errorMessage"><span id="passwordMessage" ></span></td>
							</tr>
							
							<!-- password confirm -->
							<tr>
								<td><input type="password" name="register_password_confirmation" placeholder="Parool uuesti" id="registerPasswordConfirmation" onkeyup="confirmPassword()" /></td>
								<td><span id="confirmPasswordPicture"></span> </td>
							</tr>
							
							<tr>
								<td class="errorMessage"><span id="confirmPasswordMessage" ></span></td>
							</tr>
							
							<!-- Bureau name -->
							<tr>
								<td><input type="text" name="bureauname" id="registerBureauName" placeholder="Büroo nimi" onkeyup="validateBureauName()" autocomplete= "off"/></td>
								<td><span id="bureauNamePicture"></span> </td>
							</tr>
							
							<tr>
								<td class="errorMessage"><span id="bureauNameMessage" ></span></td>
							</tr>
							
							<!-- registrycode -->
							<tr>
								<td><input type="text" name="regcode" id="registerRegcode" placeholder="Registrikood" onkeyup="validateRegistrycode()" autocomplete="off"/></td>
								<td><span id="regcodePicture"></span> </td>
							</tr>
							
							<tr>
								<td class="errorMessage"><span id="regcodeMessage"></span></td>
							</tr>
	
							<!-- address -->
							<tr>
								<td><input type="text" name="street_address" id="registerStreetAddress"  placeholder="Aadress" onkeyup="validateStreetAddress()" autocomplete="off"/></td>
								<td><span id="streetAddressPicture"></span></td>
							</tr>
							
							<tr>
								<td class="errorMessage"><span id="streetAddressMessage" ></span></td>
							</tr>
							
							<!-- postalcode -->
							<tr>
								<td><input type="text" name="postal_code" id="registerPostalcode" placeholder="Postiindeks" onkeyup="validatePostalCode()" autocomplete="off"/></td>
								<td><span id="postalcodePicture"></span> </td>
							</tr>
							
							<tr>
								<td class="errorMessage"><span id="postalcodeMessage" ></span></td>
							</tr>
							
							<!-- Phone -->
							<tr>
								<td><input type="text" name="phone" id="registerPhone" placeholder="Telefoninumber" onkeyup="validatePhoneNumber()" autocomplete="off"/></td>
								<td><span id="phonePicture"></span> </td>
							</tr>
							
							<tr>
								<td class="errorMessage"><span id="phoneMessage" ></span></td>
							</tr>
							
							<!-- Region -->
							<tr>
								<td>
									<select name="regions" id="registerRegion" class="regSelect" placeholder="Regioon" onchange="validateRegion()" >
										<option value="0">-- Vali regioon --</option>
										<option value="1">Põhja-Eesti</option> <!-- /*Harjumaa, , Järvamaa, Raplamaa */ -->
	 										<option value="2">Ida-Eesti</option>    <!-- Ida-Virumaa, Lääne-Virumaa, Jõgevamaa -->
	 										<option value="3">Lääne-Eesti</option> <!--  Läänemaa, Hiiumaa, Saaremaa, Pärnumaa -->
	 										<option value="4">Lõuna-Eesti</option> <!--  Viljandimaa, Tartumaa, Põlvamaa, Valgamaa, Võrumaa -->
									</select>
								</td>
								<td><span id="registerRegionPicture"></span></td>
							</tr>
							
							<tr>
								<td class="errorMessage"><span id="registerRegionMessage" ></span></td>
							</tr>
							
							<!-- County -->
							<tr>
								<td>
									<select name="counties" id="registerCounty" class="countySelect" placeholder="Maakond" onchange="validateCounty()" >
										<option value="0" class="static">--Vali maakond--</option>
										<option value="1" class="sub_1">Harjumaa</option>
										<option value="2" class="sub_1">Järvamaa</option>
										<option value="3" class="sub_1">Raplamaa</option>
										<option value="4" class="sub_2">Ida-Virumaa</option>
										<option value="5" class="sub_2">Jõgevamaa</option>
										<option value="6" class="sub_2">Lääne-Virumaa</option>
										<option value="7" class="sub_3">Hiiumaa</option>
										<option value="8" class="sub_3">Läänemaa</option>
										<option value="9" class="sub_3">Pärnumaa</option>
										<option value="10" class="sub_3">Saaremaa</option>
										<option value="11" class="sub_4">Põlvamaa</option>
										<option value="12" class="sub_4">Tartumaa</option>
										<option value="13" class="sub_4">Valgamaa</option>
										<option value="14" class="sub_4">Viljandimaa</option>
										<option value="15" class="sub_4">Võrumaa</option>
   									</select>
								</td>
								<td><span id="registerCountyPicture"></span></td>
							</tr>
							
							<tr>
								<td class="errorMessage"><span id="registerCountyMessage" ></span></td>
							</tr>
							
							<!-- Country -->
							<tr>
								<td>
									<select name="cities" id="registerCity" class="citySelect" onblur="validateForm()" placeholder="Linn" onchange="validateCity()" >
										<option value="0" class="static">--Vali linn--</option>
										<option value="1" class="sub_1">Tallinn</option>
										<option value="2" class="sub_2">Paide</option>
										<option value="3" class="sub_3">Rapla</option>
										<option value="4" class="sub_4">Jõhvi</option>
										<option value="5" class="sub_5">Jõgeva</option>
										<option value="6" class="sub_6">Rakvere</option>
										<option value="7" class="sub_7">Kärdla</option>
										<option value="8" class="sub_8">Haapsalu</option>
										<option value="9" class="sub_9">Pärnu</option>
										<option value="10" class="sub_10">Kuressaare</option>
										<option value="11" class="sub_11">Põlva</option>
										<option value="12" class="sub_12">Tartu</option>
										<option value="13" class="sub_13">Valga</option>
										<option value="14" class="sub_14">Viljandi</option>
										<option value="15" class="sub_15">Võru</option> 
									</select>
								</td>
								<td><span id = "registerCityPicture"></span></td>
							</tr>
							
							<tr>
								<td class="errorMessage"><span id="registerCityMessage" ></span></td>
							</tr>						
							
							<!-- Submit -->
							<tr>
								<td><input type="submit" value="Registreeru" class="button" name="submit" id="Registreeru" /></td>
								<td><span id="confirmInput"></span></td>
							</tr>
						</table>
					</form>
				
		</div>
		<!-- end #content -->
		<!-- <div style="clear: both;">&nbsp;</div> -->
		<!-- end #page -->
		
		<!-- end #footer -->
	</div>
	<!-- include footer -->
		<%@include file='/footer.jsp'%>
</div>
</body>

</html>