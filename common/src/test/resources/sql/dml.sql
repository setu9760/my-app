-- Insert to users
INSERT INTO user_details VALUES ('USER-1', 'F_NAME', 'l_name', 'aDDress', 0, 'true', 'LOGGED_IN');
INSERT INTO user_details VALUES ('USER-2', 'F_NAME4', 'l_name', 'aDDress', 2, 'true', 'LOGGED_OUT');
INSERT INTO user_details VALUES ('USER-3', 'F_NAME1', 'l_name', 'aDDress', 6, 'false', 'LOGGED_OUT');
INSERT INTO user_details VALUES ('USER-4', 'F_NAME2', 'l_name', 'aDDress', 4, 'false', 'LOGGED_OUT');
INSERT INTO user_details VALUES ('USER-5', 'F_NAME3', 'l_name', 'aDDress', 1, 'true', 'LOGGED_OUT');

--Insert to usrr
INSERT INTO usrr VALUES ('USER-1', 'password1', 'true', 'N/A');
INSERT INTO usrr VALUES ('USER-2', 'password2', 'true', 'N/A');
INSERT INTO usrr VALUES ('USER-3', 'password3', 'false', 'password');
INSERT INTO usrr VALUES ('USER-4', 'password4', 'false', 'password');
INSERT INTO usrr VALUES ('USER-5', 'password5', 'true', 'N/A');

-- Insert to roles
INSERT INTO roles VALUES ('ROLE1', 'ROLE-FULL-1', 'some description');
INSERT INTO roles VALUES ('ROLE2', 'ROLE-FULL-1', 'some description');
INSERT INTO roles VALUES ('ROLE3', 'ROLE-FULL-1', 'some description');
INSERT INTO roles VALUES ('ADMIN', 'ROLE-FULL-1', 'some description');
INSERT INTO roles VALUES ('READ_ONLY', 'ROLE-FULL-1', 'some description');

-- Insert to user_role
INSERT INTO user_role VALUES ('USER-1', 'ROLE1');
INSERT INTO user_role VALUES ('USER-2', 'ROLE1');
INSERT INTO user_role VALUES ('USER-3', 'ROLE2');
INSERT INTO user_role VALUES ('USER-4', 'READ_ONLY');
INSERT INTO user_role VALUES ('USER-5', 'ADMIN');
INSERT INTO user_role VALUES ('USER-1', 'ROLE2');
INSERT INTO user_role VALUES ('USER-2', 'ROLE3');
INSERT INTO user_role VALUES ('USER-3', 'ROLE3');

-- Insert to user_function
INSERT INTO user_function VALUES (-1, 'Invalid password');
INSERT INTO user_function VALUES (1, 'Succesfull login');
INSERT INTO user_function VALUES (2, 'Succesfull logout');
INSERT INTO user_function VALUES (3, 'Password expired');
INSERT INTO user_function VALUES (4, 'Account locked');
INSERT INTO user_function VALUES (5, 'User already logged in');
INSERT INTO user_function VALUES (6, 'User exceeded MAX_FAILED_ATTEMPTS');
INSERT INTO user_function VALUES (7, 'User tried to login from blocked ip address/host.');

-- Insert to student
INSERT INTO student VALUES('studentid1','f_name1','l_name1',21,'address1');
INSERT INTO student VALUES('studentid2','f_name2','l_name2',21,'address2');
INSERT INTO student VALUES('studentid3','f_name3','l_name3',21,'address3');
INSERT INTO student VALUES('studentid4','f_name4','l_name4',21,'address4');
INSERT INTO student VALUES('studentid5','f_name4','l_name5',21,'address5');

-- Insert to cost 
INSERT INTO cost VALUES('BASIC',1000);
INSERT INTO cost VALUES('RESEARCH',1200);
INSERT INTO cost VALUES('PRACTICAL',1500);
INSERT INTO cost VALUES('DISTANCE_LEARNING',800);
--INSERT INTO cost VALUES('TEST_1',100);
--INSERT INTO cost VALUES('TEST_2',300);
--INSERT INTO cost VALUES('TEST_3',1600);
--INSERT INTO cost VALUES('TEST_4',400);
--INSERT INTO cost VALUES('TEST_5',500);

INSERT INTO cost VALUES('UCC1',1100);
INSERT INTO cost VALUES('UCC2',700);
INSERT INTO cost VALUES('UCC3',2500);
INSERT INTO cost VALUES('UCC4',2000);
INSERT INTO cost VALUES('UCC5',100);

-- Insert to subject
INSERT INTO subject VALUES('subjectid1','name1','BASIC','true');
INSERT INTO subject VALUES('subjectid2','name2','RESEARCH','false');
INSERT INTO subject VALUES('subjectid3','name3','PRACTICAL','true');
INSERT INTO subject VALUES('subjectid4','name4','BASIC','true');
INSERT INTO subject VALUES('subjectid5','name5','DISTANCE_LEARNING','false');

-- Insert to tutor
INSERT INTO tutor VALUES('tutorid1','f_name1','l_name1','address1', 'true', 'subjectid1');
INSERT INTO tutor VALUES('tutorid2','f_name2','l_name2','address2', 'true', 'subjectid2');
INSERT INTO tutor VALUES('tutorid3','f_name3','l_name3','address3', 'true', 'subjectid3');
INSERT INTO tutor VALUES('tutorid4','f_name4','l_name4','address4', 'true', 'subjectid4');
INSERT INTO tutor VALUES('tutorid5','f_name4','l_name5','address5', 'true', 'subjectid5');
INSERT INTO tutor VALUES('tutorid6','f_name4','l_name6','address6', 'false', 'subjectid2');
INSERT INTO tutor VALUES('tutorid7','f_name7','l_name7','address7', 'false', 'subjectid4');

-- Insert to payment
INSERT INTO payment (id, amount, type, stud_id) VALUES('payment1', 800, 'CHEQUE', 'studentid1');
INSERT INTO payment (id, amount, type, stud_id) VALUES('payment2', 700, 'CASH', 'studentid2');
INSERT INTO payment (id, amount, type, stud_id) VALUES('payment3', 600, 'CHEQUE', 'studentid3');
INSERT INTO payment (id, amount, type, stud_id) VALUES('payment4', 900, 'CASH', 'studentid1');
INSERT INTO payment (id, amount, type, stud_id) VALUES('payment5', 400, 'CHEQUE', 'studentid5');

-- Insert scholorships
INSERT INTO scholorship (id, type, total_amount, paid_amount, stud_id) VALUES ('schlrid1', 'STATE_PART', 500, 100, 'studentid1');
INSERT INTO scholorship (id, type, total_amount, paid_amount, isFullyPaid, stud_id) VALUES ('schlrid2', 'MGMT_PART', 100, 100, 'true', 'studentid1');
INSERT INTO scholorship (id, type, total_amount, paid_amount, stud_id) VALUES ('schlrid3', 'MGMT_PART', 300, 100, 'studentid2');
INSERT INTO scholorship (id, type, total_amount, paid_amount, stud_id) VALUES ('schlrid4', 'MGMT_PART', 200, 100, 'studentid4');
INSERT INTO scholorship (id, type, total_amount, paid_amount, stud_id) VALUES ('schlrid5', 'MGMT_PART', 200, 100, 'studentid5');

-- Insert into subj_stud_link
INSERT INTO subj_stud_link VALUES ('subjectid1', 'studentid1');
INSERT INTO subj_stud_link VALUES ('subjectid2', 'studentid1');
INSERT INTO subj_stud_link VALUES ('subjectid3', 'studentid1');
INSERT INTO subj_stud_link VALUES ('subjectid4', 'studentid1');
INSERT INTO subj_stud_link VALUES ('subjectid1', 'studentid2');
INSERT INTO subj_stud_link VALUES ('subjectid1', 'studentid3');
INSERT INTO subj_stud_link VALUES ('subjectid1', 'studentid4');
INSERT INTO subj_stud_link VALUES ('subjectid2', 'studentid3');
INSERT INTO subj_stud_link VALUES ('subjectid2', 'studentid4');
INSERT INTO subj_stud_link VALUES ('subjectid3', 'studentid4');
INSERT INTO subj_stud_link VALUES ('subjectid3', 'studentid3');
INSERT INTO subj_stud_link VALUES ('subjectid3', 'studentid2');
INSERT INTO subj_stud_link VALUES ('subjectid4', 'studentid5');
