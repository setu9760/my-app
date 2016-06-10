
CREATE TABLE `student` (
  `id` varchar(36) NOT NULL,
  `f_name` varchar(45) NOT NULL,
  `l_name` varchar(45) NOT NULL,
  `age` int(11) NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `scholorship` (
  `id` varchar(36) NOT NULL,
  `external_ref` varchar(45) NOT NULL DEFAULT 'N/A',
  `type` varchar(45) NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `paid_amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `isFullyPaid` varchar(6) NOT NULL DEFAULT 'false',
  `isPostPay` varchar(6) NOT NULL DEFAULT 'false',
  `additional_comments` varchar(100) DEFAULT NULL,
  `stud_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `student_ids_fk` FOREIGN KEY (`stud_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cost` (
  `cost_code` varchar(36) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`cost_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `subject` (
  `id` varchar(36) NOT NULL,
  `name` varchar(45) NOT NULL,
  `cost_code` varchar(36) NOT NULL,
  `isMandatory` varchar(6) NOT NULL DEFAULT 'false',
  PRIMARY KEY (`id`),
  CONSTRAINT `cost_code_fk` FOREIGN KEY (`cost_code`) REFERENCES `cost` (`cost_code`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tutor` (
  `id` varchar(36) NOT NULL,
  `f_name` varchar(45) NOT NULL,
  `l_name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `isFulltime` varchar(6) NOT NULL DEFAULT 'true',
  `subj_id` varchar(36) NOT NULL DEFAULT 'N/A',
  PRIMARY KEY (`id`),
  CONSTRAINT `subj_key_fk` FOREIGN KEY (`subj_id`) REFERENCES `subject` (`id`) ON DELETE SET DEFAULT ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `payment` (
  `id` varchar(36) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `type` varchar(45) NOT NULL,
  `stud_id` varchar(36) NOT NULL,
  `datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `student_id_fk` FOREIGN KEY (`stud_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `subj_stud_link` (
  `subj_id` varchar(36) NOT NULL,
  `stud_id` varchar(36) NOT NULL,
  PRIMARY KEY (`subj_id`,`stud_id`),
  CONSTRAINT `stud_id_fk` FOREIGN KEY (`stud_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `subj_id_fk` FOREIGN KEY (`subj_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;