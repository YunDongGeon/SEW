CREATE TABLE member (
	memId varchar(20) primary key,
	memPw varchar(20) not null,
	memName varchar(20) not null,
	memBirth varchar(8) not null,
	memEmail varchar(50) not null,
	memPhone varchar(11) default null,
	memZipCode varchar(5) default null,
	memAddr varchar(200) default null
);