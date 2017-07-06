CREATE SCHEMA `user`;
-- Table: users
CREATE TABLE `stype_employee` (
  `id` int(11) NOT NULL,
  `stype` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `semployee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `EMPL_LASTNAME` varchar(45) NOT NULL ,
  `EMPL_FIRSTNAME` varchar(45) NOT NULL ,
  `EMPL_MIDDLENAME` varchar(45) NOT NULL ,
  `empl_type` int(11) NOT NULL ,
  `org_id` int(11) DEFAULT NULL ,
  `empl_email` varchar(45) DEFAULT NULL,
  `empl_telephone` int(11) DEFAULT NULL,
  `empl_work_telephone` int(11) DEFAULT NULL,
  `empl_create_date` date NOT NULL ,
  PRIMARY KEY (`id`),
  KEY `f1emp_type_idx` (`empl_type`),
  CONSTRAINT `f1emp_type` FOREIGN KEY (`empl_type`) REFERENCES `stype_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

CREATE TABLE `sorganization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_name` varchar(45) NOT NULL,
  `emp_director` int(11) NOT NULL,
  `empl_contact` int(11) NOT NULL,
  `org_ur_adress` varchar(45) NOT NULL,
  `org_adress` varchar(45) NOT NULL,
  `org_telephone` int(11) DEFAULT NULL,
  `org_email` varchar(45) DEFAULT NULL,
  `org_create_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `f1org_sempl_idx` (`emp_director`),
  KEY `f2org_sempl_idx` (`empl_contact`),
  CONSTRAINT `f1org_sempl` FOREIGN KEY (`emp_director`) REFERENCES `semployee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `f2org_sempl` FOREIGN KEY (`empl_contact`) REFERENCES `semployee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;






-- Insert data
INSERT INTO `stype_employee` (`id`,`stype`) VALUES (1,'Working');
INSERT INTO `stype_employee` (`id`,`stype`) VALUES (2,'Not working');


INSERT INTO `semployee` (`id`,`EMPL_LASTNAME`,`EMPL_FIRSTNAME`,`EMPL_MIDDLENAME`,`empl_type`,`org_id`,`empl_email`,`empl_telephone`,`empl_work_telephone`,`empl_create_date`) VALUES (1,'Dzukivsky','Denis','Sergeevich',1,1,'den@mail.ru',123,1233,'2017-04-19');
INSERT INTO `semployee` (`id`,`EMPL_LASTNAME`,`EMPL_FIRSTNAME`,`EMPL_MIDDLENAME`,`empl_type`,`org_id`,`empl_email`,`empl_telephone`,`empl_work_telephone`,`empl_create_date`) VALUES (2,'Burlachenko','Nikita','Illich',1,1,'nik@mail.ru',123,1233,'2017-04-18');
INSERT INTO `semployee` (`id`,`EMPL_LASTNAME`,`EMPL_FIRSTNAME`,`EMPL_MIDDLENAME`,`empl_type`,`org_id`,`empl_email`,`empl_telephone`,`empl_work_telephone`,`empl_create_date`) VALUES (3,'Kyznetsova','Anastasya','Sergeevna',1,1,'nastya@mail.ru',123111,12,'2017-04-20');
INSERT INTO `semployee` (`id`,`EMPL_LASTNAME`,`EMPL_FIRSTNAME`,`EMPL_MIDDLENAME`,`empl_type`,`org_id`,`empl_email`,`empl_telephone`,`empl_work_telephone`,`empl_create_date`) VALUES (5,'Blinkova ','Ekaterina','Sergeevna',1,2,'katya@mail.ru',123111,123,'2017-04-21');
INSERT INTO `semployee` (`id`,`EMPL_LASTNAME`,`EMPL_FIRSTNAME`,`EMPL_MIDDLENAME`,`empl_type`,`org_id`,`empl_email`,`empl_telephone`,`empl_work_telephone`,`empl_create_date`) VALUES (6,'Biculov','Vasiliy','Ivanovich',1,4,'vas@mail.ru',123111,12,'2017-04-23');
INSERT INTO `semployee` (`id`,`EMPL_LASTNAME`,`EMPL_FIRSTNAME`,`EMPL_MIDDLENAME`,`empl_type`,`org_id`,`empl_email`,`empl_telephone`,`empl_work_telephone`,`empl_create_date`) VALUES (8,'Kyznetsova','Anastasya','Valerievna',2,NULL,'ana@mail.ru',123111,NULL,'2017-04-24');
INSERT INTO `semployee` (`id`,`EMPL_LASTNAME`,`EMPL_FIRSTNAME`,`EMPL_MIDDLENAME`,`empl_type`,`org_id`,`empl_email`,`empl_telephone`,`empl_work_telephone`,`empl_create_date`) VALUES (9,'Burkina','Elena','Sergeevna',2,NULL,'lena@mail.ru',12345,NULL,'2017-04-24');
INSERT INTO `semployee` (`id`,`EMPL_LASTNAME`,`EMPL_FIRSTNAME`,`EMPL_MIDDLENAME`,`empl_type`,`org_id`,`empl_email`,`empl_telephone`,`empl_work_telephone`,`empl_create_date`) VALUES (10,'Sychovv','Sergey','Ivanovich',1,4,'ser@mail.ru',12345,12,'2017-04-24');


INSERT INTO `sorganization` (`id`,`org_name`,`emp_director`,`empl_contact`,`org_ur_adress`,`org_adress`,`org_telephone`,`org_email`,`org_create_date`) VALUES (1,'TRS',1,1,'ur adress','Litvinova',123,'trs@mail.ru','2017-04-10');
INSERT INTO `sorganization` (`id`,`org_name`,`emp_director`,`empl_contact`,`org_ur_adress`,`org_adress`,`org_telephone`,`org_email`,`org_create_date`) VALUES (2,'Transneft',3,2,'ur adress','Moskov',12345,'neft@mail.ru','2017-04-23');
INSERT INTO `sorganization` (`id`,`org_name`,`emp_director`,`empl_contact`,`org_ur_adress`,`org_adress`,`org_telephone`,`org_email`,`org_create_date`) VALUES (4,'Tetra',2,5,'ur adress','ads',12345,'ra@mail.ru','2017-04-25');
commit;
ALTER TABLE `user`.`semployee`
ADD INDEX `f2empl_org_idx` (`org_id` ASC);
ALTER TABLE `user`.`semployee`
ADD CONSTRAINT `f2empl_org`
FOREIGN KEY (`org_id`)
REFERENCES `user`.`sorganization` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
