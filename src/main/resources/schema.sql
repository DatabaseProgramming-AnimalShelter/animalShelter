DROP SEQUENCE apply_id_seq;
DROP SEQUENCE cat_id_seq;
DROP SEQUENCE dog_id_seq;
DROP SEQUENCE post_id_seq;
DROP SEQUENCE animal_id_seq;
DROP SEQUENCE r_heart_id_seq;
DROP SEQUENCE  a_heart_id_seq;
DROP SEQUENCE qna_category_id_seq;
DROP SEQUENCE qna_id_seq;
DROP SEQUENCE comment_no_seq;
DROP SEQUENCE reply_id_seq;
DROP SEQUENCE comment_id_seq;

DROP TABLE AdoptApply CASCADE CONSTRAINTS PURGE;

DROP TABLE A_heart CASCADE CONSTRAINTS PURGE;

DROP TABLE R_heart CASCADE CONSTRAINTS PURGE;

DROP TABLE Review CASCADE CONSTRAINTS PURGE;

DROP TABLE Animal CASCADE CONSTRAINTS PURGE;

DROP TABLE category CASCADE CONSTRAINTS PURGE;

DROP TABLE Adopter CASCADE CONSTRAINTS PURGE;

DROP TABLE Qna CASCADE CONSTRAINTS PURGE;

DROP TABLE qna_category CASCADE CONSTRAINTS PURGE;

DROP TABLE Qna_Reply CASCADE CONSTRAINTS PURGE;

DROP TABLE Qna_Comment CASCADE CONSTRAINTS PURGE;

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
   user_id              VARCHAR2(20) NULL,
   FOREIGN KEY (user_id) REFERENCES Adopter (user_id) ON DELETE SET NULL
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

CREATE TABLE Review
(
   post_id              INTEGER NOT NULL ,
   title                VARCHAR2(40) NOT NULL ,
   content              VARCHAR2(4000) NULL ,
   creationDate         DATE NULL ,
   image                VARCHAR2(40) NULL ,
   writer               VARCHAR2(20) NULL ,
   animal_id            INTEGER NOT NULL,
   FOREIGN KEY (writer) REFERENCES Adopter (user_id) ON DELETE SET NULL
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
   user_id              VARCHAR2(20) NULL ,
   creationDate         DATE NULL ,
   parent               INTEGER NULL ,
   content              VARCHAR2(4000) NULL ,
    PRIMARY KEY (comment_id),
   FOREIGN KEY (user_id) REFERENCES Adopter (user_id) ON DELETE SET NULL,
   FOREIGN KEY (post_id) REFERENCES Review (post_id)
);


CREATE TABLE Qna
(
   qna_id               INTEGER NOT NULL ,
   qna_title            VARCHAR2(100) NULL ,
   qna_content          VARCHAR2(1000) NULL ,
   qna_password         VARCHAR2(100) NULL ,
   qna_category_id      INTEGER NULL ,
   qna_writer           VARCHAR2(100) NULL ,
   qna_date             DATE NULL
);

ALTER TABLE Qna
   ADD CONSTRAINT  XPKQna PRIMARY KEY (qna_id);

CREATE TABLE qna_category
(
   qna_category_id      INTEGER NOT NULL ,
   qna_type             VARCHAR2(40) NULL 
);

ALTER TABLE qna_category
   ADD CONSTRAINT  XPKqna_category PRIMARY KEY (qna_category_id);

CREATE TABLE Qna_Comment
(
   qna_id               INTEGER NOT NULL ,
   qna_title            VARCHAR2(100) NULL ,
   qna_content          VARCHAR2(1000) NULL ,
   qna_password         VARCHAR2(100) NULL ,
   qna_category_id      INTEGER NULL ,
   qna_writer           VARCHAR2(100) NULL ,
   qna_date             DATE NUL
);

ALTER TABLE Qna_Comment
   ADD CONSTRAINT  XPKComment PRIMARY KEY (comment_no);

CREATE TABLE Qna_Reply
(
   reply_id             INTEGER NOT NULL ,
   comment_no           INTEGER NOT NULL ,
   reply_writer         VARCHAR2(100) NULL ,
   reply_content        VARCHAR2(500) NULL ,
   reg_date             DATE NULL
);

ALTER TABLE Qna_Reply
   ADD CONSTRAINT  XPKReply PRIMARY KEY (reply_id);

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

ALTER TABLE Review
   ADD (CONSTRAINT  ��기작 �� FOREIGN KEY (writer) REFERENCES Adopter (user_id) ON DELETE CASCADE);

ALTER TABLE Review
   ADD (CONSTRAINT R_17 FOREIGN KEY (animal_id) REFERENCES Animal (animal_id) ON DELETE CASCADE);

ALTER TABLE R_heart
   ADD (CONSTRAINT R_30 FOREIGN KEY (post_id) REFERENCES Review (post_id) ON DELETE CASCADE);

ALTER TABLE R_heart
   ADD (CONSTRAINT R_34 FOREIGN KEY (user_id) REFERENCES Adopter (user_id) ON DELETE CASCADE);

ALTER TABLE Qna
   ADD (CONSTRAINT R_33 FOREIGN KEY (qna_category_id) REFERENCES qna_category (qna_category_id) ON DELETE SET NULL);

ALTER TABLE Qna_Comment
   ADD (CONSTRAINT R_36 FOREIGN KEY (qna_id) REFERENCES Qna (qna_id));

ALTER TABLE Qna_Reply
   ADD (CONSTRAINT R_37 FOREIGN KEY (comment_no) REFERENCES Qna_Comment (comment_no));   
   
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
CREATE SEQUENCE qna_category_id_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE comment_id_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE qna_id_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE comment_no_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE reply_id_seq
START WITH 1
INCREMENT BY 1;

INSERT INTO Adopter VALUES ('admin', 'admin','admin', 'admin@dongduk.ac.kr', '02-940-9999');
INSERT INTO Adopter VALUES ( 'hyunsoo', '1234', 'song', 'hyunsu@gmail.com', '010-1234-5678');
INSERT INTO Adopter VALUES ( 'yujin', '1234', 'han', 'yujin@naver.com', '010-5323-7788');

INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '��������ü', '��');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '�ͽ���', '��');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '��Ÿ', '��');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '������', '��');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, 'ǳ�갳', '��');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, 'ġ�Ϳ�', '��');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '��ũ���׸���', '��');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '���', '��');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, 'Ǫ��', '��');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '��Ƽ��', '��');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '��縮Ʈ����', '��');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '���޶�Ͼ�', '��');
INSERT INTO category VALUES (dog_id_seq.NEXTVAL, '�����ڱ�', '��');

INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '�������ü', '�����');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '�ͽ�', '�����');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '��Ÿ', '�����');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '�ڸ��ȼ����' ,'�����');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '��', '�����');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '��', '�����');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '��ġŲ', '�����');
INSERT INTO category VALUES (cat_id_seq.NEXTVAL, '����ũ��', '�����');

INSERT INTO qna_category VALUES (qna_category_id_seq.NEXTVAL, 'suggest');
INSERT INTO qna_category VALUES (qna_category_id_seq.NEXTVAL, 'inquiry');

commit;