DROP SEQUENCE apply_id_seq;
DROP SEQUENCE cat_id_seq;
DROP SEQUENCE dog_id_seq;
DROP SEQUENCE post_id_seq;
DROP SEQUENCE animal_id_seq;
DROP SEQUENCE r_heart_id_seq;
DROP SEQUENCE  a_heart_id_seq;
DROP SEQUENCE qna_id_seq;
DROP SEQUENCE qna_category_id_seq;
DROP SEQUENCE comment_category_id_seq;

DROP TABLE AdoptApply CASCADE CONSTRAINTS PURGE;

DROP TABLE A_heart CASCADE CONSTRAINTS PURGE;

DROP TABLE R_heart CASCADE CONSTRAINTS PURGE;

DROP TABLE Review CASCADE CONSTRAINTS PURGE;

DROP TABLE Animal CASCADE CONSTRAINTS PURGE;

DROP TABLE category CASCADE CONSTRAINTS PURGE;

DROP TABLE Qna CASCADE CONSTRAINTS PURGE;

DROP TABLE qna_category CASCADE CONSTRAINTS PURGE;

DROP TABLE Adopter CASCADE CONSTRAINTS PURGE;

DROP TABLE Review_Comment CASCADE CONSTRAINTS PURGE;

CREATE TABLE Adopter
(
   user_id              VARCHAR2(20) NOT NULL ,
   password             VARCHAR2(40) NOT NULL ,
   user_name            VARCHAR2(40) NOT NULL ,
   email                VARCHAR2(40) NOT NULL ,
   phone                VARCHAR2(40) NOT NULL 
);

ALTER TABLE Adopter
   ADD CONSTRAINT  XPKUser PRIMARY KEY (user_id);

CREATE TABLE Animal
(
   animal_id            INTEGER NOT NULL ,
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
   ADD CONSTRAINT  XPKAnimal PRIMARY KEY (animal_id);

CREATE TABLE AdoptApply
(
   apply_id             INTEGER NOT NULL ,
   user_id              VARCHAR2(20) NULL ,
   animal_id            INTEGER NULL ,
   content              VARCHAR2(40) NULL ,
   living_environment   VARCHAR2(40) NULL ,
   apply_matched        INT NULL ,
   have_pets            VARCHAR2(40) NULL ,
   apply_date           DATE NULL ,
   approval_date        DATE NULL 
);

ALTER TABLE AdoptApply
   ADD CONSTRAINT  XPKAdoptApply PRIMARY KEY (apply_id);

CREATE TABLE A_heart
(
   a_heart_id           INTEGER NOT NULL ,
   animal_id            INTEGER NULL ,
   user_id              VARCHAR2(20) NULL 
);

ALTER TABLE A_heart
   ADD CONSTRAINT  XPKE_heart PRIMARY KEY (a_heart_id);

CREATE TABLE category
(
   category_id          INTEGER NOT NULL ,
   species              VARCHAR2(40) NULL ,
   animal_type          VARCHAR2(40) NULL 
);

ALTER TABLE category
   ADD CONSTRAINT  XPKcategory PRIMARY KEY (category_id);

CREATE TABLE qna_category
(
   qna_category_id      INTEGER NOT NULL ,
   qna_type             VARCHAR2(40) NULL 
);


ALTER TABLE qna_category
   ADD CONSTRAINT  XPKqna_category PRIMARY KEY (qna_category_id);

CREATE TABLE Qna
(
   qna_id               INTEGER NOT NULL ,
   title                VARCHAR2(40) NULL ,
   content              VARCHAR2(40) NULL ,
   password             VARCHAR2(40) NULL ,
   user_id              VARCHAR2(20) NULL ,
   qna_category_id      INTEGER NULL 
);


ALTER TABLE Qna
   ADD CONSTRAINT  XPKQna PRIMARY KEY (qna_id);

CREATE TABLE Review
(
   post_id              INTEGER NOT NULL ,
   title                VARCHAR2(40) NOT NULL ,
   content              VARCHAR2(4000) NULL ,
   creationDate         DATE NULL ,
   image                VARCHAR2(40) NULL ,
   writer               VARCHAR2(20) NOT NULL ,
   animal_id            INTEGER NOT NULL 
);

ALTER TABLE Review
   ADD CONSTRAINT  XPKReview PRIMARY KEY (post_id);

CREATE TABLE R_heart
(
   r_heart_id           INTEGER NOT NULL ,
   post_id              INTEGER NULL ,
   count                INTEGER NULL ,
   user_id              VARCHAR2(20) NULL 
);


CREATE TABLE Review_Comment
(
	comment_id           INTEGER NOT NULL ,
	post_id              INTEGER NOT NULL ,
	user_id              VARCHAR2(20) NOT NULL ,
	creationDate         DATE NULL ,
	parent               INTEGER NULL ,
	content              VARCHAR2(20) NULL ,
 	PRIMARY KEY (comment_id),
	FOREIGN KEY (user_id) REFERENCES Adopter (user_id),
	FOREIGN KEY (post_id) REFERENCES Review (post_id)
);


ALTER TABLE R_heart
   ADD CONSTRAINT  XPKR_heart PRIMARY KEY (r_heart_id);

ALTER TABLE Animal
   ADD (CONSTRAINT R_27 FOREIGN KEY (category_id) REFERENCES category (category_id));

ALTER TABLE AdoptApply
   ADD (CONSTRAINT R_20 FOREIGN KEY (user_id) REFERENCES Adopter (user_id) ON DELETE CASCADE);

ALTER TABLE AdoptApply
   ADD (CONSTRAINT R_22 FOREIGN KEY (animal_id) REFERENCES Animal (animal_id) ON DELETE CASCADE);

ALTER TABLE A_heart
   ADD (CONSTRAINT R_28 FOREIGN KEY (animal_id) REFERENCES Animal (animal_id) ON DELETE CASCADE);

ALTER TABLE A_heart
   ADD (CONSTRAINT R_29 FOREIGN KEY (user_id) REFERENCES Adopter (user_id) ON DELETE CASCADE);

ALTER TABLE Qna
   ADD (CONSTRAINT R_32 FOREIGN KEY (user_id) REFERENCES Adopter (user_id) ON DELETE CASCADE);

ALTER TABLE Qna
   ADD (CONSTRAINT R_33 FOREIGN KEY (qna_category_id) REFERENCES qna_category (qna_category_id) ON DELETE CASCADE);

ALTER TABLE Review
   ADD (CONSTRAINT 후기작성 FOREIGN KEY (writer) REFERENCES Adopter (user_id) ON DELETE CASCADE);

ALTER TABLE Review
   ADD (CONSTRAINT R_17 FOREIGN KEY (animal_id) REFERENCES Animal (animal_id) ON DELETE CASCADE);

ALTER TABLE R_heart
   ADD (CONSTRAINT R_30 FOREIGN KEY (post_id) REFERENCES Review (post_id) ON DELETE CASCADE);

ALTER TABLE R_heart
   ADD (CONSTRAINT R_34 FOREIGN KEY (user_id) REFERENCES Adopter (user_id) ON DELETE CASCADE);

CREATE SEQUENCE dog_id_seq
START WITH 100
INCREMENT BY 1;
CREATE SEQUENCE cat_id_seq
START WITH 200
INCREMENT BY 1;
CREATE SEQUENCE apply_id_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE post_id_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE animal_id_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE r_heart_id_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE a_heart_id_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE qna_id_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE qna_category_id_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE comment_id_seq
START WITH 1
INCREMENT BY 1;


INSERT INTO Adopter VALUES ('admin', 'admin','admin', 'admin@dongduk.ac.kr', '02-940-9999');
INSERT INTO Adopter VALUES ( 'hyunsoo', '1234', 'song', 'hyunsu@gmail.com', '010-1234-5678');
INSERT INTO Adopter VALUES ( 'yujin', '1234', 'han', 'yujin@naver.com', '010-5323-7788');

INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '강아지전체', '개');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '믹스견', '개');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '기타', '개');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '진돗개', '개');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '풍산개', '개');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '치와와', '개');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '요크셔테리어', '개');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '비숑', '개');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '푸들', '개');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '말티즈', '개');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '골든리트리버', '개');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '포메라니안', '개');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '웰시코기', '개');

INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '고양이전체', '고양이');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '믹스', '고양이');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '기타', '고양이');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '코리안숏헤어' ,'고양이');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '샴', '고양이');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '뱅갈', '고양이');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '먼치킨', '고양이');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '스핑크스', '고양이');

INSERT INTO qna_category VALUES (qna_category_id_seq.NEXTVAL, 'suggest');
INSERT INTO qna_category VALUES (qna_category_id_seq.NEXTVAL, 'inquiry');


commit;
