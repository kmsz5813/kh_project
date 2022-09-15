SELECT * FROM ACCOUNTS;
SELECT * FROM COMMUNITY;
SELECT * FROM COMMUNITY_COMMENT;
SELECT * FROM COMMUNITY_MAIN;
SELECT * FROM SEL_ITEM;
SELECT * FROM SEL_ITEM_COMMENT;
SELECT * FROM FILE_UPLOAD;
SELECT * FROM MESSAGE;
SELECT * FROM LIKE_ITEM;
SELECT * FROM REPORT;



DROP TABLE ACCOUNTS;
DROP TABLE COMMUNITY;
DROP TABLE COMMUNITY_COMMENT;
DROP TABLE COMMUNITY_MAIN;
DROP TABLE SEL_ITEM;
DROP TABLE SEL_ITEM_COMMENT;
DROP TABLE FILE_UPLOAD;
DROP TABLE MESSAGE;
DROP TABLE LIKE_ITEM;
DROP TABLE REPORT;

CREATE SEQUENCE ACCOUNTS_SEQ NOCACHE;
CREATE SEQUENCE COMMUNITY_SEQ NOCACHE;
CREATE SEQUENCE COMMUNITY_COMMENT_SEQ NOCACHE;
CREATE SEQUENCE COMMUNITY_MAIN_SEQ NOCACHE;
CREATE SEQUENCE SEL_ITEM_SEQ NOCACHE;
CREATE SEQUENCE SEL_ITEM_COMMENT_SEQ NOCACHE;
CREATE SEQUENCE FILE_UPLOAD_SEQ NOCACHE;
CREATE SEQUENCE MESSAGE_SEQ NOCACHE;
CREATE SEQUENCE REPORT_SEQ NOCACHE;

SELECT * FROM ACCOUNTS;
DROP TABLE ACCOUNTS;

--ACCOUNTS계정중 INDEX 10번은 일반회원 20번은 판매자 30번은 관리인
--AC_NUMBER는 회원번호

CREATE TABLE ACCOUNTS(
			AC_NUMBER NUMBER NOT NULL,
			AC_EMAIL VARCHAR2(30) PRIMARY KEY,
			AC_NAME VARCHAR2(20) UNIQUE,
			AC_PW VARCHAR2(50) NOT NULL,
			AC_JOB VARCHAR2(50) NOT NULL,
			AC_FIELD VARCHAR2(50) NOT NULL,
			AC_INTEREST VARCHAR2(200) NOT NULL,
			AC_INDEX NUMBER DEFAULT(10),
			AC_SIGNDAY DATE DEFAULT(SYSDATE), 
			AC_SENDEMAIL VARCHAR2(1) DEFAULT('N') CHECK(AC_SENDEMAIL IN('Y', 'N')),
			AC_IP VARCHAR2(30));

		
CREATE TABLE COMMUNITY(
			 CUM_ID NUMBER PRIMARY KEY,
			 CUM_NAME VARCHAR2(20),
			 CUM_MAIN VARCHAR2(20) NOT NULL,
			 CUM_TITLE VARCHAR2(50) NOT NULL,
			 CUM_CONTENT VARCHAR2(500) NOT NULL,
			 CUM_WRITEDAY DATE DEFAULT(SYSDATE),
			 CUM_LIKE NUMBER DEFAULT(0),
			 CUM_VIEW NUMBER DEFAULT(0),
			 CUM_REPORT NUMBER DEFAULT(0) UNIQUE,
			 CONSTRAINT COMMUNITY_CUM_NAME_FK FOREIGN KEY(CUM_NAME) REFERENCES ACCOUNTS(AC_NAME)
			 );



CREATE TABLE COMMUNITY_COMMENT(
			 CUM_COM_ID NUMBER PRIMARY KEY,
			 CUM_COM_BID NUMBER NOT NULL,
			 CUM_COM_NAME VARCHAR2(20),
			 CUM_COM_CONTENT VARCHAR2(500) NOT NULL,
			 CUM_COM_WRITEDAY DATE DEFAULT(SYSDATE),
			 CUM_COM_LIKE NUMBER DEFAULT(0),
			 CONSTRAINT COMMUNITY_COMMENT_CUM_COM_NAME_FK FOREIGN KEY(CUM_COM_NAME) REFERENCES ACCOUNTS(AC_NAME)
			);

CREATE TABLE COMMUNITY_MAIN(
			 MAIN_ID NUMBER PRIMARY KEY,
			 MAIN_NAME VARCHAR2(20),
			 MAIN_TITLE VARCHAR2(20) NOT NULL,
			 MAIN_CONTENT VARCHAR2(500) NOT NULL,
			 MAIN_WRITEDAY DATE DEFAULT(SYSDATE),
			 MAIN_LIKE NUMBER DEFAULT(0),
			 MAIN_VIEW NUMBER DEFAULT(0),
			 CONSTRAINT COMMUNITY_MAIN_MAIN_NAME_FK FOREIGN KEY(MAIN_NAME) REFERENCES ACCOUNTS(AC_NAME));

CREATE TABLE SEL_ITEM(
			 SEL_ID NUMBER PRIMARY KEY,
			 SEL_NAME VARCHAR2(20),
			 SEL_INTEREST VARCHAR2(200) NOT NULL,
			 SEL_LOCATION VARCHAR2(20) NOT NULL,
			 SEL_TITLE VARCHAR2(50) NOT NULL,
			 SEL_CONTENT VARCHAR2(500) NOT NULL,
			 SEL_WRITEDAY DATE DEFAULT(SYSDATE),
			 SEL_LIKE NUMBER DEFAULT(0),
			 SEL_VIEW NUMBER DEFAULT(0),
			 SEL_REPORT NUMBER DEFAULT(0) UNIQUE,
			 CONSTRAINT SEL_ITEM_SEL_NAME_FK FOREIGN KEY(SEL_NAME) REFERENCES ACCOUNTS(AC_NAME)
			);

CREATE TABLE SEL_ITEM_COMMENT(
			 ITEM_COM_ID NUMBER PRIMARY KEY,
			 ITEM_COM_BID NUMBER,
			 ITEM_COM_NAME VARCHAR2(20),
			 ITEM_COM_CONTENT VARCHAR2(500) NOT NULL,
			 ITEM_COM_WRITEDAY DATE DEFAULT(SYSDATE),
			 CONSTRAINT SEL_ITEM_COMMENT_ITEM_COM_NAME_FK FOREIGN KEY(ITEM_COM_NAME) REFERENCES ACCOUNTS(AC_NAME)
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
		     MSG_SENDER VARCHAR2(20),
		     MSG_RECEIVER VARCHAR2(20),
		     MSG_TITLE VARCHAR2(50) NOT NULL,
		     MSG_CONTENT VARCHAR2(500),
		     CONSTRAINT MESSAGE_MSG_SENDER_FK FOREIGN KEY(MSG_SENDER) REFERENCES ACCOUNTS(AC_NAME),
			 CONSTRAINT MESSAGE_MSG_RECEIVER_FK FOREIGN KEY(MSG_RECEIVER) REFERENCES ACCOUNTS(AC_NAME)
			  );

CREATE TABLE LIKE_ITEM(
			 LIKE_NAME VARCHAR2(20),
			 LIKE_ITEM NUMBER,
			 LIKE_LIKE NUMBER DEFAULT(0),
			 CONSTRAINT LIKE_ITEM_LIKE_NAME_FK FOREIGN KEY(LIKE_NAME) REFERENCES ACCOUNTS(AC_NAME),
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

CREATE TABLE BLACKLIST(
			 BLACK_EMAIL VARCHAR2(30) PRIMARY KEY,
			 IP_ADDRESS VARCHAR2(30),
			 BANNED VARCHAR(2) DEFAULT('N'));
			