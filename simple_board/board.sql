CREATE TABLE spring_board (
	bno NUMBER(10, 0),
	title VARCHAR2(200) NOT NULL,
	content VARCHAR2(2000) NOT NULL,
	writer VARCHAR2(50) NOT NULL,
	regdate DATE DEFAULT SYSDATE,
	updatedate DATE DEFAULT SYSDATE
);

ALTER TABLE spring_board ADD CONSTRAINT pk_spring_board PRIMARY KEY(bno);

CREATE SEQUENCE seq_board;

-- 더미 데이터 만들기
INSERT INTO spring_board(bno, title, content, writer)(SELECT seq_board.nextval, title, content, writer FROM spring_board);
select count(*) from spring_board;

-- oracle 페이지 나누기 => rownum 사용
-- rownum은 ORDER BY와 함께 사용 시 주의 => ORDER BY 절에서 사용하는 컬럼이 index가 아닐 때
--	=> 임의로 행을 가지고 나온 후 번호를 붙임 (inline query)
SELECT rownum, bno, title FROM spring_board;
SELECT rownum, ... FROM (SELECT * FROM board WHERE bno > 0 ORDER BY re_ref DESC);

-- 1) rownum 사용 방식
SELECT rownum, bno, title, writer FROM(SELECT bno, title, writer FROM spring_board ORDER BY bno DESC) WHERE rownum <= 10;

-- 2) ORDER BY 컬럼이 index라면, oracle hint 사용 가능
SELECT /*+INDEX_DESC(spring_board pk_spring_board*/ rownum, bno, title, writer FROM spring_board WHERE rownum <= 10;

-- 1페이지 최신글 가져오기
SELECT bno, title, writer, regdate, updatedate
FROM (SELECT /*+INDEX_DESC(spring_board pk_spring_board*/ rownum r, bno, title, writer, regdate, updatedate
	FROM spring_board
	WHERE rownum <= 10)
WHERE r > 0;

-- 2페이지 최신글 가져오기
SELECT bno, title, writer, regdate, updatedate FROM (SELECT /*+INDEX_DESC(spring_board pk_spring_board*/ rownum r, bno, title, writer, regdate, updatedate FROM spring_board WHERE rownum <= 20) WHERE r > 10;

-- 페이지 나누기 + 검색
SELECT bno, title, writer, regdate, updatedate
FROM (SELECT /*+INDEX_DESC(spring_board pk_spring_board*/ rownum r, bno, title, writer, regdate, updatedate
	FROM spring_board
	WHERE bno > 0 AND (title LIKE '%modal%' OR content LIKE '%modal%') rownum <= (1 * 30))
WHERE r > (1-1) * 30;
