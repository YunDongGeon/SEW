drop table if exists user;
CREATE TABLE user(
	memId varchar(20) primary key,
	memPw varchar(512) not null,
	memName varchar(20) not null,
	memBirth varchar(8) not null,
	memEmail varchar(50) not null,
	memPhone varchar(11) not null,
	memZipCode varchar(5) default null,
	memAddr1 varchar(200) default null,
	memAddr2 varchar(200) default null,
    memStat varchar(10) default 'no',
    memAuth varchar(10) default 'no',
    memPoint int default 0
);

CREATE TABLE product(
	prodNo int primary key auto_increment,
	prodGen varchar(4) not null,
	prodType varchar(3) not null,
	prodCat varchar(20) not null,
	prodName varchar(40) not null,
	prodListP int not null,
	prodPrice int not null,
	prodDeli int default 2500,
	prodCode varchar(20) not null,	
	prodOrigin varchar(20) not null
);

CREATE TABLE prodImage(
	prodNo int primary key,
	prodThumbUrl varchar(100) not null,
    prodThumbName varchar(100) not null,
    prodContUrl varchar(100) not null,
    prodContName varchar(100) not null,    
    foreign key(prodNo) references product(prodNo)
);

CREATE TABLE prodInven(
	prodNo int not null,
	prodColor varchar(40) not null,
	prodSsize int not null,
	prodMsize int not null,
	prodLsize int not null,
	prodXLsize int not null,	
	foreign key (prodNo) references product(prodNo)
);

CREATE TABLE buyList(
	buyNo int primary key auto_increment,
	prodNo int not null,
	buyType varchar(10) not null,
	buyStat varchar(10) not null,
	buyDate Timestamp not null
);

CREATE TABLE cartList(
	cartNo int primary key auto_increment,
	prodNo int not null,
	memId varchar(20) not null,
	totalAmount int not null,
	totalPrice int not null,
	totalListPrice int not null,
	foreign key(prodNo) references product(prodNo),
	foreign key(memId) references user(memId)
);

CREATE TABLE cartOptionList(
	optionNo int primary key auto_increment,
	cartNo int not null,	
	prodColor varchar(40) not null,
	prodSize varchar(20) not null,
	prodAmount int not null,
	constraint optionfk foreign key(cartNo) references cartList(cartNo)	
	on delete cascade
);