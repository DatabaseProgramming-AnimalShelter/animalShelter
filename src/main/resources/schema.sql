DROP TABLE Review CASCADE CONSTRAINTS PURGE;

DROP TABLE AdoptApply CASCADE CONSTRAINTS PURGE;

DROP TABLE Animal CASCADE CONSTRAINTS PURGE;

DROP TABLE category CASCADE CONSTRAINTS PURGE;

DROP TABLE Adopter CASCADE CONSTRAINTS PURGE;

CREATE TABLE Adopter
(
	user_id              CHAR(20) NOT NULL ,
	password             CHAR(20) NOT NULL ,
	userName             CHAR(10) NOT NULL ,
	email                CHAR(18) NULL ,
	address              CHAR(30) NULL 
);

ALTER TABLE Adopter
	ADD  PRIMARY KEY (user_id);

CREATE TABLE Animal
(
	animal_name          CHAR(10) DEFAULT  0  NULL  CHECK (animal_name BETWEEN 0 AND 30),
	animal_age           INT NULL ,
	animal_location      CHAR(30) NULL ,
	animal_id            INTEGER NOT NULL ,
	animal_matched       INT NULL ,
	category_id          CHAR(18) NOT NULL 
);

ALTER TABLE Animal
	ADD  PRIMARY KEY (animal_id);

CREATE TABLE Review
(
	post_id              INTEGER NOT NULL ,
	title                CHAR(18) NOT NULL ,
	content              CHAR(18) NULL ,
	creationDate         DATE NULL ,
	image                BINARY_DOUBLE NULL ,
	writer               CHAR(20) NOT NULL ,
	animal_id            INTEGER NOT NULL 
);

ALTER TABLE Review
	ADD  PRIMARY KEY (post_id);

CREATE TABLE AdoptApply
(
	apply_id             INTEGER NOT NULL ,
	content              CHAR(50) NULL ,
	matched              INT NULL ,
	user_id              CHAR(20) NULL ,
	animal_id            INTEGER NULL ,
	apply_date           DATE NULL ,
	living_environment   CHAR(40) NULL ,
	have_pets            CHAR(30) NULL 
);

ALTER TABLE AdoptApply
	ADD  PRIMARY KEY (apply_id);

CREATE TABLE category
(
	species              CHAR(18) NULL ,
	character            CHAR(18) NULL ,
	category_id          CHAR(18) NOT NULL 
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
