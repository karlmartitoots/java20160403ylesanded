<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/favicon.ico">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/theme/style.css" type="text/css" />
		<title>�igusportaal</title>
	</head>
	
	<body>
		<div id="wrapper" style="clear:both;">
			
			<!-- including header file -->
			<%@include file='/header.jsp'%>
			
			<div class="page">
			
				<div class="content">
					<!-- <img src="images/welcoming.png" /> -->
					<div id="firstPageWelcome">
					</div>
					
					
				</div>
				<div class="valdkonnad" >
					<table id="valdkonnadTabel">
						<tr class="valdkonnadHeading">
							<td colspan="3"><h2>Valdkonnad</h2>
						</tr>
						<tr class="valdkonnadLinks">
							<td class="firstLinks">					  
									<a name="CivilLaw"        				href="<%=request.getContextPath()%>/FieldSearchServlet?param=1" title="Detailplaneeringud. Ehitus- ja kasutuslubade v�ljastus. Vastavate haldusaktide vaidlustamine">Ehitus- ja planeerimis�igus</a> <br>
									<a name="Privitization" 				href="<%=request.getContextPath()%>/FieldSearchServlet?param=2" title="Maa tagastamise ja erastamisega seotud k�simused; Ostuees�igusega erastamine">Erastamine</a><br>
									<a name="DebtCollectionServices" 		href="<%=request.getContextPath()%>/FieldSearchServlet?param=3" title="Mulle ollakse v�lgu; Mina olen v�lgu; Sissen�utavate intressid v�imalikkus ja suurused ">Inkassoteenused</a><br>
									<a name="IntellectualProperty" 			href="<%=request.getContextPath()%>/FieldSearchServlet?param=4" title="Autori�igusega seotud k�simused; Kaubam�rgid; Domeeninimed; Disainilahendused">Intellektuaalneomand</a><br>
									<a name="ITLaw" 						href="<%=request.getContextPath()%>/FieldSearchServlet?param=5" title="Autori�iguste kaitse ja sellest loobumine nii t��suhtes kui ka t��v�tulepinguga loodavatele programmidele ja andmebaasidele">IT-�igus</a><br>
									<a name="EnvironmentalLaw" 				href="<%=request.getContextPath()%>/FieldSearchServlet?param=6" title="Keskkonnale tekitatud kahju; Keskkonnam�jude hindamine; Kaevandamis�igused; ">Keskkonna�igus</a><br>
									<a name="InsuranceLaw" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=7" title="Kohustuslik (nt s�idukikindlustus) ja vabatahtlik (nt KASKO) kindlustus; Kindlustush�vitiste v�hendamine; ">Kindlustus�igus</a><br>
									<a name="NonMovableProperty" 			href="<%=request.getContextPath()%>/FieldSearchServlet?param=8" title="Kinnisvaraga seotud tehingud (v��randamine, ��rimine); �igusauditite l�biviimine">Kinnisvara�igus</a><br>
									<a name="CompetitionLaw" 				href="<%=request.getContextPath()%>/FieldSearchServlet?param=9" title="Koondumised; Keelatud kokkulepped; Turgu valitseva seisundi kuritarvitamine">Konkurentsi�igus</a><br>
									
							</td>
							
							<td class="secondLinks">
									<a name="CriminalLaw" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=10" title="Viivistasuotstsuste vaidlustamine; Kriminaalmenetlus; Kohtuasjade uuesti l�bivaatamine">Kriminaal-ja v��rteo�igus</a><br>
									<a name="Divorce"  						href="<%=request.getContextPath()%>/FieldSearchServlet?param=11" title="Abielu lahutamise v�imalused, Vara jagamine ja lapse hooldus�igus">Lahutus</a><br>
									<a name="TrafficLaw" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=12" title="Liiklustrahvid; Joobeseisund ja selle tuvastamine; Mootors�idukite juhtimiskeelud">Liiklus�igus</a><br>
									<a name="EconomicLaw" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=13" title="�ri�hingute loomine; �ri�hingu juhtkonna vastutus; Osa ja aktsia v��randamine (sh ostuees�igus); Osanike ja aktsion�ride vahelised vaidlused; ">Majandus�igus</a><br>
									<a name="TaxLaw" 						href="<%=request.getContextPath()%>/FieldSearchServlet?param=14" title="Maksusoodustused; Maksuvaidlused; Maksukohustused v�lisriikides">Maksu�igus</a> <br>
									<a name="MedicineLaw" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=15" title="Meditsiiniteenustega seotud vaidlused; Ravivead; Load ja litsentsid; Raviteenuse osutaja vastutus/andmekaitse/ravimiost">Meditsiin ja ravimid</a><br> 
									<a name="MediaAndTelecommunicationLaw" 	href="<%=request.getContextPath()%>/FieldSearchServlet?param=16" title="Au ja v��rikuse riivamine avalikus meedias; Telekommunikatsiooni teenust pakkuvate operaatorite vahelised �igused; Kasutajate �igused">Meedia- ja telekommunikatsiooni�igus</a><br> 
									<a name="PropertyReform" 				href="<%=request.getContextPath()%>/FieldSearchServlet?param=17" title="Maade tagastamine; Sund��rnikud">Omandireform</a> <br>
									<a name="BankingAndFinancialFunds" 		href="<%=request.getContextPath()%>/FieldSearchServlet?param=18" title="Laenu- ja liisinglepingud krediidiasutustega; Kinnisasjade finantseerimine (sh k�endamine ja h�poteek) ; V�lakirjade emissioon; ">Pangandus- ja finants�igus, kapitaliturud</a><br>
									
									
							</td>
							<td class="thirdLinks">
									<a name="FamilyLaw" 								href="<%=request.getContextPath()%>/FieldSearchServlet?param=19" title="Isaduse tuvastamine; Lapse eeskoste; �lalpidamiskohustus; Abieluvaralepingud; Elatise n�uded ">Perekonna�igus</a><br>
									<a name="HeritageLaw" 								href="<%=request.getContextPath()%>/FieldSearchServlet?param=20" title="P�randist loobumine; Seadusj�rgne p�rimine; Testament ja selle vaidlustamine; Sundosa; P�randvaralepingud; Riigil�ivud p�rimismenetluses">P�rimis�igus</a> <br>
									<a name="RestructingLaw" 							href="<%=request.getContextPath()%>/FieldSearchServlet?param=21" title="Ettev�tte saneerimis protsess; Pankrotiavalduse esitamine; Restruktureerimisplaan ">Restruktureerimine, saneerimine ja maksej�uetus (pankrot)</a><br> 
									<a name="SocialWelfareLaw" 							href="<%=request.getContextPath()%>/FieldSearchServlet?param=22" title="Sotsiaaltoetused; Hoolekandeteenused - rehabiliteerimine, ��p�evaringne hooldamine, t��tamise toetamine">Sotsiaalhoolekande�igus</a><br>
									<a name="TransportTradeAndSeaLaw" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=23" title="Rahvusvaheliste kaupade autoveolepingud (CRM); Vedaja vastutus; Merin�uded ja meriv�lad">Transpordi-, kaubandus- ja mere�igus</a><br> 
									<a name="LaborLaw" 									href="<%=request.getContextPath()%>/FieldSearchServlet?param=24" title="T��lepingu s�lmimine; T�htajalised ja t�htajatud t��lepingud; t��lepingu l�petamine (koondamine, poolte kokkuleppel, �les�tlemine); T��tuskindlustus h�vitis; V�hendatud palgaga t��">T���igus</a> <br>
									<a name="AliensLaw" 								href="<%=request.getContextPath()%>/FieldSearchServlet?param=25" title="V�lismaalaste viibimine Eestis; Elamis- ja t��lubade v�ljastamine; Kodakondsuse tekkimine ja andmine ">V�lismaalaste�igus</a><br> 
									<a name="MergersAndAcquisitions" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=26" title="You can create a web page HTML mouseover text description, similar to an image alt tag, that will be viewed when your mouse is placed over the text link. ">�hinemised ja �lev�tmised</a><br>
							</td>
						</tr>
					</table>
				</div>
				<div class="blog">
					
				</div>
				<div class="contacts">
					<img src="images/contact_shadow.png" />
					<h2>Kontaktid</h2>
					<p>Telefon: </p>
					<p>E-mail: </p>
					<p><strong>�igusportaal O�</strong></p>
					<p>Reg: </p>
				</div>
			</div>
			<!-- include footer -->
			<%@include file='/footer.jsp'%>
		</div>
	</body>

</html>