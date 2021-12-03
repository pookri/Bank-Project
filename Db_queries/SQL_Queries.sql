--ACCOUNT Queries
--CREATE DATE QUERY
--Insert into account query(to insert data into account)
INSERT into account (account_number,branch_id,acc_type,balance,interest_rate,create_date) VALUES();
--GET_ALL_THE_ACCOUNTS_ORDER_BY_CREATION_DATE
SELECT account_number,branch_id,acc_type,balance,interest_rate,create_date FROM account ORDER BY create_date;
--GET_ALL_THE_ACCOUNTS_ORDER_BY_BALANCE
SELECT account_number,branch_id,acc_type,balance,interest_rate,create_date FROM account ORDER BY balance;
--GET_PARTICULAR_ACCOUNT_INFO
SELECT account_number,branch_id,acc_type,balance,interest_rate,create_date FROM account WHERE account_number=;
--GET_PARTICULAR_BRANCH_ALL_ACCOUNT_INFO
SELECT account_number,branch_id,acc_type,balance,interest_rate,create_date FROM account WHERE branch_id='BARBO';
--COUNT_ACCOUNTS_FOR_ALL_BRANCH
SELECT branch_id,COUNT(account_number)AS Number_of_accounts FROM account GROUP BY branch_id;
--COUNT_No.ofAcc_BASED_ON_CREATIONDATE
--How many accounts are created on particular date
SELECT create_date,COUNT(account_number) FROM account WHERE create_date=;

--DELETE_ACCOUNT
DELETE FROM account WHERE account_number=;
--UPDATE_ACCOUNT
UPDATE account SET account_number=,branch_id=,acc_type=,balance=,interest_rate=,create_date= WHERE account_number=;

--ACCOUNT TYPE queries

--INSERT_ACC_TYPE
INSERT INTO account_type(account_type,accounttype_name) VALUES();
--GET_ALL_ACC_TYPE
SELECT account_type,accounttype_name FROM account_type;
--UPDATE_ACC_TYPE
UPDATE account_type SET account_type=,accounttype_name=;
--DELETE_ACC_TYPE
DELETE FROM account_type WHERE account_type=;
--COUNT_CUSTOMERS_IN_ALL_ACC_TYPE
SELECT ac.acc_type, COUNT( c.cssn) AS Num_of_customers FROM account ac,customer c,customer_account ca, WHERE ca.account_number= ac.account_number, ca.ssn=c.cssn GROUP BY ac.acc_type;


--BRANCH Queries

--INSERT_BRANCH
INSERT INTO branch (branch_id,branch_name,manager_ssn,assistant_ssn,assets,address,city,state,zip_code) VALUES();
--GET_ALL_BRANCH
SELECT branch_id,branch_name,manager_ssn,assistant_ssn,assets,address,city,state,zip_code FROM branch;
--GET_MANAGER_OF_PARTICULAR_BRANCH
SELECT br.branch_id,br.branch_name,br.manager_ssn,e.e_firstname,e.e_lastname FROM branch br, employee e WHERE br.manager_ssn=e.employee_ssn AND br.branch_id='BARBO';
--GET_ASSISTANT_OF_PARTICULAR_BRANCH
SELECT br.branch_id,br.branch_name,br.assistant_ssn, e.e_firstname,e.e_lastname FROM branch br, employee e WHERE br.assistant_ssn=e.employee_ssn AND br.branch_id='BARBO';
--GET_ASSETS_OF_PARTICULAR_BRANCH
SELECT branch_id,branch_name,assets FROM branch where branch_id='BARBO';
--GET_ASSETS_OF_ALL_BRANCH
SELECT branch_id,branch_name,assets FROM branch ORDER BY assets;
--COUNT_NUM_OF_CUSTOMERS_IN_PARTICULAR_BRANCH
SELECT ac.branch_id,COUNT( c.cssn) AS Num_of_customers FROM customer c, customer_account ca, account ac where ac.account_number=ca.account_number AND c.cssn=ca.cssn AND ac.branch_id='BARBO' GROUP BY ac.branch_id;
--GET_NUM_OF_CUSTOMERS_IN_ALL_BRANCH  INCOMPLETE
SELECT br.branch_id,br.branch_name,COUNT(c.cssn) AS Num_of_customers FROM branch br, customer c, customer_account ca, account ac where br.branch_id = ac.branch_id AND ac.account_number=ca.account_number AND c.cssn=ca.cssn;

--CUSTOMER Queries

--INSERT_CUSTOMER
INSERT (cssn,c_firstname,c_lastname,apartment_number,street_number,city,state,zip_code) INTO customer VALUES();
--GET_ACC_INFO_OF__CUSTOMER
SELECT c.cssn,c.c_firstname,c.c_lastname,ac.account_number,ac.branch_id,ac.acc_type,ac.balance,ac.interest_rate,ac.create_date,ca.recent_access_date FROM account ac,customer c, customer_account ca WHERE ac.account_number=ca.account_number AND ca.cssn=c.cssn;
--GET_CUSTOMER_HAVINGACC_INALL_BRANCH  INCOMPLETE
SELECT c.c_firstname,c.c_lastname
FROM customer c
INNER JOIN customer_account ca ON c.cssn = ca.cssn
INNER JOIN account a ON a.account_number = ca.account_number
INNER JOIN branch b ON a.branch_id = b.branch_id
GROUP BY b.branch_id
HAVING COUNT(DISTINCT b.branch_id) = (
    SELECT COUNT(branch_id)
    FROM branch ); 



--Employee

--INSERT
INSERT INTO employee (employee_ssn,branch_id,e_firstname,e_lastname,mobile_number,start_date) VALUES();

--TRANSACTION Queries

--INSERT_TRANSACTION
INSERT INTO transaction ( transaction_account_number,transaction_type,transaction_amount,transaction_time,transaction_date,transaction_id,transaction_balance ) VALUES();

--TRANSACTION_TYPE Queries

--INSERT_TRANSACTION_TYPE
INSERT INTO transaction_type(transaction_type,transactiontype_name,transaction_charge) VALUES();
--UPDATE_TRANSACTION_TYPE
UPDATE transaction_type SET transaction_type,transactiontype_name,transaction_charge=?;
--DELETE_TRANSACTION_TYPE
DELETE FROM transaction_type WHERE transaction_type=?;


--ASSIST Queries

--INSERT_ASSIST
INSERT INTO assist (cssn,essn) VALUES();

--EMPLOYEE_DEPENDENT Queries

--INSERT_EMPLOYEE_DEPENDENT
INSERT INTO employee_dependent(dependent_name,employee_ssn)  VALUES();

--CUSTOMER_ACCOUNT Queries

--INSERT_CA
INSERT INTO customer_account (account_number,cssn,recent_access_date) VALUES();

--LOAN Queries

--INSERT_LOAN
INSERT INTO loan (loan_number,account_number,loan_amount,monthly_pay) VALUES();

--OVERDRAFT Queries

--INSERT_OVERDRAFT
INSERT INTO overdraft (overdraft_number,checking_account_number,overdraft_date,overdraft_fee) VALUES();

--TRANSACTION Queries

--INSERT
INSERT INTO transaction(transaction_account_number,transaction_type,transaction_amount,transaction_time,transaction_date,transaction_id,transaction_balance) VALUES();

--UPDATE
UPDATE transaction SET transaction_account_number=?,transaction_type=?,transaction_amount=?,transaction_time=?,transaction_date=?,transaction_id=?,transaction_balance=?;
--DELETE
DELETE FROM transaction WHERE transaction_id=?;

--GET_ALL_TRANSACTION
SELECT transaction_account_number,transaction_type,transaction_amount,transaction_time,transaction_date,transaction_id,transaction_balance FROM transaction WHERE transaction_account_number=?

