-- Insert to student
INSERT INTO student VALUES('studentid1','f_name1','l_name1',21,'address1');
INSERT INTO student VALUES('studentid2','f_name2','l_name2',21,'address2');
INSERT INTO student VALUES('studentid3','f_name3','l_name3',21,'address3');
INSERT INTO student VALUES('studentid4','f_name4','l_name4',21,'address4');
INSERT INTO student VALUES('studentid5','f_name5','l_name5',21,'address5');

-- Insert to cost 
INSERT INTO cost VALUES('cost_code1',1000);
INSERT INTO cost VALUES('cost_code2',1200);
INSERT INTO cost VALUES('cost_code3',1500);

-- Insert to subject
INSERT INTO subject VALUES('subject1','name1','cost_code1','false');
INSERT INTO subject VALUES('subject2','name2','cost_code2','true');
INSERT INTO subject VALUES('subject3','name3','cost_code3','false');
INSERT INTO subject VALUES('subject4','name4','cost_code1','true');
INSERT INTO subject VALUES('subject5','name5','cost_code2','false');

-- Insert to tutor
INSERT INTO tutor VALUES('tutorid1','f_name1','l_name1','address1', 'true', 'subject1');
INSERT INTO tutor VALUES('tutorid2','f_name2','l_name2','address2', 'true', 'subject2');
INSERT INTO tutor VALUES('tutorid3','f_name3','l_name3','address3', 'true', 'subject3');
INSERT INTO tutor VALUES('tutorid4','f_name4','l_name4','address4', 'true', 'subject4');
INSERT INTO tutor VALUES('tutorid5','f_name5','l_name5','address5', 'true', 'subject5');
INSERT INTO tutor VALUES('tutorid6','f_name6','l_name6','address6', 'false', 'subject2');
INSERT INTO tutor VALUES('tutorid7','f_name7','l_name7','address7', 'false', 'subject4');

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

-- Insert into subj_stud_link
INSERT INTO subj_stud_link VALUES ('subject1', 'studentid1');
INSERT INTO subj_stud_link VALUES ('subject2', 'studentid1');
INSERT INTO subj_stud_link VALUES ('subject3', 'studentid1');
INSERT INTO subj_stud_link VALUES ('subject4', 'studentid1');
INSERT INTO subj_stud_link VALUES ('subject1', 'studentid2');
INSERT INTO subj_stud_link VALUES ('subject1', 'studentid3');
INSERT INTO subj_stud_link VALUES ('subject1', 'studentid4');
INSERT INTO subj_stud_link VALUES ('subject2', 'studentid3');
INSERT INTO subj_stud_link VALUES ('subject2', 'studentid4');
INSERT INTO subj_stud_link VALUES ('subject3', 'studentid4');
INSERT INTO subj_stud_link VALUES ('subject3', 'studentid3');
INSERT INTO subj_stud_link VALUES ('subject3', 'studentid2');
INSERT INTO subj_stud_link VALUES ('subject4', 'studentid4');
