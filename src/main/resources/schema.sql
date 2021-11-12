DROP SEQUENCE apply_id_seq;

DROP SEQUENCE animal_id_seq;

DROP SEQUENCE apply_id_seq;
=======
DROP SEQUENCE category_id_seq;

DROP SEQUENCE user_no_seq;

DROP SEQUENCE post_id_seq;

DROP TABLE Review CASCADE CONSTRAINTS PURGE;

DROP TABLE AdoptApply CASCADE CONSTRAINTS PURGE;

DROP TABLE Animal CASCADE CONSTRAINTS PURGE;

DROP TABLE category CASCADE CONSTRAINTS PURGE;

DROP TABLE Adopter CASCADE CONSTRAINTS PURGE;

CREATE TABLE Adopter
(
	user_no              INTEGER NOT NULL ,
	password             VARCHAR() NOT NULL ,
	userName             VARCHAR() NOT NULL ,
	email                VARCHAR() NOT NULL ,
	phone                VARCHAR() NOT NULL ,
	user_id              VARCHAR() NOT NULL 
);

CREATE UNIQUE INDEX XPKUser ON Adopter
(user_no   ASC);

CREATE UNIQUE INDEX user_no_seq ON Adopter
(user_no   ASC);

CREATE SEQUENCE user_no_seq
START WITH 1
INCREMENT BY 1; 

ALTER TABLE Adopter
	ADD CONSTRAINT  XPKUser PRIMARY KEY (user_no);

CREATE TABLE Animal
(
	age                  INT NULL ,
	location             VARCHAR() NULL ,
	animal_id            VARCHAR() NOT NULL ,
	animal_matched       INT NULL ,
	category_id          INTEGER NOT NULL ,
	image                VARCHAR() NULL ,
	gender               VARCHAR() NULL ,
	weight               VARCHAR() NULL ,
	etc                  VARCHAR() NULL 
);

CREATE UNIQUE INDEX XPKAnimal ON Animal
(animal_id   ASC);

ALTER TABLE Animal
	ADD CONSTRAINT  XPKAnimal PRIMARY KEY (animal_id);

CREATE TABLE Review
(
	post_id              INTEGER NOT NULL ,
	title                VARCHAR() NOT NULL ,
	content              VARCHAR() NULL ,
	creationDate         DATE NULL ,
	image                VARCHAR() NULL ,
	writer               VARCHAR() NOT NULL ,
	animal_id            VARCHAR() NOT NULL 
);

CREATE SEQUENCE post_id_seq
START WITH 1
INCREMENT BY 1;

CREATE UNIQUE INDEX XPKReview ON Review
(post_id   ASC);

ALTER TABLE Review
	ADD CONSTRAINT  XPKReview PRIMARY KEY (post_id);

CREATE TABLE AdoptApply
(
	apply_id             INTEGER NOT NULL ,
	content              VARCHAR() NULL ,
	apply_matched        INT NULL ,
	user_no              INTEGER NULL ,
	animal_id            VARCHAR() NULL ,
	apply_date           DATE NULL ,
	living_environment   VARCHAR() NULL ,
	have_pets            VARCHAR() NULL ,
	approval_date        DATE NULL 
);

CREATE SEQUENCE apply_id_seq
START WITH 1
INCREMENT BY 1;

CREATE UNIQUE INDEX XPKAdoptApply ON AdoptApply
(apply_id   ASC);

ALTER TABLE AdoptApply
	ADD CONSTRAINT  XPKAdoptApply PRIMARY KEY (apply_id);

CREATE TABLE category
(
	species              VARCHAR() NULL ,
	category_id          INTEGER NOT NULL ,
	animal_type          VARCHAR() NULL 
);

CREATE SEQUENCE category_id_seq
START WITH 1
INCREMENT BY 1;

CREATE UNIQUE INDEX XPKcategory ON category
(category_id   ASC);

ALTER TABLE category
	ADD CONSTRAINT  XPKcategory PRIMARY KEY (category_id);

ALTER TABLE Animal
	ADD (CONSTRAINT R_27 FOREIGN KEY (category_id) REFERENCES category (category_id));

ALTER TABLE Review
	ADD (CONSTRAINT ÈÄ±âÀÛ¼º FOREIGN KEY (writer) REFERENCES Adopter (user_no) ON DELETE SET NULL);

ALTER TABLE Review
	ADD (CONSTRAINT R_17 FOREIGN KEY (animal_id) REFERENCES Animal (animal_id) ON DELETE SET NULL);

ALTER TABLE AdoptApply
	ADD (CONSTRAINT R_20 FOREIGN KEY (user_no) REFERENCES Adopter (user_no) ON DELETE SET NULL);

ALTER TABLE AdoptApply
	ADD (CONSTRAINT R_22 FOREIGN KEY (animal_id) REFERENCES Animal (animal_id) ON DELETE SET NULL);

CREATE SEQUENCE apply_id_seq
START WITH 1
INCREMENT BY 1; 
