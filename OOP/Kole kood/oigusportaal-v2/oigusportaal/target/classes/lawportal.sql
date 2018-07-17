CREATE TABLE bureau (
    bureauid bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    registrycode bigint DEFAULT 1 NOT NULL,
    name varchar(100),
    email varchar(254),
    password varchar(100),
    averageprice bigint,
    street varchar(50),
    postalcode bigint,
    phone varchar(30),
    regionname varchar(30),
    cityname varchar(30),
    countyname varchar(30),
    image varchar(120),
    category bigint DEFAULT 2,
    active bigint DEFAULT 1 NOT NULL,
    hash varchar(10)
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

ALTER TABLE attorney
    ADD CONSTRAINT fk_bureauid_attorney FOREIGN KEY (bureauid) REFERENCES bureau(bureauid);

ALTER TABLE successstory
    ADD CONSTRAINT fk_bureauid_successstory FOREIGN KEY (bureauid) REFERENCES bureau(bureauid);

insert into bureau (bureauid, registrycode, name, email, password, averageprice, street, postalcode, phone, regionname, cityname, countyname, image, category, active)
values (9, 1, 'admin', 'admin@admin.com', 9999, null, null, null, null, null, null, null, null, 1, 1);

insert into bureau (bureauid, registrycode, name, email, password, averageprice, street, postalcode, phone, regionname, cityname, countyname, image, category, active)
values (5, 21356, 'Firma E', 'firmae@firmae.com', 5555, 50, 'vabandame', 23145, 13299, 'Lõuna-Eesti', 'Jõhvä', 'Ida-Virumaa', null, 2, 1);

insert into field (fieldname) values ('Ehitus- ja planeerimisõigus');
insert into field (fieldname) values ('Erastamine');
