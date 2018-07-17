
CREATE TABLE bureau
  (
     bureauid     BIGINT NOT NULL auto_increment PRIMARY KEY,
     registrycode BIGINT DEFAULT 1 NOT NULL,
     name         VARCHAR(100),
     email        VARCHAR(254),
     password     VARCHAR(100),
     averageprice BIGINT,
     street       VARCHAR(50),
     postalcode   BIGINT,
     phone        VARCHAR(30),
     regionname   VARCHAR(30),
     cityname     VARCHAR(30),
     countyname   VARCHAR(30),
     image        VARCHAR(120),
     category     BIGINT DEFAULT 2,
     active       BIGINT DEFAULT 1 NOT NULL,
     hash         VARCHAR(10)
  );

CREATE TABLE successstory (
    ssid bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    participants varchar(30),
    date date,
    reference varchar(30),
    conclusion varchar(300),
    bureauid bigint,
    fieldid bigint,
    filepath varchar(200)
);

CREATE TABLE attorney (
    name varchar(100),
    bureauid bigint NOT NULL,
    email varchar(300),
    attorneyid bigint NOT NULL AUTO_INCREMENT PRIMARY KEY  ,
	  imgpath varchar(100)
);

CREATE TABLE fieldbureaujunction (
	bureauid bigint NOT NULL,
	fieldid bigint NOT NULL
);

CREATE TABLE field (
    fieldid bigint NOT NULL AUTO_INCREMENT PRIMARY KEY  ,
    fieldname varchar(100)
);

ALTER TABLE ONLY attorney
    ADD CONSTRAINT bureauid FOREIGN KEY (bureauid) REFERENCES bureau(bureauid);

ALTER TABLE ONLY successstory
    ADD CONSTRAINT bureauid FOREIGN KEY (bureauid) REFERENCES bureau(bureauid);

insert into bureau (bureauid, registrycode, name, email, password, averageprice, street, postalcode, phone, regionname, cityname, countyname, image, category, active)
values (9, 1, 'admin', 'admin@admin.com', 9999, null, null, null, null, null, null, null, null, 1, 1);

insert into bureau (bureauid, registrycode, name, email, password, averageprice, street, postalcode, phone, regionname, cityname, countyname, image, category, active)
values (5, 21356, 'Firma E', 'firmae@firmae.com', 5555, 50, 'vabandame', 23145, 13299, 'Lõuna-Eesti', 'Jõhvä', 'Ida-Virumaa', null, 2, 1);

insert into field (fieldname) values ('Ehitus- ja planeerimisõigus');
insert into field (fieldname) values ('Erastamine');
