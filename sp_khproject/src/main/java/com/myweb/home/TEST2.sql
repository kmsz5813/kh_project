DROP TABLE NOTICE;
DROP TABLE QUESTION_STATICS;
DROP TABLE FINDSTU_STATICS;
DROP TABLE FINDPRO_STATICS;
DROP TABLE LIFE_STATICS;
DROP TABLE QUESTION;
DROP TABLE FINDSTU;
DROP TABLE FINDPRO;
DROP TABLE LIFE;
DROP TABLE REPORT;
DROP TABLE LIKE_ITEM;
DROP TABLE MESSAGE;
DROP TABLE FILE_UPLOAD;
DROP TABLE COUPON;
DROP TABLE ISBUY;
DROP TABLE SEL_ITEM_COMMENT;
DROP TABLE SEL_ITEM;
DROP TABLE COMMUNITY_MAIN;
DROP TABLE COMMUNITY_COMMENT;
DROP TABLE COMMUNITY;
DROP TABLE ACCOUNTS;
DROP TABLE BLACKLIST;



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

CREATE SEQUENCE ACCOUNTS_SEQ NOCACHE;
CREATE SEQUENCE COMMUNITY_SEQ NOCACHE;
CREATE SEQUENCE COMMUNITY_COMMENT_SEQ NOCACHE;
CREATE SEQUENCE COMMUNITY_MAIN_SEQ NOCACHE;
CREATE SEQUENCE ISBUY_SEQ NOCACHE;
CREATE SEQUENCE EVENTCOUPON_SEQ NOCACHE;
CREATE SEQUENCE SEL_ITEM_SEQ NOCACHE;
CREATE SEQUENCE SEL_ITEM_COMMENT_SEQ NOCACHE;
CREATE SEQUENCE FILE_UPLOAD_SEQ NOCACHE;
CREATE SEQUENCE MESSAGE_SEQ NOCACHE;
CREATE SEQUENCE REPORT_SEQ NOCACHE;
CREATE SEQUENCE CHATTING_SEQ;

DROP SEQUENCE ACCOUNTS_SEQ;
DROP SEQUENCE COMMUNITY_SEQ;
DROP SEQUENCE COMMUNITY_COMMENT_SEQ;
DROP SEQUENCE COMMUNITY_MAIN_SEQ;
DROP SEQUENCE ISBUY_SEQ NOCACHE;
DROP SEQUENCE EVENTCOUPON_SEQ NOCACHE;
DROP SEQUENCE SEL_ITEM_SEQ;
DROP SEQUENCE SEL_ITEM_COMMENT_SEQ;
DROP SEQUENCE FILE_UPLOAD_SEQ;
DROP SEQUENCE MESSAGE_SEQ;
DROP SEQUENCE REPORT_SEQ;

SELECT * FROM ACCOUNTS;
--ACCOUNTS계정중 INDEX 10번은 일반회원 20번은 판매자 30번은 관리인
--AC_NUMBER는 회원번호
DROP TABLE ACCOUNTS;


CREATE TABLE ACCOUNTS(
			AC_NUMBER NUMBER NOT NULL,
			AC_EMAIL VARCHAR2(30) PRIMARY KEY,
			AC_NAME VARCHAR2(40) UNIQUE,
			AC_PW VARCHAR2(20) NOT NULL,
			AC_JOB VARCHAR2(50) NOT NULL,
			AC_FIELD VARCHAR2(50) NOT NULL,
			AC_INTEREST VARCHAR2(200) NOT NULL,
			AC_INDEX NUMBER DEFAULT(10),
			AC_SIGNDAY DATE DEFAULT(SYSDATE),
			AC_POINT NUMBER DEFAULT(100),	-- 보유 포인트(기본값 100원)
			AC_SENDEMAIL VARCHAR2(1) DEFAULT('N') CHECK(AC_SENDEMAIL IN('Y', 'N')),
			AC_IP VARCHAR2(30));
		
UPDATE ACCOUNTS SET AC_POINT = 1000000 WHERE AC_EMAIL = 'isckd@naver.com';	
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(200, 'findofficial9@gmail.com', '관리자', '123123', '직업1', '비즈니스분야1', '관심사1', 30);

INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(101, '1@naver.com', '닉네임1', '123123', '직업1', '비즈니스분야1', '관심사1', 10);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(102, '2@naver.com', '닉네임2', '123123', '직업2', '비즈니스분야2', '관심사2', 10);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(103, '3@naver.com', '닉네임3', '123123', '직업3', '비즈니스분야3', '관심사3', 10);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(104, '4@naver.com', '닉네임4', '123123', '직업4', '비즈니스분야4', '관심사4', 10);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(105, '5@naver.com', '닉네임5', '123123', '직업5', '비즈니스분야5', '관심사5', 10);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(106, '6@naver.com', '닉네임6', '123123', '직업6', '비즈니스분야6', '관심사6', 10);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(107, '7@naver.com', '닉네임7', '123123', '직업7', '비즈니스분야7', '관심사7', 10);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(108, '8@naver.com', '닉네임8', '123123', '직업8', '비즈니스분야8', '관심사8', 10);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(109, '9@naver.com', '닉네임9', '123123', '직업9', '비즈니스분야9', '관심사9', 10);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(110, '10@naver.com', '닉네임10', '123123', '직업10', '비즈니스분야10', '관심사10', 10);

INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(111, '11@naver.com', '닉네임11', '123123', '직업1', '비즈니스분야1', '관심사1', 20);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(112, '12@naver.com', '닉네임12', '123123', '직업2', '비즈니스분야2', '관심사2', 20);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(113, '13@naver.com', '닉네임13', '123123', '직업3', '비즈니스분야3', '관심사3', 20);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(114, '14@naver.com', '닉네임14', '123123', '직업4', '비즈니스분야4', '관심사4', 20);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(115, '15@naver.com', '닉네임15', '123123', '직업5', '비즈니스분야5', '관심사5', 20);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(116, '16@naver.com', '닉네임16', '123123', '직업6', '비즈니스분야6', '관심사6', 20);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(117, '17@naver.com', '닉네임17', '123123', '직업7', '비즈니스분야7', '관심사7', 20);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(118, '18@naver.com', '닉네임18', '123123', '직업8', '비즈니스분야8', '관심사8', 20);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(119, '19@naver.com', '닉네임19', '123123', '직업9', '비즈니스분야9', '관심사9', 20);
INSERT INTO ACCOUNTS(AC_NUMBER, AC_EMAIL, AC_NAME, AC_PW, AC_JOB, AC_FIELD, AC_INTEREST, AC_INDEX) VALUES(120, '20@naver.com', '닉네임20', '123123', '직업10', '비즈니스분야10', '관심사10', 20);

CREATE TABLE COMMUNITY(
			 CUM_ID NUMBER PRIMARY KEY,
			 CUM_NAME VARCHAR2(20),
			 CUM_TITLE VARCHAR2(50) NOT NULL,
			 CUM_SUBJECT VARCHAR2(30) NOT NULL,
			 CUM_CONTENT VARCHAR2(500) NOT NULL,
			 CUM_WRITEDAY DATE DEFAULT(SYSDATE),
			 CUM_LIKE NUMBER DEFAULT(0),
			 CUM_VIEW NUMBER DEFAULT(0),
			 CUM_REPORT NUMBER DEFAULT(0),
			 CONSTRAINT COMMUNITY_CUM_NAME_FK FOREIGN KEY(CUM_NAME) REFERENCES ACCOUNTS(AC_NAME)
			 );

CREATE TABLE COMMUNITY_COMMENT(		-- 게시글의 하위목록
			 CUM_COM_ID NUMBER PRIMARY KEY,
			 CUM_COM_BID NUMBER NOT NULL,
			 CUM_COM_NAME VARCHAR2(20),
			 CUM_COM_CONTENT VARCHAR2(500) NOT NULL,
			 CUM_COM_WRITEDAY DATE DEFAULT(SYSDATE),
			 CUM_COM_LIKE NUMBER DEFAULT(0),
			 CONSTRAINT COMMUNITY_COMMENT_CUM_COM_NAME_FK FOREIGN KEY(CUM_COM_NAME) REFERENCES ACCOUNTS(AC_NAME)
			);

CREATE TABLE SEL_ITEM(
			 SEL_ID NUMBER PRIMARY KEY,				-- 상품번호
			 SEL_NAME VARCHAR2(20),					-- 판매자
			 SEL_FIELD VARCHAR2(200) NOT NULL,		-- 분야
			 SEL_LOCATION VARCHAR2(20) NOT NULL,	-- 거주위치
			 SEL_TITLE VARCHAR2(50) NOT NULL,		-- 제목
			 SEL_CONTENT VARCHAR2(500) NOT NULL,	-- 내용(텍스트)
			 SEL_WRITEDAY DATE DEFAULT(SYSDATE),	-- 작성일자
			 SEL_NUMBER NUMBER DEFAULT(0),			-- 판매횟수
			 SEL_PRICE NUMBER DEFAULT(1000),		-- 가격
			 SEL_VIEW NUMBER DEFAULT(0),			-- 조회수
			 SEL_REPORT NUMBER DEFAULT(0),			-- 신고 수
			 CONSTRAINT SEL_ITEM_SEL_NAME_FK FOREIGN KEY(SEL_NAME) REFERENCES ACCOUNTS(AC_NAME)
			);
				
SELECT * FROM SEL_ITEM;

UPDATE SEL_ITEM SET SEL_NUMBER = SEL_NUMBER + 1
 WHERE SEL_ID = 23;

INSERT INTO SEL_ITEM(SEL_ID, SEL_NAME, SEL_FIELD, SEL_LOCATION, SEL_TITLE, SEL_CONTENT)
VALUES(SEL_ITEM_SEQ.NEXTVAL, '닉네임11', '분야1', '지역1', '제목1', '내용1');
INSERT INTO SEL_ITEM(SEL_ID, SEL_NAME, SEL_FIELD, SEL_LOCATION, SEL_TITLE, SEL_CONTENT)
VALUES(SEL_ITEM_SEQ.NEXTVAL, '닉네임12', '분야2', '지역2', '제목2', '내용2');
INSERT INTO SEL_ITEM(SEL_ID, SEL_NAME, SEL_FIELD, SEL_LOCATION, SEL_TITLE, SEL_CONTENT)
VALUES(SEL_ITEM_SEQ.NEXTVAL, '닉네임13', '분야3', '지역3', '제목3', '내용3');
INSERT INTO SEL_ITEM(SEL_ID, SEL_NAME, SEL_FIELD, SEL_LOCATION, SEL_TITLE, SEL_CONTENT)
VALUES(SEL_ITEM_SEQ.NEXTVAL, '닉네임14', '분야4', '지역4', '제목4', '내용4');
INSERT INTO SEL_ITEM(SEL_ID, SEL_NAME, SEL_FIELD, SEL_LOCATION, SEL_TITLE, SEL_CONTENT)
VALUES(SEL_ITEM_SEQ.NEXTVAL, '닉네임15', '분야5', '지역5', '제목5', '내용5');
INSERT INTO SEL_ITEM(SEL_ID, SEL_NAME, SEL_FIELD, SEL_LOCATION, SEL_TITLE, SEL_CONTENT)
VALUES(SEL_ITEM_SEQ.NEXTVAL, '닉네임16', '분야6', '지역6', '제목6', '내용6');

CREATE TABLE ISBUY(
			BUY_NUMBER NUMBER PRIMARY KEY,		-- 구매번호
			BUY_ITEMNUMBER NUMBER NOT NULL,		-- 상품번호
			BUY_BUYER VARCHAR2(20) NOT NULL,	-- 구매자(닉네임)
			BUY_SELLER VARCHAR2(20) NOT NULL,	-- 판매자(닉네임)
			BUY_BUYDAY DATE DEFAULT(SYSDATE),	-- 구매일자
			BUY_PRICE NUMBER NOT NULL,			-- 상품가격
			BUY_USEDPOINT NUMBER DEFAULT(0),	-- 사용한 포인트
			BUY_USEDCOUPON NUMBER,				-- 사용한 쿠폰
			BUY_REALPRICE NUMBER NOT NULL,		-- 실제 구매가격
			CONSTRAINT ISBUY_BUY_ITEMNUMBER_FK FOREIGN KEY(BUY_ITEMNUMBER) REFERENCES SEL_ITEM(SEL_ID),
			CONSTRAINT ISBUY_BUY_BUYER_FK FOREIGN KEY(BUY_BUYER) REFERENCES ACCOUNTS(AC_NAME),
			CONSTRAINT ISBUY_BUY_SELLER_FK FOREIGN KEY(BUY_SELLER) REFERENCES ACCOUNTS(AC_NAME)
		);

SELECT * FROM ISBUY;	
INSERT INTO ISBUY(BUY_NUMBER, BUY_ITEMNUMBER, BUY_BUYER, BUY_SELLER, BUY_PRICE)
 VALUES (ISBUY_SEQ.NEXTVAL, '21', '닉네임1', '닉네임11', '1000');	
	
CREATE TABLE COUPON(
			COUPON_NUMBER NUMBER PRIMARY KEY,		-- 쿠폰 고유번호 (회원마다, 쿠폰마다 다르므로 PK)
			COUPON_NAME VARCHAR2(50) NOT NULL,		-- 쿠폰설명
			COUPON_STARTDATE DATE DEFAULT SYSDATE,	-- 쿠폰 다운로드일
			COUPON_ENDDATE DATE NOT NULL,			-- 쿠폰 마감일
			COUPON_USERNAME VARCHAR2(30) NOT NULL, 	-- 쿠폰 다운받은 회원닉네임
			COUPON_USED VARCHAR2(2), 					-- 사용 전 : NULL, 사용 후 : 'Y'
			COUPON_SALEPERCENT NUMBER DEFAULT(0),	-- 쿠폰 할인 %
			CONSTRAINT COUPON_COUPON_USERNAME_FK FOREIGN KEY(COUPON_USERNAME) REFERENCES ACCOUNTS(AC_NAME)
);
SELECT * FROM COUPON;

SELECT COUNT(*) FROM COUPON
 		 WHERE COUPON_NAME = '오픈이벤트 15% 할인 쿠폰' 
 		   AND COUPON_USERNAME = '닉네임1';

INSERT INTO COUPON(COUPON_NUMBER, COUPON_NAME, COUPON_ENDDATE, COUPON_USERNAME, COUPON_SALEPERCENT)
 VALUES(01010101, '쿠폰1', SYSDATE + 30, '닉네임1', 5);
INSERT INTO COUPON(COUPON_NUMBER, COUPON_NAME, COUPON_ENDDATE, COUPON_USERNAME, COUPON_SALEPERCENT)
 VALUES(01010102, '쿠폰2', SYSDATE + 30, '닉네임1', 10);
INSERT INTO COUPON(COUPON_NUMBER, COUPON_NAME, COUPON_ENDDATE, COUPON_USERNAME, COUPON_SALEPERCENT)
 VALUES(01010103, '쿠폰3', SYSDATE + 30, '닉네임1', 15);
INSERT INTO COUPON(COUPON_NUMBER, COUPON_NAME, COUPON_ENDDATE, COUPON_USERNAME, COUPON_SALEPERCENT)
 VALUES(01010104, '쿠폰4', SYSDATE + 30, '닉네임1', 15);
INSERT INTO COUPON(COUPON_NUMBER, COUPON_NAME, COUPON_ENDDATE, COUPON_USERNAME, COUPON_SALEPERCENT)
 VALUES(01010105, '쿠폰5', SYSDATE + 30, '닉네임1', 15);


CREATE TABLE EVENTCOUPON (	-- 관리자가 이벤트 쿠폰 발급
			EVTCOU_SEQ NUMBER PRIMARY KEY,			-- 이벤트 쿠폰 종류 구분을 위한 시퀀스
			EVTCOU_NAME VARCHAR2(50) UNIQUE,		-- 쿠폰설명
			EVTCOU_STARTDATE DATE DEFAULT SYSDATE,	-- 쿠폰 발급일
			EVTCOU_ENDDATE DATE NOT NULL,			-- 쿠폰 마감일
			EVTCOU_SALEPERCENT NUMBER DEFAULT(0)	-- 쿠폰 할인 %
);
INSERT INTO EVENTCOUPON (EVTCOU_SEQ, EVTCOU_NAME, EVTCOU_ENDDATE, EVTCOU_SALEPERCENT)
 VALUES(EVENTCOUPON_SEQ.NEXTVAL, '이벤트쿠폰1 15%', SYSDATE + 30, 15);
INSERT INTO EVENTCOUPON (EVTCOU_SEQ, EVTCOU_NAME, EVTCOU_ENDDATE, EVTCOU_SALEPERCENT)
 VALUES(EVENTCOUPON_SEQ.NEXTVAL, '이벤트쿠폰2 20%', SYSDATE + 30, 20);
INSERT INTO EVENTCOUPON (EVTCOU_SEQ, EVTCOU_NAME, EVTCOU_ENDDATE, EVTCOU_SALEPERCENT)
 VALUES(EVENTCOUPON_SEQ.NEXTVAL, '이벤트쿠폰3 25%', SYSDATE + 30, 25);
INSERT INTO EVENTCOUPON (EVTCOU_SEQ, EVTCOU_NAME, EVTCOU_ENDDATE, EVTCOU_SALEPERCENT)
 VALUES(EVENTCOUPON_SEQ.NEXTVAL, '이벤트쿠폰4 30%', SYSDATE + 30, 30);
INSERT INTO EVENTCOUPON (EVTCOU_SEQ, EVTCOU_NAME, EVTCOU_ENDDATE, EVTCOU_SALEPERCENT)
 VALUES(EVENTCOUPON_SEQ.NEXTVAL, '이벤트쿠폰5 35%', SYSDATE + 30, 35);

SELECT * FROM EVENTCOUPON;
DROP TABLE EVENTCOUPON;

CREATE TABLE SEL_ITEM_COMMENT(
			 ITEM_COM_ID NUMBER PRIMARY KEY,  -- 댓글번호
			 ITEM_COM_BID NUMBER, -- 게시글의 번호
			 ITEM_COM_NAME VARCHAR2(20),
			 ITEM_COM_CONTENT VARCHAR2(500) NOT NULL,
			 ITEM_COM_WRITEDAY DATE DEFAULT(SYSDATE),
			 CONSTRAINT SEL_ITEM_COMMENT_ITEM_COM_BID_FK FOREIGN KEY(SEL_ITEM_SEL_ID) REFERENCES ACCOUNTS(AC_NAME)
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
SELECT * FROM FILE_UPLOAD;

CREATE TABLE MESSAGE(
		     MSG_ID NUMBER PRIMARY KEY,	
		     MSG_SENDER VARCHAR2(20),	
		     MSG_RECEIVER VARCHAR2(20),	
		     MSG_TITLE VARCHAR2(50) NOT NULL,	
		     MSG_CONTENT VARCHAR2(500),	
		     CONSTRAINT MESSAGE_MSG_SENDER_FK FOREIGN KEY(MSG_SENDER) REFERENCES ACCOUNTS(AC_NAME),
			 CONSTRAINT MESSAGE_MSG_RECEIVER_FK FOREIGN KEY(MSG_RECEIVER) REFERENCES ACCOUNTS(AC_NAME)
			  );
			  
CREATE TABLE CHATTING (
			 CH_CONTENT	VARCHAR2(4000)		NULL
);

CREATE TABLE LIKE_ITEM(		-- 관심상품(장바구니)
			 LIKE_NAME VARCHAR2(20),	-- 본인 닉네임
			 LIKE_ITEM NUMBER,	-- SEL_ID
			 LIKE_LIKE NUMBER DEFAULT(0),	-- 관심상품 갯수?
			 CONSTRAINT LIKE_ITEM_LIKE_NAME_FK FOREIGN KEY(LIKE_NAME) REFERENCES ACCOUNTS(AC_NAME),
			 CONSTRAINT LIKE_ITEM_LIKE_ITEM_FK FOREIGN KEY(LIKE_ITEM) REFERENCES SEL_ITEM(SEL_ID));
			
CREATE TABLE REPORT(
			 REPORT_ID NUMBER,	-- 신고 번호(시퀀스)
			 CUM_REPORT NUMBER,	-- 
			 SEL_REPORT NUMBER,	-- ??
			 REPORT_MAIN VARCHAR2(200) NOT NULL,	-- 
			 REPORT_CONTENT VARCHAR2(500) NOT NULL,	-- 
			 REPORT_WRITEDAY DATE DEFAULT(SYSDATE),
			 CONSTRAINT REPORT_CUM_REPORT_FK FOREIGN KEY(CUM_REPORT) REFERENCES COMMUNITY(CUM_REPORT),
			 CONSTRAINT REPORT_SEL_REPORT_FK FOREIGN KEY(SEL_REPORT) REFERENCES SEL_ITEM(SEL_REPORT));

CREATE TABLE BLACKLIST(
			 BLACK_EMAIL VARCHAR2(30) PRIMARY KEY,
			 IP_ADDRESS VARCHAR2(30) NOT NULL,
			 BANNED VARCHAR(2) DEFAULT('N'));

INSERT INTO BLACKLIST
VALUES('12@NAVER.COM', '127.0.0.1', 'Y');
INSERT INTO BLACKLIST
VALUES('13@NAVER.COM', '127.0.0.2', 'Y');

SELECT IP_ADDRESS FROM BLACKLIST;
SELECT * FROM BLACKLIST;
DROP TABLE BLACKLIST;

--커뮤수정			 




CREATE TABLE NOTICE(
	NOTICE_NO NUMBER PRIMARY KEY,
	NOTICE_TITLE VARCHAR2(50) NOT NULL,
	NOTICE_CONTENT VARCHAR2(500) NOT NULL,
	NOTICE_DATE DATE DEFAULT(SYSDATE)
);

CREATE TABLE QUESTION(
	QUESTION_ID NUMBER PRIMARY KEY,
	USER_NAME VARCHAR2(20),
	QUESTION_TITLE VARCHAR2(50) NOT NULL,
	QUESTION_CONTENT VARCHAR2(500) NOT NULL,
	QUESTION_DATE DATE DEFAULT(SYSDATE),
	QUESTION_LIKE NUMBER DEFAULT(0),
	QUESTION_VIEW NUMBER DEFAULT(0),
	CONSTRAINT QUESTION_USER_NAME_FK FOREIGN KEY(USER_NAME) REFERENCES ACCOUNTS(AC_NAME)
);


CREATE TABLE QUESTION_STATICS(
	   QUESTION_ID NUMBER PRIMARY KEY
	 , USER_NAME VARCHAR2(20)
	 , QUESTION_BID NUMBER
	 , QUESTION_VIEWED VARCHAR2(1) CHECK(QUESTION_VIEWED IN('Y', 'N'))
	 , QUESTION_LATEST_VIEW_DATE DATE
	 , QUESTION_LIKED VARCHAR2(1) CHECK(QUESTION_LIKED IN('Y', 'N'))
	 , CONSTRAINT QUESTION_STATICS_USER_NAME_FK FOREIGN KEY(USER_NAME) REFERENCES ACCOUNTS(AC_NAME)
	 , CONSTRAINT QUESTION_STATICS_QUESTION_BID_FK FOREIGN KEY(QUESTION_BID) REFERENCES QUESTION(QUESTION_ID)
);

CREATE TABLE FINDSTU(
	FINDSTU_ID NUMBER PRIMARY KEY,
	USER_NAME VARCHAR2(20),
	FINDSTU_TITLE VARCHAR2(50) NOT NULL,
	FINDSTU_CONTENT VARCHAR2(500) NOT NULL,
	FINDSTU_DATE DATE DEFAULT(SYSDATE),
	FINDSTU_LIKE NUMBER DEFAULT(0),
	FINDSTU_VIEW NUMBER DEFAULT(0),
	CONSTRAINT FINDSTU_USER_NAME_FK FOREIGN KEY(USER_NAME) REFERENCES ACCOUNTS(AC_NAME)
);


CREATE TABLE FINDSTU_STATICS(
	   FINDSTU_ID NUMBER PRIMARY KEY
	 , USER_NAME VARCHAR2(20)
	 , FINDSTU_BID NUMBER
	 , FINDSTU_VIEWED VARCHAR2(1) CHECK(FINDSTU_VIEWED IN('Y', 'N'))
	 , FINDSTU_LATEST_VIEW_DATE DATE
	 , FINDSTU_LIKED VARCHAR2(1) CHECK(FINDSTU_LIKED IN('Y', 'N'))
	 , CONSTRAINT FINDSTU_STATICS_USER_NAME_FK FOREIGN KEY(USER_NAME) REFERENCES ACCOUNTS(AC_NAME)
	 , CONSTRAINT FINDSTU_STATICS_FINDSTU_BID_FK FOREIGN KEY(FINDSTU_BID) REFERENCES FINDSTU(FINDSTU_ID)
);

CREATE TABLE FINDPRO(
	FINDPRO_ID NUMBER PRIMARY KEY,
	USER_NAME VARCHAR2(20),
	FINDPRO_TITLE VARCHAR2(50) NOT NULL,
	FINDPRO_CONTENT VARCHAR2(500) NOT NULL,
	FINDPRO_DATE DATE DEFAULT(SYSDATE),
	FINDPRO_LIKE NUMBER DEFAULT(0),
	FINDPRO_VIEW NUMBER DEFAULT(0),
	CONSTRAINT FINDPRO_USER_NAME_FK FOREIGN KEY(USER_NAME) REFERENCES ACCOUNTS(AC_NAME)
);


CREATE TABLE FINDPRO_STATICS(
	   FINDPRO_ID NUMBER PRIMARY KEY
	 , USER_NAME VARCHAR2(20)
	 , FINDPRO_BID NUMBER
	 , FINDPRO_VIEWED VARCHAR2(1) CHECK(FINDPRO_VIEWED IN('Y', 'N'))
	 , FINDPRO_LATEST_VIEW_DATE DATE
	 , FINDPRO_LIKED VARCHAR2(1) CHECK(FINDPRO_LIKED IN('Y', 'N'))
	 , CONSTRAINT FINDPRO_STATICS_USER_NAME_FK FOREIGN KEY(USER_NAME) REFERENCES ACCOUNTS(AC_NAME)
	 , CONSTRAINT FINDPRO_STATICS_FINDPRO_BID_FK FOREIGN KEY(FINDPRO_BID) REFERENCES FINDPRO(FINDPRO_ID)
);

CREATE TABLE LIFE(
	LIFE_ID NUMBER PRIMARY KEY,
	USER_NAME VARCHAR2(20),
	LIFE_TITLE VARCHAR2(50) NOT NULL,
	LIFE_CONTENT VARCHAR2(500) NOT NULL,
	LIFE_DATE DATE DEFAULT(SYSDATE),
	LIFE_LIKE NUMBER DEFAULT(0),
	LIFE_VIEW NUMBER DEFAULT(0),
	CONSTRAINT LIFE_USER_NAME_FK FOREIGN KEY(USER_NAME) REFERENCES ACCOUNTS(AC_NAME)
);


CREATE TABLE LIFE_STATICS(
	   LIFE_ID NUMBER PRIMARY KEY
	 , USER_NAME VARCHAR2(20)
	 , LIFE_BID NUMBER
	 , LIFE_VIEWED VARCHAR2(1) CHECK(LIFE_VIEWED IN('Y', 'N'))
	 , LIFE_LATEST_VIEW_DATE DATE
	 , LIFE_LIKED VARCHAR2(1) CHECK(LIFE_LIKED IN('Y', 'N'))
	 , CONSTRAINT LIFE_STATICS_USER_NAME_FK FOREIGN KEY(USER_NAME) REFERENCES ACCOUNTS(AC_NAME)
	 , CONSTRAINT LIFE_STATICS_LIFE_BID_FK FOREIGN KEY(LIFE_BID) REFERENCES LIFE(LIFE_ID)
);


CREATE SEQUENCE NOTICE_SEQ NOCACHE;
CREATE SEQUENCE QUESTION_SEQ NOCACHE;
CREATE SEQUENCE QUESTION_STATICS_SEQ NOCACHE;
CREATE SEQUENCE FINDSTU_SEQ NOCACHE;
CREATE SEQUENCE FINDSTU_STATICS_SEQ NOCACHE;
CREATE SEQUENCE FINDPRO_SEQ NOCACHE;
CREATE SEQUENCE FINDPRO_STATICS_SEQ NOCACHE;
CREATE SEQUENCE LIFE_SEQ NOCACHE;
CREATE SEQUENCE LIFE_STATICS_SEQ NOCACHE;
			