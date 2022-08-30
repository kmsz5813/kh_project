SELECT * FROM CUSTOMER_ACCOUNT;
SELECT * FROM SELLER_ACCOUNT;
SELECT * FROM ADM_ACCOUNT;
SELECT * FROM COMMUNITY;
SELECT * FROM COMMUNITY_COMMENT;
SELECT * FROM COMMUNITY_MAIN;
SELECT * FROM SEL_ITEM;
<<<<<<< HEAD
SELECT * FROM 
테스트에요요요요요ㅛ
ㅁㄴㅇㄻ
ㄻㄴㅇㄻㄴddㅇ
=======
SELECT * FROM SEL_ITEM_COMMENT;
SELECT * FROM FILE_UPLOAD;
SELECT * FROM MESSAGE;
SELECT * FROM LIKE_ITEM;
SELECT * FROM REPORT;
>>>>>>> refs/remotes/origin/정종두



DROP TABLE CUSTOMER_ACCOUNT;
DROP TABLE SELLER_ACCOUNT;
DROP TABLE ADM_ACCOUNT;
DROP TABLE COMMUNITY;
DROP TABLE COMMUNITY_COMMENT;
DROP TABLE COMMUNITY_MAIN;
DROP TABLE SEL_ITEM;
DROP TABLE SEL_ITEM_COMMENT;
DROP TABLE FILE_UPLOAD;
DROP TABLE MESSAGE;
DROP TABLE LIKE_ITEM;
DROP TABLE REPORT;



CREATE SEQUENCE COMMUNITY_SEQ NOCACHE;
CREATE SEQUENCE COMMUNITY_COMMENT_SEQ NOCACHE;
CREATE SEQUENCE COMMUNITY_MAIN_SEQ NOCACHE;
CREATE SEQUENCE SEL_ITEM_SEQ NOCACHE;
CREATE SEQUENCE SEL_ITEM_COMMENT_SEQ NOCACHE;
CREATE SEQUENCE FILE_UPLOAD_SEQ NOCACHE;
CREATE SEQUENCE MESSAGE_SEQ NOCACHE;
CREATE SEQUENCE REPORT_SEQ NOCACHE;




CREATE TABLE CUSTOMER_ACCOUNT(
			CUS_EMAIL VARCHAR2(30) PRIMARY KEY,
			CUS_NAME VARCHAR2(20) UNIQUE,
			CUS_PW VARCHAR2(50) NOT NULL,
			CUS_JOB VARCHAR2(50) NOT NULL,
			CUS_FIELD VARCHAR2(50) NOT NULL,
			CUS_INTEREST VARCHAR2(200) NOT NULL,
			CUS_INDEX NUMBER DEFAULT(10),
			CUS_SENDEMAIL VARCHAR2(1) DEFAULT('N') CHECK(CUS_SENDEMAIL IN('Y', 'N')));

INSERT INTO CUSTOMER_ACCOUNT(CUS_EMAIL, CUS_NAME, CUS_PW, CUS_JOB, CUS_FIELD, CUS_INTEREST, CUS_SENDEMAIL)
VALUES('테스트1', '테스트1', '테스트1', '테스트1', '테스트1', 'ㅌ[스ㅡ1', 'Y' );


CREATE TABLE SELLER_ACCOUNT(
			 SEL_EMAIL VARCHAR2(30) PRIMARY KEY,
			SEL_NAME VARCHAR2(20) UNIQUE,
			SEL_PW VARCHAR2(30) NOT NULL,
			SEL_JOB VARCHAR2(50) NOT NULL,
			SEL_FIELD VARCHAR2(50) NOT NULL,
			SEL_INTEREST VARCHAR2(200) NOT NULL,
			SEL_INDEX NUMBER DEFAULT(20),
			SEL_SENDEMAIL VARCHAR2(1) DEFAULT('N') CHECK(SEL_SENDEMAIL IN('Y', 'N')));

CREATE TABLE ADM_ACCOUNT(
			ADM_EMAIL VARCHAR2(30) PRIMARY KEY,
			ADM_NAME VARCHAR2(20) UNIQUE,
			ADM_PW VARCHAR2(20) NOT NULL
			ADM_INDEX NUMBER DEFAULT(30));
		
CREATE TABLE COMMUNITY(
			 CUM_ID NUMBER PRIMARY KEY,
			 CUM_CUSNAME VARCHAR2(20),
			 CUM_SELNAME VARCHAR2(20),
			 CUM_MAIN VARCHAR2(20) NOT NULL,
			 CUM_TITLE VARCHAR2(50) NOT NULL,
			 CUM_CONTENT VARCHAR2(500) NOT NULL,
			 CUM_WRITEDAY DATE DEFAULT(SYSDATE),
			 CUM_LIKE NUMBER DEFAULT(0),
			 CUM_VIEW NUMBER DEFAULT(0),
			 CUM_REPORT NUMBER DEFAULT(0) UNIQUE,
			 CONSTRAINT COMMUNITY_CUM_CUSNAME_FK FOREIGN KEY(CUM_CUSNAME) REFERENCES CUSTOMER_ACCOUNT(CUS_NAME),
			 CONSTRAINT COMMUNITY_CUM_SELNAME_FK FOREIGN KEY(CUM_SELNAME) REFERENCES SELLER_ACCOUNT(SEL_NAME)
			 );



CREATE TABLE COMMUNITY_COMMENT(
			 CUM_COM_ID NUMBER PRIMARY KEY,
			 CUM_COM_BID NUMBER NOT NULL,
			 CUM_COM_CUSNAME VARCHAR2(20),
			 CUM_COM_SELNAME VARCHAR2(20),
			 CUM_COM_CONTENT VARCHAR2(500) NOT NULL,
			 CUM_COM_WRITEDAY DATE DEFAULT(SYSDATE),
			 CUM_COM_LIKE NUMBER DEFAULT(0),
			 CONSTRAINT COMMUNITY_COMMENT_CUM_COM_CUSNAME_FK FOREIGN KEY(CUM_COM_CUSNAME) REFERENCES CUSTOMER_ACCOUNT(CUS_NAME),
			 CONSTRAINT COMMUNITY_COMMENT_CUM_COM_SELNAME_FK FOREIGN KEY(CUM_COM_SELNAME) REFERENCES SELLER_ACCOUNT(SEL_NAME)
			);

CREATE TABLE COMMUNITY_MAIN(
			 MAIN_ID NUMBER PRIMARY KEY,
			 MAIN_NAME VARCHAR2(20),
			 MAIN_TITLE VARCHAR2(20) NOT NULL,
			 MAIN_CONTENT VARCHAR2(500) NOT NULL,
			 MAIN_WRITEDAY DATE DEFAULT(SYSDATE),
			 MAIN_LIKE NUMBER DEFAULT(0),
			 MAIN_VIEW NUMBER DEFAULT(0),
			 CONSTRAINT COMMUNITY_MAIN_MAIN_NAME_FK FOREIGN KEY(MAIN_NAME) REFERENCES ADM_ACCOUNT(ADM_NAME));

CREATE TABLE SEL_ITEM(
			 SEL_ID NUMBER PRIMARY KEY,
			 SEL_CUSNAME VARCHAR2(20),
			 SEL_SELNAME VARCHAR2(20),
			 SEL_INTEREST VARCHAR2(200) NOT NULL,
			 SEL_LOCATION VARCHAR2(20) NOT NULL,
			 SEL_TITLE VARCHAR2(50) NOT NULL,
			 SEL_CONTENT VARCHAR2(500) NOT NULL,
			 SEL_WRITEDAY DATE DEFAULT(SYSDATE),
			 SEL_LIKE NUMBER DEFAULT(0),
			 SEL_VIEW NUMBER DEFAULT(0),
			 SEL_REPORT NUMBER DEFAULT(0) UNIQUE,
			 CONSTRAINT SEL_ITEM_SEL_CUSNAME_FK FOREIGN KEY(SEL_CUSNAME) REFERENCES CUSTOMER_ACCOUNT(CUS_NAME),
			 CONSTRAINT SEL_ITEM_SEL_SELNAME_FK FOREIGN KEY(SEL_SELNAME) REFERENCES SELLER_ACCOUNT(SEL_NAME)
			);

CREATE TABLE SEL_ITEM_COMMENT(
			 ITEM_COM_ID NUMBER PRIMARY KEY,
			 ITEM_COM_BID NUMBER,
			 ITEM_COM_CUSNAME VARCHAR2(20),
			 ITEM_COM_SELNAME VARCHAR2(20),
			 ITEM_COM_CONTENT VARCHAR2(500) NOT NULL,
			 ITEM_COM_WRITEDAY DATE DEFAULT(SYSDATE),
			 CONSTRAINT SEL_ITEM_COMMENT_ITEM_COM_CUSNAME_FK FOREIGN KEY(ITEM_COM_CUSNAME) REFERENCES CUSTOMER_ACCOUNT(CUS_NAME),
			 CONSTRAINT SEL_ITEM_COMMENT_ITEM_COM_SELNAME_FK FOREIGN KEY(ITEM_COM_SELNAME) REFERENCES SELLER_ACCOUNT(SEL_NAME)
			 );

CREATE TABLE FILE_UPLOAD(
	FILE_ID NUMBER, 							--순번
	FILE_BID NUMBER,							--게시글 번호
	FILENAME VARCHAR2(200) NOT NULL,	--파일명
	UUIDNAME VARCHAR2(36) NOT NULL,     --UUID명
	LOCATION VARCHAR2(500) NOT NULL,	--실제 저장위치
	URL VARCHAR2(500) NOT NULL,			--업로드한 파일을 찾을 수 있는 URL주소
	FILESIZE NUMBER DEFAULT(0),			--파일크기
	CONTENTTYPE VARCHAR2(100) NOT NULL, --파일종류
	CONSTRAINT FILEUPLOAD_FILE_ID_PK PRIMARY KEY(FILE_ID),
	CONSTRAINT FILEUPLOAD_FILE_BID_FK FOREIGN KEY(FILE_BID) REFERENCES SEL_ITEM(SEL_ID)
);		

CREATE TABLE MESSAGE(
		     MSG_ID NUMBER PRIMARY KEY,
		     MSG_CUS_SENDER VARCHAR2(20),
		     MSG_SEL_SENDER VARCHAR2(20),
		     MSG_CUS_RECEIVER VARCHAR2(20),
		     MSG_SEL_RECEIVER VARCHAR2(20),
		     MSG_TITLE VARCHAR2(50) NOT NULL,
		     MSG_CONTENT VARCHAR2(500),
		     CONSTRAINT MESSAGE_MSG_CUS_SENDER_FK FOREIGN KEY(MSG_CUS_SENDER) REFERENCES CUSTOMER_ACCOUNT(CUS_NAME),
			 CONSTRAINT MESSAGE_MSG_SEL_SENDER_FK FOREIGN KEY(MSG_SEL_SENDER) REFERENCES SELLER_ACCOUNT(SEL_NAME),
			 CONSTRAINT MESSAGE_ITEM_MSG_CUS_RECEIVER_FK FOREIGN KEY(MSG_CUS_RECEIVER) REFERENCES CUSTOMER_ACCOUNT(CUS_NAME),
			 CONSTRAINT MESSAGE_ITEM_MSG_SEL_RECEIVER_FK FOREIGN KEY(MSG_SEL_RECEIVER) REFERENCES SELLER_ACCOUNT(SEL_NAME)
			 );

CREATE TABLE LIKE_ITEM(
			 LIKE_NAME VARCHAR2(20),
			 LIKE_ITEM NUMBER,
			 LIKE_LIKE NUMBER DEFAULT(0),
			 CONSTRAINT LIKE_ITEM_LIKE_NAME_FK FOREIGN KEY(LIKE_NAME) REFERENCES CUSTOMER_ACCOUNT(CUS_NAME),
			 CONSTRAINT LIKE_ITEM_LIKE_ITEM_FK FOREIGN KEY(LIKE_ITEM) REFERENCES SEL_ITEM(SEL_ID));
			
CREATE TABLE REPORT(
			 REPORT_ID NUMBER,
			 CUM_REPORT NUMBER,
			 SEL_REPORT NUMBER,
			 REPORT_MAIN VARCHAR2(200) NOT NULL,
			 REPORT_CONTENT VARCHAR2(500) NOT NULL,
			 REPORT_WRITEDAY DATE DEFAULT(SYSDATE),
			 CONSTRAINT REPORT_CUM_REPORT_FK FOREIGN KEY(CUM_REPORT) REFERENCES COMMUNITY(CUM_REPORT),
			 CONSTRAINT REPORT_SEL_REPORT_FK FOREIGN KEY(SEL_REPORT) REFERENCES SEL_ITEM(SEL_REPORT));


			