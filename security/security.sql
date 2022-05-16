CREATE TABLE sp_user (
	userid VARCHAR2(50),
	email VARCHAR2(100) NOT NULL,
	enabled CHAR(1) DEFAULT '1',
	password VARCHAR2(100) NOT NULL
);

ALTER TABLE sp_user ADD CONSTRAINT sp_user_pk PRIMARY KEY(userid);

CREATE TABLE sp_user_authority (
	userid VARCHAR2(50) NOT NULL,
	authority VARCHAR2(50) NOT NULL
);

ALTER TABLE sp_user_authority ADD CONSTRAINT sp_user_authority_fk FOREIGN KEY(userid) REFERENCES sp_user(userid);

INSERT INTO sp_user(userid, email, password) VALUES('1', 'user@test.com', '1111');
INSERT INTO sp_user_authority(userid, authority) VALUES('1', 'ROLE_USER');
INSERT INTO sp_user_authority(userid, authority) VALUES('1', 'ROLE_ADMIN');

-- 권한 가져오기
SELECT * FROM sp_user_authority WHERE userid = '1';

-- JOIN
SELECT s1.userid, email, password, enabled, authority 
FROM sp_user s1 JOIN sp_user_authority s2 on s1.userid = s2.userid 
WHERE s1.userid = '1';

-- 로그인 기억하기 테이블
CREATE TABLE persistent_logins (
	username VARCHAR(64) NOT NULL,
	series VARCHAR(64) PRIMARY KEY,
	token VARCHAR(64) NOT NULL,
	last_used TIMESTAMP NOT NULL
);

SELECT * FROM persistent_logins;