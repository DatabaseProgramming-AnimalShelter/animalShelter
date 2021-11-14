DROP SEQUENCE apply_id_seq;
DROP SEQUENCE category_id_seq;
DROP SEQUENCE post_id_seq;
DROP TABLE Review CASCADE CONSTRAINTS PURGE;
DROP TABLE AdoptApply CASCADE CONSTRAINTS PURGE;
DROP TABLE Animal CASCADE CONSTRAINTS PURGE;
DROP TABLE category CASCADE CONSTRAINTS PURGE;
DROP TABLE Adopter CASCADE CONSTRAINTS PURGE;
CREATE TABLE Adopter
(
	user_id              VARCHAR2(20) NOT NULL ,
	password             VARCHAR2(40) NOT NULL ,
	user_name            VARCHAR2(40) NOT NULL ,
	email                VARCHAR2(40) NOT NULL ,
	phone                VARCHAR2(40) NOT NULL
);
ALTER TABLE Adopter
	ADD  PRIMARY KEY (user_id);
CREATE TABLE Animal
(
	animal_id            VARCHAR2(40) NOT NULL ,
	category_id          INTEGER NOT NULL ,
	age                  INT NULL ,
	location             VARCHAR2(40) NULL ,
	image                VARCHAR2(40) NULL ,
	gender               VARCHAR2(40) NULL ,
	weight               VARCHAR2(40) NULL ,
	etc                  VARCHAR2(40) NULL ,
	animal_matched       INT NULL
);
ALTER TABLE Animal
	ADD  PRIMARY KEY (animal_id);
CREATE TABLE Review
(
	post_id              INTEGER NOT NULL ,
	animal_id            VARCHAR2(40) NOT NULL ,
	writer               VARCHAR2(20) NOT NULL ,
	title                VARCHAR2(40) NOT NULL ,
	content              VARCHAR2(40) NULL ,
	creationDate         DATE NULL ,
	image                VARCHAR2(40) NULL
);
ALTER TABLE Review
	ADD  PRIMARY KEY (post_id);
CREATE TABLE AdoptApply
(
	apply_id             INTEGER NOT NULL ,
	user_id              VARCHAR2(20) NULL ,
	animal_id            VARCHAR2(40) NULL ,
	content              VARCHAR2(40) NULL ,
	living_environment   VARCHAR2(40) NULL ,
	have_pets            VARCHAR2(40) NULL ,
	apply_matched        INT NULL ,
	apply_date           DATE NULL ,
	approval_date        DATE NULL
);
ALTER TABLE AdoptApply
	ADD  PRIMARY KEY (apply_id);
CREATE TABLE category
(
	category_id          INTEGER NOT NULL ,
	species              VARCHAR2(40) NULL ,
	animal_type          VARCHAR2(40) NULL
);
ALTER TABLE category
	ADD  PRIMARY KEY (category_id);
ALTER TABLE Animal
	ADD (FOREIGN KEY (category_id) REFERENCES category (category_id));
ALTER TABLE Review
	ADD (FOREIGN KEY (writer) REFERENCES Adopter (user_id) ON DELETE SET NULL);
ALTER TABLE Review
	ADD (FOREIGN KEY (animal_id) REFERENCES Animal (animal_id) ON DELETE SET NULL);
ALTER TABLE AdoptApply
	ADD (FOREIGN KEY (user_id) REFERENCES Adopter (user_id) ON DELETE SET NULL);
ALTER TABLE AdoptApply
	ADD (FOREIGN KEY (animal_id) REFERENCES Animal (animal_id) ON DELETE SET NULL);
CREATE SEQUENCE category_id_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE apply_id_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE post_id_seq
START WITH 1
INCREMENT BY 1;
INSERT INTO Adopter VALUES ('admin', 'admin','admin', 'admin@dongduk.ac.kr', '02-940-9999');
INSERT INTO Adopter VALUES ( 'hyunsoo', '1234', '송현수', 'hyunsu@gmail.com', '010-1234-5678');
INSERT INTO Adopter VALUES ( 'yujin', '1234', '한유진', 'yujin@naver.com', '010-5323-7788');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '믹스견', '개');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '진돗개', '개');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '풍산개', '개');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '치와와', '개');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '요크셔테리어', '개');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '비숑', '개');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '푸들', '개');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '말티즈', '개');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '골든리트리버', '개');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '러시안블루', '고양이');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '먼치킨' '고양이');
, '고양이');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '샴', '고양이');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '뱅갈', '고양이');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '코리안숏헤어', '고양이');
INSERT INTO category VALUES (category_id_seq.NEXTVAL, '스핑크스', '고양이');