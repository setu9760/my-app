CREATE TABLE `users` (
  `user_id` varchar(36) NOT NULL,
  `f_name` varchar(45) NOT NULL,
  `l_name` varchar(45) NOT NULL,
  `age` int(11) NOT NULL,
  `address` varchar(100) NOT NULL,
  `failed_attempts` int(11) NOT NULL DEFAULT '0',
  `account_locked` varchar(6) NOT NULL DEFAULT 'false',
  `sign_on_status` varchar(36) NOT NULL DEFAULT 'LOGGED_OUT',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `usrr` ( 
 `user_id` varchar(36) NOT NULL,
 `password` varchar(100) NOT NULL,
 `password_expired` varchar(6) NOT NULL DEFAULT 'false',
 `previous_password` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_id_fkk` FOREIGN KEY(`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `roles` (
 `role` varchar(36) NOT NULL,
 `role_full` varchar(36) NOT NULL,
 `description` varchar(255) NOT NULL,
 PRIMARY KEY(`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_role`( 
 `user_id` varchar(36) NOT NULL,
 `role` varchar (36) NOT NULL,
 PRIMARY KEY (`user_id`,`role`),
 CONSTRAINT `user_id_fk` FOREIGN KEY(`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
 CONSTRAINT `role_fk` FOREIGN KEY (`role`) REFERENCES `roles` (`role`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_function` (
 `function_code` int(5) NOT NULL auto_increment,
 `description` varchar(255) NOT NULL,
 PRIMARY KEY (`function_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_log` (
 `user_id` varchar(36) NOT NULL,
 `datetime` datetime DEFAULT CURRENT_TIMESTAMP,
 `user_function` int(5) NOT NULL,
 CONSTRAINT `user_function_fk` FOREIGN KEY(`user_function`) REFERENCES `user_function` (`function_code`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `additional_comments` varchar(400) DEFAULT NULL,
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
  `additional_comments` varchar(100) DEFAULT NULL,
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