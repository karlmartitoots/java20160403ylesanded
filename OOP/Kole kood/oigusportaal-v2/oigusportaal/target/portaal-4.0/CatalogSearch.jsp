<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@ page import="main.details.BureauDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/favicon.ico">

<script src="js/functions.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/widgetstyle.css" />
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
<script>
	$(function() {
		$("#datepickerTwo").datepicker();
	});
	$(function() {

		// Create a new jQuery UI Slider element
		// and set some default parameters.
		$("#slider").slider({
			range : "min",
			value : 50,
			min : 0,
			max : 100,
			slide : function(event, ui) {

				// While sliding, update the value in the #amount div element
				$("#amount").html(ui.value);				
				$("input[name='averageprice']").val(ui.value);

			}
		});

		// Set the initial slider amount in the #amount div element
		var value = $("#slider").slider("value");
		$("#amount").html(value);

	});
</script>
<script type="text/javascript" defer="defer">
	function cascadeSelect(parent, child) {
		var childOptions = child.find('option:not(.static)');
		child.data('options', childOptions);

		parent.change(function() {
			childOptions.remove();
			child.append(child.data('options').filter('.sub_' + this.value))
					.change();
		})

		childOptions.not('.static, .sub_' + parent.val()).remove();

	}

	$(function() {
		cascadeForm = $('.cascade');
		orgSelect = cascadeForm.find('.regSelect');
		terrSelect = cascadeForm.find('.countySelect');
		locSelect = cascadeForm.find('.citySelect');

		cascadeSelect(orgSelect, terrSelect);
		cascadeSelect(terrSelect, locSelect);
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/style.css" />
<style type="text/css">
#header {
	position: relative;
	background: url(images/main_header_bg.png) no-repeat center top;
	height: 150px;
}
</style>

<title>’igusportaal</title>
</head>

<body>

	<%
		ArrayList<BureauDetails> br = (ArrayList<BureauDetails>) request.getAttribute("bureau");
	%>

	<div id="wrapper">
		<!-- include header -->
		<%@include file='/header.jsp'%>

		<div class="page">
			<h2>Otsing</h2>
			<h3>Valdkond: <%=request.getAttribute("fieldname")%></h3>
			<div class="content">
				<form action="<%=request.getContextPath()%>/SearchServlet" method="get" class="cascade">
					<table id="catalogSearch">
						<tr>
							<td>
								<p>Asukoht:</p>
							</td>


							<td><select name="regions" class="regSelect">
									<option value="0">--Vali regioon--</option>
									<option value="1">Pıhja-Eesti</option>
									<option value="2">Ida-Eesti</option>
									<option value="3">L‰‰ne-Eesti</option>
									<option value="4">Lıuna-Eesti</option>
							</select></td>

							<td><select name="counties" class="countySelect">
									<option value="0" class="static">--Vali maakond--</option>
									<option value="1" class="sub_1">Harjumaa</option>
									<option value="2" class="sub_1">J‰rvamaa</option>
									<option value="3" class="sub_1">Raplamaa</option>
									<option value="4" class="sub_2">Ida-Virumaa</option>
									<option value="5" class="sub_2">Jıgevamaa</option>
									<option value="6" class="sub_2">L‰‰ne-Virumaa</option>
									<option value="7" class="sub_3">Hiiumaa</option>
									<option value="8" class="sub_3">L‰‰nemaa</option>
									<option value="9" class="sub_3">P‰rnumaa</option>
									<option value="10" class="sub_3">Saaremaa</option>
									<option value="11" class="sub_4">Pılvamaa</option>
									<option value="12" class="sub_4">Tartumaa</option>
									<option value="13" class="sub_4">Valgamaa</option>
									<option value="14" class="sub_4">Viljandimaa</option>
									<option value="15" class="sub_4">Vırumaa</option>
							</select></td>
							<td><select name="cities" class="citySelect">
									<option value="0" class="static">--Vali linn--</option>
									<option value="1" class="sub_1">Tallinn</option>
									<option value="2" class="sub_2">Paide</option>
									<option value="3" class="sub_3">Rapla</option>
									<option value="4" class="sub_4">Jıhvi</option>
									<option value="5" class="sub_5">Jıgeva</option>
									<option value="6" class="sub_6">Rakvere</option>
									<option value="7" class="sub_7">K‰rdla</option>
									<option value="8" class="sub_8">Haapsalu</option>
									<option value="9" class="sub_9">P‰rnu</option>
									<option value="10" class="sub_10">Kuressaare</option>
									<option value="11" class="sub_11">Pılva</option>
									<option value="12" class="sub_12">Tartu</option>
									<option value="13" class="sub_13">Valga</option>
									<option value="14" class="sub_14">Viljandi</option>
									<option value="15" class="sub_15">Vıru</option>
							</select></td>
						</tr>
						<!--[if IE]>
								<tr>
									<td class="column_height">
										<p>Keskmine tunnihind:</p>
									</td>
								
									<td>
                                		<div id="avgprice">
                                    		<input name="averageprice" type="text" value="50" >
	                                    </div>
	                                </td>    
	                          		<td class="column_height" id="column_checkbox">
										<input type="checkbox" name="Price" value="Price" checked="checked" class="catalogCheckbox">   Kasuta otsingus.
									</td>
								</tr>
							<![endif]-->

						<!--[if !IE]> -->
						<tr>
							<td class="column_height">
								<p>Keskmine tunnihind:</p>
							</td>

							<td><div id="price">
									<div id="slider" style="margin-top: 42px;"></div>
									<div id="amount" style="color: #777; font-size: 20px; text-align: center;"></div>
									<input type="hidden" name="averageprice" id="averageprice" value="50">
								</div></td>
							<td class="column_height" id="column_checkbox"><input type="checkbox" name="Price" value="Price" checked="checked" class="catalogCheckbox"> Kasuta otsingus.</td>
						</tr>
						<!--<![endif]-->
						<tr>
							<td><input type="hidden" name="fieldId" value="<%=request.getAttribute("fieldId")%>"></td>
						</tr>

						<tr>
							<td>
								<p id="success">Viimane edulugu</p>
							</td>
							<td>
								<div id="success_date">
									<p>
										Alates:<br> <input type="text" id="datepicker" name="From" value="<%=request.getAttribute("dateFrom")%>" />
									</p>
									<p>
										Kuni:<br> <input type="text" id="datepickerTwo" name="To" value="<%=request.getAttribute("dateTo")%>" />
									</p>
								</div>
							</td>
							<td><input type="checkbox" name="Date" value="Date" checked="checked" class="catalogCheckbox"> Kasuta otsingus</td>
						<tr>
							<td colspan="4" align="right"><input type="submit" class="button" value="Otsi" id="searching" /></td>
						</tr>
					</table>
				</form>
			</div>

			<div class="page">

				<h2 id="searchHeader2">-- Kıik leitud b¸rood --</h2>
				<table id="searchResults" width="95%">
					<%
						for (int i = 0; i < br.size(); i++) {
					%>

					<tr class="borderdesign">
						<td width="100" class="firstColumn">
							<%
								if (br.get(i).getImage() != null) {
							%> <img class="profilepic" src="<%=br.get(i).getImage()%>" /> <%
 	}
 %>
						</td>
						<td width="20" align="center" class="middleColumn">
						<td width="100" class="lastColumn">
							<h3>
								<a href="<%=request.getContextPath()%>/BureauViewServlet?bureauId=<%=br.get(i).getBureauId()%>"><%=br.get(i).getBureauName()%></a>
							</h3>
							<p>
								<a href="mailto:<%=br.get(i).getEmail()%>"><%=br.get(i).getEmail()%></a>
							</p>
							<p>
								<b>Linn: </b>
								<%=br.get(i).getCity()%></p>
							<p>
								<b>Keskmine tunnihind: </b>
								<%=br.get(i).getAveragePrice()%> </p>
								
							<%
									if (br.get(i).getLastStoryParticipants() != null) {
								%>
							<p>
								<b>Viimane edulugu: </b><%=br.get(i).getLastStoryParticipants()%>
								<%if (br.get(i).getLastStoryPath()!=null){ %><a href="<%=br.get(i).getLastStoryPath()%>">Lae alla</a><% } %>
							</p> <%	} %>
						</td>
					</tr>
					<%
						if ((i + 1) % 2 == 0) {
					%>

					<tr>
						<%
							}

							}
						%>

					</tr>
				</table>
			</div>
		</div>
		<!-- include footer -->
		<%@include file='/footer.jsp'%>
	</div>
</body>

</html>