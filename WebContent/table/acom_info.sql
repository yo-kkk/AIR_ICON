
/* Drop Tables */

DROP TABLE acom_info CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE acom_info_no;




/* Create Sequences */

CREATE SEQUENCE acom_info_no;



/* Create Tables */

CREATE TABLE acom_info
(
	acom_no number NOT NULL,
	host_id varchar2(20) NOT NULL,
	type varchar2(30) NOT NULL,
	address varchar2(320) NOT NULL,
	price number(10) NOT NULL,
	maxperson number(4) NOT NULL,
	kitchen varchar2(1) NOT NULL,
	parking varchar2(1) NOT NULL,
	toiletries varchar2(1) NOT NULL,
	elevator varchar2(1) NOT NULL,
	tv varchar2(1) NOT NULL,
	aircond varchar2(1) NOT NULL,
	hotwater varchar2(1) NOT NULL,
	washer varchar2(1) NOT NULL,
	wifi varchar2(1) NOT NULL,
	intro_title varchar2(300),
	intro_cont clob,
	intro_etc varchar2(4000),
	intro_pubtrans varchar2(1000),
	reg_date date DEFAULT sysdate NOT NULL,
	mod_date date,
	PRIMARY KEY (acom_no)
);

select * from acom_info;

