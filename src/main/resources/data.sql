INSERT INTO user
VALUES ('admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','관리자','19980325','dbsehdrjs20@gmail.com','01091778955','04948','서울시 광진구 영화사로 87-12', '102동 501호', 'no', 'yes', 99999);
INSERT INTO user(memId, memPw, memName, memBirth, memEmail, memPhone, memZipCode, memAddr1, memAddr2) VALUES 
	('test','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08','테스터','19980325','dbsehdrjs20@gmail.com','01012345678',null,null,null);
INSERT INTO product(prodGen, prodType, prodCat, prodName, prodListP, prodPrice, prodDeli, prodCode, prodOrigin) VALUES 
	('남성', '상의', '셔츠', '남성 스트라이프 세미 오버핏 셔츠', '69900', '29900', '2500', 'KA9S1-MRC090', '기타국가');
INSERT INTO product(prodGen, prodType, prodCat, prodName, prodListP, prodPrice, prodDeli, prodCode, prodOrigin) VALUES 
	('남성', '상의', '자켓/코트/점퍼', '남성 모던 체크 싱글 자켓', '199000', '84900', '0', 'KA9S1-MJK010', '기타국가');
INSERT INTO product(prodGen, prodType, prodCat, prodName, prodListP, prodPrice, prodDeli, prodCode, prodOrigin) VALUES 
	('남성', '하의', '데님', '남성 그레이쉬 라이트 데님', '89900', '44900', '2500', 'KA9FS-MDP050', '기타국가');
INSERT INTO product(prodGen, prodType, prodCat, prodName, prodListP, prodPrice, prodDeli, prodCode, prodOrigin) VALUES 
	('남성', '하의', '팬츠', '남성 옆밴딩 슬림 슬랙스 팬츠', '79900', '24900', '2500', 'KA8S1-MPL050', '기타국가');
INSERT INTO prodImage 
VALUES(1, 'prodThumb/', 'register_detail_071.jpg', 'prodCont/', 'KA9S1-MRC090_1.jpg');
INSERT INTO prodImage 
VALUES(2, 'prodThumb/', 'register_detail_049.jpg', 'prodCont/', 'KA9S1-MJK010_1.jpg');
INSERT INTO prodImage 
VALUES(3, 'prodThumb/', 'register_detail_083.jpg', 'prodCont/', 'KA9FS-MDP050_1.jpg');
INSERT INTO prodImage 
VALUES(4, 'prodThumb/', 'register_detail_078.jpg', 'prodCont/', 'KA8S1-MPL050_1.jpg');

INSERT INTO prodInven VALUES(1, '화이트', 100, 96, 84, 94);
INSERT INTO prodInven VALUES(1, '베이지', 100, 96, 84, 20);
INSERT INTO prodInven VALUES(2, '블랙', 200, 150, 70, 0);
INSERT INTO prodInven VALUES(3, '연청', 172, 164, 88, 0);
INSERT INTO prodInven VALUES(4, '블랙', 0, 90, 6, 4);
INSERT INTO prodInven VALUES(4, '챠콜', 0, 90, 6, 9);