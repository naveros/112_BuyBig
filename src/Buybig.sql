DROP SEQUENCE SEQ_ID_USER;
DROP SEQUENCE SEQ_ID_BOOK;
DROP SEQUENCE SEQ_ID_PURCHASE;
DROP SEQUENCE SEQ_ID_ORDER;

DROP TABLE user 		CASCADE CONSTRAINTS PURGE;
DROP TABLE book        	CASCADE CONSTRAINTS PURGE;
DROP TABLE purchase    	CASCADE CONSTRAINTS PURGE;
DROP TABLE order    	CASCADE CONSTRAINTS PURGE;

CREATE SEQUENCE SEQ_ID_USER      	START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_ID_BOOK       	START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_ID_PURCHASE     START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_ID_ORDER 		START WITH 1 INCREMENT BY 1;

CREATE TABLE user (idUser   NUMBER       CHECK (idMembre > 0),
                     username   VARCHAR(100) NOT NULL,
                     firstname  VARCHAR(100) NOT NULL,
                     lastname   VARCHAR(100) NOT NULL,
                     adress		VARCHAR(100) NOT NULL,      
                     CONSTRAINT pk_user    PRIMARY KEY (idbook));

CREATE TABLE book (idBook         	NUMBER       CHECK (idBook > 0),
                    title           VARCHAR(100) NOT NULL,
                    isbn     		VARCHAR(100) NOT NULL,
                    author          VARCHAR(100) NOT NULL,
                    price           NUMBER    NOT NULL,
                    CONSTRAINT      pk_book     PRIMARY KEY (idBook));

CREATE TABLE purchase (idPurchase     NUMBER     CHECK (idPret > 0),
                   idUser   NUMBER     CHECK (idUser > 0),
                   idBook    NUMBER     CHECK (idBook > 0),
                   purchaseDate   TIMESTAMP,
                   CONSTRAINT pk_perchase PRIMARY KEY (idPret),
                   CONSTRAINT fk_purchase_user    FOREIGN KEY (idUser) 	REFERENCES user (idUser)   ON DELETE CASCADE,
                   CONSTRAINT fk_pucchase_book    FOREIGN KEY (idBook)  REFERENCES book (idBook)   ON DELETE CASCADE);

CREATE TABLE order (idOrder  NUMBER      CHECK (idReservation > 0),
                          idUser         NUMBER     CHECK (idUser > 0),
                          idBook         NUMBER     CHECK (idBook > 0),
                          dateAdded		 TIMESTAMP,
                          CONSTRAINT      pk_order  PRIMARY KEY (idOrder),
                          CONSTRAINT fk_order_user    FOREIGN KEY (idUser) 	REFERENCES user (idUser)   ON DELETE CASCADE,
                   		  CONSTRAINT fk_order_book    FOREIGN KEY (idBook)  REFERENCES book (idBook)   ON DELETE CASCADE);