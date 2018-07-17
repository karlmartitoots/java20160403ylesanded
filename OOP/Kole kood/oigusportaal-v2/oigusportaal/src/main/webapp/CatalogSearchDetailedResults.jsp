<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@ page import="main.details.BureauSearchResults"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/favicon.ico">
<script src="js/jquery.js"></script>
                <script src="js/functions.js"></script>
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/widgetstyle.css" />
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
  <script>
  $(function() {
    $( "#datepickerTwo" ).datepicker();
  });
  $(function() {
	  
	    // Create a new jQuery UI Slider element
	    // and set some default parameters.
	    $( "#slider" ).slider({
	      range: "min",
	      value: <%if(request.getAttribute("averageprice") != null){%><%=request.getAttribute("averageprice")%><%} else {%>50<%}%>,
	      min: 0,
	      max: 100,
	      slide: function( event, ui ) {
	      
	        // While sliding, update the value in the #amount div element
	        $( "#amount" ).html( ui.value );	        
	        $("input[name='averageprice']").val( ui.value );
	        
	      }
	    });
	    
	    // Set the initial slider amount in the #amount div element
	    var value = $( "#slider" ).slider( "value" );
	    $( "#amount" ).html( value );
	    
	  });
  </script>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/style.css" />
	<style type="text/css">
	#header {
	position: relative;
	background: url(images/main_header_bg.png) no-repeat center top;
	height:150px;
	}
	</style>
                
                <title>’igusportaal</title>
        </head>
        
        <body>
        
        <%
                ArrayList<BureauSearchResults> br = (ArrayList<BureauSearchResults>) request
                                .getAttribute("bureauSR");
        %>
        
                <div id="wrapper">
                        <!-- include header -->
                        <%@include file='/header.jsp'%>
                        
                        <div class="page">
                                <h2>Otsing</h2>
                                <h3>Valdkond: <%=request.getAttribute("fieldname")%></h3>
                                <div class="content" style="background-color:#f0f0f0;">
					<form action="<%=request.getContextPath()%>/SearchServlet" method="get" class="cascade">
						<table id="catalogSearch">
							<tr>
								<td>
									<p>Asukoht: </p>
								</td>
								
								
								<td>
									<% int region = (Integer) request.getAttribute("regionValue");%> 
									<select name="regions" id="editRegion" class="regSelect" onchange="validateRegion()">
										<option value="0">-- Vali regioon --</option>
										<option value="1" <%if (region == 1) {%> selected <%}%>>Pıhja-Eesti</option>
										<!-- /*Harjumaa, , J‰rvamaa, Raplamaa */ -->
										<option value="2" <%if (region == 2) {%> selected <%}%>>Ida-Eesti</option>
										<!-- Ida-Virumaa, L‰‰ne-Virumaa, Jıgevamaa -->
										<option value="3" <%if (region == 3) {%> selected <%}%>>L‰‰ne-Eesti</option>
										<!--  L‰‰nemaa, Hiiumaa, Saaremaa, P‰rnumaa -->
										<option value="4" <%if (region == 4) {%> selected <%}%>>Lıuna-Eesti</option>
										<!--  Viljandimaa, Tartumaa, Pılvamaa, Valgamaa, Vırumaa -->
									</select>									
								</td>
								
								<td>
									<% int county = (Integer) request.getAttribute("countyValue");%> 
									<select name="counties" id="editCounty" class="countySelect" onchange="validateCounty()">
											<option value="0" class="static">--Vali maakond--</option>
											<option value="1" class="sub_1" <%if (county == 1) {%> selected <%}%>>Harjumaa</option>
											<option value="2" class="sub_1" <%if (county == 2) {%> selected <%}%>>J‰rvamaa</option>
											<option value="3" class="sub_1" <%if (county == 3) {%> selected <%}%>>Raplamaa</option>
											<option value="4" class="sub_2" <%if (county == 4) {%> selected <%}%>>Ida-Virumaa</option>
											<option value="5" class="sub_2" <%if (county == 5) {%> selected <%}%>>Jıgevamaa</option>
											<option value="6" class="sub_2" <%if (county == 6) {%> selected <%}%>>L‰‰ne-Virumaa</option>
											<option value="7" class="sub_3" <%if (county == 7) {%> selected <%}%>>Hiiumaa</option>
											<option value="8" class="sub_3" <%if (county == 8) {%> selected <%}%>>L‰‰nemaa</option>
											<option value="9" class="sub_3" <%if (county == 9) {%> selected <%}%>>P‰rnumaa</option>
											<option value="10" class="sub_3" <%if (county == 10) {%> selected <%}%>>Saaremaa</option>
											<option value="11" class="sub_4" <%if (county == 11) {%> selected <%}%>>Pılvamaa</option>
											<option value="12" class="sub_4" <%if (county == 12) {%> selected <%}%>>Tartumaa</option>
											<option value="13" class="sub_4" <%if (county == 13) {%> selected <%}%>>Valgamaa</option>
											<option value="14" class="sub_4" <%if (county == 14) {%> selected <%}%>>Viljandimaa</option>
											<option value="15" class="sub_4" <%if (county == 15) {%> selected <%}%>>Vırumaa</option>
									</select>
								</td>
								<td>
									<%int city = (Integer) request.getAttribute("cityValue");%> 
									<select name="cities" id="editCity" class="citySelect" onblur="validateForm()" placeholder="Linn" onchange="validateCity()">
											<option value="0" class="static">--Vali linn--</option>
											<option value="1" class="sub_1" <%if (city == 1) {%> selected <%}%>>Tallinn</option>
											<option value="2" class="sub_2" <%if (city == 2) {%> selected <%}%>>Paide</option>
											<option value="3" class="sub_3" <%if (city == 3) {%> selected <%}%>>Rapla</option>
											<option value="4" class="sub_4" <%if (city == 4) {%> selected <%}%>>Jıhvi</option>
											<option value="5" class="sub_5" <%if (city == 5) {%> selected <%}%>>Jıgeva</option>
											<option value="6" class="sub_6" <%if (city == 6) {%> selected <%}%>>Rakvere</option>
											<option value="7" class="sub_7" <%if (city == 7) {%> selected <%}%>>K‰rdla</option>
											<option value="8" class="sub_8" <%if (city == 8) {%> selected <%}%>>Haapsalu</option>
											<option value="9" class="sub_9" <%if (city == 9) {%> selected <%}%>>P‰rnu</option>
											<option value="10" class="sub_10" <%if (city == 10) {%> selected <%}%>>Kuressaare</option>
											<option value="11" class="sub_11" <%if (city == 11) {%> selected <%}%>>Pılva</option>
											<option value="12" class="sub_12" <%if (city == 12) {%> selected <%}%>>Tartu</option>
											<option value="13" class="sub_13" <%if (city == 13) {%> selected <%}%>>Valga</option>
											<option value="14" class="sub_14" <%if (city == 14) {%> selected <%}%>>Viljandi</option>
											<option value="15" class="sub_15" <%if (city == 15) {%> selected <%}%>>Vıru</option>
									</select>	
								</td>
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
                                		<div id="slider" style="margin-top:42px;"></div> 
                                		<div id="amount" style="color:#777;font-size:20px;text-align:center;"></div>
                                		<input type="hidden" name="averageprice" id="averageprice" value="<%=request.getAttribute("averageprice")%>">
                                		</div>
	                                </td>    
	                          		<td class="column_height" id="column_checkbox">
										<input type="checkbox" name="Price" value="Price" class="catalogCheckbox" <%if (request.getAttribute("priceChecked") != null) { %>checked<%} %>>   Kasuta otsingus.
									</td>
								</tr>
							<!-- <![endif]-->
							<tr>
                                <td><input type="hidden" name="fieldId" value="<%= request.getAttribute("fieldId") %>"></td>
                            </tr>
                            
                            <tr>
                            	<td>
                            		<p id="success">Viimane edulugu</p>
                            	</td>
                                <td> 
                                	<div id="success_date">
                                    	<p>Alates:<br> <input type="text" id="datepicker" name="From" value="<%=request.getAttribute("fromDate")%>"/></p>                                    	
                                   		<p>Kuni:<br> <input type="text" id="datepickerTwo" name="To" value="<%=request.getAttribute("toDate")%>"/></p>
                                   		
                                    </div>
                                </td>
                                <td>
                                	<input type="checkbox" name="Date" value="Date" class="catalogCheckbox" <%if (request.getAttribute("dateChecked") != null) { %>checked<%} %>>  Kasuta otsingus
                                </td>
                             <tr>
                             	<td colspan="4" align="right" ><input type="submit" class="button" value="Otsi" id="searching"/></td>
                           	</tr>
                       	</table>
                        </form>
                        </div>
                        
                        <div class="page">
                  
                			<h2 id="searchHeader2" >-- Kıik leitud b¸rood --</h2>
                			<table id="searchResults" width="95%"  >                        
                                <%
                                        for (int i = 0; i < br.size(); i++) {
                                %>
                                
								<tr class="borderdesign"  >								
								<td width="100" class="firstColumn">
								<% if (br.get(i).getImage() != null) { %>
								<img class="profilepic" src="<%=br.get(i).getImage()%>"/><%} %></td>
	                                <td width="20" align="center" class="middleColumn">
	                                	<td width="100" class="lastColumn">
	                                        <h3><a href="<%=request.getContextPath()%>/BureauViewServlet?bureauId=<%=br.get(i).getBureauId()%>"><%=br.get(i).getName()%></a></h3>
	                                        <p><a href="mailto:<%=br.get(i).getEmail()%>"><%=br.get(i).getEmail()%></a></p>
	                                        <p><b>Linn: </b> <%=br.get(i).getCity()%></p>
	                                        <p><b>Keskmine tunnihind: </b> <%=br.get(i).getAveragePrice()%>
	                                        <% if (br.get(i).getLastStoryParticipants() != null) { %>
	                                        <p><b>Viimane edulugu: </b><%=br.get(i).getLastStoryParticipants() %>
	                                        <%if (br.get(i).getLastStoryPath()!=null){ %><a href="<%=br.get(i).getLastStoryPath()%>">Lae alla</a><% } %>
	                                        </p>
	                                        <% } %>
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
                        <!-- include footer -->
                        <%@include file='/footer.jsp'%>
                </div>
        </body>

</html>