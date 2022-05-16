CREATE TABLE spring_member (
	userid VARCHAR2(50) NOT NULL PRIMARY KEY,
	userpw VARCHAR2(100) NOT NULL,
	username VARCHAR2(100) NOT NULL,
	regdate DATE DEFAULT SYSDATE,
	updatedate DATE DEFAULT SYSDATE,
	enabled CHAR(1) DEFAULT '1'
);

CREATE TABLE spring_member_auth (
	userid VARCHAR2(50) NOT NULL,
	auth VARCHAR2(50) NOT NULL,
	CONSTRAINT fk_member_auth FOREIGN KEY(userid) REFERENCES spring_member(userid)
);

select * from spring_member;