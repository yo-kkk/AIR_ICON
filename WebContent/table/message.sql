
/* Drop Tables */

DROP TABLE message CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE message_no;




/* Create Sequences */

CREATE SEQUENCE message_no;



/* Create Tables */

CREATE TABLE message
(
	message_no number NOT NULL,
	sender_id varchar2(20) NOT NULL,
	getter_id varchar2(20) NOT NULL,
	content varchar2(4000) NOT NULL,
	reg_date varchar2(20) DEFAULT 'sysdate' NOT NULL,
	PRIMARY KEY (message_no)
);



