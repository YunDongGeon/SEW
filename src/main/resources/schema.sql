drop table if exists user;
CREATE TABLE user(
	memId varchar(20) primary key,
	memPw varchar(512) not null,
	memName varchar(20) not null,
	memBirth varchar(8) not null,
	memEmail varchar(50) not null,
	memPhone varchar(11) default null,
	memZipCode varchar(5) default null,
	memAddr1 varchar(200) default null,
	memAddr2 varchar(200) default null,
    memStat varchar(10) default 'no',
    memAuth varchar(10) default 'no'
);

CREATE TABLE menProd(
	prodNo int primary key auto_increment,
	prodType varchar(3) not null,
	prodCat varchar(20) not null,
	prodName varchar(40) not null,
	prodListP int not null,
	prodPrice int not null,
	prodDeli int default 2500,
	prodCode varchar(20) not null,	
	prodOrigin varchar(20) not null,
    prodThumb varchar(100) not null,
    prodThumbOriName varchar(100) not null,
    prodThumbUrl varchar(100) not null,
	prodCont varchar(100) not null,
    prodContOriName varchar(100) not null,
    prodContUrl varchar(100) not null
);

CREATE TABLE menTopInven(
	topInvenNo int primary key auto_increment,
	prodNo int not null,
	mSize int not null,
	lSize int not null,
	xlSize int not null,	
	foreign key (prodNo) references menProd(prodNo)
);

CREATE TABLE menBotInven(
	botInvenNo int primary key auto_increment,
	prodNo int not null,
	mSize int not null,
	lSize int not null,
	xlSize int not null,	
	foreign key (prodNo) references menProd(prodNo)
);

CREATE TABLE womenProd(
	prodNo int primary key auto_increment,
	prodType varchar(3) not null,
	prodCat varchar(20) not null,
	prodName varchar(40) not null,
	prodListP int not null,
	prodPrice int not null,
	prodLimit int default 1,
	prodDeli int default 2500,
	prodCode varchar(20) not null,	
	prodOrigin varchar(20) not null,
	prodCont varchar(100) not null
);

CREATE TABLE womenTopInven(
	topInvenNo int primary key auto_increment,
	prodNo int not null,
	mSize int not null,
	lSize int not null,
	xlSize int not null,	
	foreign key (prodNo) references womenProd(prodNo)
);

CREATE TABLE womenBotInven(
	botInvenNo int primary key auto_increment,
	prodNo int not null,
	mSize int not null,
	lSize int not null,
	xlSize int not null,	
	foreign key (prodNo) references womenProd(prodNo)
);