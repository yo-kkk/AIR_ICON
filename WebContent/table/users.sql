
/* Drop Tables */

DROP TABLE users CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE users
(
	user_id varchar2(20) NOT NULL,
	password varchar2(128) NOT NULL,
	name varchar2(50) NOT NULL,
	tel varchar2(30) NOT NULL,
	birth date NOT NULL,
	gender varchar2(1) NOT NULL,
	address varchar2(320) NOT NULL,
	email varchar2(100) NOT NULL,
	host_yn varchar2(1) DEFAULT 'n' NOT NULL,
	card_num varchar2(16),
	card_date varchar2(4),
	card_cvc varchar2(3),
	card_password varchar2(4),
	reg_date date DEFAULT sysdate NOT NULL,
	mod_date date,
	PRIMARY KEY (user_id)
);

select * from users;

