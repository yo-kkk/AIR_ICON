
/* Drop Tables */

DROP TABLE acom_resrv CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE acom_resrv_no;




/* Create Sequences */

CREATE SEQUENCE acom_resrv_no;



/* Create Tables */

CREATE TABLE acom_resrv
(
	acom_reserv_no number NOT NULL,
	acom_no number NOT NULL,
	user_id varchar2(20) NOT NULL,
	person number(4) NOT NULL,
	etc varchar2(4000),
	reserv_date_start date NOT NULL,
	reserv_date_end date NOT NULL,
	reg_date date DEFAULT sysdate NOT NULL,
	mod_date date,
	PRIMARY KEY (acom_reserv_no)
);



