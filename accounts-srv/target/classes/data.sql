INSERT INTO T_CUSTOMER (NAME, SURNAME, CREATE_DATE, UPDATE_DATE) VALUES ('DANIELE', 'TEST', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO T_CUSTOMER (NAME, SURNAME, CREATE_DATE, UPDATE_DATE) VALUES ('ANTONIO', 'FULL', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO T_ACCOUNT (CUSTOMERID, CREATE_DATE, UPDATE_DATE) VALUES (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO T_ACCOUNT (CUSTOMERID, CREATE_DATE, UPDATE_DATE) VALUES (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());