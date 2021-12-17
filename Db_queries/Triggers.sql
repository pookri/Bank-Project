--1)  Overdraft trigger
CREATE OR REPLACE TRIGGER OVERDRAFT 
BEFORE INSERT ON TRANSACTION 
REFERENCING OLD AS OLD NEW AS NEW 
FOR EACH ROW
DECLARE
  acc_balance FLOAT;
  fee float;
BEGIN
    IF :NEW.transaction_type = 'WD' THEN
 acc_balance := 'select balance from account where account_number =       :NEW.transaction_account_number ;';
        IF acc_balance < :NEW.transaction_amount THEN
            fee:='select transaction_charge from transaction_type where transaction_type = "OD";';
insert into overdraft (checking_account_number,overdraft_date,overdraft_fee,overdraft_number)
            	values(:NEW.transaction_account_number,SYSDATE,fee,OD_SC_NUM.nextval);
            RAISE_APPLICATION_ERROR(-20001,'Account does not have sufficient money');
        END IF;
    END IF;
END;







--2) Balance update
create or replace TRIGGER UPDATE_BALANCE
AFTER INSERT ON TRANSACTION 
REFERENCING OLD AS OLD NEW AS NEW 
FOR EACH ROW
DECLARE
  acc_balance FLOAT;
BEGIN
    IF :NEW.transaction_type IN('WD','OD','SC') THEN 
        UPDATE account 
        SET balance = balance - :NEW.transaction_amount
        where account_number = :NEW.transaction_account_number
acc_balance := 'select balance from account where account_number =                         :NEW.transaction_account_number ;';
        UPDATE transaction
        SET transaction_balance =  acc_balance
        where transaction_id = :NEW.transaction_id;
    ELSIF :NEW.transaction_type = 'DS' THEN
        UPDATE account 
        SET balance = balance + :NEW.transaction_amount
        where account_number = :NEW.transaction_account_number;
        acc_balance := 'select balance from account where account_number = :NEW.transaction_account_number;';
        UPDATE transaction
        SET transaction_balance = acc_balance
        where transaction_id = :NEW.transaction_id;
    END IF;
END;

--3) Branch Charge Account
create or replace TRIGGER CHARGE_ACC_TRG 
AFTER INSERT ON BRANCH 
REFERENCING OLD AS OLD NEW AS NEW 
FOR EACH ROW
BEGIN
  insert into account
  (account_number,
  branch_id,acc_type,balance,interest_rate,create_date)
  values
  (seq_no.nextval,
  :NEW.branch_id,
  'BCHRG',
  0,
  0.0,
  sysdate);
END;











4) Initial Deposit
create or replace TRIGGER INTIAL_DEPOSIT_TRG 
AFTER INSERT ON ACCOUNT 
REFERENCING OLD AS OLD NEW AS NEW 
FOR EACH ROW
BEGIN
    IF :NEW.acc_type NOT IN ('BCHRG') THEN
    insert into transaction
  (transaction_account_number,transaction_type,transaction_amount, transaction_time, transaction_date, transaction_id)
  values
  (
  :NEW.account_number,
  'DS',
  :NEW.balance,
  CURRENT_TIMESTAMP,
  SYSDATE,
  TRN_SC_NUM.nextval
  );
  END IF;
END;
