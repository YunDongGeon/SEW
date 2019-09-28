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
    memStat varchar(10) default 'no'
);