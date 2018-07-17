<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/favicon.ico">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/theme/style.css" type="text/css" />
		<title>Õigusportaal</title>
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
									<a name="CivilLaw"        				href="<%=request.getContextPath()%>/FieldSearchServlet?param=1" title="Detailplaneeringud. Ehitus- ja kasutuslubade väljastus. Vastavate haldusaktide vaidlustamine">Ehitus- ja planeerimisõigus</a> <br>
									<a name="Privitization" 				href="<%=request.getContextPath()%>/FieldSearchServlet?param=2" title="Maa tagastamise ja erastamisega seotud küsimused; Ostueesõigusega erastamine">Erastamine</a><br>
									<a name="DebtCollectionServices" 		href="<%=request.getContextPath()%>/FieldSearchServlet?param=3" title="Mulle ollakse võlgu; Mina olen võlgu; Sissenõutavate intressid võimalikkus ja suurused ">Inkassoteenused</a><br>
									<a name="IntellectualProperty" 			href="<%=request.getContextPath()%>/FieldSearchServlet?param=4" title="Autoriõigusega seotud küsimused; Kaubamärgid; Domeeninimed; Disainilahendused">Intellektuaalneomand</a><br>
									<a name="ITLaw" 						href="<%=request.getContextPath()%>/FieldSearchServlet?param=5" title="Autoriõiguste kaitse ja sellest loobumine nii töösuhtes kui ka töövõtulepinguga loodavatele programmidele ja andmebaasidele">IT-õigus</a><br>
									<a name="EnvironmentalLaw" 				href="<%=request.getContextPath()%>/FieldSearchServlet?param=6" title="Keskkonnale tekitatud kahju; Keskkonnamõjude hindamine; Kaevandamisõigused; ">Keskkonnaõigus</a><br>
									<a name="InsuranceLaw" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=7" title="Kohustuslik (nt sõidukikindlustus) ja vabatahtlik (nt KASKO) kindlustus; Kindlustushüvitiste vähendamine; ">Kindlustusõigus</a><br>
									<a name="NonMovableProperty" 			href="<%=request.getContextPath()%>/FieldSearchServlet?param=8" title="Kinnisvaraga seotud tehingud (võõrandamine, üürimine); Õigusauditite läbiviimine">Kinnisvaraõigus</a><br>
									<a name="CompetitionLaw" 				href="<%=request.getContextPath()%>/FieldSearchServlet?param=9" title="Koondumised; Keelatud kokkulepped; Turgu valitseva seisundi kuritarvitamine">Konkurentsiõigus</a><br>
									
							</td>
							
							<td class="secondLinks">
									<a name="CriminalLaw" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=10" title="Viivistasuotstsuste vaidlustamine; Kriminaalmenetlus; Kohtuasjade uuesti läbivaatamine">Kriminaal-ja väärteoõigus</a><br>
									<a name="Divorce"  						href="<%=request.getContextPath()%>/FieldSearchServlet?param=11" title="Abielu lahutamise võimalused, Vara jagamine ja lapse hooldusõigus">Lahutus</a><br>
									<a name="TrafficLaw" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=12" title="Liiklustrahvid; Joobeseisund ja selle tuvastamine; Mootorsõidukite juhtimiskeelud">Liiklusõigus</a><br>
									<a name="EconomicLaw" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=13" title="Äriühingute loomine; Äriühingu juhtkonna vastutus; Osa ja aktsia võõrandamine (sh ostueesõigus); Osanike ja aktsionäride vahelised vaidlused; ">Majandusõigus</a><br>
									<a name="TaxLaw" 						href="<%=request.getContextPath()%>/FieldSearchServlet?param=14" title="Maksusoodustused; Maksuvaidlused; Maksukohustused välisriikides">Maksuõigus</a> <br>
									<a name="MedicineLaw" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=15" title="Meditsiiniteenustega seotud vaidlused; Ravivead; Load ja litsentsid; Raviteenuse osutaja vastutus/andmekaitse/ravimiost">Meditsiin ja ravimid</a><br> 
									<a name="MediaAndTelecommunicationLaw" 	href="<%=request.getContextPath()%>/FieldSearchServlet?param=16" title="Au ja väärikuse riivamine avalikus meedias; Telekommunikatsiooni teenust pakkuvate operaatorite vahelised õigused; Kasutajate õigused">Meedia- ja telekommunikatsiooniõigus</a><br> 
									<a name="PropertyReform" 				href="<%=request.getContextPath()%>/FieldSearchServlet?param=17" title="Maade tagastamine; Sundüürnikud">Omandireform</a> <br>
									<a name="BankingAndFinancialFunds" 		href="<%=request.getContextPath()%>/FieldSearchServlet?param=18" title="Laenu- ja liisinglepingud krediidiasutustega; Kinnisasjade finantseerimine (sh käendamine ja hüpoteek) ; Võlakirjade emissioon; ">Pangandus- ja finantsõigus, kapitaliturud</a><br>
									
									
							</td>
							<td class="thirdLinks">
									<a name="FamilyLaw" 								href="<%=request.getContextPath()%>/FieldSearchServlet?param=19" title="Isaduse tuvastamine; Lapse eeskoste; Ülalpidamiskohustus; Abieluvaralepingud; Elatise nõuded ">Perekonnaõigus</a><br>
									<a name="HeritageLaw" 								href="<%=request.getContextPath()%>/FieldSearchServlet?param=20" title="Pärandist loobumine; Seadusjärgne pärimine; Testament ja selle vaidlustamine; Sundosa; Pärandvaralepingud; Riigilõivud pärimismenetluses">Pärimisõigus</a> <br>
									<a name="RestructingLaw" 							href="<%=request.getContextPath()%>/FieldSearchServlet?param=21" title="Ettevõtte saneerimis protsess; Pankrotiavalduse esitamine; Restruktureerimisplaan ">Restruktureerimine, saneerimine ja maksejõuetus (pankrot)</a><br> 
									<a name="SocialWelfareLaw" 							href="<%=request.getContextPath()%>/FieldSearchServlet?param=22" title="Sotsiaaltoetused; Hoolekandeteenused - rehabiliteerimine, ööpäevaringne hooldamine, töötamise toetamine">Sotsiaalhoolekandeõigus</a><br>
									<a name="TransportTradeAndSeaLaw" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=23" title="Rahvusvaheliste kaupade autoveolepingud (CRM); Vedaja vastutus; Merinõuded ja merivõlad">Transpordi-, kaubandus- ja mereõigus</a><br> 
									<a name="LaborLaw" 									href="<%=request.getContextPath()%>/FieldSearchServlet?param=24" title="Töölepingu sõlmimine; Tähtajalised ja tähtajatud töölepingud; töölepingu lõpetamine (koondamine, poolte kokkuleppel, ülesütlemine); Töötuskindlustus hüvitis; Vähendatud palgaga töö">Tööõigus</a> <br>
									<a name="AliensLaw" 								href="<%=request.getContextPath()%>/FieldSearchServlet?param=25" title="Välismaalaste viibimine Eestis; Elamis- ja töölubade väljastamine; Kodakondsuse tekkimine ja andmine ">Välismaalasteõigus</a><br> 
									<a name="MergersAndAcquisitions" 					href="<%=request.getContextPath()%>/FieldSearchServlet?param=26" title="You can create a web page HTML mouseover text description, similar to an image alt tag, that will be viewed when your mouse is placed over the text link. ">Ühinemised ja ülevõtmised</a><br>
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
					<p><strong>Õigusportaal OÜ</strong></p>
					<p>Reg: </p>
				</div>
			</div>
			<!-- include footer -->
			<%@include file='/footer.jsp'%>
		</div>
	</body>

</html>