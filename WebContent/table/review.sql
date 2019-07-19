
/* Drop Tables */

DROP TABLE review CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE review_no;




/* Create Sequences */

CREATE SEQUENCE review_no;



/* Create Tables */

CREATE TABLE review
(
	review_no number NOT NULL,
	acom_no number NOT NULL,
	user_id varchar2(20) NOT NULL,
	content varchar2(500) NOT NULL,
	rate number(2,1) NOT NULL,
	reg_date date DEFAULT sysdate NOT NULL,
	mod_date date,
	PRIMARY KEY (review_no)
);


select * from review;
