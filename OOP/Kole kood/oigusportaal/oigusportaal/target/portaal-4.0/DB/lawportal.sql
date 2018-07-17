
CREATE TABLE bureau (
    bureauid integer PRIMARY KEY AUTO INCREMENT NOT NULL,
    registrycode integer DEFAULT 1 NOT NULL,
    name character varying(100),
    email character varying(254),
    password character varying(100),
    averageprice integer,
    street character varying(50),
    postalcode integer,
    phone character varying(30),
    regionname character varying(30),
    cityname character varying(30),
    countyname character varying(30),
    image character varying(120),
    category integer DEFAULT 2,
    active integer DEFAULT 1 NOT NULL,
    hash character varying(10)
);

CREATE TABLE successstory (
    ssid integer PRIMARY KEY AUTO INCREMENT NOT NULL,
    participants character varying(30),
    date date,
    reference character varying(30),
    conclusion character varying(300),
    bureauid integer,
    fieldid integer,
    filepath character varying(200)
);

CREATE TABLE attorney (
    name character varying(100),
    bureauid integer NOT NULL,
    email character varying(300),
    attorneyid integer PRIMARY KEY AUTO INCREMENT NOT NULL,
	  imgpath character varying(100)
);

CREATE TABLE fieldbureaujunction (
	bureauid integer NOT NULL,
	fieldid integer NOT NULL
);

CREATE TABLE field (
    fieldid integer PRIMARY KEY AUTO INCREMENT NOT NULL,
    fieldname character varying(100)
);

COPY bureau (bureauid, registrycode, name, email, password, averageprice, street, postalcode, phone, regionname, cityname, countyname, image, category, active) FROM stdin;
9	1	admin	admin@admin.com	9999	\N	\N	\N	\N	\N	\N	\N	\N	1	1
5	21356	Firma E	firmae@firmae.com	5555	50	vabandame	23145	13299	Lõuna-Eesti	Jõhvä	Ida-Virumaa	\N	2	1
7	111142	Firma G	firmag@firmag.com	7777	10	raatuse	32145	12310	Lõuna-Eesti	Jõgeva	Jõgevamaa	\N	2	1
6	412341	Firma F	firmaf@firmaf.com	6666	40	kala	32131	5432t624	Lääne-Eesti	Viljandi	Viljandimaa	C:/Users/Servet/git/portal/WebContent/bureauLogos/FirmaAlogo.gif	2	1
11	231177	Firma M	firmam@firmam.com	1111	30	hakkan	23125	112356	Lääne-Eesti	Haapsalu	Läänemaa	\N	2	1
10	312456	Firma H	firmah@firmah.com	1010	20	piik	132188	112245	Lõuna-Eesti	Rakvere	Lääne-Virumaa	\N	2	1
12	32145	Firma N	firman@firman.com	1212	22	turu	23145	123145	Ida-Eesti	Põlva	Põlvamaa	\N	2	1
13	29001	Firma R	firmar@firmar.com	1313	33	kala	24588	778899	Ida-Eesti	Tartu	Tartumaa	\N	2	1
4	321314	Firma D	firmad@firmad.com	4444	40	turu	4123	435	Lääne-Eesti	Kärdla	Hiiumaa	C:/Users/Servet/git/portal/WebContent/bureauLogos/FirmaAlogo.gif	2	1
14	249901	Firma K 	firmak@firmak.com	1414	44	karu	241931	99810	Ida-Eesti	Valga	Valgamaa	\N	2	1
8	31231	Firma L	firmal@firmal.com	8888	40	karu	33331	2222	Lääne-Eesti	Saaremaa	Kuresaare	C:/Users/Servet/git/portal/WebContent/bureauLogos/FirmaAlogo.gif	2	1
15	231245	Firma S 	firmas@firmas.com	1515	55	rütli	241245	91892	Ida-Eesti	Viljandi	Viljandimaa	\N	2	1
16	145991	Firma V	firmav@firmav.com	1616	66	raatuse	139900	13450	Ida-Eesti	Võru	Võrumaa	\N	2	1
1	213413	Firma A	firmaa@firmaa.com	1111	80	rütli	33124	32131	Põhja-Eesti	Tallinn	Harjumaa	\N	2	1
2	55555	Firma B	firmab@firmab.com	2222	70	riia	12345	54321	Põhja-Eesti	Paide	Järvamaa	\N	2	1
3	134561	Firma C	firmac@firmac.com	3333	60	uus	31567	98201	Põhja-Eesti	Rapla	Raplamaa	\N	2	1
\.

COPY field (fieldname) FROM stdin;
Ehitus- ja planeerimisõigus
Erastamine
Inkassoteenused
Intellektuaalne omand
IT-õigus
Keskkonnaõigus
Kindlustusõigus
Kinnisvaraõigus
Konkurentsiõigus
Kriminaal- ja väärteoõigus
Lahutus
Liiklusõigus
Majandusõigus
Maksuõigus
Meditsiin ja ravimid
Meedia- ja telekommunikatsiooniõigus
Omandireform
Pangandus- ja finantsõigus, kapitaliturud
Perekonnaõigus
Pärimisõigus
Restruktueerimine, saneerimine ja maksujõuetus (pankrot)
Sotsiaalhoolekandeõigus
Transpordi-, kaubandus- ja mereõigus
Tööõigus
Välismaalasteõigus
Ühinemised ja ülevõtmised
\.

ALTER TABLE ONLY attorney
    ADD CONSTRAINT bureauid FOREIGN KEY (bureauid) REFERENCES bureau(bureauid);

ALTER TABLE ONLY successstory
    ADD CONSTRAINT bureauid FOREIGN KEY (bureauid) REFERENCES bureau(bureauid);
