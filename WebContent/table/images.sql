
/* Drop Tables */

DROP TABLE images CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE images
(
	acom_no number NOT NULL,
	no number NOT NULL,
	origin_file varchar2(4000) NOT NULL,
	save_file varchar2(30) NOT NULL,
	PRIMARY KEY (acom_no, no)
);


insert into images(acom_no, no, origin_file, save_file)
 				values(18,1,'18_1','18_1.webp');
insert into images(acom_no, no, origin_file, save_file)
 				values(15,1,'15_1','15_1.webp');
insert into images(acom_no, no, origin_file, save_file)
 				values(16,1,'16_1','16_1.webp');
insert into images(acom_no, no, origin_file, save_file)
 				values(22,1,'22_1','22_1.webp');
insert into images(acom_no, no, origin_file, save_file)
 				values(23,1,'23_1','23_1.webp');
 				
 				insert into images(acom_no, no, origin_file, save_file)
 				values(2,1,'2_1','2_1.webp');
 				insert into images(acom_no, no, origin_file, save_file)
 				values(3,1,'3_1','3_1.webp');
 				insert into images(acom_no, no, origin_file, save_file)
 				values(8,1,'8_1','8_1.webp');
 				insert into images(acom_no, no, origin_file, save_file)
 				values(17,1,'17_1','17_1.webp');
 				insert into images(acom_no, no, origin_file, save_file)
 				values(20,1,'20_1','20_1.webp');
 				insert into images(acom_no, no, origin_file, save_file)
 				values(10,1,'10_1','10_1.webp');
insert into images(acom_no, no, origin_file, save_file)
 				values(11,1,'11_1','11_1.webp');
 				insert into images(acom_no, no, origin_file, save_file)
 				values(21,1,'21_1','21_1.webp');
insert into images(acom_no, no, origin_file, save_file)
				values(41,1,'KakaoTalk_20190408_100603218.jpg','41_1.jpg');
insert into images(acom_no, no, origin_file, save_file)
				values(45,1,'KakaoTalk_20190326_152949066.jpg','45_1.jpg');
insert into images(acom_no, no, origin_file, save_file)
				values(46,1,'KakaoTalk_20190422_162517776.jpg','46_1.jpg');
 				select * from images;
delete from images where acom_no = 10;

select * from images;

select acom_no, no, origin_file, save_file from images where acom_no=2 