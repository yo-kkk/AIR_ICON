
/* Drop Tables */

DROP TABLE bookmark CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE bookmark_no;




/* Create Sequences */

CREATE SEQUENCE bookmark_no;



/* Create Tables */

CREATE TABLE bookmark
(
	bookmark_no number NOT NULL,
	acom_no number NOT NULL,
	user_id varchar2(20) NOT NULL,
	PRIMARY KEY (bookmark_no)
);



